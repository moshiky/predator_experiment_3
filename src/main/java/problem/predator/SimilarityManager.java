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
        if(action==0) {
            if (state[1] - 2 >-4 && state[3] - 2 >-4) {
                double[] newState = {state[0], state[1] - 2, state[2], state[3]-2};
                similarityRecords.add(new SimilarityRecord(newState, 1, 1));
            }
            if (state[0] - 1 > -4 && state[2] - 1 > -4) {
                double[] newState = {state[0] -1, state[1]-1, state[2]-1, state[3]-1};
                similarityRecords.add(new SimilarityRecord(newState, 3, 1));
            }
            if (state[0] + 1 < 4 && state[2] + 1 < 4) {
                double[] newState = {state[0] +1, state[1]-1, state[2]+1, state[3]-1};
                similarityRecords.add(new SimilarityRecord(newState, 2, 1));
            }
            double[] newState = {state[0], state[1]-1, state[2], state[3]-1};
            similarityRecords.add(new SimilarityRecord(newState, 4, 1));

        }
/*
        if(action==1) {
            if (state[1] + 2 <4 && state[3] + 2 <4) {
                double[] newState = {state[0], state[1] + 2, state[2], state[3]+2};
                similarityRecords.add(new SimilarityRecord(newState, 0, 1));
            }
            if (state[0] - 1 > -4 && state[2] - 1 > -4) {
                double[] newState = {state[0] -1, state[1]+1, state[2]-1, state[3]+1};
                similarityRecords.add(new SimilarityRecord(newState, 3, 1));
            }
            if (state[0] + 1 < 4 && state[2] + 1 < 4) {
                double[] newState = {state[0] +1, state[1]+1, state[2]+1, state[3]+1};
                similarityRecords.add(new SimilarityRecord(newState, 2, 1));
            }
            double[] newState = {state[0], state[1]+1, state[2], state[3]+1};
            similarityRecords.add(new SimilarityRecord(newState, 4, 1));

        }
*/
        if(action==2) {

            if (state[0] -2 > -4 && state[2] - 2 > -4) {
                double[] newState = {state[0]-2 , state[1], state[2]-2, state[3]};
                similarityRecords.add(new SimilarityRecord(newState, 3, 1));
            }
            if (state[1] + 1 < 4 && state[3] + 1 < 4) {
                double[] newState = {state[0] -1, state[1]+1, state[2]-1, state[3]+1};
                similarityRecords.add(new SimilarityRecord(newState, 0, 1));
            }
            if (state[1] -1 > -4 && state[3] -1 > -4) {
                double[] newState = {state[0] -1, state[1]-1, state[2]-1, state[3]-1};
                similarityRecords.add(new SimilarityRecord(newState, 1, 1));
            }

            double[] newState = {state[0]-1, state[1], state[2]-1, state[3]};
            similarityRecords.add(new SimilarityRecord(newState, 4, 1));

        }

        if(action==3) {

            if (state[0] +2 <4 && state[2] +2 <4) {
                double[] newState = {state[0]+2 , state[1], state[2]+2, state[3]};
                similarityRecords.add(new SimilarityRecord(newState, 2, 1));
            }


        }






        // *** END OF YOUR CODE ********************************************************************

        return similarityRecords;
    }
}
