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
        double[] newState = new double[4];

        // moving in the right direction
        if ((state[2] < 0) && (state[3] < 0) && (action==3))
            similarityRecords.add(new SimilarityRecord(state,1,0.5));
        newState[0] = state[0];
        newState[1] = state[1];
        newState[2] = state[3];
        newState[3] = state[2];
        similarityRecords.add(new SimilarityRecord(newState,1,0.5));
        if ((state[2] < 0) && (state[3] < 0) && (action==1))
            similarityRecords.add(new SimilarityRecord(state,3,0.5));
        newState[0] = state[0];
        newState[1] = state[1];
        newState[2] = state[3];
        newState[3] = state[2];
        similarityRecords.add(new SimilarityRecord(newState,3,0.5));
        if ((state[2] > 0) && (state[3] > 0) && (action==2))
            similarityRecords.add(new SimilarityRecord(state,0,0.5));
        newState[0] = state[0];
        newState[1] = state[1];
        newState[2] = state[3];
        newState[3] = state[2];
        similarityRecords.add(new SimilarityRecord(newState,0,0.5));
        if ((state[2] > 0) && (state[3] > 0) && (action==0))
            similarityRecords.add(new SimilarityRecord(state,2,0.5));
            newState[0] = state[0];
            newState[1] = state[1];
            newState[2] = state[3];
            newState[3] = state[2];
            similarityRecords.add(new SimilarityRecord(newState,2,0.5));

        // winning the game
        if (state[2] == -1 && state[3] == 0 && action==3)
            newState[0] = state[0];
            newState[1] = state[1];
            newState[2] = 0;
            newState[3] = 1;
            similarityRecords.add(new SimilarityRecord(newState,0,1));
            newState[0] = state[0];
            newState[1] = state[1];
            newState[2] = 0;
            newState[3] = -1;
            similarityRecords.add(new SimilarityRecord(newState,1,1));
            newState[0] = state[0];
            newState[1] = state[1];
            newState[2] = 1;
            newState[3] = 0;
            similarityRecords.add(new SimilarityRecord(newState,2,1));
        if (state[2] == 0 && state[3] == 1 && action==0)
            newState[0] = state[0];
            newState[1] = state[1];
            newState[2] = -1;
            newState[3] = 0;
            similarityRecords.add(new SimilarityRecord(newState,3,1));
            newState[0] = state[0];
            newState[1] = state[1];
            newState[2] = 0;
            newState[3] = -1;
            similarityRecords.add(new SimilarityRecord(newState,1,1));
            newState[0] = state[0];
            newState[1] = state[1];
            newState[2] = 1;
            newState[3] = 0;
            similarityRecords.add(new SimilarityRecord(newState,2,1));
        if (state[2] == 0 && state[3] == -1 && action==1)
            newState[0] = state[0];
            newState[1] = state[1];
            newState[2] = 0;
            newState[3] = 1;
            similarityRecords.add(new SimilarityRecord(newState,0,1));
            newState[0] = state[0];
            newState[1] = state[1];
            newState[2] = -1;
            newState[3] = 0;
            similarityRecords.add(new SimilarityRecord(newState,3,1));
            newState[0] = state[0];
            newState[1] = state[1];
            newState[2] = 1;
            newState[3] = 0;
            similarityRecords.add(new SimilarityRecord(newState,2,1));
        if (state[2] == 1 && state[3] == 0 && action==2)
            newState[0] = state[0];
            newState[1] = state[1];
            newState[2] = 0;
            newState[3] = 1;
            similarityRecords.add(new SimilarityRecord(newState,0,1));
            newState[0] = state[0];
            newState[1] = state[1];
            newState[2] = 0;
            newState[3] = -1;
            similarityRecords.add(new SimilarityRecord(newState,1,1));
            newState[0] = state[0];
            newState[1] = state[1];
            newState[2] = -1;
            newState[3] = 0;
            similarityRecords.add(new SimilarityRecord(newState,3,1));



        // *** END OF YOUR CODE ********************************************************************

        return similarityRecords;
    }
}
