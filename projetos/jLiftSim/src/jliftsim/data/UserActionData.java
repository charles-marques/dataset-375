package jliftsim.data;

/**
 *
 * @author Bernard Ladenthin (bernard@ladenthin.net)
 * @version 0.1a
 */
public class UserActionData extends JobData {

    public boolean isSetFloor;
    public boolean floorEnable;
    public boolean isSetLoad;
    public boolean isSetAlarm;
    public boolean isSetEmergencyhold;
    public boolean isSetAirCondition;
    public boolean isSetDoorOpen;
    public boolean isSetDoorClose;
    public boolean isSetHand;

    public UserActionData() {
        isSetFloor = false;
        floorEnable = false;
        isSetLoad = false;
        isSetAlarm = false;
        isSetEmergencyhold = false;
        isSetAirCondition = false;
        isSetDoorOpen = false;
        isSetHand = false;
    }
}
