/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import rvm.*;

/**
 *
 * @author root
 */
public class ChanTest {

    public static void main(String[] args) {
        CPU cpu = new CPU();
        Chan ch = new Chan(cpu);
       // ch.useChan2(ch.useChan1());
    }
}
