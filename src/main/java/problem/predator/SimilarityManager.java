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



        if (action == 4) {
            /*
            for (int i = -19; i < 20; ++i) {
                for (int j = -19; j < 20; ++j) {
                */
                    double[] stateDown = state.clone();
                    double[] stateLeft = state.clone();
                    double[] stateUp = state.clone();
                    double[] stateRight = state.clone();
                    stateUp[3] = state[3] - 1;
                    stateLeft[2] = state[0] - 1;
                    stateDown[3] = state[3] + 1;
                    stateRight[2] = state[2] + 1;
                   /*
                    stateDown[0] = i;
                    stateDown[1] = j;
                    stateUp[0] = i;
                    stateUp[1] = j;
                    stateLeft[0] = i;
                    stateLeft[1] = j;
                    stateRight[0] = i;
                    stateRight[1] = j;
                    */
                    similarityRecords.add(new SimilarityRecord(stateUp, 1, 1 ));
                    similarityRecords.add(new SimilarityRecord(stateDown, 0, 1 ));
                    similarityRecords.add(new SimilarityRecord(stateLeft, 3, 1 ));
                    similarityRecords.add(new SimilarityRecord(stateRight, 2, 1 ));
               // }
            //}
/*
        }
        if (action == 3) {
            /*
            for (int i = -19; i < 20; ++i) {
                for (int j = -19; j < 20; ++j) {
                    double[] stateDown = state.clone();
                    double[] stateLeft = state.clone();
                    double[] stateUp = state.clone();
                    double[] stateRight = state.clone();
                    stateUp[3] = state[3] - 1;
                    stateLeft[2] = state[0] - 1;
                    stateDown[3] = state[3] + 1;
                    stateRight[2] = state[2] + 1;
                    /*
                    stateDown[0] = i;
                    stateDown[1] = j;
                    stateUp[0] = i;
                    stateUp[1] = j;
                    stateLeft[0] = i;
                    stateLeft[1] = j;
                    stateRight[0] = i;
                    stateRight[1] = j;
                    similarityRecords.add(new SimilarityRecord(stateRight, 4, 1));
                    stateDown[2] = state[2] + 1;
                    similarityRecords.add(new SimilarityRecord(stateRight, 0, 1));
                    stateUp[2] = state[2] + 1;
                    similarityRecords.add(new SimilarityRecord(stateRight, 1, 1));
                //}
            //}
        }
        if (action == 2) {
            /*
            for (int i = -19; i < 20; ++i) {
                for (int j = -19; j < 20; ++j) {
                    double[] stateDown = state.clone();
                    double[] stateLeft = state.clone();
                    double[] stateUp = state.clone();
                    double[] stateRight = state.clone();
                    stateUp[3] = state[3] - 1;
                    stateLeft[2] = state[0] - 1;
                    stateDown[3] = state[3] + 1;
                    stateRight[2] = state[2] + 1;
                    stateDown[0] = i;
                    stateDown[1] = j;
                    stateUp[0] = i;
                    stateUp[1] = j;
                    stateLeft[0] = i;
                    stateLeft[1] = j;
                    stateRight[0] = i;
                    stateRight[1] = j;
                    similarityRecords.add(new SimilarityRecord(stateLeft,4,1));
                    stateDown[2] = state[2] - 1;
                    similarityRecords.add(new SimilarityRecord(stateRight, 0, 1 ));
                    stateUp[2] = state[2] - 1;
                    similarityRecords.add(new SimilarityRecord(stateRight, 1, 1 ));
               // }
       //     }
        }
        if (action == 1) {
            /*
            for (int i = -19; i < 20; ++i) {
                for (int j = -19; j < 20; ++j) {
                    double[] stateDown = state.clone();
                    double[] stateLeft = state.clone();
                    double[] stateUp = state.clone();
                    double[] stateRight = state.clone();
                    stateUp[3] = state[3] - 1;
                    stateLeft[2] = state[0] - 1;
                    stateDown[3] = state[3] + 1;
                    stateRight[2] = state[2] + 1;
                    /*
                    stateDown[0] = i;
                    stateDown[1] = j;
                    stateUp[0] = i;
                    stateUp[1] = j;
                    stateLeft[0] = i;
                    stateLeft[1] = j;
                    stateRight[0] = i;
                    stateRight[1] = j;
                    similarityRecords.add(new SimilarityRecord(stateDown, 4, 1));

               // }
        //    }
            */
        }
        if (action == 0) {

        }
        // *** END OF YOUR CODE ********************************************************************

        return similarityRecords;
    }
}
