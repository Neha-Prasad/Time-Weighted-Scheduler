package Algorithms.Genetic;

import TestDataGenerator.TestData;

import java.util.List;

public class Population {

    private Individual[] individuals;

    public Population(int populationSize, boolean initialise, List<TestData> testData) {
        individuals = new Individual[populationSize];
        if (initialise) {
            for (int i = 0; i < size(); i++) {
                Individual newIndividual = new Individual(testData.size());
                newIndividual.generateIndividual(testData);
                saveIndividual(i, newIndividual);
            }
        }
    }

    public Individual getIndividual(int index) {
        return individuals[index];
    }

    public Individual getFittest(FitnessCalculator fitnessCalculator) {
        Individual fittest = individuals[0];
        for (int i = 0; i < size(); i++) {
            if (getIndividual(i).getFitness(fitnessCalculator) < fittest.getFitness(fitnessCalculator)) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    public int size() {
        return individuals.length;
    }

    public void saveIndividual(int index, Individual indiv) {
        individuals[index] = indiv;
    }
}
