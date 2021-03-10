import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//  TO COMPLETE


public class FormationWolf implements Wolf {
    @Override
    public int[] moveAll(List<int[]> wolvesSight, List<int[]> preysSight) {
        int[] mymove = new int[2];

        double[] distances_btw_wolves = new double[wolvesSight.size()];
        for (int i=1; i < distances_btw_wolves.length; i++){
            distances_btw_wolves[i] = Math.hypot(wolvesSight.get(i)[0],wolvesSight.get(i)[1]);
        }

        for (int i=1; i < distances_btw_wolves.length; i++){
            if (distances_btw_wolves[i] < 1){
                Random r = new Random();
                mymove[0] = r.nextInt(3)-1;
                mymove[1] = r.nextInt(3)-1;
            }
            else{
                mymove[0] = -(Integer.signum(wolvesSight.get(1)[0]));
                mymove[1] = -(Integer.signum(wolvesSight.get(1)[1]));
            }
        }





        return mymove;
    }

    public int moveLim(List<int[]> wolvesSight, List<int[]> preysSight) {
        Random r = new Random();
        return r.nextInt(4) + 1;
    }


}