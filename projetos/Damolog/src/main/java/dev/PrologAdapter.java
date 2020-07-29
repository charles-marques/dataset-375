package dev;

import gnu.prolog.term.AtomTerm;
import gnu.prolog.term.CompoundTerm;
import gnu.prolog.term.IntegerTerm;
import gnu.prolog.term.VariableTerm;
import gnu.prolog.vm.Environment;
import gnu.prolog.vm.Interpreter;
import gnu.prolog.vm.Interpreter.Goal;
import gnu.prolog.vm.PrologCode;
import gnu.prolog.vm.PrologException;

import java.util.ArrayList;
import java.util.List;

import de.hwr.damolog.controller.IPrologAdapter;
import de.hwr.damolog.model.Checker;
import de.hwr.damolog.model.Color;
import de.hwr.damolog.model.Model;
import de.hwr.damolog.model.Point;

/**
 * 
 * Der Prolog Adapter
 * 
 * @author nsmeenk
 * 
 */
public class PrologAdapter implements IPrologAdapter {

	private Environment _environment;
	private Interpreter _interpreter;

	/**
	 * 
	 */
	public PrologAdapter() {
		_environment = new Environment();
		_environment.ensureLoaded(AtomTerm.get(PrologAdapter.class.getClassLoader()
				.getResource("dev/nico_prolog_test.pl").getFile()));

		_interpreter = _environment.createInterpreter();

		_environment.runInitialization(_interpreter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hwr.damolog.controller.IPrologAdapter#getPossibleFiguresToPlay(de. hwr.damolog.model.Model)
	 */
	@Override
	public List<Checker> getPossibleCheckersToPlay(Model pModel) {
		List<Checker> returnList = new ArrayList<Checker>();

		VariableTerm x = new VariableTerm("X");
		VariableTerm y = new VariableTerm("Y");

		VariableTerm[] params = { x, y };

		CompoundTerm goalTerm = new CompoundTerm(AtomTerm.get("canPlayWith"), params);

		Goal goal = _interpreter.prepareGoal(goalTerm);

		try {
			int result;
			do {
				result = _interpreter.execute(goal);
				if (result != PrologCode.FAIL) {
					returnList.add(pModel.getCheckerAtPosition(new Point(Integer.parseInt(x.value.toString()), Integer
							.parseInt(y.value.toString()))));
				}
			} while (result == PrologCode.SUCCESS && result != PrologCode.SUCCESS_LAST);
		} catch (PrologException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnList;
	}

	/**
	 * @param pChecker
	 * @return
	 */
	@Override
	public List<Point> getPossiblePointsToGoWith(Checker pChecker) {
		List<Point> returnList = new ArrayList<Point>();

		VariableTerm x = new VariableTerm("X");
		VariableTerm y = new VariableTerm("Y");
		VariableTerm xGoTo = new VariableTerm("YGoTo");
		VariableTerm yGoTo = new VariableTerm("YGoTo");

		x.value = IntegerTerm.get(pChecker.getPlace().getX());
		y.value = IntegerTerm.get(pChecker.getPlace().getY());

		VariableTerm[] params = { x, y, xGoTo, yGoTo };

		CompoundTerm goalTerm = new CompoundTerm(AtomTerm.get("canSetCheckerOnField"), params);

		Goal goal = _interpreter.prepareGoal(goalTerm);

		try {
			int result;
			do {
				result = _interpreter.execute(goal);
				if (result != PrologCode.FAIL) {
					returnList.add(new Point(Integer.parseInt(xGoTo.value.toString()), Integer.parseInt(yGoTo.value
							.toString())));
				}
			} while (result == PrologCode.SUCCESS && result != PrologCode.SUCCESS_LAST);
		} catch (PrologException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hwr.damolog.controller.IPrologAdapter#getCheckers()
	 */
	@Override
	public List<Checker> getCheckers() {
		List<Checker> returnList = new ArrayList<Checker>();

		VariableTerm color = new VariableTerm("Color");
		VariableTerm x = new VariableTerm("X");
		VariableTerm y = new VariableTerm("Y");

		VariableTerm[] params = { color, x, y };

		CompoundTerm goalTerm = new CompoundTerm(AtomTerm.get("checker"), params);

		Goal goal = _interpreter.prepareGoal(goalTerm);

		try {
			int result;
			do {
				result = _interpreter.execute(goal);
				if (result != PrologCode.FAIL) {
					String colorString = color.value.toString();
					Color colorColor = null;
					if (colorString.endsWith(Color.BLACK.toString())) {
						colorColor = Color.BLACK;
					} else if (colorString.endsWith(Color.WHITE.toString())) {
						colorColor = Color.WHITE;
					}
					returnList.add(new Checker(colorColor, new Point(Integer.parseInt(x.value.toString()), Integer
							.parseInt(y.value.toString()))));
				}
			} while (result == PrologCode.SUCCESS && result != PrologCode.SUCCESS_LAST);
		} catch (PrologException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hwr.damolog.controller.IPrologAdapter#resetData()
	 */
	@Override
	public void resetData() {

		VariableTerm no = new VariableTerm("No");
		no.value = AtomTerm.get("_");

		VariableTerm[] params = { no };

		CompoundTerm goalTerm = new CompoundTerm(AtomTerm.get("resetField"), params);

		Goal goal = _interpreter.prepareGoal(goalTerm);

		try {
			_interpreter.execute(goal);
		} catch (PrologException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hwr.damolog.controller.IPrologAdapter#setCheckerOnField(de.hwr.damolog .model.Checker,
	 * de.hwr.damolog.model.Point)
	 */
	@Override
	public void setCheckerOnField(Checker pSelectedChecker, Point pPoint) {

		VariableTerm x = new VariableTerm("X");
		VariableTerm y = new VariableTerm("Y");
		VariableTerm xGoTo = new VariableTerm("YGoTo");
		VariableTerm yGoTo = new VariableTerm("YGoTo");

		x.value = IntegerTerm.get(pSelectedChecker.getPlace().getX());
		y.value = IntegerTerm.get(pSelectedChecker.getPlace().getY());
		xGoTo.value = IntegerTerm.get(pPoint.getX());
		yGoTo.value = IntegerTerm.get(pPoint.getY());

		VariableTerm[] params = { x, y, xGoTo, yGoTo };

		CompoundTerm goalTerm = new CompoundTerm(AtomTerm.get("go"), params);

		Goal goal = _interpreter.prepareGoal(goalTerm);

		try {
			_interpreter.execute(goal);
		} catch (PrologException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hwr.damolog.controller.IPrologAdapter#isGameEnded()
	 */
	@Override
	public boolean isGameEnded() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hwr.damolog.controller.IPrologAdapter#getWinnerColor()
	 */
	@Override
	public Color getWinnerColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean nextTurn(Point pPoint, boolean b) {
		return false;
		// TODO Auto-generated method stub

	}
}
