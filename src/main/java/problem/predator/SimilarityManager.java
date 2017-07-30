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
        double [] similarity_state = new double[4];
        similarity_state[0] = state[0];
        similarity_state[1] = state[1];
        similarity_state[2] = state[2];
        similarity_state[3] = state[3];

        //Left
        if(action == 2 && state[3] != 0) {
            similarity_state[3] = state[3] - 1;

            double [] state1 = new double[4];
            state1[0] = state[0];
            state1[1] = state[1];
            state1[2] = state[2];
            state1[3] = Math.max(state[3] - 2, -19);

            int action1 = 3;
            double similarityFactor1 = 1;
            SimilarityRecord s = new SimilarityRecord(state1,action1,similarityFactor1);
            similarityRecords.add(s);

            double [] state2 = new double[4];
            state2[0] = state[0];
            state2[1] = state[1];
            state2[2] = Math.max(state[2] - 1, -19);
            state2[3] = Math.max(state[2] - 1, -19);
            int action2 = 0;

            SimilarityRecord s2 = new SimilarityRecord(state2,action2,similarityFactor1);
            similarityRecords.add(s2);
        }
//        if(action == 2 && state[3] == 0)
//            similarity_state[3] = state[3] + 1;
//
//        if(action == 3 && state[3] != 0)
//            similarity_state[3] = state[3] + 1;
//        if(action == 3 && state[3] == 0)
//            similarity_state[3] = state[3] + 1;
//
//        if(action == 0 && state[4] != 0)
//            similarity_state[4] = state[4] - 1;
//        if(action == 0 && state[4] == 0)
//            similarity_state[4] = state[4] + 1;
//
//        if(action == 1 && state[4] != 0)
//            similarity_state[4] = state[4] + 1;
//        if(action == 1 && state[4] == 0)
//            similarity_state[4] = state[4] + 1;



        // *** END OF YOUR CODE ********************************************************************

        return similarityRecords;
    }
}
