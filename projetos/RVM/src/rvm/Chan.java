package rvm;

import java.util.Scanner;

/**
 * Kanalų registro įrenginio klasė
 * @author ernestas
 */
public class Chan {
    
    private CPU cpu;
    private Word[] buffer;
    private int bufferSize = 0;
    private Screen scr = new Screen();

    /**
     * Kanalų įrenginio kūrimas. Visu kanalu pradines busenos "available"
     */
    public Chan(CPU cpu) {
        this.cpu = cpu;
        cpu.setChanAvailable(1);
        cpu.setChanAvailable(2);
        cpu.setChanAvailable(3);
        buffer = new Word[5];
    }

    /**
     *
     * @param index kanalų įrenginio numeris
     * @return true, jei kanalas nurodytu numeriu laisvas
     */
    public boolean isAvailable(int index) {
        if (index <= 3 && index >= 0) {
            return cpu.isChanAvailable(index);
        } else {
            return false;
        }
    }

    /**
     * Kol nėra Interfeiso ir OS gal užteks tokio.
     * Kai bus interfeisas imsim iš imputo, kai os gal dar kažką
     * @return nuskaitytas ivedimas
     */
    //
    public void useChan1(String input) {
        cpu.setChanOccupied(1);
        /*StringBuilder str = new StringBuilder(input);
        while (str.length() > 0 && bufferSize < 5) {
            Word piece;
            if (str.length() >= 4) { 
                piece = new Word(Integer.parseInt(input.substring(0, 3)));
                str.delete(0, 3);
            } else {
                piece = new Word(Integer.parseInt(input));
                str.delete(0, str.length());
            }
            buffer[bufferSize] = piece;
            bufferSize++;
        }*/
        
        
        cpu.setChanAvailable(1);
    }
    /**
     * Isvedimas i antraji kanala
     * @param output duomenys isvedami i kanala
     */
    public void useChan2(Word toPut) {
        cpu.setChanOccupied(2);
        scr.put(toPut);
        cpu.setChanAvailable(2);        
    }
    
    public Screen getScreen() {
        return scr;
    }
}
