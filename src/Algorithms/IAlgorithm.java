package Algorithms;

import TestDataGenerator.TestData;

import java.util.List;

public interface IAlgorithm {
    public String getName();
    public void Run(List<TestData> tests);
}
