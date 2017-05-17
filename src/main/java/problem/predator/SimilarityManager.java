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
        // find end state
        double[] end_state = new double[4];
        switch(action){
            case 0:
                //up
                if(state[1]-1<=-20 || state[3]-1<=-20){
                    end_state = state;
                }
                else {
                    end_state[0] = state[0];
                    end_state[1] = state[1] - 1;
                    end_state[2] = state[2];
                    end_state[3] = state[3] - 1;
                }
                break;
            case 1:
                //down
                if(state[1]+1>=20 || state[3]+1>=20){
                    end_state = state;
                }
                else {
                    end_state[0] = state[0];
                    end_state[1] = state[1] + 1;
                    end_state[2] = state[2];
                    end_state[3] = state[3] + 1;
                }
                break;
            case 2:
                //left
                if(state[0]-1<=-20 || state[2]-1<=-20){
                    end_state = state;
                }
                else {
                    end_state[0] = state[0] - 1;
                    end_state[1] = state[1];
                    end_state[2] = state[2] - 1;
                    end_state[3] = state[3];
                }
                break;
            case 3:
                //right
                if(state[0]+1>=20 || state[2]+1>=20){
                    end_state = state;
                }
                else {
                    end_state[0] = state[0] + 1;
                    end_state[1] = state[1];
                    end_state[2] = state[2] + 1;
                    end_state[3] = state[3];
                }
                break;
            case 4:
                //stay
                end_state = state;
                break;
        }
        // find all states that go to end state - those are the most similar states to the current state!
        //up
        double[] current_state = new double[4];
        SimilarityRecord sm;
        if(state[1]-1>-20 || state[3]-1>-20) {
            current_state[0] = end_state[0];
            current_state[1] = end_state[1] - 1;
            current_state[2] = end_state[2];
            current_state[3] = end_state[3] - 1;
            sm = new SimilarityRecord(current_state, 0, 1);
            similarityRecords.add(sm);
        }
        //down
        if(state[1]+1<20 || state[3]+1<20) {
            current_state[0] = end_state[0];
            current_state[1] = end_state[1] + 1;
            current_state[2] = end_state[2];
            current_state[3] = end_state[3] + 1;
            sm = new SimilarityRecord(current_state, 1, 1);
            similarityRecords.add(sm);
        }
        //left
        if(state[0]-1>-20 || state[2]-1>-20) {
            current_state[0] = end_state[0] - 1;
            current_state[1] = end_state[1];
            current_state[2] = end_state[2] - 1;
            current_state[3] = end_state[3];
            sm = new SimilarityRecord(current_state, 2, 1);
            similarityRecords.add(sm);
        }
        //right
        if(state[0]+1<20 || state[2]+1<20) {
            current_state[0] = end_state[0] + 1;
            current_state[1] = end_state[1];
            current_state[2] = end_state[2] + 1;
            current_state[3] = end_state[3];
            sm = new SimilarityRecord(current_state, 3, 1);
            similarityRecords.add(sm);
        }
        //stay
        sm = new SimilarityRecord(end_state, 4, 1);
        similarityRecords.add(sm);
        // *** END OF YOUR CODE ********************************************************************
        return similarityRecords;
    }
}
