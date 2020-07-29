package jliftsim;

/**
 *
 * @author Bernard Ladenthin (bernard@ladenthin.net)
 * @version 0.1a
 */
public interface UserAction {
    public void userActionAlarm();
    public void userActionHand();
    public void userActionDoorClose();
    public void userActionEmergencyHold();
    public void userActionAirCondition();
    public void userActionDoorOpen();
    public void userActionToFloor(int floor);
}
