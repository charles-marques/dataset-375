package engine.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import engine.SplayerEngine;

@SuppressWarnings("serial")
public class ActionPlay extends AbstractAction {

    private SplayerEngine engine;

    public ActionPlay(SplayerEngine engine)
    {
        super("");
        this.engine = engine;
        this.putValue(SHORT_DESCRIPTION, "Lecture/pause");
    }
    
    @Override
    public void actionPerformed(ActionEvent event)
    {
        engine.playPause();
    }
    
}
