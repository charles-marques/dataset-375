package rvm;

/**
 *
 * VU MIF PS 1gr. 2012
 *
 * @author Ernestas Prisakaru
 * @author Lukas Ignatavicius
 *
 */
public class CPU {
    private Word PTR;    // Puslapiavimo lenteles registras
    private Word R1;     // Bendros paskirties registras
    private Word R2;     // Bendros paskirties registras
    private short IP;    // Sekancios komandos adreso registras (Instruction Pointer)
    private boolean C;   // Loginis registras
    private byte MODE;   // Rezimas 0 - supervisor | 1 - user
    private Timer TIMER; // Taimeris
    private byte TI;     // Taimerio pertraukimo registras
    private byte IOI;    // Ivedimo/Isvedimo pertraukimo registras
    private byte SI;     // Supervizoriaus pertraukimo registras
    private byte PI;     // Programinio pertraukimo registras
    private boolean[] CHN; // Ivedimo/Isvedimo/Isorines talpykos uzimtumo registrai

    /**
     * Inicijuojamas procesorius ir jo registrai
     */
    public CPU() {
        IP = 0;
        TIMER = new Timer(1);
        TI = 0;
        IOI = 0;
        SI = 0;
        PI = 0;
        CHN = new boolean[3];
        for (boolean i: CHN) {
            i = true;
        }
    }

    /**
     * Pertraukimu ivykio patikra
     */
    public boolean interruptTest() {
        if ((PI + SI + IOI + TI) > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 
     */
    public void setTI(byte val) {
        TI = val;
    }
    
    public void setPI(byte val) {
        PI = val;
    }
    
    public void setSI(byte val) {
        SI = val;
    }

    public void setIOI(byte val) {
        IOI = val;
    }
    
    /**
     * @param index kanalo numeris
     * @return kanalo laisvumas. True - laisvas | False - uzimtas
     */
    public boolean isChanAvailable(int index) {
        if (index < 3 && index >= 0) {
            return CHN[index];
        }
        return false;
    }
    
    /**
     * Kanalo busenos nustatymas, kaip laisvas
     * @param index kanalas numeris
     */
    public void setChanAvailable(int index) {
        if (index < 3 && index >= 0) {
            CHN[index] = true;
        }
    }

    /**
     * Kanalo busenos nustatymas, kaip uzimtas
     * @param index kanalo numeris
     */
    public void setChanOccupied(int index) {
        if (index < 3 && index >= 0) {
            CHN[index] = false;
        }
    }

    
   /* useChannel1() {
        
    }*/
}
