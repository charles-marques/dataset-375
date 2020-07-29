package gui;

import java.awt.Component;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.User;
import model.Status;

public class UserStatusRenderer extends JLabel implements ListCellRenderer {

	ClassLoader cldr = this.getClass().getClassLoader();

	java.net.URL accepted   = cldr.getResource("gui/images/accepted.gif");
	java.net.URL declined   = cldr.getResource("gui/images/declined.gif");
	java.net.URL waiting   = cldr.getResource("gui/images/waiting.gif");
	
	ImageIcon acceptedIcon = new ImageIcon(accepted);
    ImageIcon declinedIcon = new ImageIcon(declined);
    ImageIcon waitingIcon = new ImageIcon(waiting);
	
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		
		String text = ((User) ((Entry) value).getKey()).getName();
		setText(text);

		switch(((Status) ((Entry) value).getValue())) {
			case ACCEPTED:
				setIcon(acceptedIcon);
				break;
			case DECLINED:
				setIcon(declinedIcon);
				break;
			case WAITING:
				setIcon(waitingIcon);
				break;
		}
		
		setEnabled(list.isEnabled());
        setFont(list.getFont());
        setOpaque(true);
        return this;
	}

}
