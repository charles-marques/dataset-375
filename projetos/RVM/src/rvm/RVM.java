package rvm;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * VU MIF PS 1gr. 2012
 *
 * @author Ernestas Prisakaru
 * @author Lukas Ignatavicius
 *
 */
public class RVM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RM rm = new RM();
 
        // keisti parametru padeti, musu AD pl adresa, tai DS kinta nuo parmetru skaiciaus, reiskis niekad tikslaus adreso nenurodysi
        VirtualMachine vm;
        try {
            // mano C:\\Users\\ernestas\\Documents\\NetBeansProjects\\RVM\\src\\program1
            // tavo /home/lukas/NetBeansProjects/RVM/src/program1
            vm = rm.startNewVM("src/program1");//
            System.out.println("pradines registru reiksmes: ");
            vm.printRegisters();
            while (!vm.isHalted()) {
                vm.step();
            }
            System.out.println("Programa sekmingai baige darba");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RVM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
