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
        if ((Math.abs(currentState[2]) <  Math.abs(previousState[2]))  &&
                (Math.abs(currentState[3]) < Math.abs(previousState[3]))) {
            rewardShaping = 10;
        } else if ((Math.abs(currentState[2]) <  Math.abs(previousState[2]))  ||
                (Math.abs(currentState[3]) < Math.abs(previousState[3]))) {
            rewardShaping = 5;
        } else {
            rewardShaping = -100;
        }

//        if ((Math.abs(currentState[0]) >  Math.abs(previousState[0]))  &&
//                (Math.abs(currentState[1]) < Math.abs(previousState[1]))) {
//            rewardShaping += 3;
//        } else if ((Math.abs(currentState[0]) <  Math.abs(previousState[0]))  &&
//                (Math.abs(currentState[1]) > Math.abs(previousState[1]))) {
//            rewardShaping += 3;
//        } else if ((Math.abs(currentState[0]) <  Math.abs(previousState[0]))  &&
//                (Math.abs(currentState[1]) < Math.abs(previousState[1]))) {
//          //  rewardShaping += 4;
//        } else {
//          //  rewardShaping += -1;
//        }

//        int distancefrompray = (int) (Math.abs(currentState[2]) -  Math.abs(previousState[2]) +  Math.abs(currentState[3]) - Math.abs(previousState[3]));
//        int distancefrompre = (int) (Math.abs(currentState[0]) -  Math.abs(previousState[0]) +  Math.abs(currentState[1]) - Math.abs(previousState[1]));
//
//        if (distancefrompre< distancefrompray) {
//            rewardShaping += -2;
//        }





        // *** END OF YOUR CODE ********************************************************************

        return rewardShaping;
    }
}
