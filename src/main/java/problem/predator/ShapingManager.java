package problem.predator;

/**
 * Created by Dev on 30/04/2017.
 */
public class ShapingManager {

    // *** YOUR CODE HERE **********************************************************************
    // Here you can add custom members, if needed

    // *** END OF YOUR CODE ********************************************************************
 double maxFar;

    public ShapingManager () {

        // *** YOUR CODE HERE **********************************************************************
        // Here you can add custom members initialization, if needed
        maxFar = Math.sqrt(Math.pow(19 ,2) +  Math.pow(19 ,2));
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
            double res1 = Math.sqrt(Math.pow(previousState[2] ,2) +  Math.pow(previousState[3] ,2));
            double res2 = Math.sqrt(Math.pow(currentState[2] ,2) +  Math.pow(currentState[3] ,2));
         if(res1 > res2)
             rewardShaping = (res1-res2) / maxFar ;
             // *** END OF YOUR CODE ********************************************************************

        return rewardShaping;
    }
}
