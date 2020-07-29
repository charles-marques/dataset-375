import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

//import ButtonGrid.ButtonHandler;

public class BattleshipController {
	/**
	 * 
	 */
	private JPanel panel = new JPanel();

	/**
	 * 
	 */
	private JButton[][] grid;

	/**
	 * 
	 */
	public static final String[] LETTERS = { " ", "A", "B", "C", "D", "E", "F",
			"G", "H", "I", "J" };

	/**
	 * 
	 */
	public static final String[] NUMBERS = { " ", "1", "2", "3", "4", "5", "6",
			"7", "8", "9", "10" };

	/**
	 * 
	 * @param width
	 *            width of grid
	 * @param height
	 *            height of grid
	 */
	public void createBoard() {
		panel.setLayout(new GridLayout(BattleshipController.LETTERS.length,
				BattleshipController.NUMBERS.length));
		grid = new JButton[BattleshipController.LETTERS.length][BattleshipController.NUMBERS.length];
		for (int y = 0; y < BattleshipController.NUMBERS.length; y++) {

			for (int x = 0; x < BattleshipController.LETTERS.length; x++) {

				if (x != 0 && y != 0) {
					grid[x][y] = new JButton();
					panel.add(grid[x][y]);
					grid[x][y].addActionListener(new ButtonHandler());
				}
				if (x == 0) {
					if (y != 0) {
						JTextField t = new JTextField(NUMBERS[y]);
						t.setEditable(false);
						t.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
						panel.add(t);
					} else {
						JTextField t = new JTextField();
						t.setEditable(false);
						panel.add(t);
					}
				} else if (y == 0) {
					JTextField t = new JTextField(LETTERS[x]);
					t.setEditable(false);
					t.setHorizontalAlignment((int) JFrame.CENTER_ALIGNMENT);
					panel.add(t);
				}
			}
		}
		// panel.setSize(250, 250);
		panel.setVisible(true);
	}

	/**
	 * 
	 * @return a reference to the button grid
	 */
	public final JPanel getBoard() {
		return panel;
	}

	/**
	 * 
	 */
	public final boolean isHit() {
		return false;
	}

	/**
	 * 
	 *
	 */
	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (isHit()) {
				Color fg = Color.RED;
				// grid[4][5].setBackground(fg);
			} else {
				Color fg = Color.BLACK;
				// grid[5][4].setBackground(fg);
			}
		}

	}
}
