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

        if(state[2] == 1 && action== 2) {
            //double[] stateTagLeft = state;

            double[] stateTagRigth = state.clone();
            stateTagRigth[0] -= 2;
            stateTagRigth[2] -= 1;
            SimilarityRecord sim1 = new SimilarityRecord(stateTagRigth, 3, 1);
            similarityRecords.add(sim1);
  /*
            double[] stateTagUP = state;
            stateTagUP[1] =-2;
            stateTagUP[3] = -1;
            SimilarityRecord sim2 = new SimilarityRecord(stateTagUP, 3, 1);
            similarityRecords.add(sim2);

            double[] stateTagDown = state;
            stateTagDown[1] =-2;
            stateTagDown[1] =2;
            stateTagDown[3] = 1;
            SimilarityRecord sim3 = new SimilarityRecord(stateTagDown, 3, 1);
            similarityRecords.add(sim3);



            double simi = state[2]/stateTagUP[2];
                if(simi > 0.5  ) {
                    SimilarityRecord sim = new SimilarityRecord(stateTagUP, 2, state[2] / stateTagUP[2]);
                    similarityRecords.add(sim);
                }
            }
        }

*/

        /*
        if(action == 2)
        {
           for(int i=1;state[0]+i > 0 && state[2]+i>0;i++)
           {
               double[] stateTag = new double[4];
               stateTag[0] = state[0]+i;
               stateTag[1] = state[1];
               stateTag[2] = state[2]+i;
               stateTag[3] = state[3];

               double simi = state[2]/stateTag[2];
               if(simi > 0.5  ) {
                   SimilarityRecord sim = new SimilarityRecord(stateTag, 2, state[2] / stateTag[2]);
                   similarityRecords.add(sim);
               }
           }
    }
*/

        }

        // *** END OF YOUR CODE ********************************************************************

        return similarityRecords;
    }

}
