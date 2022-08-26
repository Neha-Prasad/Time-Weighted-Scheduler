package Algorithms;

import TestDataGenerator.TestData;

import java.util.*;

public class ShortestJobFirstPreemptive implements IAlgorithm {
    private int numOfAvailableHils;

    @Override
    public String getName() {
        return "ShortestJobFirstPreemptive";
    }

    private Comparator<TestData> testDataComparator = new Comparator<TestData>() {
        @Override
        public int compare(TestData t1, TestData t2) {
            return t1.getExecutionTime() - t2.getExecutionTime();
        }
    };

    @Override
    public int Run(List<TestData> tests, int numberOfHils) {
        this.numOfAvailableHils = numberOfHils;
        List<TestData> remainingProcess = new ArrayList<>();
        Map<Integer, ArrayList<TestData>> arrivals = new HashMap<>();

        for (TestData t: tests) {
            arrivals.putIfAbsent(t.getArrivalTime(), new ArrayList<>());
            arrivals.get(t.getArrivalTime()).add(t);
        }

        int timer = 0;
        while (arrivals.size() != 0 || remainingProcess.size() != 0) {
            RemoveCompletedProcess(remainingProcess);
            if (arrivals.get(timer) != null) {
                List<TestData> newArrivals = arrivals.get(timer);
                remainingProcess = this.AddNewArrivals(remainingProcess, newArrivals);
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

    private List<TestData> AddNewArrivals(List<TestData> remainingProcess, List<TestData> newArrivals) {
        Collections.sort(remainingProcess, this.testDataComparator);
        Collections.sort(newArrivals, this.testDataComparator);
        List<TestData> newRemainingProcess = new ArrayList<>();
        int i=0;
        int j=0;
        while (i < remainingProcess.size() && j < newArrivals.size()) {
            if (newArrivals.get(j).getExecutionTime() < remainingProcess.get(i).getExecutionTime()) {
                if (remainingProcess.get(i).isExecuting()) {
                    this.numOfAvailableHils += 1;
                    remainingProcess.get(i).setExecuting(false);
                }
                newRemainingProcess.add(newArrivals.get(j));
                j++;
            } else {
                newRemainingProcess.add(remainingProcess.get(i));
                i++;
            }
        }

        while (i < remainingProcess.size()) {
            newRemainingProcess.add(remainingProcess.get(i));
            i++;
        }

        while (j < newArrivals.size()) {
            newRemainingProcess.add(newArrivals.get(j));
            j++;
        }
        return newRemainingProcess;
    }
}
