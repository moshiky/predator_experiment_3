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

        if(Math.abs(state[0])>17||Math.abs(state[1])>17||Math.abs(state[1])>17||Math.abs(state[3])>17) {
            return similarityRecords;
        }
        boolean isFriendToTheLeft = state[0] > 0 ? true : false;
        boolean isFriendUnder = state[1] > 0  ? true : false;

        boolean isPrayToTheLeft = state[2] > 0 ? true : false;
        boolean isPrayUnder = state[3] > 0  ? true : false;

        double[] newState = state.clone();
        //if other pred to your left


        if(action==2) {
            if (isFriendToTheLeft) newState[0]=newState[0]-2;
            else newState[0]=newState[0]+2;
            if (isPrayToTheLeft) newState[2]=newState[2]-2;
            else newState[2]=newState[2]+2;
            similarityRecords.add(new SimilarityRecord(newState, 3, (1 / 2)));
        }
        if(action==3) {
            if (isFriendToTheLeft) newState[0]=newState[0]+2;
            else newState[0]=newState[0]-2;
            if (isPrayToTheLeft) newState[2]=newState[2]+2;
            else newState[2]=newState[2]-2;
            similarityRecords.add(new SimilarityRecord(newState, 2, (1 / 2)));
        }
        if(action==0) {
            if (isFriendUnder) newState[1]=newState[1]+2;
            else newState[1]=newState[1]-2;
            if (isPrayUnder) newState[3]=newState[3]+2;
            else newState[3]=newState[3]-2;
            similarityRecords.add(new SimilarityRecord(newState, 1, (1 / 2)));
        }
        if(action==3) {
            if (isFriendUnder) newState[1]=newState[1]-2;
            else newState[1]=newState[1]-2;
            if (isPrayUnder) newState[3]=newState[3]-2;
            else newState[3]=newState[3]+2;
            similarityRecords.add(new SimilarityRecord(newState, 2, (1 / 2)));
        }


        // *** END OF YOUR CODE ********************************************************************

        return similarityRecords;
    }
}
