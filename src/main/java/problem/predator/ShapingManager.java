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
        double distanceFromFriend=distanceFrom("Friend",previousState,currentState);
        double distanceFromFood=distanceFrom("Food",previousState,currentState);

        // *** YOUR CODE HERE **********************************************************************
        if(prevIsLikeCurrent(previousState,currentState)||distanceFromFood==0||distanceFromFriend==0) {
         //   System.out.println("reward is 0");
            return 0;
        }

        rewardShaping= 1/distanceFromFood+1/distanceFromFriend;
        //System.out.println("reward is:"+rewardShaping);
       // // *** END OF YOUR CODE ********************************************************************

        return rewardShaping;
    }

    private static boolean prevIsLikeCurrent(double[] previousState,double[] currentState) {
        boolean ans=true;
        ans = ans && (previousState[0] == currentState[0]);
        ans = ans && (previousState[1] == currentState[1]);
        ans = ans && (previousState[2] == currentState[2]);
        ans = ans && (previousState[3] == currentState[3]);
        return ans;
    }

    public static double distanceFrom(String fromWho,double[] previousState, double[] currentState){
            double oldDistance=1;
            double newDistance=1;
            switch(fromWho) {
                case "Friend":
                    oldDistance=Math.abs(previousState[0])+Math.abs(previousState[1]);
                    newDistance=Math.abs(currentState[0])+Math.abs(currentState[1]);
                case "Food":
                    oldDistance=Math.abs(previousState[2])+Math.abs(previousState[3]);
                    newDistance=Math.abs(currentState[2])+Math.abs(currentState[3]);

            }
           return newDistance<oldDistance ? 1/newDistance : -1/newDistance;
    }

}
