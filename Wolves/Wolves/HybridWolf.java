package Wolves;

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

        // The state 1 is the default state (Move on the same line, look at other wolves and preys)
        if (state == 1) {
            int direction = r.nextInt(3) - 1;

            mymove[1] = direction;

            // If there is at least one prey in sight, change state to 3
            if (preysSight.size() > 0) {
                state = 3;
            }

            // If we have no memory about other wolves, we create one
            boolean init = false;
            if (xWolves == null) {
                xWolves = new double[wolvesSight.size()];
                init = true;
            }

            // For each wolves, compare the current position with the one in memory (except when the memory as been initialize)
            // If one other wolves is not on the same line than before, store index of the wolf and change state to 2, even if we
            // change to state 3 before.
            // Update the memory.
            for (int i = 1; i < wolvesSight.size(); i++) {
                if ((xWolves[i] != wolvesSight.get(i)[0]) && (!init)) {
                    if ((Math.hypot(wolvesSight.get(i)[0], wolvesSight.get(i)[1])) != 0) {
                        state = 2;
                        indexWolfFollowing = i;
                    }
                }

                xWolves[i] = wolvesSight.get(i)[0];
            }
        }

        // The state 2 is the "formation" state, if a wolf didn't stay on his line, it means he is following a prey so we move to him.
        if (state == 2) {

            // Look if the wolves are all close one to another, if it is the case, change the state to 1.
            // This prevent the wolves to be all in state 2 and not move at all (if the wolf with the prey lose its prey) .
            boolean together = true;
            for (int i = 1; i < wolvesSight.size(); i++)
                if (2 < (Math.hypot(wolvesSight.get(i)[0], wolvesSight.get(i)[1])))
                    together = false;

            if (together)
                state = 1;

            // Move to the wolf to follow
            mymove[0] = -(Integer.signum(wolvesSight.get(indexWolfFollowing)[0]));
            mymove[1] = -(Integer.signum(wolvesSight.get(indexWolfFollowing)[1]));
        }

        // The state 3 is the "follower" state, if a prey is in the sight of a wolf, it will follow the prey
        if (state == 3) {
            // Look if all the wolves are following a prey (e.g. they all got a prey in sight in the same). If it is the case,
            // each wolg jave 1 chance on 3 to change the state to 1.
            boolean allFollowing = true;
            for (int i = 1; i < wolvesSight.size(); i++) {
                if (xWolves[i] == wolvesSight.get(i)[0]) {
                    allFollowing = false;
                }
                xWolves[i] = wolvesSight.get(i)[0];
            }

            if (allFollowing) {
                double rand = Math.random();
                if (rand < 0.33) {
                    state = 1;
                }
            }

            // If the wolves lose its prey, it go back to state 1
            if (preysSight.size() == 0) {
                state = 1;
                // Else we follow the prey
            } else {
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

                // Move to the prey
                mymove[0] = -(Integer.signum(preysSight.get(index_of_closest_prey)[0]));
                mymove[1] = -(Integer.signum(preysSight.get(index_of_closest_prey)[1]));
            }
        }

        return mymove;
    }

    public int moveLim(List<int[]> wolvesSight, List<int[]> preysSight) {
        Random r = new Random();
        int mymove = 0;

        // The state 1 is the default state (Move on the same line, look at other wolves and preys)
        if (state == 1) {
            boolean direction = r.nextBoolean();

            mymove = direction ? 2 : 4;

            // If there is at least one prey in sight, change state to 3
            if (preysSight.size() > 0) {
                state = 3;
            }

            // If we have no memory about other wolves, we create one
            boolean init = false;
            if (xWolves == null) {
                xWolves = new double[wolvesSight.size()];
                init = true;
            }

            // For each wolves, compare the current position with the one in memory (except when the memory as been initialize)
            // If one other wolves is not on the same line than before, store index of the wolf and change state to 2, even if we
            // change to state 3 before.
            // Update the memory.
            for (int i = 1; i < wolvesSight.size(); i++) {
                if ((xWolves[i] != wolvesSight.get(i)[0]) && (!init)) {
                    if ((Math.hypot(wolvesSight.get(i)[0], wolvesSight.get(i)[1])) != 0) {
                        state = 2;
                        indexWolfFollowing = i;
                    }
                }

                xWolves[i] = wolvesSight.get(i)[0];
            }
        }

        // The state 2 is the "formation" state, if a wolf didn't stay on his line, it means he is following a prey so we move to him.
        if (state == 2) {

            // Look if the wolves are all close one to another, if it is the case, change the state to 1.
            // This prevent the wolves to be all in state 2 and not move at all (if the wolf with the prey lose its prey) .
            boolean together = true;
            for (int i = 1; i < wolvesSight.size(); i++)
                if (2 < (Math.hypot(wolvesSight.get(i)[0], wolvesSight.get(i)[1])))
                    together = false;

            if (together)
                state = 1;

            // Move to the wolf to follow
            if(wolvesSight.get(indexWolfFollowing)[0] > wolvesSight.get(indexWolfFollowing)[1])
                if (Integer.signum(wolvesSight.get(indexWolfFollowing)[0]) == 1)
                    mymove = 1;
                else if (Integer.signum(wolvesSight.get(indexWolfFollowing)[0]) == -1)
                    mymove = 3;
            else
                if (Integer.signum(wolvesSight.get(indexWolfFollowing)[1]) == 1)
                    mymove = 4;
                else if (Integer.signum(wolvesSight.get(indexWolfFollowing)[1]) == -1)
                    mymove = 2;

        }

        // The state 3 is the "follower" state, if a prey is in the sight of a wolf, it will follow the prey
        if (state == 3) {
            // Look if all the wolves are following a prey (e.g. they all got a prey in sight in the same). If it is the case,
            // each wolg jave 1 chance on 3 to change the state to 1.
            boolean allFollowing = true;
            for (int i = 1; i < wolvesSight.size(); i++) {
                if (xWolves[i] == wolvesSight.get(i)[0]) {
                    allFollowing = false;
                }
                xWolves[i] = wolvesSight.get(i)[0];
            }

            if (allFollowing) {
                double rand = Math.random();
                if (rand < 0.33) {
                    state = 1;
                }
            }

            // If the wolves lose its prey, it go back to state 1
            if (preysSight.size() == 0) {
                state = 1;
                // Else we follow the prey
            } else {
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

                // Move to the prey
                if(Math.abs(preysSight.get(index_of_closest_prey)[0]) > Math.abs(preysSight.get(index_of_closest_prey)[1])) {
                    if (Integer.signum(preysSight.get(index_of_closest_prey)[0]) == 1)
                        mymove = 1;
                    else if (Integer.signum(preysSight.get(index_of_closest_prey)[0]) == -1)
                        mymove = 3;
                } else {
                    if (Integer.signum(preysSight.get(index_of_closest_prey)[1]) == 1)
                        mymove = 4;
                    else if (Integer.signum(preysSight.get(index_of_closest_prey)[1]) == -1)
                        mymove = 2;
                }
            }
        }

        return mymove;
    }
}