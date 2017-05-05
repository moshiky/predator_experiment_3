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

        // *** END OF YOUR CODE ********************************************************************

        return similarityRecords;
    }
}
