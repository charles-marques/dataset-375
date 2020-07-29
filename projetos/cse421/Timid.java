public class Timid extends Prisoner {

    public Timid(int ID) {
        super(ID);
    }

    public String strategyDescription() {
        return "Play it very safe: Never claim that everyone has " +
            "been to room.";
    }

    public boolean visit(int day, LightBulb light) {
        return false;
    }
}
