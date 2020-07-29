package gui;

import javax.swing.DefaultListModel;

public class ListModel extends DefaultListModel{
	
	private DefaultListModel listModel;
	
	public ListModel(){
		this.setListModel(new DefaultListModel());
	}

	public void setListModel(DefaultListModel listModel) {
		this.listModel = listModel;
	}

	public DefaultListModel getListModel() {
		return listModel;
	}

}
