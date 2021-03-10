import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FollowerWolf implements Wolf {

    @Override
    public int[] moveAll(List<int[]> wolvesSight, List<int[]> preysSight) {
        Random r = new Random();
        int[] mymove = new int[2];
        if(preysSight.size() == 0) {
            mymove[0] = r.nextInt(3) - 1;
            mymove[1] = r.nextInt(3) - 1;
        }
        else{
            double[] distances_to_preys = new double[preysSight.size()];
            // compute the distances between the wolves and the seen preys
            for(int i=1; i < distances_to_preys.length; i++){
                distances_to_preys[i] = Math.hypot(preysSight.get(i)[0],preysSight.get(i)[1]);
            }
            // find the closest prey
            int index_of_closest_prey = 0;
            for (int i=1; i < distances_to_preys.length; i++){
                if(distances_to_preys[i] < distances_to_preys[index_of_closest_prey]){
                    index_of_closest_prey = i;
                }
            }
            // move to the closest prey
            mymove[0] = -(Integer.signum(preysSight.get(index_of_closest_prey)[0]));
            mymove[1] = -(Integer.signum(preysSight.get(index_of_closest_prey)[1]));
            // then the wolves will follow their preys and hopefully randomly meet another wolf
        }
        return mymove;
    }

    public int moveLim(List<int[]> wolvesSight, List<int[]> preysSight) {
        Random r = new Random();
        return r.nextInt(4) + 1;
    }
}
