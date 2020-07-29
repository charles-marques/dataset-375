package jliftsim.panel;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import jliftsim.data.FloorData;

/**
 *
 * @author Bernard Ladenthin (bernard@ladenthin.net)
 * @version 0.1a
 */
public class CreateFloorsPanel extends JPanel implements Observer {
    
    private JLabel m_JLabelFloors;

    private JSpinner m_JSpinnerFloors;
    private SpinnerModel m_SpinnerModelFloors;

    public CreateFloorsPanel() {
        m_SpinnerModelFloors = new SpinnerNumberModel(3, 3, Integer.MAX_VALUE, 1);
        m_JSpinnerFloors = new JSpinner(m_SpinnerModelFloors);

        m_JLabelFloors = new JLabel();

        createAndShowGUI();
    }

    public int getFloors() {
        return (Integer)m_JSpinnerFloors.getValue();
    }

    private void createAndShowGUI() {
        this.setLayout(null);
        Dimension dm = new Dimension();
        dm.setSize(300, 40);
        this.setPreferredSize(dm);

        /*
         * Common size
         */
        Rectangle r = new Rectangle();

        /*
         * JLabel
         */
        r.x = 10;
        r.y = 10;
        r.width = 120;
        r.height = 20;
        m_JLabelFloors.setText("Number of floors");
        m_JLabelFloors.setBounds(r);
        this.add(m_JLabelFloors);

        /*
         * JSpinner
         */
        r.x = 130;
        r.y = 10;
        r.width = 80;
        r.height = 20;
        m_JSpinnerFloors.setBounds(r);
        this.add(m_JSpinnerFloors);
    }

    @Override
    public void update(Observable o, Object arg) {
        FloorData fd;
        if( arg instanceof FloorData ) {
            fd = (FloorData)arg;
            m_JSpinnerFloors.setValue(fd.floors);
        }
    }
}
