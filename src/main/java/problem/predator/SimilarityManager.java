package problem.predator;

import java.util.ArrayList;

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
        double[] newState = state.clone();
        if (action == 0 && newState[1] > 0 && newState[3] > 0) {
            newState[1] = state[1] - 1;
            newState[3] = state[3] - 1;
        } else if (action == 1 && newState[1] < 19 && newState[3] < 19) {
            newState[1] = state[1] + 1;
            newState[3] = state[3] + 1;
        } else if (action == 2 && newState[0] > 0 && newState[2] > 0) {
            newState[0] = state[0] - 1;
            newState[2] = state[2] - 1;
        } else if (action == 3 && newState[0] < 19 && newState[2] < 19) {
            newState[0] = state[0] + 1;
            newState[2] = state[2] + 1;
        }

        // up
        double[] SimilarAction = newState.clone();
        if (newState[1] > 0 && newState[3] > 0) {
            SimilarAction[1] = newState[1] - 1;
            SimilarAction[3] = newState[3] - 1;
            SimilarityRecord record = new SimilarityRecord(SimilarAction, 1, 1);
            similarityRecords.add(record);
        }

        // down
        SimilarAction = newState.clone();
        if (newState[1] < 19 && newState[3] < 19) {
            SimilarAction[1] = newState[1] + 1;
            SimilarAction[3] = newState[3] + 1;
            SimilarityRecord record = new SimilarityRecord(SimilarAction, 0, 1);
            similarityRecords.add(record);
        }

        // Left
        SimilarAction = newState.clone();
        if (newState[0] > 0 && newState[2] > 0) {
            SimilarAction[0] = newState[0] - 1;
            SimilarAction[2] = newState[2] - 1;
            SimilarityRecord record = new SimilarityRecord(SimilarAction, 3, 1);
            similarityRecords.add(record);
        }

        // Right
        SimilarAction = newState.clone();
        if (newState[0] < 19 && newState[2] < 19) {
            SimilarAction[0] = newState[0] + 1;
            SimilarAction[2] = newState[2] + 1;
            SimilarityRecord record = new SimilarityRecord(SimilarAction, 2, 1);
            similarityRecords.add(record);
        }

        // up
        SimilarAction = newState.clone();
        if (newState[1] > 1 && newState[3] > 1) {
            SimilarAction[1] = newState[1] - 2;
            SimilarAction[3] = newState[3] - 2;
            SimilarityRecord record = new SimilarityRecord(SimilarAction, 1, 0.8);
            similarityRecords.add(record);
        }

        // down
        SimilarAction = newState.clone();
        if (newState[1] < 18 && newState[3] < 18) {
            SimilarAction[1] = newState[1] + 2;
            SimilarAction[3] = newState[3] + 2;
            SimilarityRecord record = new SimilarityRecord(SimilarAction, 0, 0.8);
            similarityRecords.add(record);
        }

        // Left
        SimilarAction = newState.clone();
        if (newState[0] > 1 && newState[2] > 1) {
            SimilarAction[0] = newState[0] - 2;
            SimilarAction[2] = newState[2] - 2;
            SimilarityRecord record = new SimilarityRecord(SimilarAction, 3, 0.8);
            similarityRecords.add(record);
        }

        // Right
        SimilarAction = newState.clone();
        if (newState[0] < 18 && newState[2] < 18) {
            SimilarAction[0] = newState[0] + 2;
            SimilarAction[2] = newState[2] + 2;
            SimilarityRecord record = new SimilarityRecord(SimilarAction, 2, 0.6);
            similarityRecords.add(record);
        }
        
        //Stay
        SimilarAction = newState.clone();
        SimilarityRecord record = new SimilarityRecord(SimilarAction, 4, 1);
        similarityRecords.add(record);

        // *** END OF YOUR CODE ********************************************************************
        return similarityRecords;
    }
}
