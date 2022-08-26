package Algorithms;

import TestDataGenerator.TestData;

import java.util.List;

public interface IAlgorithm {
    public String getName();
    public int Run(List<TestData> tests, int numberOfHils);
}
