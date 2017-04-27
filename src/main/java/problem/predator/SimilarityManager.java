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
     * @return
     */
    public static ArrayList<SimilarityRecord> getSimilarityRecords(double[] state, int action) throws Exception {
        ArrayList<SimilarityRecord> similarityRecords = new ArrayList<>();

        // *** YOUR CODE HERE **********************************************************************
        double[] newState;

        // reflection
        newState = state.clone();
        if (action < 2) {
            // means it UP or DOWN
            newState[1] *= -1;
            newState[3] *= -1;
            similarityRecords.add(new SimilarityRecord(newState, 1-action, 1));

            newState[0] *= -1;
            newState[2] *= -1;
            similarityRecords.add(new SimilarityRecord(newState, 1-action, 1));

            newState[1] *= -1;
            newState[3] *= -1;
            similarityRecords.add(new SimilarityRecord(newState, action, 1));
        }
        else {
            // means it LEFT or RIGHT
            newState[0] *= -1;
            newState[2] *= -1;
            similarityRecords.add(new SimilarityRecord(newState, 5-action, 1));

            newState[1] *= -1;
            newState[3] *= -1;
            similarityRecords.add(new SimilarityRecord(newState, 5-action, 1));

            newState[0] *= -1;
            newState[2] *= -1;
            similarityRecords.add(new SimilarityRecord(newState, action, 1));
        }

        // (x, y) -> (y, x)
        newState = state.clone();
        swapIndexes(0, 1, newState, false, false);
        swapIndexes(2, 3, newState, false, false);

        similarityRecords.add(new SimilarityRecord(newState, 3-action, 1));

        // (x, y) -> (-y, -x)
        newState = state.clone();
        swapIndexes(0, 1, newState, true, true);
        swapIndexes(2, 3, newState, true, true);

        if (1 == action || 3 == action) {
            // means it DOWN or RIGHT
            similarityRecords.add(new SimilarityRecord(newState, 4-action, 1));
        }
        else {
            // means it UP or LEFT
            similarityRecords.add(new SimilarityRecord(newState, 2-action, 1));
        }

        // (x, y) -> (-y, x)
        newState = state.clone();
        swapIndexes(0, 1, newState, false, true);
        swapIndexes(2, 3, newState, false, true);

        if (0 == action) {
            // UP --> LEFT
            similarityRecords.add(new SimilarityRecord(newState, 2, 1));
        }
        else if (1 == action){
            // DOWN --> RIGHT
            similarityRecords.add(new SimilarityRecord(newState, 3, 1));
        }
        else if (2 == action){
            // LEFT --> DOWN
            similarityRecords.add(new SimilarityRecord(newState, 1, 1));
        }
        else {
            // RIGHT --> UP
            similarityRecords.add(new SimilarityRecord(newState, 0, 1));
        }

        // (x, y) -> (-y, x)
        newState = state.clone();
        swapIndexes(0, 1, newState, true, false);
        swapIndexes(2, 3, newState, true, false);

        if (0 == action) {
            // UP --> RIGHT
            similarityRecords.add(new SimilarityRecord(newState, 3, 1));
        }
        else if (1 == action){
            // DOWN --> LEFT
            similarityRecords.add(new SimilarityRecord(newState, 2, 1));
        }
        else if (2 == action){
            // LEFT --> UP
            similarityRecords.add(new SimilarityRecord(newState, 0, 1));
        }
        else {
            // RIGHT --> DOWN
            similarityRecords.add(new SimilarityRecord(newState, 1, 1));
        }

        // *** END OF YOUR CODE ********************************************************************

        return similarityRecords;
    }

    private static void swapIndexes(int firstIndex, int secondIndex, double[] arr, boolean flipFirst, boolean flipSecond) {
        double tmp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex] * (flipSecond? -1 : 1);
        arr[secondIndex] = tmp * (flipFirst? -1 : 1);
    }

}
