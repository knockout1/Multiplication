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
    private int numberOfTasksLeftToResolve;

    Calculations(ArrayList<Integer> multiplicands) {
        tasksLeftToResolve = prepareCalculations(multiplicands);
        numberOfTasksLeftToResolve = numberOfTasksToResolve;
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
        numberOfTasksLeftToResolve--;
    }

    Integer getMultiplicand() {
        return multiplicand;
    }

    Integer getMultiplier() {
        return multiplier;
    }

    private Integer getCurrentTaskIndex() {
        return currentTaskIndex;
    }

    Pair getCurrentTask() {
        return tasksLeftToResolve.get(getCurrentTaskIndex());
    }

    void setCurrentTask(Integer currentTaskIndex) {
        this.currentTaskIndex = currentTaskIndex;
        multiplicand = tasksLeftToResolve.get(getCurrentTaskIndex()).first;
        multiplier = tasksLeftToResolve.get(getCurrentTaskIndex()).second;

    }

    int getNumberOfTasksToResolve() {
        return numberOfTasksToResolve;
    }

    int getNumberOfTasksLeftToResolve() {
        return numberOfTasksLeftToResolve;
    }

}

