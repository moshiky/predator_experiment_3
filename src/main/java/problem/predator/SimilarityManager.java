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
    public static ArrayList<SimilarityRecord> getSimilarityRecords(double[] state, int action)
    {
        ArrayList<SimilarityRecord> similarityRecords = new ArrayList<>();

        // *** YOUR CODE HERE **********************************************************************

        if(action == 4)
        {
            similarityRecords.add(new SimilarityRecord(state, action, 1));
        }

        double []newState = new double[4];

        if(action == 2) {
            // for one step left:
            for (int i = 0; i < 4; i++)
                newState[i] = state[i];

            if (newState[1] > 0)
                newState[1] -= 1;

            if (newState[3] > 0)
                newState[3] -= 1;

            similarityRecords.add(new SimilarityRecord(newState, 4, 0.95));
        }

        if(action == 0) {
            // one step up
            for (int i = 0; i < 4; i++)
                newState[i] = state[i];

            if (newState[1] > 0)
                newState[1] -= 1;

            if (newState[3] > 0)
                newState[3] -= 1;

            similarityRecords.add(new SimilarityRecord(newState, 4, 0.95));
        }
        if(action == 3) {
            // onr step right
            for (int i = 0; i < 4; i++)
                newState[i] = state[i];

            if (newState[0] < 19)
                newState[0] += 1;

            if (newState[2] < 19)
                newState[2] += 1;

            similarityRecords.add(new SimilarityRecord(newState, 4, 0.95));
        }
        // one step down:

        if(action == 1) {
            // onr step down
            for (int i = 0; i < 4; i++)
                newState[i] = state[i];

            if (newState[1] < 19)
                newState[1] += 1;

            if (newState[3] < 19)
                newState[3] += 1;


            similarityRecords.add(new SimilarityRecord(newState, 4, 0.95));
        }
        //similarityRecords.add(new SimilarityRecord(state, action, 0.95));

        // *** END OF YOUR CODE ********************************************************************

        return similarityRecords;
    }
}
