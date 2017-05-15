package problem.predator;

/**
 * Created by Dev on 30/04/2017.
 */
public class ShapingManager {

    // *** YOUR CODE HERE **********************************************************************
    // Here you can add custom members, if needed

    // *** END OF YOUR CODE ********************************************************************


    /* @param action represent the action:
            *        0: UP
     *               1: DOWN
     *               2: LEFT
     *               3: RIGHT
     *               4: STAY
    */
    public ShapingManager () {

        // *** YOUR CODE HERE **********************************************************************
        // Here you can add custom members initialization, if needed

        // *** END OF YOUR CODE ********************************************************************
    }

    public double getShapingReward(double[] previousState, int previousAction, double[] currentState) {
        double rewardShaping = 0.0;

        // *** YOUR CODE HERE **********************************************************************
        double rewardShapingPositive = 5;
        double rewardShapingPositivePlus = 10;
        //double rewardShapingNegative = -5;
        double rewardShapingNegativePlus = -10;

        //Check if the distance was decreased
        boolean xDiff = Math.abs (previousState[2]) >  Math.abs (currentState[2]);
        boolean yDiff = Math.abs (previousState[3]) >  Math.abs (currentState[3]);

        if(xDiff && yDiff)
        {
            rewardShaping = rewardShapingPositivePlus ;
        }
        else
        {
            if ((xDiff && !yDiff) || (!xDiff && yDiff))
                rewardShaping = rewardShapingPositive;
            else
                rewardShaping = rewardShapingNegativePlus;


        }

        // *** END OF YOUR CODE ********************************************************************

        return rewardShaping;
    }
}
