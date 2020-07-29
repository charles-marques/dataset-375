package jliftsim;

/**
 *
 * @author Bernard Ladenthin (bernard@ladenthin.net)
 * @version 0.1a
 */
public class FireTimeEvent extends Thread {

    int heartbeat;
    TimeEvent te;

    public FireTimeEvent(TimeEvent te, int heartbeat) {
        this.te = te;
        this.heartbeat = heartbeat;
    }

    @Override
    public void run() {
        for(;;) {
            try {
                sleep(heartbeat);
                te.timeEvent("heartbeat");
            } catch(InterruptedException ex) {
                return;
            }
        }
    }
}
