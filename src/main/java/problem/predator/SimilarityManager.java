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
        if (action == 2){
            double tmp_state_up[] = new double[]{-state[0],-state[1], state[3],state[2]};
            similarityRecords.add(new SimilarityRecord(tmp_state_up ,0,1));

            double tmp_state_down[] = new double[]{state[0],state[1],-state[3], -state[2],};
            similarityRecords.add(new SimilarityRecord(tmp_state_down ,1,1));

            double tmp_state_right[] = new double[]{-state[0],-state[1],- state[2],-state[3]};
            similarityRecords.add(new SimilarityRecord(tmp_state_right ,3,1));

        }
        if (action == 0){
            double tmp_state_left[] = new double[]{-state[0],-state[1], state[3],state[2]};
            similarityRecords.add(new SimilarityRecord(tmp_state_left ,2,1));

            double tmp_state_right[] = new double[]{state[0],state[1],-state[3], -state[2],};
            similarityRecords.add(new SimilarityRecord(tmp_state_right ,3,1));

            double tmp_state_down[] = new double[]{-state[0],-state[1],- state[2],-state[3]};
            similarityRecords.add(new SimilarityRecord(tmp_state_down ,1,1));

        }

        // *** END OF YOUR CODE ********************************************************************

        return similarityRecords;
    }
}
