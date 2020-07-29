package jliftsim.panel;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import jliftsim.UserAction;

/**
 *
 * @author Bernard Ladenthin (bernard@ladenthin.net)
 * @version 0.1a
 */
public class InCabinRoomPanel extends JPanel implements Observer,
        ActionListener {

    private UserAction m_UserAction;

    private JButton m_JButtonAlarm;
    private JButton m_JButtonHand;
    private JButton m_JButtonDoorClose;
    private JButton m_JButtonEmergencyHold;
    private JButton m_JButtonAirCondition;
    private JButton m_JButtonDoorOpen;

    private JCheckBox m_JCheckBoxOverload;

    private InCabinRoomFloorsPanel m_InCabinRoomFloorsPanel;

    private JScrollPane m_InCabinRoomFloorsScrollPane;

    public InCabinRoomPanel(UserAction userAction) {
        m_UserAction = userAction;
        m_JButtonAlarm = new JButton();
        m_JButtonHand = new JButton();
        m_JButtonDoorClose = new JButton();
        m_JButtonEmergencyHold = new JButton();
        m_JButtonAirCondition = new JButton();
        m_JButtonDoorOpen = new JButton();

        m_JCheckBoxOverload = new JCheckBox();

        m_InCabinRoomFloorsPanel = new InCabinRoomFloorsPanel(m_UserAction);
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        this.setLayout(null);
        Dimension dm = new Dimension();
        dm.setSize(700, 430);
        this.setPreferredSize(dm);

        /*
         * Common size
         */
        Rectangle r = new Rectangle();

        //######################################################################
        /*
         * JButton
         */
        r.x = 10;
        r.y = 10;
        r.width = 160;
        r.height = 20;
        m_JButtonAlarm.setText("Alarm");
        m_JButtonAlarm.setBounds(r);
        m_JButtonAlarm.addActionListener(this);
        this.add(m_JButtonAlarm);

        r.y += 40;
        m_JButtonHand.setText("Hand");
        m_JButtonHand.setBounds(r);
        m_JButtonHand.addActionListener(this);
        this.add(m_JButtonHand);

        r.y += 40;
        m_JButtonDoorClose.setText("Door close");
        m_JButtonDoorClose.setBounds(r);
        m_JButtonDoorClose.addActionListener(this);
        this.add(m_JButtonDoorClose);

        r.x = 180;
        r.y = 10;
        m_JButtonEmergencyHold.setText("Emergency hold");
        m_JButtonEmergencyHold.setBounds(r);
        m_JButtonEmergencyHold.addActionListener(this);
        this.add(m_JButtonEmergencyHold);

        r.y += 40;
        m_JButtonAirCondition.setText("Air Condition");
        m_JButtonAirCondition.setBounds(r);
        m_JButtonAirCondition.addActionListener(this);
        this.add(m_JButtonAirCondition);

        r.y += 40;
        m_JButtonDoorOpen.setText("Door open");
        m_JButtonDoorOpen.setBounds(r);
        m_JButtonDoorOpen.addActionListener(this);
        this.add(m_JButtonDoorOpen);


        //######################################################################
        /*
         * JCheckBox
         */
        r.x = 350;
        r.y = 10;
        r.width = 160;
        r.height = 20;
        m_JCheckBoxOverload.setText("Overload");
        m_JCheckBoxOverload.setEnabled(false);
        m_JCheckBoxOverload.setSelected(true);
        m_JCheckBoxOverload.setBounds(r);
        this.add(m_JCheckBoxOverload);

        //######################################################################
        /*
         * InCabinRoomFloorsPanel
         */
        r.x = 10;
        r.y = 130;
        r.width = 680;
        r.height = 290;

        m_InCabinRoomFloorsScrollPane = new JScrollPane(m_InCabinRoomFloorsPanel);
        m_InCabinRoomFloorsScrollPane.setBounds(r);
        this.add(m_InCabinRoomFloorsScrollPane);
    }

    public InCabinRoomFloorsPanel getInCabinRoomFloorsPanelInstance() {
        return m_InCabinRoomFloorsPanel;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Update in InCabinRoomPanel: " +arg.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == m_JButtonAlarm) {
            m_UserAction.userActionAlarm();
        } else if(e.getSource() == m_JButtonHand) {
            m_UserAction.userActionHand();
        } else if (e.getSource() == m_JButtonDoorClose) {
            m_UserAction.userActionDoorClose();
        } else if (e.getSource() == m_JButtonEmergencyHold) {
            m_UserAction.userActionEmergencyHold();
        } else if (e.getSource() == m_JButtonAirCondition) {
            m_UserAction.userActionAirCondition();
        } else if (e.getSource() == m_JButtonDoorOpen) {
            m_UserAction.userActionDoorOpen();
        }
    }
}
