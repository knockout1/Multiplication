package com.example.multiplication;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;


class Calculations {

    private List<Pair<Integer, Integer>> tasksLeftToResolve;
    private Integer multiplicand;
    private Integer multiplier;
    private Integer currentTaskIndex;
    private int numberOfTasksToResolve;

    Calculations(ArrayList<Integer> multiplicands) {
        tasksLeftToResolve = prepareCalculations(multiplicands);
    }

    private List<Pair<Integer, Integer>> prepareCalculations(ArrayList<Integer> choosedNumbers) {
        List<Pair<Integer, Integer>> calculations = new ArrayList<>();
        for (int i = 0; i < choosedNumbers.size(); i++) {
            for (int j = 0; j < 10; j++) {
                calculations.add(new Pair<>(choosedNumbers.get(i), j));
            }
        }
        numberOfTasksToResolve = calculations.size();
        return calculations;
    }

    void removeCalculation(Pair pair) {
        tasksLeftToResolve.remove(pair);
    }

    Integer getMultiplicand() {
        return multiplicand;
    }

    Integer getMultiplier() {
        return multiplier;
    }

    Pair getCurrentTask() {
        return tasksLeftToResolve.get(currentTaskIndex);
    }

    void setCurrentTask(Integer currentTaskIndex) {
        this.currentTaskIndex = currentTaskIndex;
        multiplicand = tasksLeftToResolve.get(currentTaskIndex).first;
        multiplier = tasksLeftToResolve.get(currentTaskIndex).second;
    }

    int getNumberOfTasksToResolve() {
        return numberOfTasksToResolve;
    }

    int getNumberOfTasksLeftToResolve() {
        return tasksLeftToResolve.size();
    }

}

