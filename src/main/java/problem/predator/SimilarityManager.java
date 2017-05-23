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

        for (int i = 0; i < 5; i++) {
            if (i != action){
                if (i ==4) {
                    similarityRecords.add(new SimilarityRecord(state.clone(),i, 0.75));
                } else {
                    similarityRecords.add(new SimilarityRecord(state.clone(),i, 0.5));
                }
            }
        }


        /*
        double[] similarState = new double[4];
        double factor;
        similarState[0] = state[0];
        similarState[1] = state[1];
        switch (action) {
            case 0:
                similarState[3] = state[3];
                for (int i = -19; i < 20; i++) {
                    if (i == state[2]) {
                        break;
                    }
                    similarState[2] = i;
                    factor = i > state[2] ? 0 : (state[2] - i)/(state[2] + 19);
                    similarityRecords.add(new SimilarityRecord(similarState.clone(), action, factor));
                }
                // Adding cases for other.
        }

 /*       double[] similarState = new double[4];
        SimilarityRecord record;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                for (int l = 0; l < 20; l++) {
                    for (int k = 0; k< 20; k++) {
                        similarState[0] = i;
                        similarState[1] = j;
                        similarState[2] = l;
                        similarState[3] = k;
                        record = SimilarityRecord(similarState, action, )
                        similarityRecords.add()
                    }
                }
            }
        }
        double[] similarState = new double[4];
        double factor;
        similarState[0] = state[0];
        similarState[1] = state[1];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                similarState[2] = i;
                similarState[3] = j;
                factor = (i - state[2] + j - state[3])/4;
                similarityRecords.add(new SimilarityRecord(similarState.clone(), action, factor));

            }
        }*/

        return similarityRecords;
    }
}
