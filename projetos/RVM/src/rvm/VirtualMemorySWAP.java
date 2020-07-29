package rvm;
import static rvm.Constants.*;
/**
 *
 * @author lukas ir ernis
 */
public class VirtualMemorySWAP extends VirtualMemory{

    protected Memory externalMemory;
    
    public VirtualMemorySWAP(Word PTR, Memory rMemory, Memory eMemory) {
        super(PTR, rMemory);
        externalMemory = eMemory;
    }
    
    @Override
    public Word readWord(int track, int word) {
        if (pageTableAddress.toInt() > PT_SIZE){
            return new Word(externalMemory.readWord(getExternalAbsoluteAddress(track, word)).toString());
        }else{
            return super.readWord(track, word);
        }
    }
    /**
     * 
     * @param track
     * @param word
     * @param data 
     */
    @Override
    public void writeWord(int track, int word, Word data) {
        if (pageTableAddress.toInt() > PT_SIZE){
            externalMemory.writeWord(getExternalAbsoluteAddress(track, word), data);
        }else{
            super.writeWord(track, word, data);
        }
    }

    private int getExternalAbsoluteAddress(int track, int word) {
        Word externalPageTableAddress = realMemory.readWord(pageTableAddress);
        Word absoluteTrack = externalMemory.readWord(externalPageTableAddress.toInt() + track);
        int absoluteAddress = absoluteTrack.toInt()*0x10 + word;
        return absoluteAddress;
    }

}
