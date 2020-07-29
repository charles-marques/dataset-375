package jliftsim;

import java.util.Collections;
import java.util.Observable;
import java.util.Observer;
import jliftsim.data.*;

/**
 *
 * @author Bernard Ladenthin (bernard@ladenthin.net)
 * @version 0.1a
 */
public class SimData extends Observable implements Observer {

    private EngineData m_EngineData;
    private FloorData m_FloorData;
    private CurrentFloorData m_CurrentFloorData;
    private NameData m_NameData;
    private JobDataList m_JobDatas;

    private              int m_last_direction;
    public  static final int last_direction_unset = 0;
    public  static final int direction_up = 1;
    public  static final int direction_down = 2;

    public SimData (
         String name
        ,int floors
        ,int maxPower
        ,int maxSpeed
        ,int maxLoad
        ,int maxAcceleration
    ) {
        m_last_direction = last_direction_unset;
        m_EngineData = new EngineData();
        m_FloorData = new FloorData();
        m_CurrentFloorData = new CurrentFloorData(0);
        m_NameData = new NameData();
        m_JobDatas = new JobDataList();

        m_NameData.name = name;

        m_FloorData.floors = floors;

        m_EngineData.maxPower = maxPower;
        m_EngineData.maxSpeed = maxSpeed;
        m_EngineData.maxLoad = maxLoad;
        m_EngineData.maxAcceleration = maxAcceleration;
    }

    public void fireJobDatas() {
        System.out.println("fireJobDatas");
        setChanged();
        notifyObservers(m_JobDatas);
    }

    public void fireNameData() {
        System.out.println("fireNameData");
        setChanged();
        notifyObservers(m_NameData);
    }

    public void fireFloorData() {
        System.out.println("fireFloorData");
        setChanged();
        notifyObservers(m_FloorData);
    }

    public void fireCurrentFloorData() {
        System.out.println("fireCurrentFloorData");
        setChanged();
        notifyObservers(m_CurrentFloorData);
    }

    public void fireEngineData() {
        System.out.println("fireEngineData");
        setChanged();
        notifyObservers(m_EngineData);
    }

    public void fireInitData() {
        fireJobDatas();
        fireNameData();
        fireFloorData();
        fireCurrentFloorData();
        fireEngineData();
    }

    public boolean isAllwaysInList(int floor) {
        for(JobData jobData : m_JobDatas) {
            if(jobData.floor == floor) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidFloor(int floor) {
        if( floor <= m_FloorData.floors && floor >= 0) {
            return true;
        }
        return false;
    }

    public boolean validateAndAddFloor(JobData jobData) {
        if(
              !isAllwaysInList(jobData.floor)
            && isValidFloor(jobData.floor)
            && m_CurrentFloorData.floor != jobData.floor
        ) {
            m_JobDatas.add(jobData);
            return true;
        }
        return false;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Update in SimData: " +arg.toString());
        if(arg instanceof UserActionData ) {
            UserActionData userActionData = (UserActionData)arg;
            ////////////////////
            if( userActionData.isSetFloor && userActionData.floorEnable == false) {
                System.out.println("isSetFloor gesetzt");
                JobData job = new JobData(
                    userActionData.floor
                );


                if( validateAndAddFloor(job) ) {
                    sortJobData();
                    fireJobDatas();
                }
                else {
                    userActionData.floorEnable = true;
                    setChanged();
                    notifyObservers(userActionData);
                }
            }
            ////////////////////
            if( userActionData.isSetAirCondition ) {
                toggleCurrentAirCondition();
                fireJobDatas();
            }
            ////////////////////
            if( userActionData.isSetAlarm ) {
                enableCurrentAlarm();
                fireJobDatas();
            }
            ////////////////////
            if( userActionData.isSetDoorClose ) {
                enableCurrentDoorClose();
                fireJobDatas();
            }
            ////////////////////
            if( userActionData.isSetDoorOpen ) {
                enableCurrentDoorOpen();
                fireJobDatas();
            }
            ////////////////////
            if( userActionData.emergencyhold ) {
                enableCurrentEmergencyhold();
                fireJobDatas();
            }
            ////////////////////
            if( userActionData.hand ) {
                enableCurrentHand();
                fireJobDatas();
            }
        } else if(arg instanceof CurrentFloorData ) {
            CurrentFloorData cfd = (CurrentFloorData)arg;
            m_CurrentFloorData.floor = cfd.floor;
        }
    }

    public void toggleCurrentAirCondition() {
        if( !m_JobDatas.isEmpty()) {
            m_JobDatas.get(0).toggleAirCondition();
        }
    }

    public boolean getCurrentAirCondition() {
        if( !m_JobDatas.isEmpty()) {
            return m_JobDatas.get(0).airCondition;
        }
        throw new RuntimeException("no Job in List");
    }

    private void enableCurrentAlarm() {
        if( !m_JobDatas.isEmpty()) {
            m_JobDatas.get(0).alarm = true;
        }
    }

    private void enableCurrentDoorClose() {
        if( !m_JobDatas.isEmpty()) {
            m_JobDatas.get(0).doorClose = true;
        }
    }

    private void enableCurrentDoorOpen() {
        if( !m_JobDatas.isEmpty()) {
            m_JobDatas.get(0).doorOpen = true;
        }
    }

    private void enableCurrentEmergencyhold() {
        if( !m_JobDatas.isEmpty()) {
            m_JobDatas.get(0).emergencyhold = true;
        }
    }

    private void enableCurrentHand() {
        if( !m_JobDatas.isEmpty()) {
            m_JobDatas.get(0).hand = true;
        }
    }

    public boolean hasJob() {
        if( !m_JobDatas.isEmpty()) {
            return true;
        }
        return false;
    }

    public void deleteCurrentJobData() {
        if( !m_JobDatas.isEmpty()) {
            m_JobDatas.remove(0);
        }
    }

    //TODO: Remove, only for testing purpose
    int getFloorFromCurrentJobData() {
        if( !m_JobDatas.isEmpty()) {
            return m_JobDatas.get(0).floor;
        }
        return 0;
    }

    int getCurrentFloor() {
        return m_CurrentFloorData.floor;
    }

    public void setLastDirection(int direction) {
        m_last_direction = direction;
    }

    private void sortJobData() {
        JobDataList newJobDatas = new JobDataList();
        JobDataList higherJobDatas = new JobDataList();
        JobDataList lowerJobDatas = new JobDataList();
        //add first job
        if(m_JobDatas.size() > 0) {
            newJobDatas.add(m_JobDatas.remove(0));
        }
        else {
            System.out.println("No jobs to sort");
            return;
        }
        //if no information about last direction exist, sort upwards
        if( m_last_direction==last_direction_unset ) {
            m_last_direction=direction_up;
        }

        for(JobData jobData : m_JobDatas) {
            //first job removed, there should be no more job with the same floor
            //in the list
            if(jobData.floor == m_CurrentFloorData.floor) {
                //ignore false jobs, nut tell the GUI a free button
                UserActionData uad = new UserActionData();
                uad.isSetFloor = true;
                uad.floor = jobData.floor;
                uad.floorEnable = true;
                setChanged();
                notifyObservers(uad);
                //throw new IllegalStateException("Some Job equals current job");
            }

            if( jobData.floor > m_CurrentFloorData.floor ) {
                higherJobDatas.add(jobData);
            }
            else if(jobData.floor < m_CurrentFloorData.floor) {
                lowerJobDatas.add(jobData);
            }
        }

        System.out.println("Higher Jobs:");
        System.out.println(higherJobDatas.toString());
        System.out.println("Lower Jobs:");
        System.out.println(lowerJobDatas.toString());

        Collections.sort(higherJobDatas);
        Collections.sort(lowerJobDatas);
        Collections.reverse(lowerJobDatas);

        if(m_last_direction == direction_up) {
            newJobDatas.addAll(higherJobDatas);
            newJobDatas.addAll(lowerJobDatas);
        }
        else if(m_last_direction == direction_down) {
            newJobDatas.addAll(lowerJobDatas);
            newJobDatas.addAll(higherJobDatas);
        }
        m_JobDatas = newJobDatas;
        fireJobDatas();
    }
}
