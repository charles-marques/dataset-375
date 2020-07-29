import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class BattleshipView {
	/**
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) {

		// new ButtonGrid(11, 11);
		JFrame f = new JFrame("BattleShip");

		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Game");
		menuBar.add(menu);
		JMenuItem m = new JMenu("New Game");
		menu.add(m);
		GameListener g = new GameListener();
		JMenuItem pvp = new JMenuItem("Player vs Player");
		pvp.addActionListener(g);
		m.add(pvp);

		f.setJMenuBar(menuBar);

		BattleshipModel p1 = new BattleshipModel();
		p1.setMyBoard();

		BattleshipModel p2 = new BattleshipModel();
		p2.setMyBoard();

		// ButtonGrid b = new ButtonGrid();
		// ButtonGrid c = new ButtonGrid();

		f.setLayout(new GridLayout());

		f.add(p1.getMyBoard());
		f.add(p2.getMyBoard());

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		// f.setSize(500, 250);
		f.setVisible(true);

	}
}
