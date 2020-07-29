package rvm;

import static rvm.Constants.*;

/**
 *
 * VU MIF PS 1gr. 2012
 *
 * @author Ernestas Prisakaru
 * @author Lukas Ignatavicius
 *
 */
public class Memory {

    /*
     * [ PAGE TABLE ] RESERVED [ SHARED MEMORY ] RESERVED [ USER MEMORY ] [ USER
     * MEMORY ]
     *
     *
     */
    protected Word[] memory;
    //private boolean[] PageEntryAvailable;
    protected int size;

    protected Memory() {
        
    }

    /**
     * Sukuriama reali atmintis
     *
     * @param size kuriamos atminties dydis
     * @see Constants
     */
    public Memory(int size) {
        this.size = size;
        memory = new Word[size];
        /*
         * for (int i=0; i < PT_SIZE; i++) { PageEntryAvailable[i] = true; }
         */
    }

    /**
     * Gaunamas atminties dydis
     *
     * @return atminties dydi
     */
    public int getSize() {
        return size;
    }

    /*
     * public boolean isPageEntryAvailable(int index) { return
     * PageEntryAvailable[index]; // outofbounds }
     */
    /**
     * Nuskaitomas zodis nurodytu fiziniu adresu
     *
     * @param absAddress fizinis adresas
     * @return nuskaitytas zodis
     */
    public Word readWord(int absAddress) {
        // FIXME uzejimas uz ribu
        if (memory[absAddress] == null) {
            return new Word(0);
        }
        return memory[absAddress];
    }

    /**
     * Nuskaitomas zodis nurodytu fiziniu adresu (pateiktu Word tipu)
     *
     * @param absAddress fizinis adresas
     * @return nuskaitytas zodis
     */
    public Word readWord(Word absAddress) {
        return readWord(absAddress.toInt());
    }

    /**
     * Irasomas zodis nurodytu fiziniu adresu
     *
     * @param absAddress fizinis adresas
     * @param data irasomas zodis
     */
    public void writeWord(int absAddress, Word data) {
        memory[absAddress] = data;
    }

    /**
     * Irasomas zodis nurodytu fiziniu adresu (pateiktu Word tipu)
     *
     * @param absAddress fizinis adresas
     * @param data irasomas zodis
     */
    public void writeWord(Word absAddress, Word data) {
        writeWord(absAddress.toInt(), data);
    }

    /**
     * Temporary
     *
     * atmintis uzpildoma zodziais 0000
     */
    public void fillZeroes() {
        Word empty = new Word(0000); // ascii -> null;
        for (int i = 0x0; i < getSize(); i++) {
            writeWord(i, empty);
        }
    }

    /**
     * Atmintis grazinama kaip zodziu masyvas
     *
     * @return zodziu masyvas
     */
    public Word[] getWords() {
        return memory;
    }
}
