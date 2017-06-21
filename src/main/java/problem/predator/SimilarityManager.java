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
        //double[] newState = state.clone();
        //int count1 =0 ,count2 =0 ;
        for (int i = -19 ; i < 20 ; i++) {
            double[] newState = state.clone();
            newState[0] = newState[1]=newState[2]=newState[3]=-21;
            for (int j = -19; j < 20; j++) {
               if( i*i + j*j == state[0] * state[0] + state[1] * state[1]) {
                   newState[0] = i;
                   newState[1] = j;
               }
                if( i*i + j*j == state[2] * state[2] + state[3] * state[3]) {
                    newState[2] = i;
                    newState[3] = j;
                }
                if(newState[0] != -21 && newState[1] != -21 && newState[2] != -21 && newState[3] != -21 )
                {
                    for (int k = 0; k < 5; k++) {
                        similarityRecords.add(new SimilarityRecord(newState, k, 0.9));
                    }
                }
            }
        }
        //    similarityRecords.add(new SimilarityRecord(newState,0 , 0.5));
      //  similarityRecords.add(new SimilarityRecord(newState, 1, 0.5));
     //   similarityRecords.add(new SimilarityRecord(newState, , 0.5));
      //  similarityRecords.add(new SimilarityRecord(newState, , 0.5));

        //}
        // *** END OF YOUR CODE ********************************************************************

        return similarityRecords;
    }
}
