package problem.predator;

import java.util.ArrayList;

/**
 * Created by Dev on 27/04/2017.
 */
public class SimilarityManager {

    public static double[] flipStateHorizontally(double[] state) {
        double[] newState = new double[4];
        newState[0] = -state[0];
        newState[1] = state[1];
        newState[2] = -state[2];
        newState[3] = state[3];

        return newState;
    }

    public static double[] flipStateVertically(double[] state) {
        double[] newState = new double[4];
        newState[0] = state[0];
        newState[1] = -state[1];
        newState[2] = state[2];
        newState[3] = -state[3];

        return newState;
    }

    public static double[] flipPrey(double[] state) {
        double[] newState = new double[4];
        newState[0] = state[0];
        newState[1] = state[1];
        newState[2] = -state[2];
        newState[3] = -state[3];

        return newState;
    }

    public static double[] flipPreyX(double[] state) {
        double[] newState = new double[4];
        newState[0] = state[0];
        newState[1] = state[1];
        newState[2] = -state[2];
        newState[3] = state[3];

        return newState;
    }
    public static double[] flipPreyY(double[] state) {
        double[] newState = new double[4];
        newState[0] = state[0];
        newState[1] = state[1];
        newState[2] = -state[2];
        newState[3] = state[3];

        return newState;
    }

    public static double[] move(double[] state, int movementX, int movementY) {
        double[] newState = new double[4];
        newState[0] = Math.min(-19., Math.max(state[0] + movementX, 19.));
        newState[1] = Math.min(-19., Math.max(state[1] + movementY, 19.));
        newState[2] = Math.min(-19., Math.max(state[2] + movementX, 19.));
        newState[3] = Math.min(-19., Math.max(state[3] + movementY, 19.));

        return newState;
    }

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

        switch (action) {
            case 0:
                similarityRecords.add(new SimilarityRecord(flipStateVertically(state), 1, 1.0));
                similarityRecords.add(new SimilarityRecord(flipPrey(state), 1, 1.0));
                similarityRecords.add(new SimilarityRecord(flipPreyY(state), 1, 1.0));
                similarityRecords.add(new SimilarityRecord(flipPreyX(state), 0, 1.0));

                similarityRecords.add(new SimilarityRecord(move(state,0,-1), 4, 1.0));
                break;
            case 1:
                similarityRecords.add(new SimilarityRecord(flipStateVertically(state), 0, 1.0));
                similarityRecords.add(new SimilarityRecord(flipPrey(state), 0, 1.0));
                similarityRecords.add(new SimilarityRecord(flipPreyY(state), 0, 1.0));
                similarityRecords.add(new SimilarityRecord(flipPreyX(state), 1, 1.0));
                similarityRecords.add(new SimilarityRecord(move(state,0,1), 4, 1.0));
                break;
            case 2:
                similarityRecords.add(new SimilarityRecord(flipStateHorizontally(state), 3, 1.0));
                similarityRecords.add(new SimilarityRecord(flipPrey(state), 3, 1.0));
                similarityRecords.add(new SimilarityRecord(flipPreyX(state), 3, 1.0));
                similarityRecords.add(new SimilarityRecord(flipPreyY(state), 2, 1.0));
                similarityRecords.add(new SimilarityRecord(move(state,-1,0), 4, 1.0));
                break;
            case 3:
                similarityRecords.add(new SimilarityRecord(flipStateHorizontally(state), 2, 1.0));
                similarityRecords.add(new SimilarityRecord(flipPrey(state), 2, 1.0));
                similarityRecords.add(new SimilarityRecord(flipPreyX(state), 2, 1.0));
                similarityRecords.add(new SimilarityRecord(flipPreyY(state), 3, 1.0));
                similarityRecords.add(new SimilarityRecord(move(state,1, 0), 4, 1.0));
                break;
            case 4:
                similarityRecords.add(new SimilarityRecord(flipStateVertically(state), 4, 1.0));
                similarityRecords.add(new SimilarityRecord(flipStateHorizontally(state), 4, 1.0));


                similarityRecords.add(new SimilarityRecord(move(state,0,-1), 1, 1.0));
                similarityRecords.add(new SimilarityRecord(move(state,0,1), 0, 1.0));
                similarityRecords.add(new SimilarityRecord(move(state,1,0), 2, 1.0));
                similarityRecords.add(new SimilarityRecord(move(state,-1,0), 3, 1.0));
                break;
        }

        // *** END OF YOUR CODE ********************************************************************

        return similarityRecords;
    }
}
