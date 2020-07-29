
public class LightBulb {
    private boolean On;
    
    LightBulb(boolean On) {
        this.On = On;
    }

    boolean isOn() {
        return On;
    }
    
    boolean isOff() {
        return !On;
    }
    
    void turnOn() {
        On = true;
    }
    
    void turnOff() {
        On = false;
    }

    void flip() {
        On = !On;
    }
}

