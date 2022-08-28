package Algorithms.Genetic;


import TestDataGenerator.TestData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FitnessCalculator {

    private int numOfAvailableHils;
    List<TestData> solution;

    public void setSolution(List<TestData> newSolution, int numOfAvailableHils) {
        this.solution = new ArrayList<>();
        for (TestData test: newSolution) {
            this.solution.add(new TestData(test.getName(), test.getArrivalTime(), test.getExecutionTime()));
        }
        this.numOfAvailableHils = numOfAvailableHils;
    }

    public int getFitness(Individual individual) {
        ArrayList<TestData> copy = new ArrayList<>();
        for (TestData test: individual.getGenes()) {
            copy.add(new TestData(test.getName(), test.getArrivalTime(), test.getExecutionTime()));
        }
        return this.GetFitness(copy);
    }

    // Get optimum fitness - min run time
    public int getFitnessWithMinRunTime() {
        List<TestData> currentSolutionCopy = new ArrayList<>();
        for (TestData test: this.solution) {
            currentSolutionCopy.add(new TestData(test.getName(), test.getArrivalTime(), test.getExecutionTime()));
        }
        return this.GetFitness(currentSolutionCopy);
    }

    // Individual fitness is calculated by just running the test cases as they arrive
    private int GetFitness(List<TestData> copy) {
        int timer = 0;
        List<TestData> remainingProcess = new ArrayList<>();
        Map<Integer, ArrayList<TestData>> arrivals = new HashMap<>();
        for (TestData t: copy) {
            arrivals.putIfAbsent(t.getArrivalTime(), new ArrayList<>());
            arrivals.get(t.getArrivalTime()).add(t);
        }

        while (arrivals.size() != 0 || remainingProcess.size() != 0) {
            this.RemoveCompletedProcess(remainingProcess);
            if (arrivals.get(timer) != null) {
                remainingProcess.addAll(arrivals.get(timer));
                arrivals.remove(timer);
            }
            timer++;
        }

        return timer;
    }

    private void RemoveCompletedProcess(List<TestData> remainingProcess) {
        for (int i=0; i < remainingProcess.size(); i++) {
            TestData rp = remainingProcess.get(i);
            if (rp.isExecuting()) {
                rp.setExecutionTimeInSeconds(rp.getExecutionTime()-1);
            } else {
                // try acquire a hil and execute
                if (this.numOfAvailableHils > 0) {
                    this.numOfAvailableHils -= 1;
                    rp.setExecutionTimeInSeconds(rp.getExecutionTime()-1);
                    rp.setExecuting(true);
                }
            }
            if (rp.getExecutionTime() == 0) {
                this.numOfAvailableHils += 1;
                rp.setExecuting(false);
                remainingProcess.remove(i);
            }
        }
    }
}