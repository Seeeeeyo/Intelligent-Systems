import java.util.ArrayList;

public class run {
    public static ArrayList<Long> rounds= new ArrayList<>();
    //public static int num_iterations = 100;
    public static int counter = 1;
    public static Long StartTime = System.currentTimeMillis();

    public static void main(String[] args) {
        launch();
    }

    public static void launch() {
        int width = 50;
        int height = 50;
        int squaresize = 15;
        int delay = 1;
        counter++;
        System.out.println(counter);
        WolvesApp wol = new WolvesApp("Hungry Hungry Wolves", height, width, squaresize);
        System.out.println("counter: " + counter);
        wol.runGoL(delay);
    }
}
