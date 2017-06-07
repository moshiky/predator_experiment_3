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

//        // *** YOUR CODE HERE **********************************************************************
//            if (((previousState[2] ==  0 && previousState[3] == -1 && previousAction == 0) ||
//                 (previousState[2] ==  0 && previousState[3] ==  1 && previousAction == 1) ||
//                 (previousState[2] ==  1 && previousState[3] ==  0 && previousAction == 2) ||
//                 (previousState[2] == -1 && previousState[3] ==  0 && previousAction == 3) ||
//                 (previousState[2] ==  0 && previousState[3] ==  0 && previousAction == 4)) && (currentState[2] == 0 && currentState[3] == 0))
//                rewardShaping = 1.0;

//            if ((Math.abs(previousState[2]) + Math.abs(previousState[3])) < (Math.abs(currentState[2]) + Math.abs(currentState[3])))
//                rewardShaping = 1.0;

            if (((previousState[3] + 1 == currentState[3])&& previousAction == 0) ||
                ((previousState[3] - 1 == currentState[3])&& previousAction == 1) ||
                ((previousState[2] + 1 == currentState[2])&& previousAction == 2) ||
                ((previousState[2] - 1 == currentState[2])&& previousAction == 3))
                    rewardShaping = 10.0;





        // *** END OF YOUR CODE ********************************************************************

        return rewardShaping;
    }
}
