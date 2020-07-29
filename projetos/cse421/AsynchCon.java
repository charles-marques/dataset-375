
public class AsynchCon extends Prisoner {

    private boolean haveTurnedLightOn;
    private int count;
    
    public AsynchCon(int ID) {
        super(ID);
        this.haveTurnedLightOn = false;
    }

    public String strategyDescription() {
        return "Everyone turns on light exactly once; Only prisoner with ID=0 turns it off, keeps count, and eventualy returns true.";
    }

    public boolean visit(int day, LightBulb light) {
        if (!this.haveTurnedLightOn && light.isOff()) {
            light.turnOn();
            this.haveTurnedLightOn = true;
        }
        if (ID == 0 && light.isOn()) {
            this.count++;
            light.turnOff();
            if (this.count == Warden.SIZE)
            {
                return true;
            }
        }
        return false;
    }
}
