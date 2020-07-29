package jliftsim.panel;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import jliftsim.CreateObservable;
import jliftsim.data.EngineData;
import jliftsim.data.FloorData;
import jliftsim.data.NameData;

/**
 *
 * @author Bernard Ladenthin (bernard@ladenthin.net)
 * @version 0.1a
 */
public class CreatePredefinedPanel extends JPanel
    implements ItemListener {
    
    private JLabel m_JLabelPredefined;
    private CreateObservable m_CreateObservable;

    private String[] m_JComboBoxItemsPredefined =
        {
            "default",
            "Burj Khalifa (Dubai)",
            "Taipei 101 (Taipei)",
            "Petronas Towers (Kuala Lumpur)",
            "Commerzbank Tower (Frankfurt a. M.)",
            "Messeturm (Frankfurt a. M.)",
            "Park Inn (Berlin)"
        };

    private JComboBox<String> m_JComboBoxPredefined;

    public CreatePredefinedPanel(CreateObservable o) {
        m_CreateObservable = o;

        m_JLabelPredefined = new JLabel();
        m_JComboBoxPredefined = new JComboBox<>();

        createAndShowGUI();
    }

    public String getPredefined() {
        return (String)m_JComboBoxPredefined.getSelectedItem();
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
        m_JLabelPredefined.setText("Predefined");
        m_JLabelPredefined.setBounds(r);
        this.add(m_JLabelPredefined);

        /*
         * JComboBox
         */
        m_JComboBoxPredefined.addItemListener(this);
        for(String i : m_JComboBoxItemsPredefined ) {
            m_JComboBoxPredefined.addItem(i);
        }
        r.x = 130;
        r.y = 10;
        r.width = 160;
        r.height = 20;
        m_JComboBoxPredefined.setBounds(r);
        this.add(m_JComboBoxPredefined);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == m_JComboBoxPredefined) {
            if(e.getStateChange() == ItemEvent.SELECTED) {
                FloorData fd = new FloorData();
                EngineData ed = new EngineData();
                NameData nd = new NameData();
                switch(m_JComboBoxPredefined.getSelectedIndex()) {
                    case 0:
                        fd.floors = 50;
                        ed.maxPower = 10;
                        ed.maxSpeed = 60;
                        ed.maxLoad = 5000;
                        ed.maxAcceleration = 20;
                        nd.name = "default";
                        break;
                    case 1:
                        fd.floors = 163;
                        ed.maxPower = 11;
                        ed.maxSpeed = 61;
                        ed.maxLoad = 5001;
                        ed.maxAcceleration = 21;
                        nd.name = "Burj Khalifa (Dubai)";
                        break;
                    case 2:
                        fd.floors = 101;
                        ed.maxPower = 12;
                        ed.maxSpeed = 62;
                        ed.maxLoad = 5002;
                        ed.maxAcceleration = 22;
                        nd.name = "Taipei 101 (Taipei)";
                        break;
                    case 3:
                        fd.floors = 88;
                        ed.maxPower = 13;
                        ed.maxSpeed = 63;
                        ed.maxLoad = 5003;
                        ed.maxAcceleration = 23;
                        nd.name = "Petronas Towers (Kuala Lumpur)";
                        break;
                    case 4:
                        fd.floors = 56;
                        ed.maxPower = 14;
                        ed.maxSpeed = 64;
                        ed.maxLoad = 5004;
                        ed.maxAcceleration = 24;
                        nd.name = "Commerzbank Tower (Frankfurt a. M.)";
                        break;
                    case 5:
                        fd.floors = 56;
                        ed.maxPower = 15;
                        ed.maxSpeed = 65;
                        ed.maxLoad = 5005;
                        ed.maxAcceleration = 25;
                        nd.name = "Messeturm (Frankfurt a. M.)";
                        break;
                    case 6:
                        fd.floors = 41;
                        ed.maxPower = 16;
                        ed.maxSpeed = 66;
                        ed.maxLoad = 5006;
                        ed.maxAcceleration = 26;
                        nd.name = "Park Inn (Berlin)";
                        break;
                }
                m_CreateObservable.engineChanged(ed);
                m_CreateObservable.floorChanged(fd);
                m_CreateObservable.nameChanged(nd);
            }
        }
    }
}
