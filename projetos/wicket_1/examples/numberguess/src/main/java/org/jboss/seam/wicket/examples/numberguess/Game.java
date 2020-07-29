package org.jboss.seam.wicket.examples.numberguess;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.naming.NamingException;

@SessionScoped
public class Game implements Serializable {
    private static final long serialVersionUID = 1L;

    private int number;
    private int guess;
    private int smallest;
    private int biggest;
    private int remainingGuesses;

    @Inject
    @MaxNumber
    private int maxNumber;

    @Inject
    Generator generator;

    @Inject
    @Random
    Instance<Integer> randomNumber;

    public Game() throws NamingException {
    }

    public int getNumber() {
        return number;
    }

    public int getGuess() {
        return guess;
    }

    public void setGuess(int guess) {
        this.guess = guess;
    }

    public int getSmallest() {
        return smallest;
    }

    public int getBiggest() {
        return biggest;
    }

    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    public boolean check() {
        if (guess > number) {
            biggest = guess - 1;
        }
        if (guess < number) {
            smallest = guess + 1;
        }
        remainingGuesses--;
        return (guess == number);
    }

    @PostConstruct
    public void reset() {
        this.smallest = 0;
        this.guess = 0;
        this.remainingGuesses = 10;
        this.biggest = maxNumber;
        this.number = randomNumber.get();
    }
}
