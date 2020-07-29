package jliftsim.panel;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import jliftsim.UserAction;

/**
 *
 * @author Bernard Ladenthin (bernard@ladenthin.net)
 * @version 0.1a
 */
public class SimPanel extends JPanel implements Observer {

    private UserAction m_UserAction;

    private AnimationPanel m_AnimationPanel;
    private EnginePanel m_EnginePanel;
    private InCabinRoomPanel m_InCabinRoomPanel;
    private JobPanel m_JobPanel;

    public SimPanel(UserAction userAction) {
        m_UserAction = userAction;
        m_EnginePanel = new EnginePanel(userAction);
        m_InCabinRoomPanel = new InCabinRoomPanel(userAction);
        m_AnimationPanel = new AnimationPanel(userAction);
        m_JobPanel = new JobPanel(userAction);

        createAndShowGUI();
        //m_EnginePanel.setVisible(false);
    }

    public EnginePanel getEnginePanelInstance() {
        return m_EnginePanel;
    }

    public InCabinRoomPanel getInCabinRoomPanelInstance() {
        return m_InCabinRoomPanel;
    }

    public AnimationPanel getAnimationPanelInstance() {
        return m_AnimationPanel;
    }

    public JobPanel getJobPanelInstance() {
        return m_JobPanel;
    }

    private void createAndShowGUI() {
        this.setLayout(null);
        Dimension dm = new Dimension();
        dm.setSize(1300, 600);
        this.setPreferredSize(dm);

        Dimension dmEnginePanel = m_EnginePanel.getPreferredSize();
        m_EnginePanel.setBounds(
            0, 0,
            dmEnginePanel.width, dmEnginePanel.height);
        //m_EnginePanel.setBackground(java.awt.Color.LIGHT_GRAY);
        this.add(m_EnginePanel);

        Dimension dmInCabinRoomPanel = m_InCabinRoomPanel.getPreferredSize();
        m_InCabinRoomPanel.getPreferredSize();
        m_InCabinRoomPanel.setBounds(
            0, dmEnginePanel.height,
            dmInCabinRoomPanel.width, dmInCabinRoomPanel.height);
        //m_InCabinRoomPanel.setBackground(java.awt.Color.WHITE);
        this.add(m_InCabinRoomPanel);

        Dimension dmAnimationPanel = m_AnimationPanel.getPreferredSize();
        m_AnimationPanel.setBounds(
            dmInCabinRoomPanel.width, 0,
            dmAnimationPanel.width, dmAnimationPanel.height);
        //m_AnimationPanel.setBackground(java.awt.Color.ORANGE);
        this.add(m_AnimationPanel);

        Dimension dmJobPanel = m_JobPanel.getPreferredSize();
        m_JobPanel.setBounds(
            dmInCabinRoomPanel.width + dmAnimationPanel.width, 0,
            dmJobPanel.width, dmJobPanel.height);
        //m_JobPanel.setBackground(java.awt.Color.LIGHT_GRAY);
        this.add(m_JobPanel);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Update in SimPanel: " +arg.toString());
    }
}
