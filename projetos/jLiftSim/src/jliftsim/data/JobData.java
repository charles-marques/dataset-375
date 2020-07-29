package jliftsim.data;

/**
 *
 * @author Bernard Ladenthin (bernard@ladenthin.net)
 * @version 0.1a
 */
public class JobData extends Object implements Comparable<JobData> {

    public int floor;
    public int load;
    public boolean alarm;
    public boolean emergencyhold;
    public boolean airCondition;
    public boolean doorOpen;
    public boolean doorClose;
    public boolean hand;

    public JobData(
        int floor,
        int load,
        boolean alarm,
        boolean emergencyhold,
        boolean airCondition,
        boolean doorOpen,
        boolean doorClose,
        boolean hand
    ) {
        this.floor = floor;
        this.load = load;
        this.alarm = alarm;
        this.emergencyhold = emergencyhold;
        this.airCondition = airCondition;
        this.doorOpen = doorOpen;
        this.doorClose = doorClose;
        this.hand = hand;
    }

    public JobData(int floor) {
        this.floor = floor;
    }

    public void toggleAirCondition() {
        this.airCondition = !this.airCondition;
    }

    public JobData( ) { }

    @Override
    public int compareTo(JobData o) {
        return floor - o.floor;
    }
}
