package Knapsack;

import Generic.GeneticAlgorithm;

import java.util.stream.Stream;

public class Knapsack extends GeneticAlgorithm {
    public static void main(String[] args) {
        (new Knapsack()).run(100, 0.5, 0.1, 1000000, 10);
    }

    @Override
    public Generic.Individual[] generatePopulation(int size) {
        Generic.Individual[] population = new Generic.Individual[size];

        for (int i = 0; i < population.length; i++)
            population[i] = new Individual();

        return population;
    }

    @Override
    public Generic.Individual[] selection(Generic.Individual[] population, int numberOfParents) {
        return Stream.of(population).sorted((a, b) -> 0 - Integer.compare(a.getFitness(), b.getFitness())).limit(numberOfParents).toArray(Generic.Individual[]::new);
    }

    @Override
    public Generic.Individual[] crossover(Generic.Individual[] parents, double probability, int sizePopulation) {

        int numberOfChildren = sizePopulation - parents.length;
        Generic.Individual[] children = new Individual[numberOfChildren];

        int i = 0;
        int j = 0;
        while (i < children.length) {
            j++;
            if (Math.random() > probability)
                continue;

            int[] indexParent = new int[]{j % parents.length, (j + 1) % parents.length};
            int crossoverPoint = (int) Math.random() * parents.length;

            boolean[] firstParentItems = ((Individual) parents[indexParent[0]]).getItems();
            boolean[] secondParentItems = ((Individual) parents[indexParent[0]]).getItems();

            boolean[] childItems = new boolean[firstParentItems.length];
            for (int k = 0; k < childItems.length; k++) {
                childItems[k] = (k < crossoverPoint) ? firstParentItems[k] : secondParentItems[k];
            }

            children[i] = new Individual(childItems);
            i++;
        }

        return children;
    }

    @Override
    public Generic.Individual[] mutation(Generic.Individual[] children, double probability) {
        for (int i = 0; i < children.length; i++) {
            if (Math.random() > probability)
                continue;

            Individual child = (Individual) children[i];
            boolean[] items = child.getItems();

            int mutationIndex = (int) Math.random() * items.length;
            items[mutationIndex] = !items[mutationIndex];

            child.setItems(items);
        }

        return children;
    }
}
