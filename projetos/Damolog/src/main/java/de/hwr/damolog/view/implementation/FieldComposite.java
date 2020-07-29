package de.hwr.damolog.view.implementation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;

import de.hwr.damolog.controller.IController;
import de.hwr.damolog.model.Checker;
import de.hwr.damolog.model.Model;
import de.hwr.damolog.model.Point;

public class FieldComposite extends Composite {

	private static final int FIELD_SIZE = 50;
	private static final int BORDER = 5;

	private List<Checker> _markedCheckers = new ArrayList<Checker>();
	private List<Point> _markedFields = new ArrayList<Point>();

	private Model _model = null;
	private IController _controller = null;
	private SwtMainWindow _mainWindow = null;

	public FieldComposite(Composite parent, int style, SwtMainWindow pMainWindow) {
		super(parent, style);

		_mainWindow = pMainWindow;
		setSize(FIELD_SIZE * 8 + BORDER * 2, FIELD_SIZE * 8 + BORDER * 2);

		addPaintListener(new PaintListener() {

			private Color fieldBlack = new Color(getDisplay(), 150, 150, 150);
			private Color fieldWhite = new Color(getDisplay(), 200, 200, 200);

			private Color fieldWhiteSelectionColor = new Color(getDisplay(), 150, 200, 150);
			private Color fieldBlackSelectionColor = new Color(getDisplay(), 100, 140, 100);

			private Color stoneBlack = new Color(getDisplay(), 0, 0, 0);
			private Color stoneBlackSelected = new Color(getDisplay(), 0, 0, 100);
			private Color stoneWhite = new Color(getDisplay(), 255, 255, 255);
			private Color stoneWhiteSelected = new Color(getDisplay(), 100, 100, 200);

			private Color whiteBack = new Color(getDisplay(), 255, 255, 255);
			private Color border = new Color(getDisplay(), 0, 0, 0);

			@SuppressWarnings ("unused")
			@Override
			public void paintControl(PaintEvent pE) {
				pE.gc.setAntialias(SWT.ON);
				int lineCount = 0;

				// Feld zeichnen
				for (int y = 0; y < Model.FIELD_SIZE; y++) {
					int columnCount = 0;
					for (int x = 0; x < Model.FIELD_SIZE; x++) {
						paintField(x, y, pE.gc);
						columnCount++;
					}
					lineCount++;
				}

				// Figuren zeichnen
				if (_model != null) {
					for (Checker checker : _model.getCheckers()) {
						paintChecker(checker, pE.gc);
					}
				}
			}

			/**
			 * Zeichnet ein einzelnes Feld
			 * 
			 * @param x
			 * @param y
			 * @param gc
			 */
			private void paintField(int x, int y, GC gc) {
				Color fieldColor = null;

				if (y % 2 != x % 2) {
					fieldColor = fieldWhite;
					if (_markedFields.contains(new Point(x, y))) {
						fieldColor = fieldWhiteSelectionColor;
					}
				} else if (y % 2 == x % 2) {
					fieldColor = fieldBlack;
					if (_markedFields.contains(new Point(x, y))) {
						fieldColor = fieldBlackSelectionColor;
					}
				}

				gc.setBackground(fieldColor);
				gc.fillRectangle(BORDER + x * FIELD_SIZE, BORDER + y * FIELD_SIZE, FIELD_SIZE, FIELD_SIZE);
			}

			/**
			 * Zeichnet eine Figur an die angegebene Position
			 * 
			 * @param x
			 * @param y
			 * @param gc
			 */
			private void paintChecker(Checker pChecker, GC gc) {
				Color fieldColor = null;

				if (pChecker.getColor() == de.hwr.damolog.model.Color.BLACK) {
					fieldColor = stoneBlack;
					if (_markedCheckers != null && _markedCheckers.contains(pChecker)) {
						fieldColor = stoneBlackSelected;
					}
				} else if (pChecker.getColor() == de.hwr.damolog.model.Color.WHITE) {
					fieldColor = stoneWhite;
					if (_markedCheckers != null && _markedCheckers.contains(pChecker)) {
						fieldColor = stoneWhiteSelected;
					}
				}

				gc.setForeground(border);
				gc.setBackground(whiteBack);
				int x = pChecker.getPlace().getX();
				int y = pChecker.getPlace().getY();
				int xStart = BORDER + x * FIELD_SIZE + 4;
				int yStart = BORDER + y * FIELD_SIZE + FIELD_SIZE / 4;
				int width = FIELD_SIZE - 8;
				int height = FIELD_SIZE / 2;

				if (pChecker.isQueen()) {
					gc.drawRectangle(xStart, yStart + height / 2 - 5, width, 5);
					gc.fillRectangle(xStart, yStart + height / 2 - 5, width, 5);
					gc.drawOval(xStart, yStart + 5, width, height);
					gc.fillOval(xStart, yStart + 5, width, height);
				}

				gc.drawRectangle(xStart, yStart + height / 2, width, 5);
				gc.fillRectangle(xStart, yStart + height / 2, width, 5);
				gc.drawOval(xStart, yStart, width, height);
				gc.fillOval(xStart, yStart, width, height);

				gc.setBackground(fieldColor);

				gc.fillOval(xStart, yStart - 5, width, height);
				gc.drawOval(xStart, yStart - 5, width, height);

			}
		});

		redraw();

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseDown(MouseEvent e) {
				if (e.x < BORDER || e.y < BORDER || e.x > BORDER + FIELD_SIZE * Model.FIELD_SIZE
						|| e.y > BORDER + FIELD_SIZE * Model.FIELD_SIZE) {
					System.out.println("Fehlerhafter Klick");
				} else {
					int x = (e.x - BORDER) / FIELD_SIZE;
					int y = (e.y - BORDER) / FIELD_SIZE;

					_controller.useField(new Point(x, y));
				}
			}
		});
	}

	/**
	 * @param pChanged
	 */
	public void setModel(Model pChanged) {
		_model = pChanged;
		_markedCheckers.clear();
		_markedCheckers.add(_controller.getSelectedChecker());
		if (_controller.getSelectedChecker() == null) {
			_markedCheckers.addAll(_controller.getPossibleCheckersToPlay());
		}
		_markedFields = _controller.getPossiblePointsToGoWith(_controller.getSelectedChecker());
		redraw();

		if (_controller.hasGameEnded()) {
			_mainWindow.endGame();
		}
	}

	/**
	 * Setzt den Controller
	 * 
	 * @param pController
	 */
	public void setController(IController pController) {
		_controller = pController;
	}
}
