package Algorithms;

import TestDataGenerator.TestData;

import java.util.*;

public class ShortestJobFirstNonPreemptive implements IAlgorithm {
    private int numOfAvailableHils;

    @Override
    public String getName() {
        return "ShortestJobFirstNonPreemptive";
    }

    private Comparator<TestData> testDataComparator = new Comparator<TestData>() {
        @Override
        public int compare(TestData t1, TestData t2) {
            if (t1.isExecuting() && !t2.isExecuting())
            {
                return -1;
            } else if (!t1.isExecuting() && t2.isExecuting())
            {
                return 1;
            }
            else if (t1.isExecuting() && t2.isExecuting())
            {
                return 0;
            }
            else
            {
                return t1.getExecutionTime() - t2.getExecutionTime();
            }
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
                remainingProcess.addAll(arrivals.get(timer));
                arrivals.remove(timer);
            }
            remainingProcess.sort(this.testDataComparator);
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
