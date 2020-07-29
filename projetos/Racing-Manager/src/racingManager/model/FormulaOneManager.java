package racingManager.model;

public class FormulaOneManager {
	private String playerName;
	
	private int cash;
	private String teamName;
	private Driver driverOne;
	private Driver driverTwo;

	public FormulaOneManager(String playerName, String teamName, Driver driverOne,
			Driver driverTwo) {
		this.playerName = playerName;
		this.teamName = teamName;
		this.driverOne = driverOne;
		this.driverTwo = driverTwo;
		// TODO: Set money with help of difficulty level (?)
		this.cash = 0;
	}

	protected void setDriverOne(Driver driverOne) {
		this.driverOne = driverOne;
	}

	protected void setDriverTwo(Driver driverTwo) {
		this.driverTwo = driverTwo;
	}

	protected String getName() {
		return playerName;
	}

	protected int getCash() {
		return cash;
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	protected Driver getDriverOne() {
		return driverOne;
	}

	protected Driver getDriverTwo() {
		return driverTwo;
	}
}