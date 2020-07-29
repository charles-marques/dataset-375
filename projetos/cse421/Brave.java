
public class Brave extends Prisoner {

    public Brave(int ID) {
        super(ID);
    }

    public String strategyDescription() {
        return "Each trip: just flip a coin and guess.";
    }

    public boolean visit(int day, LightBulb light) {
        if (Math.random()*Warden.SIZE < 1.0) {
            return true;
        }
        else {
            return false;
        }
    }
}
