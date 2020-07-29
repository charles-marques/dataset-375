package rvm;
/**
 * 
 * VU MIF PS 1gr. 2012
 * @author Ernestas Prisakaru 
 * @author Lukas Ignatavicius
 * 
 */

public final class Constants {
    public static final int WORD_SIZE = 0x4;
    public static final int BLOCK_SIZE = 0x10;
    public static final int PT_SIZE = 0x10;
    public static final int STACK_SIZE = 0x2;
    public static final int MAX_BLOCKS_IN_VM = 0xF;
    public static final int DEFAULT_DS_SIZE = 0x3;
    public static final int DEFAULT_CS_SIZE = 0x3;
    public static final int SCREEN_SIZE = 20;
    public static final int MEMORY_SIZE = 0x1000; // 0..FFF
    
    public static enum INTERRUPT_TYPE {ILLEGAL_ADDRESS, UNKNOWN_OPCODE};
    public static enum PROCESS_STATUS {READY, RUNNING, BLOCKED, FINISHED};
    
    private Constants() {
        throw new AssertionError("<Constants> is uninstansiable");
    }
    
}
