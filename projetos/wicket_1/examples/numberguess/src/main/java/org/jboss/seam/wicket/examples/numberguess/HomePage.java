package org.jboss.seam.wicket.examples.numberguess;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

public class HomePage extends WebPage {
    @Inject
    private Game game;

    @Inject
    private Conversation conversation;

    public HomePage() {
        conversation.begin();
        Form<String> form = new Form<String>("NumberGuessMain");
        add(form);
        form.add(new FeedbackPanel("messages").setOutputMarkupId(true));

        final Component prompt = new Label("prompt", new Model<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String getObject() {
                return "I'm thinking of a number between " + game.getSmallest() + " and " + game.getBiggest() +
                        ".  You have " + game.getRemainingGuesses() + " guesses.";
            }
        });

        form.add(prompt);

        final Component guessLabel = new Label("guessLabel", "Your Guess:");
        form.add(guessLabel);
        final Component inputGuess = new TextField<Serializable>("inputGuess", new Model<Serializable>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Serializable getObject() {
                return game.getGuess();
            }

            @Override
            public void setObject(Serializable object) {
                game.setGuess(Integer.parseInt(object.toString()));
            }
        });
        form.add(inputGuess);

        final Component guessButton = new AjaxButton("GuessButton") {
            private static final long serialVersionUID = 1L;

            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                if (game.check()) {
                    info("Correct!");
                    setVisible(false);
                    prompt.setVisible(false);
                    guessLabel.setVisible(false);
                    inputGuess.setVisible(false);
                } else if (game.getRemainingGuesses() == 0) {
                    info("Sorry, the answer was " + game.getNumber());
                    setVisible(false);
                    guessLabel.setVisible(false);
                    inputGuess.setVisible(false);
                } else if (game.getNumber() > game.getGuess()) {
                    info("Higher!");
                } else if (game.getNumber() < game.getGuess()) {
                    info("Lower");
                }
                target.addComponent(form);
            }
        };
        form.add(guessButton);

        form.add(new AjaxButton("RestartButton") {
            private static final long serialVersionUID = 1L;

            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                game.reset();
                guessButton.setVisible(true);
                prompt.setVisible(true);
                guessLabel.setVisible(true);
                inputGuess.setVisible(true);
                target.addComponent(form);
            }
        });
    }
}
