package jliftsim.frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import jliftsim.panel.CreatePanel;

/**
 *
 * @author Bernard Ladenthin (bernard@ladenthin.net)
 * @version 0.1a
 */
public final class CreateFrame extends JFrame {

    CreatePanel m_CreatePanel;

    public void createAndShowGUI() {
        this.add(m_CreatePanel);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("jLiftSim Instancer");
        /*
         * http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4511106
         * First Call To JWindow.pack() gives inconsistent
         * size(height rtns one Pixel less)
         * resolution: call this.pack() twice
         */
        this.pack();
        this.pack();
        centerThis();
    }

    public CreateFrame() {
        m_CreatePanel = new CreatePanel();

        createAndShowGUI();
    }

    public void centerThis() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int top  = (screenSize.height - getHeight()) / 2;
        int left = (screenSize.width  - getWidth() ) / 2;
        setLocation(left, top);
    }
}
