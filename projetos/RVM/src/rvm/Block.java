package rvm;

import static rvm.Constants.*;

/**
 * 
 * VU MIF PS 1gr. 2012
 * @author Ernestas Prisakaru 
 * @author Lukas Ignatavicius
 * 
 */
public class Block {
    Word[] block;
    
    public Block() {
        block = new Word[BLOCK_SIZE];
    }
    
    public Block(Word[] words) {
        //FIXME ifai
        block = new Word[BLOCK_SIZE];
        block = words; // bbs
    }
    
    public Word getWord(int n) {
        return block[n];
    }
    
    public Word[] getBlock() {
        return block;
    }
    
    
}
