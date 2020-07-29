/*
 * MainController.java 1.1 2012/6/1
 * 
 * Copyright (c) 2012 Northeastern University Software Engineering College
 * Software International 1001 Group Three
 * 
 * All rights reserved.
 */

package controller;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Account;
import model.Lesson;
import view.EnsureFrame;
import view.MainFrame;

/**
 * A class controls the view which the show all the lessons of the account. It
 * can also add or delete lesson. And it can test all the lesson user have
 * already learned.
 * 
 * @author Eric
 * @version 1.1
 * @see model.Account;
 * @see model.Lesson;
 * @see view.MainFrame;
 */
public class MainController {
	private static MainController mainController;

	private Account account;
	private MainFrame view;
	private LessonController lessonController;
	private HistoryController historyController;

	private int currentEnableLessonIndex;

	/**
	 * get the singleton of MainController. It is thread-safe
	 * 
	 * @return singleton of MainController
	 */
	public static MainController getMainController() {
		if (mainController == null) {
			synchronized (MainController.class) {
				if (mainController == null) {
					mainController = new MainController();
				}
			}
		}
		return mainController;
	}

	/**
	 * create a new instance of MainController. Initially the view is invisible
	 */
	private MainController() {
		view = new MainFrame();
		addListener();
		addJtableListener();
	}

	/**
	 * set the visibility of the view
	 * 
	 * @param visible the visibility of the view
	 */
	public void setVisible(boolean visible) {
		view.setVisible(visible);
	}

	/**
	 * set the account of the controller, and then refresh the view
	 * 
	 * @param account the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
		lessonController = LessonController.getLessonController();
		lessonController.setLesson(account.getLesson(0));
		historyController = new HistoryController(account.getLesson(0));
		account.getLesson(0).setEnable(true);
		initAllLessons();
	}

	/**
	 * get the history controller
	 * 
	 * @return historyController
	 */
	public HistoryController getHistoryController() {
		return historyController;
	}

	/**
	 * get the lesson controller
	 * 
	 * @return lessonController
	 */
	public LessonController getLessonController() {
		return lessonController;
	}

	/**
	 * set next lesson ability to be tested and learned.
	 */
	public void increaseEnabeLessonIndex() {
		currentEnableLessonIndex++;
		account.getLesson(currentEnableLessonIndex).setEnable(true);
	}

	/**
	 * initialize all the lesson 
	 */
	private void initAllLessons() {
		view.clearLessonTableContent();
		view.getLessonTable().changeSelection(0, 0, false, false);
		for (Lesson l : account.getAllLessons()) {
			view.addLesson(l.getLessonName());
		}
	}

	/**
	 * Add some listeners to the view
	 */
	private void addListener() {

		// exit listener
		view.getExitButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (account.isModify()) {
					int value = JOptionPane.showConfirmDialog(null,
							"Do you want to save you changes ?", "Log out",
							JOptionPane.YES_NO_CANCEL_OPTION);
					if (value == JOptionPane.CANCEL_OPTION) {
						return;
					} else if (value == JOptionPane.YES_OPTION) {
						account.writeToDatabase();
					}

				}
				view.setVisible(false);
				view.dispose();
				System.exit(0);
			}
		});

		// log out listener
		view.getLogoutButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (account.isModify()) {
					int value = JOptionPane.showConfirmDialog(null,
							"Do you want to save you changes ?", "Log out",
							JOptionPane.YES_NO_CANCEL_OPTION);
					if (value == JOptionPane.CANCEL_OPTION) {
						return;
					} else if (value == JOptionPane.YES_OPTION) {
						account.writeToDatabase();
					}
				}
				view.setVisible(false);
				view.dispose();
				new LoginController();
			}
		});

		// add lesson listener
		view.getAddLessonButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String name = JOptionPane
						.showInputDialog("Please input a name");
				try {
					if (name == null || name.equals(""))
						throw new Exception("not valid lesson name");
					account.createNewLesson(name);
					view.addLesson(name);
				} catch (Exception e) {
					EnsureFrame.showMessageDialog(e.getMessage());
				}
			}
		});

		// delete lesson controller
		view.getDeleteLessonButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = view.getLessonTable().getSelectedRow();
				if (row == -1)
					return;
				account.deleteLesson(row);
				view.deleteLesson(row);
			}
		});

		// test all controller
		view.getTestAllButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestController.getTestController().setAccount(account);
				setVisible(false);
			}
		});

	}

	/**
	 * add listener to the table of the view
	 */
	private void addJtableListener() {
		ListSelectionModel listSelectionModel = view.getLessonTable()
				.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionHandler());
	}

	/**
	 * An inner class to handle the row selecting action
	 * 
	 * @author Eric
	 * 
	 */
	class ListSelectionHandler implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel) e.getSource();

			boolean isAdjusting = e.getValueIsAdjusting();

			// System.out.println("("+isAdjusting+","+firstIndex+","+lastIndex+","+minIndex+","+maxIndex+")");
			if (!lsm.isSelectionEmpty() && !isAdjusting) {
				int index = lsm.getMinSelectionIndex();
				Lesson l = account.getLesson(index);
				lessonController.setSelectedLesson(l);
				historyController.setSelectedLesson(l);
			}
		}
	}

}
