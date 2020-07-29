package rvm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static rvm.Constants.*;
import sun.org.mozilla.javascript.internal.regexp.SubString;
import Tests.MemoryTest.*;

/**
 *
 * @author ernestas
 */
public class RM {

    public static void print(Memory mem) {
        print(mem, true);
    }

    public static void print(Memory mem, boolean iSinteger) {
        for (int i = 0x00; i < 0x20; i++) {
            System.out.print(Integer.toHexString(i).toUpperCase() + "_: ");
            for (int j = 0x0; j <= 0xF; j++) {
                System.out.print("'" + ((iSinteger) ? mem.readWord(i * 0x10 + j).toInt() + " " : mem.readWord(i * 0x10 + j)) + "' ");
            }
            System.out.println();
        }
    }
    CPU cpu; // Centrinis procesorius
    Memory mem; // Reali atmintis
    ArrayList<VirtualMachine> VMList; // Virtualiu masinu sarasas
    Chan ch;

    /**
     * Sukuriama reali masina, inicijuojant procesoriu, atminti, ir VM sarasas
     */
    public RM() {
        cpu = new CPU();
        VMList = new ArrayList();
        mem = new Memory(Constants.MEMORY_SIZE);
        ch = new Chan(cpu);
        //mem.fillZeroes();
    }

    /**
     * Virtuali mašina be parametrų
     *
     * @param file programos failas (musu FSe)
     * @return virtualią mašiną
     */
    public VirtualMachine startNewVM(String fileName) throws FileNotFoundException {
        return startNewVM(fileName, "");
    }
    
    /**
     * Virtualios masinos paleidimas
     *
     * @param komandos komandu sarasas
     * @param args parametrai paleidziami programai
     * @return sugeneruota VM
     */
    public VirtualMachine startNewVM(String fileName, String args) throws FileNotFoundException {
        /*
         * Byte[] segs = new Byte[3];//DS, CS, SS if (!args.isEmpty() &&
         * !args.trim().isEmpty()) { segs[0] = (Byte) (byte) (args.length() /
         * BLOCK_SIZE); // DS } else { segs[0] = 0x0; }
         */
        // 1budas
        // du ciklai vienu nustatom ar korektiska
        // kitu  jau zinom kur bus renkam duomenis
        // 2budas
        // viename cikle atpazystam ir renkam
        // cia 1 budas
        Scanner scanner;
        scanner = new Scanner(new FileInputStream(fileName));
        String line;
        boolean dataSegPresents = false;
        boolean codeSegPresents = false;
        boolean haltPresents = false;
        boolean illegalStructure = false;
        int counter = 0;
        int dataSegStart = -1;
        int codeSegStart = -1;

        // checkas segmentu korektiskumui
        while (scanner.hasNext()) {
            line = scanner.nextLine();
            // segmentu isdestimo checkas
            if ((line.equals(".DATA") && dataSegPresents) || (line.equals(".CODE") && codeSegPresents)
                    || (line.equals("HALT") && !codeSegPresents)) {
                illegalStructure = true;
            }

            if (line.equals(".DATA")) {
                dataSegPresents = true;
                dataSegStart = counter + 1; // po .DATA duomenys
                counter++;
                continue;
            }
            if (dataSegPresents && line.equals(".CODE")) { // jau yra data ir radom code
                codeSegPresents = true;
                codeSegStart = counter + 1; // po .CODE komandos
                counter++;
                continue;
            }
            if (codeSegPresents && line.equals("HALT")) { // jau yra code (tai ir data) ir radom halt
                haltPresents = true;
            }
            counter++;
        }

        //System.out.println("DATASEG: " + dataSegPresents + dataSegStart);
        //System.out.println("CODESEG: " + codeSegPresents + codeSegStart);
        //System.out.println("HALT: " + haltPresents);

        // jau surinkimas atminties
        if (dataSegPresents && codeSegPresents && haltPresents && !illegalStructure) {
            //System.out.println("Perkeliama i atminti");
            scanner = new Scanner(new FileInputStream(fileName));
            // apskaiciavimas reikiamu bloku
            int requiredBlocks = (counter % BLOCK_SIZE > 0) ? (counter / BLOCK_SIZE + 1) : (counter / BLOCK_SIZE);
            requiredBlocks += STACK_SIZE;
            VirtualMemory vm = alloc(requiredBlocks); // dydis toks, koks failas minus .DATA ir minus .CODE zodzius
            alloc(5);
            alloc(6);
            VirtualMemory laikina = alloc(7);
            alloc(8);
            free(laikina);
            alloc(0xf);
            //print(mem);
            counter = 0;
            int memCursorPosition = 0;
            boolean haltReached = false;

            short DS = 0;
            short CS = (short) (codeSegStart - dataSegStart - 1);
            short SS;

            while (scanner.hasNext() && !haltReached) {
                line = scanner.nextLine();
                //System.out.println("parsinamas: " + line);

                // duomenu irasymas
                if (counter >= dataSegStart && counter < codeSegStart - 1) { // counter yra tarp .data ... .code
                    if (line.startsWith("STR ")) {
                    //    System.out.println(" str [" + memCursorPosition + "] := " + line.substring(4));
                        vm.writeWord(memCursorPosition, new Word(line.substring(4)));
                        memCursorPosition++;
                    } else {
                    //    System.out.println(" int [" + memCursorPosition + "] := " + Integer.parseInt(line));
                        vm.writeWord(memCursorPosition, new Word(Integer.parseInt(line)));
                        memCursorPosition++;
                    }
                }

                // kodo irasymas
                if (counter >= codeSegStart) {
                    //System.out.println("[" + memCursorPosition + "] := " + line);
                    vm.writeWord(memCursorPosition, new Word(line));
                    memCursorPosition++;
                }

                if (line.equals("HALT")) {
                    haltReached = true;
                }
                counter++;
                //print(mem,false);
            }
            SS = (short) memCursorPosition;

            
            //System.out.println("left main loop, vmsize: " + vm.getSize() + " memcursor: " + memCursorPosition);
            for (int i = 0; i < vm.getSize(); i++) {
            //    System.out.println("[" + i + "]" + vm.readWord(i));
            }

            short[] segs = {DS, CS, SS};
            VMList.add(new VirtualMachine(this, segs, vm));
            return VMList.get(0);
        } else {
            return null;
        }
    }

    public Word[] getAvailableBlocks(int blocks) {
        Word[] track = new Word[blocks * 0xf];
        return track;
    }

    //todo 
    /**
     * Neuzbaigtas
     *
     * @param Track
     * @return
     */
    public boolean isAvailable(int Track) {
        for (int i = 0x010; i <= 0x0F0; i++) {
            //for ()
        }
        return true;
    }

    /**
     * Pertraukimo ivykio patikrinimas
     *
     * @return true - ivyko | false - neivyko
     */
    public boolean interruptTest() {
        return cpu.interruptTest();
    }

    /**
     * Iskiriama atmintis atmintis virtualiai masinai
     *
     * @return virtuali atmintis
     */
    public VirtualMemory alloc(int blocksRequired) {

        // pirmiausia patikrinam ar yra laisvas blokas page teble entriui, 
        // teigiam kad pilnas, ir jei bent viena randam 0 ilgio reiskias nepilnas
        //boolean  = true;
        Word newPTR = null;
        int memSize = blocksRequired;
        int memUsed = 0;
        for (int i = 1; i < BLOCK_SIZE; i++) { // pt ilgiai yra block size dydzio
            Word currentWord = mem.readWord(i);
            if (currentWord == null || currentWord.toInt() == 0) {
                if (newPTR == null) {
                    newPTR = new Word(i * BLOCK_SIZE);
                }
            } else {
                memUsed += currentWord.toInt();
            }

        }

        // jei nera ptro, reiskias lentele pilna
        //

        //System.out.println("memused" + memUsed);
        //System.out.println("Memory left:" + (MEMORY_SIZE - PT_SIZE - memUsed));
        if (newPTR == null && (MEMORY_SIZE - PT_SIZE - memUsed < blocksRequired)) {
            return null;
        }

        int ptCursor = newPTR.toInt();
        int blocksAlloced = 0;
        // begam ir tikrinam kiekviena tracka
        for (int blockAddress = PT_SIZE; blockAddress < MEMORY_SIZE/BLOCK_SIZE; blockAddress++) {
            // begam vel per ilgius ir imam nenulinius
            //System.out.println("current blockAddress: " + blockAddress);
            boolean notUsed = true;
            for (int sizeTableIndex = 1; sizeTableIndex < BLOCK_SIZE; sizeTableIndex++) {
                //System.out.println("current SizeTableIndex: " + sizeTableIndex);
                // ptro wordas
                //TODO refactorint
                int ptrStart = (sizeTableIndex) * BLOCK_SIZE;
                int ptrEnd = ptrStart + mem.readWord(sizeTableIndex).toInt();
                //System.out.println("strt: " + ptrStart + " end: " + ptrEnd + " index" + mem.readWord(sizeTableIndex).toInt());
                for (int word = ptrStart; word < ptrEnd; word++) {
                    //System.out.println("foriukas");
                    if (blockAddress == mem.readWord(word).toInt()) {
                        notUsed = false;
                        break;
                    }
                }
                if (!notUsed) {
                    break;
                }
            }
            if (blocksAlloced >= blocksRequired) {
                break;
            }

            if (notUsed) {
                mem.writeWord(ptCursor, new Word(blockAddress));
                ptCursor++;
                blocksAlloced++;
            }
        }

        if (blocksAlloced >= blocksRequired) {
            mem.writeWord(newPTR.toInt() / BLOCK_SIZE, new Word(blocksRequired));
        }

        VirtualMemory VMmemory = new VirtualMemory(newPTR, mem);
        VMmemory.setSize(memSize * BLOCK_SIZE);
        return VMmemory;
    }

    private void free(VirtualMemory vm) {
        mem.writeWord((vm.getPTR().toInt())/BLOCK_SIZE, new Word(0));
    }
    
    public Word[] getMemoryGui() {
        return mem.getWords();
    }
    
    public Chan getChans(){
        return ch;
    };
}
