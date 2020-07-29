package jliftsim.frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import jliftsim.UserAction;
import jliftsim.data.EngineData;
import jliftsim.data.FloorData;
import jliftsim.data.NameData;
import jliftsim.panel.SimPanel;

/**
 *
 * @author Bernard Ladenthin (bernard@ladenthin.net)
 * @version 0.1a
 */
public class SimFrame extends JFrame implements Observer {

    SimPanel m_SimPanel;
    UserAction m_UserAction;

    public SimFrame(UserAction userAction) {
        m_UserAction = userAction;
        createAndShowGUI();
    }

    public SimPanel getSimPanelInstance() {
        return m_SimPanel;
    }

    private void createAndShowGUI() {
        m_SimPanel = new SimPanel(m_UserAction);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //this.setTitle("jLiftSim SimFrame ("+name+")");
        this.add(m_SimPanel);
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

    public void centerThis() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int top  = (screenSize.height - getHeight()) / 2;
        int left = (screenSize.width  - getWidth() ) / 2;
        setLocation(left, top);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Update in SimFrame: " +arg.toString());
        FloorData fd;
        EngineData ed;
        NameData nd;
        if( arg instanceof FloorData ) {
            fd = (FloorData)arg;
            System.out.println("FloorData");
            //m_JSpinnerFloors.setValue(fd.floors);
        }
        else if (arg instanceof EngineData) {
            ed = (EngineData)arg;
            System.out.println("EngineData");
        }
        else if (arg instanceof NameData) {
            nd = (NameData)arg;
            System.out.println("NameData");
        }
    }

}
