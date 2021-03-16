package agents;

import java.util.stream.Stream;

public class MinMaxAgent implements Agent {
    private double Q[][];
    private double pi[];
    private double alpha;
    private double alphaDecay;
    private double gamma;

    public MinMaxAgent(int numberOfActions) {
        Q = new double[numberOfActions][numberOfActions];
        pi = new double[numberOfActions];

        for (int i = 0; i < numberOfActions; i++) {
            for (int j = 0; j < numberOfActions; j++)
                Q[i][j] = 0;
            pi[i] = 1 / numberOfActions;
        }

        alpha = 0.4;
        alphaDecay = 1.0;
        gamma = 0.3;
    }

    @Override
    public double actionProb(int i) {
        return pi[i];
    }

    @Override
    public int selectAction() {
        return 0;
    }

    @Override
    public void update(int own, int other, double reward) {
        Q[own][other] = Q[own][other] * (1 - alpha) + alpha * (reward + gamma);
        alpha *= alphaDecay;

        int minimum = Integer.MAX_VALUE;
        for (int i = 0; i < Q.length; i++) {
            int sum = 0;
            for (int j = 0; j < Q.length; j++)
                sum += Q[i][j] * pi[i];
            minimum = (sum < minimum) ? sum : minimum;
        }

        for (int i = 0; i < pi.length; i++)
            pi[i] = Math.max(pi[i], minimum);
    }

    @Override
    public double getQ(int i) {
        return Q[i][0];
    }
}
