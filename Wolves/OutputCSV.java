import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Deals with the simulations' results and outputs them to a CSV file
 */
public class OutputCSV {

    private int number_wolves;
    private int number_preys;
    private int[] wolves_types = new int[]{1,1,1};
    private boolean homogeneous;
    private int wolves_to_catch_preys;

    private double visibility;
    private int min_prays_captured;
    private String fileName;
    private String stringHeaderResume = " , #Wolves, #Preys, Wolf_Type, Homogeneous?, #Wolves_to_catch_preys, Visibility, Min_Preys_Captured";
    private String stringDataResume;

    public OutputCSV(int number_wolves, int number_preys, int visibility, int min_prays_captured, int wolves_to_catch_preys, boolean homogeneous){
        this.number_wolves = number_wolves;
        this.min_prays_captured = min_prays_captured;
        this.visibility = visibility;
        this.number_preys = number_preys;
        this.homogeneous = homogeneous;
        this.wolves_to_catch_preys = wolves_to_catch_preys;
        fileName = "6_FullHybrid.txt";
    }


    public void writeResume() {

        stringDataResume = "," + number_wolves +","+ number_preys+","+ wolves_types +","+homogeneous +","+wolves_to_catch_preys+","+visibility+","+min_prays_captured;
        try {
            String filepath = fileName;
            FileWriter fileWriter = new FileWriter(filepath,false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            printWriter.println(stringHeaderResume);
            printWriter.println(stringDataResume);
            printWriter.println("number_rounds");

            for (int i = 0; i < run.rounds.size();i++){
                printWriter.println(run.rounds.get(i));
            }
            printWriter.println();

            printWriter.flush();
            printWriter.close();
            System.out.println();
            System.out.println("Data is saved in: " + fileName);
        }
        catch (Exception e) {
            System.out.println("Not saved");
        }
    }
}