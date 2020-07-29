package rvm;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lukas
 */
public class ExternalMemory extends Memory {

    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    public ExternalMemory() {
        super();
        readExternalMemory();
    }

    public ExternalMemory(int size) {
        super(size);
        writeExternalMemory();
    }

    @Override
    public Word readWord(int absAddress) {
        readExternalMemory();
        return super.readWord(absAddress);
    }

    @Override
    public void writeWord(int absAddress, Word data) {
        super.writeWord(absAddress, data);
        writeExternalMemory();
    }

    private void setInputStream() throws IOException {
        inputStream = new ObjectInputStream(new FileInputStream("src/memory.mem"));
    }

    private void setOutputStream() throws FileNotFoundException, IOException {
        outputStream = new ObjectOutputStream(new FileOutputStream("src/memory.mem"));
    }

    private void closeInputStream() throws IOException {
        inputStream.close();
    }

    private void closeOutputStream() throws IOException {
        outputStream.close();
    }

    private void readExternalMemory() {
        try {
            setInputStream();
            memory = (Word[]) inputStream.readObject();
            closeInputStream();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ExternalMemory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void writeExternalMemory() {
        try {
            setOutputStream();
            outputStream.writeObject(memory);
            outputStream.flush();
        } catch (IOException ex) {
            Logger.getLogger(ExternalMemory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
