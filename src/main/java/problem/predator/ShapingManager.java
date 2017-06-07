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

        // *** YOUR CODE HERE **********************************************************************
        double xReward = 0.0;
        double yReward = 0.0;
        double xRewardbefore = 0.0;
        double yRewardBefore = 0.0;
        if (previousState[2] != 0 && previousState[3] != 0) {
            xRewardbefore = Math.abs(1.0 / currentState[2]);
            yRewardBefore = Math.abs(1.0 / currentState[3]);
        }
        else if (previousState[2] == 0)
            xRewardbefore = 1.0;
        else
            yRewardBefore = 1.0;

        if (currentState[2] != 0 && currentState[3] != 0) {
            xReward = Math.abs(1.0 / currentState[2]);
            yReward = Math.abs(1.0 / currentState[3]);
        }
        else if (currentState[2] == 0)
            xReward = 1.0;
        else
            yReward = 1.0;
        rewardShaping =  Math.sqrt(Math.pow(xReward - xRewardbefore,2) + Math.pow(yReward  - yRewardBefore,2));
        // *** END OF YOUR CODE ********************************************************************

        return rewardShaping;
    }
}
