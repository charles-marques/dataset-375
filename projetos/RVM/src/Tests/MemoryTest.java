package Tests;

import rvm.*;
import static rvm.Constants.*;
/**
 *
 * @author ernestas
 */
public class MemoryTest {
    // rankom 3ia (random) tracka uzpildau random adresais, ir paduodu i ptr adresa table entrio su tais duomenim
    public static void main(String[] args) {
        Memory memory = new Memory(0xFFF);
        memory.fillZeroes();
        // address klase? [F][track][word]  nereiks hexais pistis
        String[] s1 = {"LABA", "VYTA", "PARA", "SULA", "MOLI", "OLIA", "PAPA", "UTUT", "AHAH", "OHOH"};      
        for (int i = 0x010; i <= 0x019; i++) {
            memory.writeWord(i, new Word(i*0x10));
            for (int j = 0x000; j <= 0x00F; j++) {
                memory.writeWord(i*0x10+j, new Word(s1[i % 0x10]));
            }   
        }

        
        String[] s2 = {"1111", "2222", "3333", "4444", "5555", "6666"};      
        for (int i = 0x02A; i <= 0x02F; i++) {
            int absAddress = (i*0x10);
            memory.writeWord(i - 0x10, new Word(absAddress));
            for (int j = 0x0; j <= 0x00F; j++) {
                memory.writeWord(absAddress+j, new Word(s2[i % 0x10 - 0xA]));
            }   
        }     
        
        print(memory);

        // VIRTUAL MEMORY
        
        VirtualMemory vm = new VirtualMemory(new Word(0x10), memory);
        System.out.println("=======VIRTUAL MEMORY");
        /*for (int i=0x0; i <= 0xF; i++) {
            System.out.print(Integer.toHexString(i).toUpperCase() + "_:");
            for (int j=0x0; j <= 0xF; j++) {
                System.out.print(vm.readWord(i, j) + " ");
            }
            System.out.println();
        }*/
        for (int i=0x0; i<= 0x1A; i++) {
            vm.writeWord(i, new Word(i+34));
        }
        
        for (int i=0x0; i<= 0xFF; i++) {
            System.out.println(vm.readWord(i) + " ");
        }
        
        print(memory);
        
        //Word a = new Word("0010");
        //System.out.println(a.toCharString() + " " + a.toInt() + " " + a.getInt2() + " " + a.toHex());
         
        
    }
    
    public static void print(Memory mem) {
        /*for (int i=0x00; i < 0xFF; i++) {
            System.out.print(Integer.toHexString(i).toUpperCase() + "_: ");
            for (int j=0x0; j <= 0xF; j++) {
                System.out.print(mem.readWord(i*0x10 + j) + " ");
            }
            System.out.println();
        } */
        /*for (int i=0x000; i< MEMORY_SIZE-1; i++) {
            System.out.println(Integer.toHexString(i).toUpperCase() + " " + mem.readWord(i));
        }*/
    }
}
