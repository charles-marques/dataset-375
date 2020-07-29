package jliftsim;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import jliftsim.frame.CreateFrame;

/**
 *
 * @author Bernard Ladenthin (bernard@ladenthin.net)
 * @version 0.1a
 */
public class JLiftSim {

    private static void createAndShowGUI() {
        CreateFrame createFrame = new CreateFrame();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                //UIManager.getSystemLookAndFeelClassName());
                UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (  UnsupportedLookAndFeelException
               | ClassNotFoundException
               | InstantiationException
               | IllegalAccessException e) {
            e.printStackTrace();
        }
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
