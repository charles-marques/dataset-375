package jliftsim.panel;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import jliftsim.JobDataList;
import jliftsim.UserAction;
import jliftsim.data.JobData;

/**
 *
 * @author Bernard Ladenthin (bernard@ladenthin.net)
 * @version 0.1a
 */
public class JobPanel extends JPanel implements Observer {

    private JSeparator m_LeftSeparator;
    private UserAction m_UserAction;
    private JScrollPane m_JScrollPane;
    private JTree m_JTree;

    public void addScrollPane(JTree jTree) {
        if(m_JScrollPane != null) {
            m_JScrollPane.removeAll();
            this.remove(m_JScrollPane);
        }
        m_JTree = jTree;
        expandTree();
        m_JScrollPane = new JScrollPane(m_JTree);
        m_JScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        m_JScrollPane.setBounds(2,0,300,300);
        this.add(m_JScrollPane);
        this.invalidate();
        this.repaint();
        this.validate();
    }

    public void collapsedTree() {
        if(m_JTree != null ) {
            for (int i = 0; i < m_JTree.getRowCount(); i++) {
                m_JTree.collapseRow(i);
            }
        }
    }

    public void expandTree() {
        if(m_JTree != null ) {
            for (int i = 0; i < m_JTree.getRowCount(); i++) {
                m_JTree.expandRow(i);
            }
        }
    }

    public JobPanel(UserAction userAction) {
        m_UserAction = userAction;
        m_LeftSeparator = new JSeparator();

        createAndShowGUI();
    }

    private void createAndShowGUI() {
        this.setLayout(null);
        Dimension dm = new Dimension();
        dm.setSize(300, 600);
        this.setPreferredSize(dm);

        /*
         * Common size
         */
        Rectangle r = new Rectangle();

        //######################################################################
        /*
         * Separator
         */
        r.x = 0;
        r.y = 0;
        r.height = dm.height;
        r.width = 2;
        m_LeftSeparator.setOrientation(SwingConstants.VERTICAL);
        m_LeftSeparator.setBounds(r);
        this.add(m_LeftSeparator);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Update in JobPanel: " +arg.toString());
        if( arg instanceof JobDataList ) {

            JobDataList jobDataList = (JobDataList)arg;

            DefaultMutableTreeNode root = new DefaultMutableTreeNode("Jobs");
            for( JobData jobData : jobDataList) {

                DefaultMutableTreeNode curJob = new DefaultMutableTreeNode(jobData);

                root.add(curJob);
                curJob.add(new DefaultMutableTreeNode(
                    "airCondition: "
                    +jobData.airCondition));
                curJob.add(new DefaultMutableTreeNode(
                    "alarm: "
                    +jobData.alarm));
                curJob.add(new DefaultMutableTreeNode(
                    "doorClose: "
                    +jobData.doorClose));
                curJob.add(new DefaultMutableTreeNode(
                    "doorOpen: "
                    +jobData.doorOpen));
                curJob.add(new DefaultMutableTreeNode(
                    "emergencyhold: "
                    +jobData.emergencyhold));
                curJob.add(new DefaultMutableTreeNode(
                    "floor: "
                    +jobData.floor));

                curJob.add(new DefaultMutableTreeNode(
                    "load: "
                    +jobData.load));
            }

            DefaultTreeModel treeModel = new DefaultTreeModel(root);
            JTree jTree = new JTree(treeModel);
            addScrollPane(jTree);
        }
    }
}
