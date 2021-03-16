package Wolves;

import java.util.List;
import java.util.Random;

public class FormationWolf implements Wolf {
    @Override
    public int[] moveAll(List<int[]> wolvesSight, List<int[]> preysSight) {
        int[] mymove = new int[2];
        double distance = 0;
        int indexDistance = 0;
        Random r = new Random();

        // Compute the distance between each wolf, and store it with the index of the wolf.
        for (int i = 1; i < wolvesSight.size(); i++) {
            if (distance < (Math.hypot(wolvesSight.get(i)[0], wolvesSight.get(i)[1]))) {
                distance = Math.hypot(wolvesSight.get(i)[0], wolvesSight.get(i)[1]);
                indexDistance = i;
            }
        }

        // If the distance is less or equals to the number of wolves (which means the wolves are really close)
        if (distance <= 3) {
            // If there is at least one prey in sight
            if (preysSight.size() > 0) {
                double[] distances_to_preys = new double[preysSight.size()];
                // Compute the distances between the wolves and the seen preys
                for (int i = 1; i < distances_to_preys.length; i++) {
                    distances_to_preys[i] = Math.hypot(preysSight.get(i)[0], preysSight.get(i)[1]);
                }
                // Find the closest prey
                int index_of_closest_prey = 0;
                for (int i = 1; i < distances_to_preys.length; i++) {
                    if (distances_to_preys[i] < distances_to_preys[index_of_closest_prey]) {
                        index_of_closest_prey = i;
                    }
                }
                // Move to the closest prey
                mymove[0] = -(Integer.signum(preysSight.get(index_of_closest_prey)[0]));
                mymove[1] = -(Integer.signum(preysSight.get(index_of_closest_prey)[1]));
            // Else (no prey in sight)
            } else {
                // A wolf has 1 chance on 5 to move randomly (So the group will move)
                double rand = Math.random();
                if (rand < 0.2) {
                    mymove[0] = r.nextInt(3) - 1;
                    mymove[1] = r.nextInt(3) - 1;
                }
            }
        // Else (the wolves are not yet close enough)
        } else {
            // Move to the wolf with the largest distance
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