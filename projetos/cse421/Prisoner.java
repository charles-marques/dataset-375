
abstract public class Prisoner {
    protected int ID;

    /**
     * @param ID
     *            the unique integer ID of this prisoner
     * @requires ID is unique amongst instances of (derived) class <br />
     *           prisoner ID's are consecutively assigned starting at 0
     */
    public Prisoner(int ID) {
        this.ID = ID;
    }

    /**
     * @return a one-line high-level description of the strategy
     */
    abstract public String strategyDescription();

    /**
     * @param day
     *            the count of day in which visit occurs (numbered from 0)
     * @param light
     *            the shared light bulb object which can be observed and
     *            modified during the visit
     * @alters light 
     * @return true iff the prisoner is willing to claim that all prisoners have
     *         visited the common room
     */
    abstract public boolean visit(int day, LightBulb light);
}
