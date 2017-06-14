package problem.predator;

/**
 * Created by Dev on 26/04/2017.
 */
public class FeatureExtractor {

    /**
     * Should return a representation of current state, as seen by caller predator (thisPredator).
     * @param thisPredator  caller predator. you can access its location by: thisPredator.x, thisPredator.y
     * @param otherPredator other predator. you can access its location by: otherPredator.x, otherPredator.y
     * @param prey  the prey. you can access its location by: prey.x, prey.y
     * @return a representation of current state, as seen by thisPredator
     */
    public static double[] getStateRepresentation(Animal thisPredator, Animal otherPredator, Animal prey) {
        double[] state = new double[2];
        // *** YOUR CODE HERE **********************************************************************
        state[0] = thisPredator.x - prey.x;
        state[1] = thisPredator.y - prey.y;
        // *** END OF YOUR CODE ********************************************************************

        return state;
    }

    /**
     *
     double[] state = new double[2];

     // *** YOUR CODE HERE **********************************************************************
     state[0] = thisPredator.x - prey.x;
     state[1] = thisPredator.y - prey.y;
     // *** END OF YOUR CODE ********************************************************************

     return state;
     */

    /**
     * For BasicQLearning while using abstraction
     * @param thisPredator
     * @param otherPredator
     * @param prey
     * @return
     */
    public static double[] getBasicStateRepresentation(Animal thisPredator, Animal otherPredator, Animal prey) {
        double[] state = new double[4];

        state[0] = thisPredator.x - otherPredator.x;
        state[1] = thisPredator.y - otherPredator.y;
        state[2] = thisPredator.x - prey.x;
        state[3] = thisPredator.y - prey.y;

        return state;
    }
}
