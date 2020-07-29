package jliftsim.panel;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import jliftsim.data.NameData;

/**
 *
 * @author Bernard Ladenthin (bernard@ladenthin.net)
 * @version 0.1a
 */
public class CreateNamePanel extends JPanel implements Observer {
    
    private JLabel m_JLabelCreateName;

    private JTextField m_JTextFieldCreateName;

    public CreateNamePanel() {
        m_JTextFieldCreateName = new JTextField();

        m_JLabelCreateName = new JLabel();

        createAndShowGUI();
    }

    public String getCreateName() {
        return m_JTextFieldCreateName.getText();
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
        m_JLabelCreateName.setText("Name");
        m_JLabelCreateName.setBounds(r);
        this.add(m_JLabelCreateName);

        /*
         * JTextField
         */
        r.x = 130;
        r.y = 10;
        r.width = 160;
        r.height = 20;
        m_JTextFieldCreateName.setBounds(r);
        this.add(m_JTextFieldCreateName);
    }

    @Override
    public void update(Observable o, Object arg) {
        NameData nd;
        if( arg instanceof NameData ) {
            nd = (NameData)arg;
            m_JTextFieldCreateName.setText(nd.name);
        }
    }
}
