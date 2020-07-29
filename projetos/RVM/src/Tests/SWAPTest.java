/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import java.io.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import rvm.*;

/**
 *
 * @author lukas
 */
public class SWAPTest {

    public static void main(String[] args) {

        Word w = new Word("op");
        Word ubaba = null;
        ObjectOutputStream outputStream;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("src/test.t"));
            outputStream.writeObject(w);
            outputStream.flush();
            outputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(SWAPTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("src/test.t"));
            ubaba = (Word) inputStream.readObject();
            inputStream.close();
        } catch (IOException |
                ClassNotFoundException ex) {
            Logger.getLogger(SWAPTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(ubaba);

        Memory mem = new ExternalMemory(0xFFF);
        int blocks = 5;
        int memSize = blocks * Constants.BLOCK_SIZE;
        ExternalMemory exMem = new ExternalMemory(0xFFFF);
        VirtualMemorySWAP vm = new VirtualMemorySWAP(new Word(0xFF), mem, exMem);
        vm.setSize(memSize);
        mem.writeWord(0xFF, new Word(0x0));
        for (int i = 0; i < blocks; i++) {
            if (i > 3) {
                i = i + 5;
            }
            int val = i + 0x10;
            exMem.writeWord((i > 3) ? i - 5 : i, new Word(val));
            for (int j = val * 0x10; j < val * 0x10 + Constants.BLOCK_SIZE; j++) {
                exMem.writeWord(j, new Word(0x555));
            }
        }
        //print(exMem);
        /*
         * for (int i = 0; i < memSize; i++){ vm.writeWord(i, new Word(0x200));
         * }
         */
        for (int i = 0; i < memSize; i++) {
            System.out.print(Integer.toHexString(i) + " " + Integer.toHexString(vm.readWord(i).toInt()) + " ");
            if (i % 0x10 == 0) {
                System.out.println();
            }
        }
        System.out.println("*********************************************************************");
        System.out.println("*******************Real mem******************************************");
        System.out.println("*********************************************************************");
        print(exMem);
    }

    public static void print(ExternalMemory mem) {
        for (int i = 0x00; i < 0xFF; i++) {
            System.out.print(Integer.toHexString(i).toUpperCase() + "_: ");
            for (int j = 0x0; j <= 0xF; j++) {
                System.out.print(Integer.toHexString(mem.readWord(i * 0x10 + j).toInt()) + " ");
            }
            System.out.println();
        }
        /*
         * for (int i=0x000; i< MEMORY_SIZE-1; i++) {
         * System.out.println(Integer.toHexString(i).toUpperCase() + " " +
         * mem.readWord(i)); }
         */
    }
}
