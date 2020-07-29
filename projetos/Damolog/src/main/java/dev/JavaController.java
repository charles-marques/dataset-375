package dev;
//package de.hwr.damolog.controller.implementation;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import de.hwr.damolog.controller.IController;
//import de.hwr.damolog.model.Checker;
//import de.hwr.damolog.model.Color;
//import de.hwr.damolog.model.Model;
//import de.hwr.damolog.model.Point;
//
///**
// * 
// * Controller f�r die Prolog Logik
// * 
// * @author nsmeenk
// * 
// */
//public class JavaController implements IController {
//
//	private Model _model;
//	private Checker _selectedChecker;
//	private Color _playerColor = Color.WHITE;
//	private boolean _hasAlreadyJumped;
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * de.hwr.damolog.controller.IController#setModel(de.hwr.damolog.model.Model
//	 * )
//	 */
//	@Override
//	public void setModel(Model pModel) {
//		_model = pModel;
//		_selectedChecker = null;
//		_playerColor = Color.WHITE;
//		_hasAlreadyJumped = false;
//		_model.resetField();
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see de.hwr.damolog.controller.IController#getPossibleFiguresToJump()
//	 */
//	@Override
//	public List<Checker> getPossibleCheckersToPlay() {
//		List<Checker> checkers = new ArrayList<Checker>();
//
//		if (_model == null)
//			return checkers;
//
//		for (Checker checker : _model.getCheckers()) {
//			if (checker.getColor() == _playerColor && getPossiblePointsToGoWith(checker).size() > 0) {
//				checkers.add(checker);
//			}
//		}
//
//		return checkers;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * de.hwr.damolog.controller.IController#getPossiblePontsToGoWith(de.hwr
//	 * .damolog.model.Figure)
//	 */
//	@Override
//	public List<Point> getPossiblePointsToGoWith(Checker pChecker) {
//		List<Point> possibleFields = new ArrayList<Point>();
//
//		if (pChecker == null)
//			return possibleFields;
//
//		int dir = 0;
//		// System.out.println(_playerColor);
//		if (_playerColor == Color.BLACK) {
//			dir = 1;
//		} else {
//			dir = -1;
//		}
//
//		if (pChecker.isQueen()) {
//			// TODO
//		} else {
//			// System.out.println("----------");
//			Point checker1pos = new Point(pChecker.getPlace().getX() - 1, pChecker.getPlace().getY() + dir);
//			// System.out.println("1 - " + checker1pos.getX() + " " +
//			// checker1pos.getY());
//			Point checker2pos = new Point(pChecker.getPlace().getX() + 1, pChecker.getPlace().getY() + dir);
//			// System.out.println("2 - " + checker2pos.getX() + " " +
//			// checker2pos.getY());
//
//			Point checkerBack3pos = new Point(pChecker.getPlace().getX() - 1, pChecker.getPlace().getY() - dir);
//			// System.out.println("3 - " + checkerBack3pos.getX() + " " +
//			// checkerBack3pos.getY());
//			Point checkerBack4pos = new Point(pChecker.getPlace().getX() + 1, pChecker.getPlace().getY() - dir);
//			// System.out.println("4 - " + checkerBack4pos.getX() + " " +
//			// checkerBack4pos.getY());
//
//			Checker checker1 = getCheckerOnField(checker1pos);
//			Checker checker2 = getCheckerOnField(checker2pos);
//
//			Checker checkerBack3 = getCheckerOnField(checkerBack3pos);
//			Checker checkerBack4 = getCheckerOnField(checkerBack4pos);
//
//			if (checker1 == null) {
//				if (!_hasAlreadyJumped) {
//					possibleFields.add(checker1pos);
//				}
//			} else {
//				if (checker1.getColor() != _playerColor) {
//					Point checker1pos2 = new Point(pChecker.getPlace().getX() - 2, pChecker.getPlace().getY() + 2 * dir);
//					if (getCheckerOnField(checker1pos2) == null) {
//						possibleFields.add(checker1pos2);
//					}
//				}
//			}
//			if (checker2 == null) {
//				if (!_hasAlreadyJumped) {
//					possibleFields.add(checker2pos);
//				}
//			} else {
//				if (checker2.getColor() != _playerColor) {
//					Point checker2pos2 = new Point(pChecker.getPlace().getX() + 2, pChecker.getPlace().getY() + 2 * dir);
//					if (getCheckerOnField(checker2pos2) == null) {
//						possibleFields.add(checker2pos2);
//					}
//				}
//			}
//			if (checkerBack3 != null && checkerBack3.getColor() != _playerColor) {
//				Point checkerJumpLeftBack = new Point(pChecker.getPlace().getX() - 2, pChecker.getPlace().getY() - 2
//						* dir);
//				// System.out.println("5 - " + checkerJumpLeftBack.getX() + " "
//				// + checkerJumpLeftBack.getY());
//				if (getCheckerOnField(checkerJumpLeftBack) == null) {
//					possibleFields.add(checkerJumpLeftBack);
//				}
//			}
//			if (checkerBack4 != null && checkerBack4.getColor() != _playerColor) {
//				Point checkerJumpRightBack = new Point(pChecker.getPlace().getX() + 2, pChecker.getPlace().getY() - 2
//						* dir);
//				// System.out.println("6 - " + checkerJumpRightBack.getX() + " "
//				// + checkerJumpRightBack.getY());
//				if (getCheckerOnField(checkerJumpRightBack) == null) {
//					possibleFields.add(checkerJumpRightBack);
//				}
//			}
//
//		}
//
//		List<Point> remove = new ArrayList<Point>();
//		for (Point point : possibleFields) {
//			if (point.getX() < 0 || point.getY() < 0 || point.getX() >= Model.FIELD_SIZE
//					|| point.getY() >= Model.FIELD_SIZE) {
//				remove.add(point);
//			}
//		}
//		possibleFields.removeAll(remove);
//
//		return possibleFields;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see de.hwr.damolog.controller.IController#getSelectedFigure()
//	 */
//	@Override
//	public Checker getSelectedChecker() {
//		return _selectedChecker;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see
//	 * de.hwr.damolog.controller.IController#useField(de.hwr.damolog.model.Point
//	 * )
//	 */
//	@Override
//	public void useField(Point pPoint) {
//		if (_selectedChecker == null) {
//			for (Checker checker : getPossibleFiguresToPlay()) {
//				if (checker.getPlace().equals(pPoint)) {
//					_selectedChecker = checker;
//				}
//			}
//		} else if (getPossiblePointsToGoWith(_selectedChecker).contains(pPoint)) {
//			if (getJumpedOver(_selectedChecker, pPoint) != null) {
//				_model.delete(getJumpedOver(_selectedChecker, pPoint));
//				_hasAlreadyJumped = true;
//				_selectedChecker.moveToPlace(pPoint);
//			}
//			if (!_hasAlreadyJumped) {
//				_selectedChecker.moveToPlace(pPoint);
//				_selectedChecker = null;
//				_hasAlreadyJumped = false;
//				if (_playerColor == Color.BLACK) {
//					_playerColor = Color.WHITE;
//				} else {
//					_playerColor = Color.BLACK;
//				}
//			} else {
//				if (getPossiblePointsToGoWith(_selectedChecker).size() <= 0) {
//					_selectedChecker.moveToPlace(pPoint);
//					_selectedChecker = null;
//					_hasAlreadyJumped = false;
//					if (_playerColor == Color.BLACK) {
//						_playerColor = Color.WHITE;
//					} else {
//						_playerColor = Color.BLACK;
//					}
//				}
//			}
//		}
//		_model.updateModel();
//	}
//
//	/**
//	 * @param pSelectedChecker
//	 * @param pPoint
//	 * @return
//	 */
//	private Checker getJumpedOver(Checker pSelectedChecker, Point pPoint) {
//		// System.out.println("---");
//		int dx = pPoint.getX() - pSelectedChecker.getPlace().getX();
//		// System.out.println("-- dx " + dx);
//		int dy = pPoint.getY() - pSelectedChecker.getPlace().getY();
//		// System.out.println("-- dy " + dy);
//
//		if ( (dx > 1 || dx < -1) && (dy > 1 || dy < -1)) {
//			int xDir = 0;
//			int yDir = 0;
//			if (dx > 1) {
//				xDir = 1;
//			} else {
//				xDir = -1;
//			}
//			if (dy > 1) {
//				yDir = 1;
//			} else {
//				yDir = -1;
//			}
//			// System.out.println("- xdir " + xDir);
//			// System.out.println("- ydir " + yDir);
//
//			for (int i = 1; i < (dx * dx) / 2; i++) {
//				Checker jumpedOver = getCheckerOnField(new Point(pSelectedChecker.getPlace().getX() + i * xDir,
//						pSelectedChecker.getPlace().getY() + i * yDir));
//				if (jumpedOver != null)
//					return jumpedOver;
//			}
//		}
//		return null;
//	}
//
//	/**
//	 * @param pPoint
//	 * @return
//	 */
//	private Checker getCheckerOnField(Point pPoint) {
//		for (Checker checker : _model.getCheckers()) {
//			if (checker.getPlace().equals(pPoint))
//				return checker;
//		}
//		return null;
//	}
//
//}
