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


        rewardShaping -= Math.abs(currentState[2]) - Math.abs(previousState[2]);
        rewardShaping -= Math.abs(currentState[3]) - Math.abs(previousState[3]);

//        System.out.println(Math.log10((currentState[0] - currentState[2])*(currentState[0] - currentState[2])
//                + (currentState[1] - currentState[3])*(currentState[1] - currentState[3]) + 1.1));
//        rewardShaping -= Math.sqrt((previousSt[0])*(currentState[0])
//                + (currentState[1])*(currentState[1]));

//        double dx = currentState[0] - previousState[0];
//        double dy = currentState[1] - previousState[1];
//        double dx2 = currentState[2] - previousState[2];
//        double dy2 = currentState[3] - previousState[3];
//        rewardShaping -= Math.sqrt((dx - dx2)*(dx - dx2)
//                        + (dy - dy2)*(dy - dy2));

        // *** END OF YOUR CODE ********************************************************************

        return rewardShaping;
    }
}
