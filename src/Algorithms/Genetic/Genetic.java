package Algorithms.Genetic;

import Algorithms.IAlgorithm;
import TestDataGenerator.TestData;
import java.util.List;

public class Genetic implements IAlgorithm {

    private static double uniformRate;
    private static double mutationRate;
    private static int tournamentSize;
    private static boolean elitism;
    private static FitnessCalculator fitnessCalculator;

    public static Population evolvePopulation(Population population, List<TestData> tests) {
        Population newPopulation = new Population(population.size(), false, tests);

        // Save the best solution
        if (elitism) {
            newPopulation.saveIndividual(0, population.getFittest(fitnessCalculator));
        }

        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }

        // Crossover
        for (int i = elitismOffset; i < population.size(); i++) {
            Individual indiv1 = tournamentSelection(population, tests);
            Individual indiv2 = tournamentSelection(population, tests);
            Individual newIndiv = crossover(indiv1, indiv2, tests);
            newPopulation.saveIndividual(i, newIndiv);
        }

        // Mutate
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    private static Individual crossover(Individual individual1, Individual individual2, List<TestData> testData) {
        Individual newIndividual = new Individual(testData.size());
        // Loop through genes and place them at indices we get from cross over
        for (TestData test: testData) {
            TestData copy = new TestData(test);
            if (Math.random() <= uniformRate) {
                int indexInIndividual1 = individual1.getGeneIndex(copy);
                newIndividual.setGene(indexInIndividual1, copy);
            }
            else {
                int indexInIndividual2 = individual2.getGeneIndex(copy);
                newIndividual.setGene(indexInIndividual2, copy);
            }
        }

        return newIndividual;
    }

    private static void mutate(Individual indiv) {
        for (int i = 0; i < indiv.size(); i++) {
            if (Math.random() <= mutationRate) {
                // Swap random genes
                indiv.swapGenes(i);
            }
        }
    }

    private static Individual tournamentSelection(Population pop, List<TestData> tests) {
        Population tournament = new Population(tournamentSize, false, tests);
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        Individual fittest = tournament.getFittest(fitnessCalculator);
        return fittest;
    }

    @Override
    public String getName() {
        return "Genetic";
    }

    @Override
    public int Run(List<TestData> tests, int numberOfHils) {
        // Set params
        this.uniformRate = 0.5 ;
        this.elitism = true;
        this.mutationRate = 0.015;
        this.tournamentSize = 25;

        this.fitnessCalculator = new FitnessCalculator();

        // Set any random solution as candidate
        fitnessCalculator.setSolution(tests, numberOfHils);

        // Initial population
        Population population = new Population(25, true, tests);

        // Evolve the population to a threshold in order to reach get an optimum solution.
        // Max fitness is the population with min time to run
        while (fitnessCalculator.getFitnessWithMinRunTime() < population.getFittest(fitnessCalculator).getFitness(fitnessCalculator)) {
            population = this.evolvePopulation(population, tests);
        }

        return population.getFittest(fitnessCalculator).getFitness(fitnessCalculator);
    }
}
