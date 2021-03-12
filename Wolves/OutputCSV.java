import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Deals with the simulations' results and outputs them to a CSV file
 */
public class OutputCSV {

    private static int number_wolves =
    private static int number_preys =
    private static int[] wolves_types =
    private static boolean homogeneous =
    private static int wolves_to_catch_preys =
    private static int number_rounds =
    private static double visibility =
    private static int min_prays_captured =


    private static String fileName;

    public static void set(){
        fileName = "output.txt";
    }

    private static String stringHeaderResume = " #Wolves, #Preys, Wolf_Type, Homogeneous?, #Wolves_to_catch_preys, #rounds, Visibility, Min_Preys_Captured";
    private static String stringDataResume;

    public static void writeResume() {

        stringDataResume = consumerAvgWaitingTime+","+corporateAvgWaitingTime+","+avgNumberCustomersInSystem+","+numCSACorporateShift1+","+numCSACorporateShift2+","+numCSACorporateShift3+","+numCSAConsumerShift1+","+numCSAConsumerShift2+","+numCSAConsumerShift3+","+costShift1+","+costShift2+","+costShift3+","+k;
        String[][] dataConsumer = new String[storageMatrixConsumer.length][storageMatrixConsumer[0].length];
        for (int i = 0; i < storageMatrixConsumer.length; i++) {
            for (int j = 0; j < storageMatrixConsumer[0].length; j++) {
                dataConsumer[i][j] = Double.toString(storageMatrixConsumer[i][j]);
            }
        }

        String[][] dataCorporate = new String[storageMatrixCorporate.length][storageMatrixCorporate[0].length];
        for (int i = 0; i < storageMatrixCorporate.length; i++) {
            for (int j = 0; j < storageMatrixCorporate[0].length; j++) {
                dataCorporate[i][j] = Double.toString(storageMatrixCorporate[i][j]);
            }
        }

        try {
            String filepath = fileName;
            FileWriter fileWriter = new FileWriter(filepath,false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            printWriter.println(stringHeaderResume);
            printWriter.println(stringDataResume);

            printWriter.println();

            printWriter.println(stringHeaderDataConsumer);
            for (int i = 0; i < dataConsumer.length; i++) {
                printWriter.println(dataConsumer[i][0]+","+dataConsumer[i][1]+","+dataConsumer[i][2]+","+dataConsumer[i][3]+","+dataConsumer[i][4]+","+dataConsumer[i][5]);
            }

            printWriter.println();

            printWriter.println(stringHeaderDataCorporate);
            for (int i = 0; i < dataCorporate.length; i++) {
                printWriter.println(dataCorporate[i][0]+","+dataCorporate[i][1]+","+dataCorporate[i][2]+","+dataCorporate[i][3]+","+dataCorporate[i][4]+","+dataCorporate[i][5]);
            }

            printWriter.flush();
            printWriter.close();
            System.out.println();
            System.out.println("Data is save in: " + fileName);
        }
        catch (Exception e) {
            System.out.println("Recorded not save");
        }
    }
}