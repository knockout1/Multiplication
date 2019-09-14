package com.example.multiplication;

import android.util.Pair;

import java.util.Random;


class Game {

    boolean isResolved = true;
    private int numberOfMistakes;
    private int numberOfAllowedMistakes;
    private int currentCalculationNumber;
    private Calculations calculations;
    private Random random;
    private boolean gameEnded;
    private boolean gameSucceeded;

    Game(Calculations calculations) {
        this.calculations = calculations;
        numberOfMistakes = 0;
        currentCalculationNumber = 1;
        random = new Random();
        numberOfAllowedMistakes = calculations.getNumberOfTasksToResolve() / 10;
    }

    Calculations getCurrentCalculation() {
        return calculations;
    }

    void prepareCalculation() {
        isResolved = false;
        calculations.setCurrentTask(random.nextInt(calculations.getNumberOfTasksLeftToResolve()));
        if (calculations.getNumberOfTasksLeftToResolve() == 1) {
            endGame(true);
        }
    }

    boolean checkProvidedAnswer(Integer enteredResult) {
        isResolved = true;
        if (enteredResult.equals(calculations.getMultiplicand() * calculations.getMultiplier())) {
            Pair pair = calculations.getCurrentTask();
            calculations.removeCalculation(pair);
            currentCalculationNumber++;
            return true;
        } else {
            numberOfMistakes++;
            return false;
        }
    }

    void checkNumberOfMistakes() {
        if (numberOfMistakes > numberOfAllowedMistakes) {
            endGame(false);
        }
    }

    int getNumberOfMistakes() {
        return numberOfMistakes;
    }

    int getNumberOfAllowedMistakes() {
        return numberOfAllowedMistakes;
    }

    int getCurrentCalculationNumber() {
        return currentCalculationNumber;
    }

    private void endGame(boolean success) {
        gameEnded = true;
        gameSucceeded = success;
    }

    boolean checkEndGame() {
        return gameEnded;
    }

    boolean isGameSucceeded() {
        return gameSucceeded;
    }

}