package Knapsack;

public class Item {
    private static final int Max_Weight = 10;

    private final int weight;
    private final int usefulness;

    public Item(int usefulness) {
        this.weight = (int) (Math.random() * Max_Weight) + 1;
        this.usefulness = usefulness;
    }

    public int getWeight() {
        return weight;
    }

    public int getUsefulness() {
        return usefulness;
    }

    @Override
    public String toString() {
        return "Item{" +
                "weight=" + weight +
                ", usefulness=" + usefulness +
                '}';
    }
}
