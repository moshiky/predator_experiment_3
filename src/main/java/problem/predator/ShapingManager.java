package problem.predator;

/**
 * Created by Dev on 30/04/2017.
 */
public class ShapingManager {

    // *** YOUR CODE HERE **********************************************************************
    // Here you can add custom members, if needed

    // *** END OF YOUR CODE ********************************************************************


    public ShapingManager () {

        // *** YOUR CODE HERE **********************************************************************
        // Here you can add custom members initialization, if needed

        // *** END OF YOUR CODE ********************************************************************
    }

    /**
     *
     * @param previousState and currentState represents states:
     *              [0] X: predator <-> other predator
     *              [1] Y: predator <-> other predator
     *              [2] X: predator <-> pray
     *              [3] Y: predator <-> pray
     * @param previousAction represent the action taken:
     *               0: UP
     *               1: DOWN
     *               2: LEFT
     *               3: RIGHT
     *               4: STAY
     * @return the value of F(s, a, s'), such that R'(s, a, s') = R(s, a, s') + F(s, a, s')
     */
    public double getShapingReward(double[] previousState, int previousAction, double[] currentState) {
        double rewardShaping = 0.0;
        double dist = 0.0;
        // *** YOUR CODE HERE **********************************************************************

        dist = Math.sqrt(Math.pow(previousState[2] - currentState[2], 2) + Math.pow(previousState[3] - currentState[3], 2));


        if (previousState[2] > currentState[2] && previousState[3] > currentState[3]) {
            rewardShaping = dist;
        }
        else if(previousState[2] < currentState[2] && previousState[3] < currentState[3]) {
            rewardShaping = -dist;
        }





        // *** END OF YOUR CODE ********************************************************************

        return rewardShaping;
    }
}
