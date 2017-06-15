package problem.predator;

import java.util.ArrayList;

/**
 * Created by Dev on 27/04/2017.
 */
public class SimilarityManager {

    public static int rotate_90_left(double []state , int action) {
        double [] s1 = state.clone();

        s1[0] = -state[1];
        s1[2] = -state[3];

        s1[1] = state[0];
        s1[3] = state[2];

        for (int i=0; i<4; i++)
            state[i] = s1[i];

        switch (action) {
            case 0:
                return 2;
            case 1:
                return 3;
            case 2:
                return 1;
            case 3:
                return 0;
        }
        return action;

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
        double [] s1;
        int a1;
        // *** YOUR CODE HERE **********************************************************************

        // mirror state and action
        s1 = state.clone();
        a1 = action;
        s1[0] = -s1[0];
        s1[2] = -s1[2];
        if (action==3 || action==2) {
            similarityRecords.add(new SimilarityRecord(s1, opposite_action(a1), 1));
        } else {
            similarityRecords.add(new SimilarityRecord(s1, a1, 1));
            similarityRecords.add(new SimilarityRecord(s1, opposite_action(a1), 1));
        }


        s1 = state.clone();
        a1 = action;
        s1[1] = -s1[1];
        s1[3] = -s1[3];
        if (action==1 || action==0) {
            similarityRecords.add(new SimilarityRecord(s1, opposite_action(a1), 1));
        } else {
            similarityRecords.add(new SimilarityRecord(s1, a1, 1));
            similarityRecords.add(new SimilarityRecord(s1, opposite_action(a1), 1));
        }

        // rotate state and action
        int a = action;
        s1 = state.clone();
        for (int i=1; i<=3; i++) {
            a = rotate_90_left(s1, a);
        }



        // indefferent more than distance 7
//        for (int j=0; j<4; j++) {
//            if (state[j] >= 8) {
//                for (double i = state[j] + 1; i <= 19; i++) {
//                    s1 = state.clone();
//                    s1[j] = i;
//                    similarityRecords.add(new SimilarityRecord(s1, action, 0.9));
//                }
//
//            }
//            if (state[j] <= -8) {
//                for (double i = state[j] - 1; i >= -19; i--) {
//                    s1 = state.clone();
//                    s1[j] = i;
//                    similarityRecords.add(new SimilarityRecord(s1, action, 0.9));
//                }
//
//            }
//        }



        // *** END OF YOUR CODE ********************************************************************

        return similarityRecords;
    }



    public static int opposite_action(int action)
    {
        switch (action) {
            case 0:
                return 1;
            case 1:
                return 0;
            case 2:
                return 3;
            case 3:
                return 2;
        }
        return action;
    }
}
