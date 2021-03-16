package Knapsack;

import java.util.Arrays;
import java.util.Random;


public class Individual implements Generic.Individual {
    private static Item[] commonItems;
    private static int numberOfItems = 10;
    private static int weightAllowance = 20;

    private boolean[] items = new boolean[numberOfItems];
    private int fitness;

    public Individual() {
        if (Individual.commonItems == null)
            generateCommonItems(Individual.numberOfItems);

        Random random = new Random();
        for (int i = 0; i < this.items.length; i++)
            this.items[i] = random.nextBoolean();

        this.computeFitness();
    }

    public Individual(boolean[] items) {
        this.items = items;
    }

    private static void generateCommonItems(int numberOfItems) {
        Individual.commonItems = new Item[numberOfItems];

        for (int i = 0; i < numberOfItems; i++) {
            Individual.commonItems[i] = new Item(i + 1);
        }

        System.out.print("List of items -> ");
        System.out.println(Arrays.toString(Individual.commonItems));
    }

    @Override
    public void computeFitness() {
        int sumWeight = 0;
        for (int i = 0; i < this.items.length; i++)
            if (items[i])
                sumWeight += Individual.commonItems[i].getWeight();

        if (sumWeight > weightAllowance)
            fitness = 0;
        else {
            int sumUsefulness = 0;
            for (int i = 0; i < this.items.length; i++)
                if (items[i])
                    sumUsefulness += Individual.commonItems[i].getUsefulness();

            fitness = sumUsefulness;
        }
    }

    @Override
    public int getFitness() {
        return this.fitness;
    }

    public static int getNumberOfItems() {
        return numberOfItems;
    }

    public void setItems(boolean[] items) {
        this.items = items;
    }

    public boolean[] getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Individual{" +
                "items=" + Arrays.toString(items) +
                ", fitness=" + fitness +
                '}';
    }

    protected Individual clone() {
        Individual clone = new Individual();

        clone.setItems(this.getItems());
        clone.computeFitness();

        return clone;
    }
}
