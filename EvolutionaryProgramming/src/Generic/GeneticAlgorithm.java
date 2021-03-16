package Generic;

import java.util.Arrays;

public abstract class GeneticAlgorithm {
    public void run(int sizePopulation, double probabilityCrossover, double probabilityMutation, int maxGeneration, int numberOfParents) {
        Individual[] population = this.generatePopulation(sizePopulation);

        System.out.print("Population -> ");
        System.out.println(Arrays.toString(population));

        int generationNumber = 0;

        System.out.println("Current Max Fitness -> " + maxFitness(population));

        do {
            //System.out.println("-------------------");
            generationNumber++;
            //System.out.println("Generation -> " + generationNumber);

            Individual[] parents = this.selection(population, numberOfParents);

            //System.out.print("Parents -> ");
            //System.out.println(parents.length + " " + Arrays.toString(parents));

            Individual[] children = this.crossover(parents, probabilityCrossover, sizePopulation);
            children = this.mutation(children, probabilityMutation);

            for (int i = 0; i < numberOfParents; i++) {
                population[i] = parents[i];
            }

            for (int i = 0; i < children.length; i++) {
                population[numberOfParents + i] = children[i];
            }

            this.setFitness(population);

            //System.out.println("Current Max Fitness -> " + maxFitness(population));
        } while (generationNumber < maxGeneration);

        System.out.println("Current Max Fitness -> " + maxFitness(population));
    }

    public int maxFitness(Individual[] population) {
        int out = population[0].getFitness();
        for (Individual individual : population)
            if (individual.getFitness() > out)
                out = individual.getFitness();
        return out;
    }

    public void setFitness(Individual[] population) {
        for (Individual individual : population)
            individual.computeFitness();
    }

    public abstract Individual[] generatePopulation(int size);

    public abstract Individual[] selection(Individual[] population, int numberOfParents);

    public abstract Individual[] crossover(Individual[] parents, double probability, int sizePopulation);

    public abstract Individual[] mutation(Individual[] parents, double probability);
}
