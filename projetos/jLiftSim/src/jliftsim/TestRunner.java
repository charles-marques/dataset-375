package jliftsim;

import jliftsim.data.UserActionData;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Bernard Ladenthin (bernard@ladenthin.net)
 * @version 0.1a
 */
public class TestRunner {
    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main(TestRunner.class.getName());
    }

    @Test
    public void SimDataJobTest() throws Exception {
        String name = "test";
        int floors = 100;
        int maxPower = 100;
        int maxSpeed = 100;
        int maxLoad = 100;
        int maxAcceleration = 100;
        SimData data = new SimData(name, floors, maxPower, maxSpeed, maxLoad,
            maxAcceleration);

        assertFalse(data.hasJob());
        UserActionData uad = new UserActionData();
        uad.floor = 5;
        uad.isSetFloor = true;
        assertTrue(data.validateAndAddFloor(uad));
        assertFalse(data.validateAndAddFloor(uad));
        assertTrue(data.hasJob());

        assertEquals("same floor", uad.floor, data.getFloorFromCurrentJobData());
        assertTrue(data.isAllwaysInList(uad.floor));
        assertFalse(data.isAllwaysInList(0));

        assertTrue(data.isValidFloor(floors));
        assertTrue(data.isValidFloor(floors-1));
        assertTrue(data.isValidFloor(0));
        assertTrue(data.isValidFloor(1));
        assertFalse(data.isValidFloor(-1));
        assertFalse(data.isValidFloor(floors+1));

        boolean airCondition = data.getCurrentAirCondition();
        data.toggleCurrentAirCondition();
        assertTrue(airCondition != data.getCurrentAirCondition()) ;
    }
}
