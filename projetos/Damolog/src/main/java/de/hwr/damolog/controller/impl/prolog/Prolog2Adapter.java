package de.hwr.damolog.controller.impl.prolog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.hwr.damolog.controller.IPrologAdapter;
import de.hwr.damolog.model.Checker;
import de.hwr.damolog.model.Color;
import de.hwr.damolog.model.Model;
import de.hwr.damolog.model.Point;

public class Prolog2Adapter implements IPrologAdapter {

	private IPrologConsult consult = new Prolog2Consult();

	public Prolog2Adapter() {
		// resetData();
	}

	@Override
	public boolean nextTurn(Point pPoint, boolean hasJumped) {
		List<Map<String, String>> list = consult.query("canJumpAhead", new int[] { pPoint.getX(), pPoint.getY() },
				new String[] { "X2", "Y2" });
		if (!hasJumped || list.isEmpty())
			return consult.execute("nextTurn");
		return false;
	}

	@Override
	public List<Checker> getPossibleCheckersToPlay(Model pModel) {
		List<Map<String, String>> list = consult.query("possibleMoves", new String[] { "X", "Y", "X2", "Y2" });
		Set<Checker> result = new HashSet<Checker>(list.size());
		for (Map<String, String> m : list) {
			Checker checker = pModel.getCheckerAtPosition(convertStrings(m.get("X"), m.get("Y")));
			if (checker != null) {
				result.add(checker);
			}
		}
		return new ArrayList<Checker>(result);
	}

	@Override
	public List<Point> getPossiblePointsToGoWith(Checker pChecker) {
		if (pChecker == null)
			return new ArrayList<Point>();
		List<Map<String, String>> query = consult.query("possibleMoves", pointsOfChecker(pChecker), new String[] {
				"X2", "Y2" });
		List<Point> result = new ArrayList<Point>(query.size());
		for (Map<String, String> m : query) {
			result.add(convertStrings(m.get("X2"), m.get("Y2")));
		}
		return result;
	}

	@Override
	public List<Checker> getCheckers() {
		List<Map<String, String>> list = consult.query("getCheckers", new String[] { "X", "Y", "Color" });
		List<Checker> result = new ArrayList<Checker>(list.size());
		for (Map<String, String> map : list) {
			Integer figureVal = new Integer(map.get("Color"));
			Color pColor = figureVal > 0 ? Color.WHITE : Color.BLACK;
			Checker e = new Checker(pColor, convertStrings(map.get("X"), map.get("Y")));
			if (Math.abs(figureVal) > 1) {
				e.changeToQueen();
			}
			result.add(e);
		}
		return result;
	}

	@Override
	public void resetData() {
		consult.execute("resetAll");
		consult.execute("makeMoves");

	}

	@Override
	public void setCheckerOnField(Checker pSelectedChecker, Point pPoint) {
		int[] nextField = new int[] { pPoint.getX(), pPoint.getY() };
		int[] params = concat(pointsOfChecker(pSelectedChecker), nextField);
		consult.query("setFigure", params, new String[] { "J", "Q" });
	}

	@Override
	public boolean isGameEnded() {
		boolean execute = consult.execute("isFinished");
		return execute;
	}

	@Override
	public Color getWinnerColor() {
		boolean isBlack = consult.query("getCurrentColor", new String[] { "C" }).get(0).get("C").equals("black");
		return isBlack ? Color.WHITE : Color.BLACK; // correct -> possibleMoves empty -> previos player won
	}

	private Point convertStrings(String x, String y) {
		return new Point(Integer.parseInt(x), Integer.parseInt(y));
	}

	private int[] pointsOfChecker(Checker c) {
		Point place = c.getPlace();
		return new int[] { place.getX(), place.getY() };
	}

	private int[] concat(int[] A, int[] B) {
		int[] C = new int[A.length + B.length];
		System.arraycopy(A, 0, C, 0, A.length);
		System.arraycopy(B, 0, C, A.length, B.length);
		return C;
	}

}
