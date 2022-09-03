package Algorithms.Genetic;

import TestDataGenerator.TestData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Individual {

    public Random random = new Random();

    public ArrayList<TestData> getGenes() {
        return genes;
    }

    private ArrayList<TestData> genes;

    public Individual(int size) {
        this.genes = new ArrayList<>(size);
        for (int i=0; i < size; i++) {
            this.genes.add(null);
        }
    }

    // Create a random individual
    public void generateIndividual(List<TestData> testData) {
        this.genes = new ArrayList<>();
        for (TestData test: testData) {
            this.genes.add(new TestData(test));
        }
        Collections.shuffle(this.genes);
    }

    public void setGene(int index, TestData test) {
        genes.set(index, test);
    }

    public void swapGenes(int index) {
        int randomIndex = this.random.nextInt(this.genes.size());
        TestData randomGene = genes.get(randomIndex);
        TestData geneToBeSwapped = genes.get(index);
        genes.set(index, randomGene);
        genes.set(randomIndex, geneToBeSwapped);
    }

    public int getGeneIndex(TestData testData) {
        for(int i=0; i < this.genes.size(); i++) {
            if (genes.get(i).equals(testData)) {
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return genes.size();
    }

    public int getFitness(FitnessCalculator fitnessCalculator) {
        return fitnessCalculator.getFitness(this);
    }
}
