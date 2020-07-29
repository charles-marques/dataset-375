package de.hwr.damolog.controller.impl.prolog;

import java.util.List;

import de.hwr.damolog.controller.IController;
import de.hwr.damolog.controller.IPrologAdapter;
import de.hwr.damolog.model.Checker;
import de.hwr.damolog.model.Color;
import de.hwr.damolog.model.Model;
import de.hwr.damolog.model.Point;

public class Prolog2Controller implements IController {

	private Checker _selectedChecker;
	private Model _model;
	private IPrologAdapter adapter;

	public Prolog2Controller() {
		adapter = new Prolog2Adapter();
	}

	@Override
	public void setModel(Model pModel) {
		_model = pModel;
		_selectedChecker = null;
		// _model.resetField();
	}

	@Override
	public List<Checker> getPossibleCheckersToPlay() {
		return adapter.getPossibleCheckersToPlay(_model);
	}

	@Override
	public List<Point> getPossiblePointsToGoWith(Checker pFigure) {
		return adapter.getPossiblePointsToGoWith(pFigure);
	}

	@Override
	public Checker getSelectedChecker() {
		return _selectedChecker;
	}

	@Override
	public void useField(Point pPoint) {
		if (_selectedChecker == null) {
			for (Checker checker : getPossibleCheckersToPlay()) {
				if (checker.getPlace().equals(pPoint)) {
					_selectedChecker = checker;
					break;
				}
			}
		} else if (_selectedChecker.getPlace().equals(pPoint)) {
			_selectedChecker = null;
		} else if (getPossiblePointsToGoWith(_selectedChecker).contains(pPoint)) {
			adapter.setCheckerOnField(_selectedChecker, pPoint);
			_model.setCheckers(adapter.getCheckers().toArray(new Checker[0]));
			boolean jump = Math.abs(_selectedChecker.getPlace().getY() - pPoint.getY()) > 1;
			_selectedChecker = _model.getCheckerAtPosition(pPoint);
			if (adapter.nextTurn(pPoint, jump)) {
				_selectedChecker = null;
			}
			;
		}
		_model.updateModel();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hwr.damolog.controller.IController#createNewGame()
	 */
	@Override
	public void createNewGame() {
		adapter.resetData();
		_selectedChecker = null;
		_model.setCheckers(adapter.getCheckers().toArray(new Checker[0]));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hwr.damolog.controller.IController#hasGameEnded()
	 */
	@Override
	public boolean hasGameEnded() {
		return adapter.isGameEnded();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hwr.damolog.controller.IController#getWinner()
	 */
	@Override
	public Color getWinner() {
		if (hasGameEnded())
			return adapter.getWinnerColor();
		return null;
	}
}
