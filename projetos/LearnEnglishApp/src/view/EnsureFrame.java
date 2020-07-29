/*
 * EnsureFrame.java 1.2 2012/10/18
 *
 * Copyright (c) 2012 Northeastern University Software Engineering College
 * Software International 1001 Group Three
 *
 * All rights reserved.
 *
 */

package view;

/**
 * EnsureFrame - The ensure frame of the learnEngilsh system.
 * <p>
 * General ensure frame of the system.
 * 
 * @author Luo Yaoshen
 * @version 1.2
 * @see view.AbstractFrame
 */

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.apache.commons.io.FilenameUtils;

public class EnsureFrame extends AbstractFrame {
	public static final int CANCEL_OPTION = 23947;
	public static final int YES_OPTION = 79237;
	public static final int NO_OPTION = 83874;

	private static EnsureFrame frame;
	private JButton firstButton, secondButton, thirdButton;
	private JLabel statementLabel;
	private JPanel buttonPanel;

	private static int optionValue;
	private static boolean isClicked;

	/**
	 * To get the ensure frame.
	 * 
	 * @return the ensure frame
	 */
	public static EnsureFrame getEnsureFrame() {
		if (frame == null) {
			frame = new EnsureFrame();
		}
		return frame;
	}

	/**
	 * To show the message dialog
	 * 
	 * @param message
	 *            the message of the dialog
	 */
	public static void showMessageDialog(String message) {
		final EnsureFrame ensure = EnsureFrame.getEnsureFrame();

		ensure.getThirdButton().setText("OK");
		ensure.getSecondButton().setText("");
		ensure.getFirstButton().setText("");
		ensure.getStatementLabel().setText(message);
		ensure.setVisible(true);

		ensure.getThirdButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ensure.setVisible(false);
				ensure.dispose();
			}
		});
		
		ensure.getThirdButton().addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				ensure.getThirdButton().setForeground(new Color(73, 143, 42));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				ensure.getThirdButton().setForeground(Color.black);
			}
		});
	}

	/**
	 * To show the confirm dialog
	 * 
	 * @param message
	 *            the message of the confirm dialog
	 * @return the number of the message of the confirm dialogs
	 */
	public static int showConfirmDialog(String message) {
		final EnsureFrame ensure = EnsureFrame.getEnsureFrame();
		ensure.getThirdButton().setText("Save");
		ensure.getSecondButton().setText("Cancel");
		ensure.getFirstButton().setText("Don't save");
		ensure.getStatementLabel().setText(message);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ensure.setVisible(true);
			}
		});

		ensure.setVisible(true);
		ensure.getThirdButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				optionValue = YES_OPTION;
				isClicked = true;
				ensure.setVisible(false);
				ensure.dispose();
			}
		});

		ensure.getSecondButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				optionValue = CANCEL_OPTION;
				isClicked = true;
				ensure.setVisible(false);
				ensure.dispose();
			}
		});

		ensure.getFirstButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				optionValue = NO_OPTION;
				isClicked = true;
				ensure.setVisible(false);
				ensure.dispose();
			}
		});

		while (!isClicked) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {

			}
		}
		return optionValue;
	}

	/**
	 * Constructs an instance of the ensure frame.
	 */
	public EnsureFrame() {
		super(FilenameUtils.separatorsToSystem("resource/EnsureFrame2.png"));
		initTextLabel();
		initButtonPanel();
		removeExitAndShrinkButton();
	}

	/**
	 * To get the statement label.
	 * 
	 * @return the statement label of the frame
	 */
	private JLabel getStatementLabel() {
		return statementLabel;
	}

	/**
	 * To get the first button of the frame
	 * 
	 * @return the first button of the frame
	 */
	private JButton getFirstButton() {
		return firstButton;
	}

	/**
	 * To get the second button of the frame
	 * 
	 * @return the second button of the frame
	 */
	private JButton getSecondButton() {
		return secondButton;
	}

	/**
	 * To get the third button of the frame
	 * 
	 * @return the third button of the frame
	 */
	private JButton getThirdButton() {
		return thirdButton;
	}

	/**
	 * Initialize the text label of the ensure frame.
	 */
	private void initTextLabel() {
		statementLabel = new JLabel();
		statementLabel.setOpaque(false);
		statementLabel.setBounds(300, 315, 360, 40);
		statementLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(statementLabel);
	}

	/**
	 * Initialize the button panel.
	 */
	private void initButtonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);

		firstButton = new JButton();
		secondButton = new JButton();
		thirdButton = new JButton();

		firstButton.setBorderPainted(false);
		secondButton.setBorderPainted(false);
		thirdButton.setBorderPainted(false);
		firstButton.setContentAreaFilled(false);
		secondButton.setContentAreaFilled(false);
		thirdButton.setContentAreaFilled(false);

		buttonPanel.add(firstButton);
		buttonPanel.add(secondButton);
		buttonPanel.add(thirdButton);

		buttonPanel.setBounds(270, 400, 400, 150);
		getContentPane().add(buttonPanel);
	}

	public static void main(String args[]) {
		int a = EnsureFrame
				.showConfirmDialog("Do you want to save the changes ?");
		System.out.println(a);
	}
}
