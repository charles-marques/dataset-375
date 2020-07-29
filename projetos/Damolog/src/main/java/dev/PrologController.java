/**
 * 
 */
package dev;

import java.util.ArrayList;
import java.util.List;

import de.hwr.damolog.controller.IController;
import de.hwr.damolog.controller.IPrologAdapter;
import de.hwr.damolog.controller.Subject;
import de.hwr.damolog.model.Checker;
import de.hwr.damolog.model.Color;
import de.hwr.damolog.model.Model;
import de.hwr.damolog.model.Point;

/**
 * @author nsmeenk
 * 
 */
public class PrologController extends Subject implements IController {

	private IPrologAdapter _prologAdapter;
	private Checker _selectedChecker;
	private Model _model;
	private boolean _isInit;

	/**
	 * 
	 */
	public PrologController() {
		_prologAdapter = new PrologAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hwr.damolog.controller.IController#setModel(de.hwr.damolog.model.Model )
	 */
	@Override
	public void setModel(Model pModel) {
		_model = pModel;
		_selectedChecker = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hwr.damolog.controller.IController#getPossibleFiguresToPlay()
	 */
	@Override
	public List<Checker> getPossibleCheckersToPlay() {
		return _prologAdapter.getPossibleCheckersToPlay(_model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hwr.damolog.controller.IController#getPossiblePointsToGoWith(de.hwr .damolog.model.Checker)
	 */
	@Override
	public List<Point> getPossiblePointsToGoWith(Checker pChecker) {
		if (pChecker == null)
			return new ArrayList<Point>();
		return _prologAdapter.getPossiblePointsToGoWith(pChecker);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hwr.damolog.controller.IController#selectChecker(de.hwr.damolog.model .Checker)
	 */
	public void selectChecker(Checker pChecker) {
		_selectedChecker = pChecker;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hwr.damolog.controller.IController#getSelectedChecker()
	 */
	@Override
	public Checker getSelectedChecker() {
		return _selectedChecker;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hwr.damolog.controller.IController#useField(de.hwr.damolog.model.Point )
	 */
	@Override
	public void useField(Point pPoint) {
		if (_selectedChecker == null) {
			Checker clickedChecker = _model.getCheckerAtPosition(pPoint);
			if (_prologAdapter.getPossibleCheckersToPlay(_model).contains(clickedChecker)) {
				_selectedChecker = clickedChecker;
			}
		} else {
			_prologAdapter.setCheckerOnField(_selectedChecker, pPoint);
			_model.setCheckers(_prologAdapter.getCheckers().toArray(new Checker[0]));
			_selectedChecker = _model.getCheckerAtPosition(pPoint);
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
		_selectedChecker = null;
		_prologAdapter.resetData();
		_model.setCheckers(_prologAdapter.getCheckers().toArray(new Checker[0]));
		_isInit = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hwr.damolog.controller.IController#hasGameEnded()
	 */
	@Override
	public boolean hasGameEnded() {
		return _prologAdapter.isGameEnded();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hwr.damolog.controller.IController#getWinner()
	 */
	@Override
	public Color getWinner() {
		return _prologAdapter.getWinnerColor();
	}
}
