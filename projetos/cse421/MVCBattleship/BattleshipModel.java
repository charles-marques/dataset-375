import javax.swing.JPanel;


public class BattleshipModel {
	private String name;
	private int shipsLeft;
	private Ship[] ships;
	private int shotsTaken;
	private int hitsTaken;
	private JPanel myBoard;

	public BattleshipModel() {
	}

	public BattleshipModel(String s) {
		name = s;
	}

	/**
	 * Used to set the players ships on the board.
	 */
	public void setMyBoard() {
		BattleshipController bC = new BattleshipController();
		bC.createBoard();
		myBoard = bC.getBoard();
	}

	/**
	 * Returns a reference to the player's board.
	 * 
	 * @return
	 */
	public JPanel getMyBoard() {
		return myBoard;
	}
}
