package problem.predator;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Dev on 27/04/2017.
 */
public class SimilarityManager {

    /**
     *
     * @param state represents the state:
     *              [0] X: predator <-> other predator
     *              [1] Y: predator <-> other predator
     *              [2] X: predator <-> pray
     *              [3] Y: predator <-> pray
     * @param action represent the action:
     *               0: UP
     *               1: DOWN
     *               2: LEFT
     *               3: RIGHT
     *               4: STAY
     * @return
     */
    public static ArrayList<SimilarityRecord> getSimilarityRecords(double[] state, int action) {
        ArrayList<SimilarityRecord> similarityRecords = new ArrayList<>();

        // *** YOUR CODE HERE **********************************************************************
        similarityRecords.addAll(flip(state, action));
        // *** END OF YOUR CODE ********************************************************************

        return similarityRecords;
    }

    private static ArrayList<SimilarityRecord> flip(double[] state, int action) {
        ArrayList<SimilarityRecord> similarityRecords = new ArrayList<>();
        double[] state_flip = new double[4];
        double[] state2 = new double[4];
        for (int i = 0; i < state_flip.length; i++) {
            state_flip[i] = state[i] * -1;
        }
        state2[0] = state[0];
        state2[1] = state[1];
        state2[2] = state[2] * -1;
        state2[3] = state[3] * -1;

        int new_action = 4;
        if (action == 0) {
            new_action = 1;
        } else if (action == 1) {
            new_action = 0;
        } else if (action == 2) {
            new_action = 3;
        } else if (action == 3) {
            new_action = 2;
        }
        similarityRecords.add(new SimilarityRecord(state_flip, new_action, 1));
//        similarityRecords.add(new SimilarityRecord(state2, action, 0.9));
        return similarityRecords;
    }

    private ArrayList<SimilarityRecord> dist(double[] state, int action) {
        ArrayList<SimilarityRecord> similarityRecords = new ArrayList<>();
        int min = (int) Arrays.stream(state).min().getAsDouble();
        int max = (int) Arrays.stream(state).max().getAsDouble();

        boolean stop = false;
        for (int i = 1; i < Math.min(max - min, 10); i++) {
            double[] new_state = state.clone();
            for (int j = 0; j < new_state.length; j++) {
                if (new_state[j] < 20 || new_state[j] > 20) {
                    stop = true;
                    break;
                }
                new_state[j] += i;
            }
            if (stop) {
                stop = false;
                break;
            }
            similarityRecords.add(new SimilarityRecord(new_state, action, 1.0 - 0.05*Math.abs(i)));
        }
        return similarityRecords;
    }
}
