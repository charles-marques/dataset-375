package jliftsim.data;

/**
 *
 * @author Bernard Ladenthin (bernard@ladenthin.net)
 * @version 0.1a
 */
public class EngineData extends Object {

    public int currentPower;
    public int currentSpeed;
    public int currentLoad;
    public int currentAcceleration;

    public int maxPower;
    public int maxSpeed;
    public int maxLoad;
    public int maxAcceleration;

    public EngineData(
        int maxPower,
        int maxSpeed,
        int maxLoad,
        int maxAcceleration
    ) {
        this.maxPower = maxPower;
        this.maxSpeed = maxSpeed;
        this.maxLoad = maxLoad;
        this.maxAcceleration = maxAcceleration;

        currentPower = 0;
        currentSpeed = 0;
        currentLoad = 0;
        currentAcceleration = 0;
    }

    public EngineData( ) { }
}
