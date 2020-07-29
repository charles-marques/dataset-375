/*
 * LoginFrame.java 1.2 2012/10/18
 * 
 * Copyright (c) 2012 Northeastern University Software Engineering College
 * Software International 1001 Group Three
 * 
 * All rights reserved.
 */

package view;

/**
 * LoginFrame - The login frame of the learnEnglish system.
 * <p>
 * Generate the login frame.
 * 
 * @author Luo Yaoshen
 * @version 1.2
 * @see view.AbstractFrame
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.io.FilenameUtils;

public class LoginFrame extends AbstractFrame {
	private JLabel titleLabel, idLabel, summaryLabel;
	private JPanel summaryPanel;
	private JComboBox userComboBox;
	private JButton loginButton, deleteButton;

	/**
	 * To generate an instance of the login frame
	 */
	public LoginFrame() {
		super(FilenameUtils.separatorsToSystem("resource/LoginFrame.png"),
				new ImageIcon(Toolkit.getDefaultToolkit().getImage(
						"resource/x_white.png")), 
				new ImageIcon(Toolkit.getDefaultToolkit().getImage(
						"resource/shrink_white.png")));
		initTitleLabel();
		initDeleteButton();
		initUserComboBox();
		initLoginButton();
		initSummaryLabel();
		this.setVisible(true);
		createBufferStrategy(2);
	}

	/**
	 * @return the deleteButton
	 */
	public JButton getDeleteButton() {
		return deleteButton;
	}

	/**
	 * @return the titleLabel
	 */
	public JLabel getTitleLabel() {
		return titleLabel;
	}

	/**
	 * @return the userComboBox
	 */
	public JComboBox getUserComboBox() {
		return userComboBox;
	}

	/**
	 * @return the summaryPanel
	 */
	public JPanel getSummaryPanel() {
		return summaryPanel;
	}

	/**
	 * @return the loginButton
	 */
	public JButton getLoginButton() {
		return loginButton;
	}

	/**
	 * Initialize the summary panel of the login frame
	 */
	private void initSummaryPanel() {
		summaryPanel = new JPanel();
		summaryPanel.setOpaque(false);
	}

	/**
	 * Initialize the summary label of the login frame
	 */
	private void initSummaryLabel() {
		ImageIcon icon = new ImageIcon(FilenameUtils.separatorsToSystem("resource/Summary.png"));
		summaryLabel = new JLabel(icon);
		summaryLabel.setBounds(0, 50, 450, 600);
		getContentPane().add(summaryLabel);
	}

	/**
	 * Initialize the title label of the login frame
	 */
	private void initTitleLabel() {
		titleLabel = new JLabel("English Tutor");
		titleLabel.setBounds(610, 71, 275, 204);
		getContentPane().add(titleLabel);
	}

	/**
	 * Initialize the delete button of the login frame
	 */
	private void initDeleteButton() {
		deleteButton = new JButton("Delete");
		deleteButton.setBounds(820, 335, 105, 40);
		deleteButton.setOpaque(false);
		deleteButton.setBackground(new Color(0, true));
		deleteButton.setFont(new Font("Arial", Font.BOLD, 20));
		deleteButton.setForeground(new Color(255, 251, 140));
		deleteButton.setContentAreaFilled(false);
		deleteButton.setBorderPainted(false);
		deleteButton.setToolTipText("Delete the selected account.");
		getContentPane().add(deleteButton);
		deleteButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				//deleteButton.setFont(new Font("Arial", Font.BOLD, 22));
				deleteButton.setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				//deleteButton.setFont(new Font("Arial", Font.BOLD, 20));
				deleteButton.setForeground(new Color(255, 251, 140));
			}
		});
	}

	/**
	 * Initialize the user comboBox of the login frame
	 */
	private void initUserComboBox() {
		idLabel = new JLabel("ID");
		Font newButtonFont = new Font("Arial", Font.BOLD, 20);
		idLabel.setFont(newButtonFont);
		idLabel.setForeground(Color.WHITE);
		idLabel.setBounds(580, 330, 100, 50);
		getContentPane().add(idLabel);

		userComboBox = new JComboBox();
		userComboBox.setEditable(true);
		userComboBox.setBounds(609, 345, 197, 20);
		getContentPane().add(userComboBox);
		
	}

	/**
	 * Initialize the login button of the login frame
	 */
	private void initLoginButton() {

		loginButton = new JButton("LOG IN");
		loginButton.setBorderPainted(false);
		Font newButtonFont = new Font("Arial", Font.BOLD, 30);
		loginButton.setFont(newButtonFont);
		loginButton.setOpaque(false);
		loginButton.setForeground(new Color(255, 251, 140));
		loginButton.setBounds(730, 438, 200, 46);
		loginButton.setToolTipText("Login the English Tutor");
		loginButton.setBackground(new Color(0, true));
		getContentPane().add(loginButton);
		loginButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				loginButton.setForeground(Color.yellow);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				loginButton.setForeground(new Color(255, 251, 140));
			}
		});
	}

	/**
	 * To add an account into the comboBox
	 * 
	 * @param userName the name of the account
	 */
	public void addAccountName(String userName) {
		userComboBox.addItem(userName);
	}

	public static void main(String args[]) {
		LoginFrame test = new LoginFrame();
		test.addAccountName("Harry");
		test.addAccountName("Eric");
		test.setVisible(true);
	}
}
