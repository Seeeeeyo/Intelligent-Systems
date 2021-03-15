import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HybridWolf implements Wolf {
    private double[] xWolves;
    private int state = 1;
    private int indexWolfFollowing = -1;
    @Override
    public int[] moveAll(List<int[]> wolvesSight, List<int[]> preysSight) {
        Random r = new Random();
        int[] mymove = new int[2];

        if (state == 1) {

            int direction = r.nextInt(3) - 1;
            double rand = Math.random();
            if (rand < 0.2){
                direction = 1;
            }
            mymove[1] = direction;
            mymove[0] = 0;
            //mymove[1] = r.nextInt(3) - 1;

            if (preysSight.size() > 0){
                state = 3;
            }
            boolean init = false;
            if (xWolves == null) {
                xWolves = new double[wolvesSight.size()];
                init = true;
            }

            for (int i = 1; i < wolvesSight.size(); i++) {
                if ((xWolves[i] != wolvesSight.get(i)[0]) && (!init)) {
                    if ((Math.hypot(wolvesSight.get(i)[0],wolvesSight.get(i)[1])) != 0) {
                        state = 2;
                        indexWolfFollowing = i;
                    }
                }
                xWolves[i] = wolvesSight.get(i)[0];
            }
        }

        if (state == 2) {

            boolean together = true;

            for (int i=1; i < wolvesSight.size(); i++){
                if (2 < (Math.hypot(wolvesSight.get(i)[0],wolvesSight.get(i)[1]))){
                    together = false;
                }
            }

            if (together){
                state = 1;
            }

            mymove[0] = -(Integer.signum(wolvesSight.get(indexWolfFollowing)[0]));
            mymove[1] = -(Integer.signum(wolvesSight.get(indexWolfFollowing)[1]));
        }

        if (state == 3){
            boolean allFollowing = true;
            for (int i = 1; i < wolvesSight.size(); i++) {
                if (xWolves[i] == wolvesSight.get(i)[0]) {
                    //System.out.println("old x: " +  xWolves[i]);
                    //System.out.println("current x: " + wolvesSight.get(i)[0]);
                   allFollowing = false;
                }
                xWolves[i] = wolvesSight.get(i)[0];
            }

            if (allFollowing){
                double rand = Math.random();
                if (rand < 0.33){
                    state = 1;
                }
            }

            if (preysSight.size() == 0){
                state = 1;
            }
            else {
                double[] distances_to_preys = new double[preysSight.size()];
                // compute the distances between the wolves and the seen preys
                for (int i = 1; i < distances_to_preys.length; i++) {
                    distances_to_preys[i] = Math.hypot(preysSight.get(i)[0], preysSight.get(i)[1]);
                }
                // find the closest prey
                int index_of_closest_prey = 0;
                for (int i = 1; i < distances_to_preys.length; i++) {
                    if (distances_to_preys[i] < distances_to_preys[index_of_closest_prey]) {
                        index_of_closest_prey = i;
                    }
                }
                mymove[0] = -(Integer.signum(preysSight.get(index_of_closest_prey)[0]));
                mymove[1] = -(Integer.signum(preysSight.get(index_of_closest_prey)[1]));
            }
        }
        //System.out.println("state: " + state);
        return mymove;
    }

    // improvement for next phase, move to the closest following wolf

    public int moveLim(List<int[]> wolvesSight, List<int[]> preysSight) {
        Random r = new Random();
        return r.nextInt(4) + 1;
    }
}