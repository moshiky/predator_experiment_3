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

        double[] stt = state.clone();

        if (action == 0)//up x-1
        {
            stt[0] -= 1;
            stt[2] -= 1;
        }
        else if (action == 1)//down x+1
        {
            stt[0] += 1;
            stt[2] += 1;
        }
        else if (action == 2)//left y-1
        {
            stt[1] -= 1;
            stt[3] -= 1;
        }
        else if (action == 3)//right y+1
        {
            stt[1] += 1;
            stt[3] += 1;
        }

        double tmp;

        double[] stt1 = stt.clone();
        tmp = stt1[3];
        stt1[3] = stt1[2];
        stt1[2] = tmp;
        similarityRecords.add(new SimilarityRecord(stt1, 1, 1));


        double[] stt2 = stt.clone();
        tmp = stt2[3];
        stt2[3] = stt2[2];
        stt2[2] = tmp;
        tmp  = stt2[0];
        stt2[0] = stt[1];
        stt[1] = tmp;
        similarityRecords.add(new SimilarityRecord(stt2, 3, 0.6));


        // *** END OF YOUR CODE ********************************************************************

        return similarityRecords;
    }
}
