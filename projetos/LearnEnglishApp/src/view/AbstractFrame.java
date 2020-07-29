/*
 * AbstarctFrame.java 1.2 2012/10/18
 *
 * Copyright (c) 2012 Northeastern University Software Engineering College
 * Software International 1001 Group Three
 *
 * All rights reserved.
 *
 */

package view;

/**
 * AbstractFrame - The abstract frame of the frames in the learnEngilsh system.
 * <p>
 * It provides the general methods of the frames in the learnEnglish system. The class which
 * wants to use it should extends it.
 * 
 * @author Luo Yaoshen
 * @version 1.2
 * @see Javax.Swing.JFram
 * @see model.Account
 */

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.apache.commons.io.FilenameUtils;

import controller.MainController;

public abstract class AbstractFrame extends JFrame {
	private Cursor cursor;
	private ImageIcon backgroundImageIcon;
	private ImagePanel contentPane;
	private int lastx, lasty;
	protected JButton exitButton, shrinkButton;
	protected ImageIcon exitImage, exitEnteredImage, shrinkImage,
			shrinkEnteredImage;

	/**
	 * Constructs an instance of frame.
	 * 
	 * @param filePath
	 *            - the file path of the backgroundIamgeIcon.
	 */
	protected AbstractFrame(String filePath) {
		super();
		exitImage = new ImageIcon(
				FilenameUtils.separatorsToSystem("resource/x_black.png"));
		exitEnteredImage = new ImageIcon(
				FilenameUtils.separatorsToSystem("resource/x_red.png"));
		shrinkImage = new ImageIcon(
				FilenameUtils.separatorsToSystem("resource/shrink_black.png"));
		shrinkEnteredImage = new ImageIcon(
				FilenameUtils.separatorsToSystem("resource/shrink_green.png"));
		initialize(filePath);
	}

	
	protected AbstractFrame(String filePath, ImageIcon exitImage, ImageIcon shrinkImage) {
		super();

		this.exitImage = exitImage;

		// Initialize the exit enter image
		exitEnteredImage = new ImageIcon(
				FilenameUtils.separatorsToSystem("resource/x_red.png"));

		this.shrinkImage = shrinkImage;

		// Initialize the shrink enter image
		shrinkEnteredImage = new ImageIcon(
				FilenameUtils.separatorsToSystem("resource/shrink_green.png"));

		initialize(filePath);
	}
	
	/**
	 * Constructs an instance of the frame.
	 * 
	 * @param filePath
	 *            - the file path of the backgroundImage
	 * @param exitWidth
	 *            - the exit width of the frame
	 * @param exitHeight
	 *            - the exit height of the frame
	 * @param shrinkWidth
	 *            - the shrink width of the frame
	 * @param shrinkHeight
	 *            - the shrink height of the frame
	 */
	protected AbstractFrame(String filePath, int exitWidth, int exitHeight,
			int shrinkWidth, int shrinkHeight) {
		super();

		// Initialize the exit image
		exitImage = new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("resource/x_black.png")
				.getScaledInstance(exitWidth, exitHeight, Image.SCALE_DEFAULT));

		// Initialize the exit enter image
		exitEnteredImage = new ImageIcon(Toolkit.getDefaultToolkit()
				.getImage("resource/x_red.png")
				.getScaledInstance(exitWidth, exitHeight, Image.SCALE_DEFAULT));

		// Initialize the shrink image
		shrinkImage = new ImageIcon(Toolkit
				.getDefaultToolkit()
				.getImage("resource/shrink_black.png")
				.getScaledInstance(shrinkWidth, shrinkHeight,
						Image.SCALE_DEFAULT));

		// Initialize the shrink enter image
		shrinkEnteredImage = new ImageIcon(Toolkit
				.getDefaultToolkit()
				.getImage("resource/shrink_green.png")
				.getScaledInstance(shrinkWidth, shrinkHeight,
						Image.SCALE_DEFAULT));

		initialize(filePath);
	}

	/**
	 * Return the exitButton of the frame.
	 * 
	 * @return the exitButton
	 */
	public JButton getExitButton() {
		return exitButton;
	}

	/**
	 * Return the shrinkButton of the frame.
	 * 
	 * @return the shrinkButton
	 */
	public JButton getShrinkButton() {
		return shrinkButton;
	}

	/**
	 * Set the size of the button.
	 * 
	 * @param exitWidth
	 *            the width of the exit button
	 * @param exitHeight
	 *            the height of the exit button
	 * @param shrinkWidth
	 *            the width of the shrink button
	 * @param shrinkHeight
	 *            the height of the shrink button
	 */
	protected void setButtonSize(int exitWidth, int exitHeight,
			int shrinkWidth, int shrinkHeight) {
	}

	/**
	 * @param exitImage
	 *            the exitImage to set
	 */
	public void setExitImage(ImageIcon exitImage) {
		this.exitImage = exitImage;
	}

	/**
	 * @param exitEnteredImage
	 *            the exitEnteredImage to set
	 */
	public void setExitEnteredImage(ImageIcon exitEnteredImage) {
		this.exitEnteredImage = exitEnteredImage;
	}

	/**
	 * @param shrinkImage
	 *            the shrinkImage to set
	 */
	public void setShrinkImage(ImageIcon shrinkImage) {
		this.shrinkImage = shrinkImage;
	}

	/**
	 * @param shrinkEnteredImage
	 *            the shrinkEnteredImage to set
	 */
	public void setShrinkEnteredImage(ImageIcon shrinkEnteredImage) {
		this.shrinkEnteredImage = shrinkEnteredImage;
	}

	/**
	 * Initialize the background image of the frame.
	 * 
	 * @param filePath
	 *            - the file path of the background image
	 */
	private void initialize(String filePath) {
		setUndecorated(true);
		setBackground(new Color(0, true));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseMotionListener(new FrameMouseListener());

		lastx = lasty = -1;

		initBackgroundImage(filePath);
		initBounds();
		initContentPane();
		initTitlePanel();
		initCursor();
		setLocationRelativeTo(null);
	}

	/**
	 * Initialize the background image.
	 * 
	 * @param filePath
	 *            the file path of the background image
	 */
	private void initBackgroundImage(String filePath) {
		backgroundImageIcon = new ImageIcon(filePath);
	}

	/**
	 * Initialize the bounds of the frame.
	 */
	private void initBounds() {
		setBounds(0, 0, backgroundImageIcon.getIconWidth(),
				backgroundImageIcon.getIconHeight());
	}

	/**
	 * Initialize the contentPane of the frame.
	 */
	private void initContentPane() {
		contentPane = new ImagePanel(backgroundImageIcon.getImage());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	/**
	 * Initialize the cursor of the frame.
	 */
	private void initCursor() {
		ImageIcon icon = new ImageIcon(
				FilenameUtils.separatorsToSystem("resource/cursor.png"));
		cursor = Toolkit.getDefaultToolkit().createCustomCursor(
				icon.getImage(), new Point(0, 0), "cursor");
		setCursor(cursor);
	}

	/**
	 * Initialize the title panel of the frame.
	 */
	protected void initTitlePanel() {
		exitButton = new JButton(exitImage);
		exitButton.addMouseListener(new ExitButtonAdapter());
		exitButton.setToolTipText("Exit");
		exitButton.setBorderPainted(false);
		exitButton.setBackground(new Color(0, 0, 0, 0));
		exitButton.setOpaque(false);
		exitButton.setBounds(getWidth() - 50, 17, 30, 30);
		exitButton.setBorderPainted(false);
		contentPane.add(exitButton);

		shrinkButton = new JButton(shrinkImage);
		shrinkButton.addMouseListener(new ShrinkButtonAdapter());
		shrinkButton.setToolTipText("Shrink");
		shrinkButton.setBorderPainted(false);
		shrinkButton.setBackground(new Color(0, 0, 0, 0));
		shrinkButton.setOpaque(false);
		shrinkButton.setBounds(getWidth() - 100, 22, 50, 20);
		shrinkButton.setBorderPainted(false);
		contentPane.add(shrinkButton);
	}

	/**
	 * Add the exit return to the controller.
	 */
	public void addExitingReturnToMainController() {
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainController main = MainController.getMainController();
				main.getLessonController().initAllPhrases();
				main.setVisible(true);
			}
		});
	}

	/**
	 * It remove the exit button and the shrink button.
	 */
	protected void removeExitAndShrinkButton() {
		contentPane.remove(exitButton);
		contentPane.remove(shrinkButton);
	}

	/**
	 * 
	 * @author Luo Yaoshen
	 * 
	 *         The inner class of the AbstractFrame. It is the MouseListener.
	 */
	private class FrameMouseListener implements MouseMotionListener {

		/**
		 * When the frame is dragged, it moves.
		 */
		public void mouseDragged(MouseEvent e) {
			if (lastx == -1 && lasty == -1) {
				lastx = e.getLocationOnScreen().x;
				lasty = e.getLocationOnScreen().y;
			} else {
				setLocation(getX() + (e.getLocationOnScreen().x - lastx),
						getY() + (e.getLocationOnScreen().y - lasty));
				lastx = e.getLocationOnScreen().x;
				lasty = e.getLocationOnScreen().y;
			}
		}

		/**
		 * When the mouse move, it releases the position of the mouse.
		 */
		public void mouseMoved(MouseEvent e) {
			lastx = e.getLocationOnScreen().x;
			lasty = e.getLocationOnScreen().y;
		}
	}

	/**
	 * The inner class of the frame. It's the mouseAdapter.
	 * 
	 * @author Luo Yaoshen
	 */
	private class ShrinkButtonAdapter extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			setExtendedState(JFrame.ICONIFIED);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			shrinkButton.setIcon(shrinkEnteredImage);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			shrinkButton.setIcon(shrinkImage);
		}

	}

	/**
	 * The inner class of the frame. It's the mouseAdapter of the exit button.
	 * 
	 * @author Luo Yaoshen
	 * 
	 */
	private class ExitButtonAdapter extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			setVisible(false);
			dispose();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			exitButton.setIcon(exitEnteredImage);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			exitButton.setIcon(exitImage);
		}

	}

	/**
	 * The inner class of the AbstractFrame.
	 * 
	 * @author Luo Yaoshen
	 * 
	 */
	private class ImagePanel extends JPanel {
		private Image image;

		/**
		 * Constructs an instance of the ImagePanel.
		 * 
		 * @param image
		 *            the image of the panel
		 */
		public ImagePanel(Image image) {
			this.image = image;
		}

		@Override
		protected void paintComponent(Graphics g) {
			g.drawImage(image, 0, 0, null);
		}
	}
}
