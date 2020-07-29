package engine.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import engine.SplayerEngine;

@SuppressWarnings("serial")
public class ActionEmpty extends AbstractAction {

    private SplayerEngine engine;
    
    public ActionEmpty(SplayerEngine engine)
    {
        super("", new ImageIcon("./data/icon/media-empty.png"));
        this.engine = engine;
        this.putValue(SHORT_DESCRIPTION, "Vider la playlist");
    }
    
    @Override
    public void actionPerformed(ActionEvent event)
    {
        engine.emptyPlaylist();
    }

}
