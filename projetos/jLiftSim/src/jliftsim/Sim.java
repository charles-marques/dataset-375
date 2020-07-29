package jliftsim;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;
import jliftsim.data.CurrentFloorData;
import jliftsim.data.UserActionData;
import jliftsim.frame.SimFrame;

/**
 *
 * @author Bernard Ladenthin (bernard@ladenthin.net)
 * @version 0.1a
 */
public class Sim extends Observable implements Observer, ActionListener,
        WindowListener, UserAction, TimeEvent {

    SimData m_SimData;
    SimFrame m_SimFrame;
    NotifyRemove m_NotifyRemove;
    FireTimeEvent m_FireTimeEvent;

    private int m_State;

    //TODO: set this so low you can
    private static final int heartbeat = 5000;
    private static final int time_doorClose = 2500;
    private              int time_doorCloseCounter;
    private static final int time_doorOpen = 2500;
    private              int time_doorOpenCounter;

    private static final int state_unset = 0;
    private static final int state_up = 1;
    private static final int state_down = 2;
    private static final int state_doorOpen = 3;
    private static final int state_doorClose = 4;
    private static final int state_doorEmergencyhold = 5;
    private static final int state_doorHand = 6;
    private static final int state_waitforjob = 7;

    public Sim (
         NotifyRemove notifyRemove,
         String name
        ,int floors
        ,int maxPower
        ,int maxSpeed
        ,int maxLoad
        ,int maxAcceleration
    ) {
        m_State = state_unset;
        time_doorCloseCounter = 0;
        time_doorOpenCounter = 0;
        m_NotifyRemove = notifyRemove;
        m_SimData = new SimData(
            name, floors, maxPower, maxSpeed, maxLoad, maxAcceleration);
        m_SimFrame = new SimFrame(this);
        m_SimFrame.setTitle(name);
        m_SimFrame.addWindowListener(this);
        m_FireTimeEvent = new FireTimeEvent(this,heartbeat);
    }

    public void connectObserver() {
        this.addObserver(m_SimFrame);
        this.addObserver(m_SimFrame.getSimPanelInstance());
        this.addObserver(m_SimFrame.getSimPanelInstance().getEnginePanelInstance());
        this.addObserver(m_SimFrame.getSimPanelInstance().getInCabinRoomPanelInstance().getInCabinRoomFloorsPanelInstance());
        this.addObserver(m_SimFrame.getSimPanelInstance().getInCabinRoomPanelInstance());
        this.addObserver(m_SimFrame.getSimPanelInstance().getAnimationPanelInstance());
        this.addObserver(m_SimFrame.getSimPanelInstance().getJobPanelInstance());
        this.addObserver(m_SimData);
        System.out.println("countObservers: "+countObservers());
        m_SimData.addObserver(this);
    }

    public void startSimulation() {
        m_SimData.fireInitData();
        m_FireTimeEvent.start();
        System.out.println("Start Simulation");
    }

    @Override
    public void update(Observable o, Object arg) {
        //throw new UnsupportedOperationException("Not supported yet.");
        System.out.println("Update in Sim: " +arg.toString());
        System.out.println("Now notify Childs.");
        this.setChanged();
        this.notifyObservers(arg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void windowOpened(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void windowClosed(WindowEvent e) {
        m_FireTimeEvent.interrupt();
        m_SimData = null;
        m_SimFrame = null;
        m_NotifyRemove.notifyRemove(this);
    }

    @Override
    public void windowIconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void windowActivated(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void userActionAlarm() {
        System.out.println("Function in Sim: actionButtonAlarm");
        setChanged();
        UserActionData userActionData = new UserActionData();
        userActionData.alarm = true;
        userActionData.isSetAlarm = true;
        notifyObservers(userActionData);
    }

    @Override
    public void userActionHand() {
        System.out.println("Function in Sim: actionButtonHand");
        setChanged();
        UserActionData userActionData = new UserActionData();
        userActionData.hand = true;
        userActionData.isSetHand = true;
        notifyObservers(userActionData);
    }

    @Override
    public void userActionDoorClose() {
        System.out.println("Function in Sim: actionButtonDoorClose");
        setChanged();
        UserActionData userActionData = new UserActionData();
        userActionData.doorClose = true;
        userActionData.isSetDoorClose = true;
        notifyObservers(userActionData);
    }

    @Override
    public void userActionEmergencyHold() {
        System.out.println("Function in Sim: actionButtonEmergencyHold");
        setChanged();
        UserActionData userActionData = new UserActionData();
        userActionData.emergencyhold = true;
        userActionData.isSetEmergencyhold = true;
        notifyObservers(userActionData);
    }

    @Override
    public void userActionAirCondition() {
        System.out.println("Function in Sim: actionButtonAirCondition");
        setChanged();
        UserActionData userActionData = new UserActionData();
        userActionData.airCondition = true;
        userActionData.isSetAirCondition = true;
        notifyObservers(userActionData);
    }

    @Override
    public void userActionDoorOpen() {
        System.out.println("Function in Sim: actionButtonDoorOpen");
        setChanged();
        UserActionData userActionData = new UserActionData();
        userActionData.doorOpen = true;
        userActionData.isSetDoorOpen = true;
        notifyObservers(userActionData);
    }

    @Override
    public void userActionToFloor(int floor) {
        System.out.println("Function in Sim: userActionToFloor");
        setChanged();
        UserActionData userActionData = new UserActionData();
        userActionData.floor = floor;
        userActionData.isSetFloor = true;
        notifyObservers(userActionData);
    }
/*
    //TODO: implement this
    private int getNextJobDirection() {
        if( m_last_direction == last_direction_unset ) {
            if( m_SimData.getFloorFromCurrentJobData() > m_SimData.getCurrentFloor() ) {
                return direction_up;
            }
            else {
                return direction_down;
            }
        }
        else if( m_SimData.hasJob() ) {
            m_SimData.getCurrentFloor() 
        }
        return state_up;
    }
*/
    private void state_unset() {
        System.out.println("state_unset");
        m_State = state_doorClose;
    }

    private void state_doorClose() {
        System.out.println("state_doorClose");
        int needed_beats = (time_doorClose/heartbeat);
        if(needed_beats == time_doorCloseCounter) {
            time_doorCloseCounter = 0;
            //m_State = getNextJobDirection();
        } else {
            ++time_doorCloseCounter;
        }
    }

    private void state_doorOpen() {
        System.out.println("state_doorOpen");
        int needed_beats = (time_doorOpen/heartbeat);
        if(needed_beats == time_doorOpenCounter) {
            time_doorOpenCounter = 0;
            if(m_SimData.hasJob()) {
                //m_State = getNextJobDirection();
            }
            else {
                m_State = state_waitforjob;
            }
        } else {
            ++time_doorOpenCounter;
        }
    }

    private void state_doorEmergencyhold() {
        System.out.println("state_doorEmergencyhold");
    }

    private void state_doorHand() {
        System.out.println("state_doorHand");
    }

    private void state_down() {
        System.out.println("state_unset");
    }

    private void state_up() {
        System.out.println("state_up");
    }

    private void state_waitforjob() {
        System.out.println("state_waitforjob");
    }

    @Override
    public void timeEvent(Object Arg) {
        System.out.println("tea time over...");

        switch(m_State) {
            case state_unset:
                state_unset();
                break;
            case state_doorClose:
                state_doorClose();
                break;
            case state_doorOpen:
                state_doorOpen();
                break;
            case state_doorEmergencyhold:
                state_doorEmergencyhold();
                break;
            case state_doorHand:
                state_doorHand();
                break;
            case state_down:
                state_down();
                break;
            case state_up:
                state_up();
                break;
            case state_waitforjob:
                state_waitforjob();
                break;
        }

        //deactivate pseudo
        if(m_SimData.hasJob() &&true ) {
            
            //TODO: Remove this, this is only for testing purpose
            int floor = m_SimData.getFloorFromCurrentJobData();

            UserActionData uad = new UserActionData();
            uad.isSetFloor = true;
            uad.floor = floor;
            uad.floorEnable = true;

            //TODO: Remove this, this is only for testing purpose
            m_SimData.deleteCurrentJobData();
            
            setChanged();
            notifyObservers(uad);

            //TODO: Remove this, this is only for testing purpose
            m_SimData.fireJobDatas();
            CurrentFloorData cfd = new CurrentFloorData();
            cfd.floor = floor;
            setChanged();
            notifyObservers(cfd);
            //System.out.println("===FIRE CFD!: "+cfd.toString());
        }
    }
}
