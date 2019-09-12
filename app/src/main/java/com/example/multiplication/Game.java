package com.example.multiplication;

import android.util.Pair;

import java.util.Random;


// TODO: refactor class
class Game {

    boolean isResolved = true;
    private int totalCalculation;
    private int numberOfMistakes;
    private int numberOfAllowedMistakes;
    private int currentCalculationNumber;
    private Calculations calculations;
    private Random random;


    Game(Calculations calculations) {
        this.calculations = calculations;
        numberOfMistakes = 0;
        currentCalculationNumber = 1;
        random = new Random();
        numberOfAllowedMistakes = calculations.getNumberOfTasksToResolve() / 10;
        totalCalculation = calculations.getNumberOfTasksToResolve();
    }

    Calculations getCurrentCalculation() {
        return calculations;
    }

    void prepareCalculation() {
        isResolved = false;
        if (calculations.getNumberOfTasksLeftToResolve() > 0) {
            calculations.setCurrentTask(random.nextInt(calculations.getNumberOfTasksLeftToResolve()));
        } else {
            endGame(true);
        }
    }

    boolean checkProvidedAnswer(Integer enteredResult) {
        currentCalculationNumber++;
        isResolved = true;
        if (enteredResult.equals(calculations.getMultiplicand() * calculations.getMultiplier())) {
            Pair pair = calculations.getCurrentTask();
            calculations.removeCalculation(pair);
            return true;
        } else {
            numberOfMistakes++;
            return false;
        }
    }

    boolean checkIfEndGame() {
        return numberOfMistakes > numberOfAllowedMistakes;
    }

    int getTotalCalculation() {
        return totalCalculation;
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

    void endGame(boolean success) {

    }
}
