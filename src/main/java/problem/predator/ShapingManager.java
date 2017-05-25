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
        //First Try
        // calculate the previous distance between the predator and the pray
        double old_dist_x = previousState[2];
        double old_dist_y = previousState[3];
        double old_dist = Math.sqrt(Math.pow(old_dist_x,2)+Math.pow(old_dist_y,2));
        //calculate current distance between the predator and the pray
        double new_dist_x = currentState[2];
        double new_dist_y = currentState[3];
        double new_dist = Math.sqrt(Math.pow(new_dist_x,2)+Math.pow(new_dist_y,2));
        //check if the predator got closer to the pray
        if(new_dist < old_dist){
            //The predator got closer so give him more reward
            rewardShaping = 10;
        }else{
            //The predator did not got closer so give him negative reward
            rewardShaping = -100;
        }
        //Second Try -BAD!!!!
        /*// calculate the previous distance between the predator and the pray
        double old_dist_x = previousState[2];
        double old_dist_y = previousState[3];
        double old_dist = Math.sqrt(Math.pow(old_dist_x,2)+Math.pow(old_dist_y,2));
        //calculate current distance between the predator and the pray
        double new_dist_x = currentState[2];
        double new_dist_y = currentState[3];
        double new_dist = Math.sqrt(Math.pow(new_dist_x,2)+Math.pow(new_dist_y,2));
        //check if the predator got closer to the pray
        if(new_dist < old_dist){
            //The predator got closer so check if the otehr predator got closer to us too - if he is close to us probably he close to the pray
            // calculate the previous distance between the predator and the pray
            double old_dist_x_other = previousState[0];
            double old_dist_y_other = previousState[1];
            double old_dist_other = Math.sqrt(Math.pow(old_dist_x_other,2)+Math.pow(old_dist_y_other,2));
            //calculate current distance between the predator and the pray
            double new_dist_x_other = currentState[0];
            double new_dist_y_other = currentState[1];
            double new_dist_other = Math.sqrt(Math.pow(new_dist_x_other,2)+Math.pow(new_dist_y_other,2));
            //check if the other predator is closer too
            if(new_dist_other < old_dist_other) {
                rewardShaping = 20;
            }else{
                rewardShaping = 10;
            }
        }else{
            //The predator did not got closer so give him negative reward
            rewardShaping = -100;
        }*/
        // *** END OF YOUR CODE ********************************************************************

        return rewardShaping;
    }
}
