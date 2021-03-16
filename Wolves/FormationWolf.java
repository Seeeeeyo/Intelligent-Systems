import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FormationWolf implements Wolf {
    @Override
    public int[] moveAll(List<int[]> wolvesSight, List<int[]> preysSight) {
        int[] mymove = new int[2];
        double distance = 0;
        int indexDistance = 0;
        Random r = new Random();

        for (int i=1; i < wolvesSight.size(); i++){
            if (distance < (Math.hypot(wolvesSight.get(i)[0],wolvesSight.get(i)[1]))){
                distance = Math.hypot(wolvesSight.get(i)[0],wolvesSight.get(i)[1]);
                indexDistance = i;
            }
        }

        if (distance < 3) {
            if (preysSight.size() > 0) {
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
                // move to the closest prey
                mymove[0] = -(Integer.signum(preysSight.get(index_of_closest_prey)[0]));
                mymove[1] = -(Integer.signum(preysSight.get(index_of_closest_prey)[1]));
            }
            else {
                double rand = Math.random();
                if(rand < 0.2){
                    mymove[0] = r.nextInt(3) - 1;
                    mymove[1] = r.nextInt(3) - 1;
                }
            }
        }
        else{
            mymove[0] = -(Integer.signum(wolvesSight.get(indexDistance)[0]));
            mymove[1] = -(Integer.signum(wolvesSight.get(indexDistance)[1]));
        }

        return mymove;
    }

    public int moveLim(List<int[]> wolvesSight, List<int[]> preysSight) {
        Random r = new Random();
        return r.nextInt(4) + 1;
    }


}