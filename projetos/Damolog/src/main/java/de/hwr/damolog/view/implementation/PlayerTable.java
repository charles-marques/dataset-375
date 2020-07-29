/**
 * 
 */
package de.hwr.damolog.view.implementation;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import de.hwr.damolog.constants.Images;
import de.hwr.damolog.constants.Strings;
import de.hwr.damolog.model.Player;

/**
 * @author nsmeenk
 * 
 */
public class PlayerTable extends TableViewer {

	/**
	 * @param pParent
	 * @param pStyle
	 */
	public PlayerTable(Composite pParent, int pStyle) {
		super(pParent, pStyle);

		initIconColumn();
		initNameColumn();
	}

	/**
	 * 
	 */
	private void initNameColumn() {
		final TableViewerColumn nameColumn = new TableViewerColumn(this, SWT.NONE);
		nameColumn.getColumn().setText(Strings.GAMER_NAME_COLUMN);
		nameColumn.getColumn().setWidth(200);
		nameColumn.setLabelProvider(new ColumnLabelProvider() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang .Object)
			 */
			@Override
			public String getText(Object pElement) {
				if (pElement instanceof Player) {
					Player player = (Player) pElement;
					return player.getName();
				}
				return null;
			}
		});
	}

	/**
	 * 
	 */
	private void initIconColumn() {
		final TableViewerColumn iconColumn = new TableViewerColumn(this, SWT.NONE);
		iconColumn.getColumn().setWidth(26);
		iconColumn.setLabelProvider(new ColumnLabelProvider() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getImage(java.lang .Object)
			 */
			@Override
			public Image getImage(Object pElement) {
				if (pElement instanceof Player) {
					Player player = (Player) pElement;
					if (player.isAI()) {
						return Images.AI;
					}
					return Images.HUMAN;
				}
				return null;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang .Object)
			 */
			@Override
			public String getText(Object pElement) {
				return "";
			}
		});
	}

	// /**
	// *
	// */
	// private void initIconColumn() {
	// final TableViewerColumn iconColumn = new TableViewerColumn(this,
	// SWT.NONE);
	// iconColumn.setLabelProvider(new CellLabelProvider() {
	//
	// @Override
	// public void update(ViewerCell pArg0) {
	// }
	// });
	// }

}
