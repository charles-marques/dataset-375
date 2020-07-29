package jliftsim.panel;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.*;
import jliftsim.UserAction;
import jliftsim.data.CurrentFloorData;
import jliftsim.data.FloorData;

/**
 *
 * @author Bernard Ladenthin (bernard@ladenthin.net)
 * @version 0.1a
 */
public class AnimationPanel extends JPanel implements Observer {

    private FloorData m_FloorData;
    private CurrentFloorData m_CurrentFloorData;

    private UserAction m_UserAction;

    private JSeparator m_LeftSeparator;
    private JSeparator m_LeftLiftSeparator;
    private JSeparator m_RightLiftSeparator;

    private JCheckBox m_JCheckBoxDoorClosing;
    private JCheckBox m_JCheckBoxDoorOpening;

    private BufferedImage m_LiftImage;
    private JLabel m_JLabelLiftImage;

    private static final int heightresolution = 400;

    public AnimationPanel(UserAction userAction) {
        m_UserAction = userAction;

        m_LeftSeparator = new JSeparator();
        m_LeftLiftSeparator = new JSeparator();
        m_RightLiftSeparator = new JSeparator();

        m_JCheckBoxDoorClosing = new JCheckBox();
        m_JCheckBoxDoorOpening = new JCheckBox();

        try {
            //image from http://de.wikipedia.org/wiki/Datei:Elevator_icon.png
            //created by http://commons.wikimedia.org/wiki/User:Omaaar 2007-8-25
            m_LiftImage = ImageIO.read(getClass().getClassLoader().getResource("jliftsim/LiftImage.png"));
            m_JLabelLiftImage = new JLabel(new ImageIcon( m_LiftImage ));
            System.out.println("image angelegt");
        } catch (IOException ex) {
            System.out.println("Fail to read Image");
        }

        createAndShowGUI();
    }

    private void drawLiftImage() {
        Rectangle r = new Rectangle();
        r.width = 40;
        r.height = 40;
        r.x = 130;
        r.y = getYRelativeOffset(
            m_FloorData.floors-1,m_CurrentFloorData.floor,heightresolution);
        //Invert offset
        r.y = Math.abs(r.y - heightresolution);
        m_JLabelLiftImage.setBounds(r);
        removeOldLiftImage();
        this.add(m_JLabelLiftImage);
        invalidate();
        repaint();
        validate();
    }

    public static int getYRelativeOffset(
        int maxFloors, int currentFloor, int heightresolution) {
        return (int)(
              ((float)heightresolution / (float)maxFloors)
            * ((float)currentFloor)
        );
    }

    private void removeOldLiftImage() {
        if( m_JLabelLiftImage!= null ) {
            this.remove(m_JLabelLiftImage);
        }
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

        r.x = 128;
        r.y = 0;
        r.height = heightresolution+40;
        r.width = 2;
        m_LeftLiftSeparator.setOrientation(SwingConstants.VERTICAL);
        m_LeftLiftSeparator.setBounds(r);
        this.add(m_LeftLiftSeparator);

        r.x = 171;
        r.y = 0;
        r.height = heightresolution+40;
        r.width = 2;
        m_RightLiftSeparator.setOrientation(SwingConstants.VERTICAL);
        m_RightLiftSeparator.setBounds(r);
        this.add(m_RightLiftSeparator);

        //######################################################################
        /*
         * Lift animation
         */

        //######################################################################
        /*
         * JCheckBox
         */
        r.x = 10;
        r.y = 480;
        r.width = 160;
        r.height = 20;
        m_JCheckBoxDoorClosing.setText("Door is closing");
        m_JCheckBoxDoorClosing.setEnabled(false);
        m_JCheckBoxDoorClosing.setSelected(true);
        m_JCheckBoxDoorClosing.setBounds(r);
        this.add(m_JCheckBoxDoorClosing);

        r.y += 40;
        m_JCheckBoxDoorOpening.setText("Door is opening");
        m_JCheckBoxDoorOpening.setEnabled(false);
        m_JCheckBoxDoorOpening.setSelected(true);
        m_JCheckBoxDoorOpening.setBounds(r);
        this.add(m_JCheckBoxDoorOpening);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Update in AnimationPanel: " +arg.toString());
        if( arg instanceof CurrentFloorData ) {
            CurrentFloorData cfd = (CurrentFloorData)arg;
            m_CurrentFloorData = cfd;
            //System.out.println("===GET CFD!: "+cfd.toString());
            drawLiftImage();
        } else if( arg instanceof FloorData ) {
            FloorData fd = (FloorData)arg;
            m_FloorData = fd;
        }
    }
}
