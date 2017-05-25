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

    public static double abs(double d) { return d > 0 ? d : -d; }

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

        // *** YOUR CODE HERE **********************************************************************

        // don't enlarge distance from pray
        if (abs(previousState[2]) < abs(currentState[2]))
            rewardShaping -= 1;
        if (abs(previousState[3]) < abs(currentState[3]))
            rewardShaping -= 1;

        if (abs(previousState[2]) + abs(previousState[3]) <= abs(currentState[2]) + abs(currentState[3]))
            rewardShaping -= 1;

        // reduce distance from pray
        if (abs(previousState[2]) > abs(currentState[2]))
            rewardShaping += 0.5;
        if (abs(previousState[3]) > abs(currentState[3]))
            rewardShaping += 0.5;

         // reward staying away from other predator
        if ((previousState[0] < -1 && previousAction == 2)
                || (previousState[0] > 1 && previousAction == 3))
            rewardShaping += 0.2;
        if ((previousState[1] < -1 && previousAction == 0)
                || (previousState[1] > 1 && previousAction == 1))
            rewardShaping += 0.2;


        // *** END OF YOUR CODE ********************************************************************

        return rewardShaping;
    }
}
