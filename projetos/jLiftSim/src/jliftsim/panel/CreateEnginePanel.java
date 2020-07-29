package jliftsim.panel;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import jliftsim.data.EngineData;

/**
 *
 * @author Bernard Ladenthin (bernard@ladenthin.net)
 * @version 0.1a
 */
public class CreateEnginePanel  extends JPanel implements Observer {
    private JLabel m_JLabelMaxPower;
    private JLabel m_JLabelMaxSpeed;
    private JLabel m_JLabelMaxLoad;
    private JLabel m_JLabelMaxAcceleration;

    private SpinnerModel m_SpinnerModelMaxPower;
    private SpinnerModel m_SpinnerModelMaxSpeed;
    private SpinnerModel m_SpinnerModelMaxLoad;
    private SpinnerModel m_SpinnerModelMaxAcceleration;

    private JSpinner m_JSpinnerMaxPower;
    private JSpinner m_JSpinnerMaxSpeed;
    private JSpinner m_JSpinnerMaxLoad;
    private JSpinner m_JSpinnerMaxAcceleration;

    private JLabel m_JLabelMaxPowerUnit;
    private JLabel m_JLabelMaxSpeedUnit;
    private JLabel m_JLabelMaxLoadUnit;
    private JLabel m_JLabelMaxAccelerationUnit;

    public CreateEnginePanel() {
        m_JLabelMaxPower = new JLabel();
        m_JLabelMaxSpeed = new JLabel();
        m_JLabelMaxLoad = new JLabel();
        m_JLabelMaxAcceleration = new JLabel();

        m_JLabelMaxPowerUnit = new JLabel();
        m_JLabelMaxSpeedUnit = new JLabel();
        m_JLabelMaxLoadUnit = new JLabel();
        m_JLabelMaxAccelerationUnit = new JLabel();

        m_SpinnerModelMaxPower = new SpinnerNumberModel(
            1, 1, Integer.MAX_VALUE, 1);
        m_JSpinnerMaxPower = new JSpinner(m_SpinnerModelMaxPower);

        m_SpinnerModelMaxSpeed = new SpinnerNumberModel(
            1, 1, Integer.MAX_VALUE, 1);
        m_JSpinnerMaxSpeed = new JSpinner(m_SpinnerModelMaxSpeed);

        m_SpinnerModelMaxLoad = new SpinnerNumberModel(
            1, 1, Integer.MAX_VALUE, 1);
        m_JSpinnerMaxLoad = new JSpinner(m_SpinnerModelMaxLoad);

        m_SpinnerModelMaxAcceleration = new SpinnerNumberModel(
            1, 1, Integer.MAX_VALUE, 1);
        m_JSpinnerMaxAcceleration = new JSpinner(m_SpinnerModelMaxAcceleration);

        createAndShowGUI();
    }

    public int getMaxPower() {
        return (int)m_JSpinnerMaxPower.getValue();
    }
    public int getMaxSpeed() {
        return (int)m_JSpinnerMaxSpeed.getValue();
    }
    public int getMaxLoad() {
        return (int)m_JSpinnerMaxLoad.getValue();
    }
    public int getMaxAcceleration() {
        return (int)m_JSpinnerMaxAcceleration.getValue();
    }

    private void createAndShowGUI() {
        this.setLayout(null);
        Dimension dm = new Dimension();
        dm.setSize(300, 160);
        this.setPreferredSize(dm);

        /*
         * Common size
         */
        Rectangle r = new Rectangle();

        //######################################################################
        /*
         * JLabel
         */
        r.x = 10;
        r.y = 10;
        r.width = 120;
        r.height = 20;
        m_JLabelMaxPower.setText("Max Power");
        m_JLabelMaxPower.setBounds(r);
        this.add(m_JLabelMaxPower);

        r.y += 40;
        m_JLabelMaxSpeed.setText("Max Speed");
        m_JLabelMaxSpeed.setBounds(r);
        this.add(m_JLabelMaxSpeed);

        r.y += 40;
        m_JLabelMaxLoad.setText("Max Load");
        m_JLabelMaxLoad.setBounds(r);
        this.add(m_JLabelMaxLoad);

        r.y += 40;
        m_JLabelMaxAcceleration.setText("Max Acceleration");
        m_JLabelMaxAcceleration.setBounds(r);
        this.add(m_JLabelMaxAcceleration);

        //######################################################################
        /*
         * JSpinner
         */
        //EngineMaxPower
        r.x = 130;
        r.y = 10;
        r.width = 80;
        r.height = 20;
        m_JSpinnerMaxPower.setBounds(r);
        this.add(m_JSpinnerMaxPower);

        //EngineMaxSpeed
        r.y += 40;
        m_JSpinnerMaxSpeed.setBounds(r);
        this.add(m_JSpinnerMaxSpeed);

        //EngineMaxLoad
        r.y += 40;
        m_JSpinnerMaxLoad.setBounds(r);
        this.add(m_JSpinnerMaxLoad);

        //EngineMaxAcceleration
        r.y += 40;
        m_JSpinnerMaxAcceleration.setBounds(r);
        this.add(m_JSpinnerMaxAcceleration);

        //######################################################################
        /*
         * Current Label Unit
         */
        r.x = 220;
        r.y = 10;
        r.width = 30;
        r.height = 20;
        m_JLabelMaxPowerUnit.setText("kw");
        m_JLabelMaxPowerUnit.setBounds(r);
        this.add(m_JLabelMaxPowerUnit);

        r.y += 40;
        m_JLabelMaxSpeedUnit.setText("km/h");
        m_JLabelMaxSpeedUnit.setBounds(r);
        this.add(m_JLabelMaxSpeedUnit);

        r.y += 40;
        m_JLabelMaxLoadUnit.setText("kg");
        m_JLabelMaxLoadUnit.setBounds(r);
        this.add(m_JLabelMaxLoadUnit);

        r.y += 40;
        m_JLabelMaxAccelerationUnit.setText("m/s²");
        m_JLabelMaxAccelerationUnit.setBounds(r);
        this.add(m_JLabelMaxAccelerationUnit);

    }

    @Override
    public void update(Observable o, Object arg) {
        EngineData cfd;
        if( arg instanceof EngineData ) {
            cfd = (EngineData)arg;
                m_JSpinnerMaxPower.setValue(cfd.maxPower);
                m_JSpinnerMaxSpeed.setValue(cfd.maxSpeed);
                m_JSpinnerMaxLoad.setValue(cfd.maxLoad);
                m_JSpinnerMaxAcceleration.setValue(cfd.maxAcceleration);
        }
    }

}
