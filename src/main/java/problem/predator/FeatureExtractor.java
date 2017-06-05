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
        //First Try - Distance between this predator and other predator and this predator and the prey - BAD...
        //state[0] = Math.sqrt(Math.pow(thisPredator.x - otherPredator.x,2)+Math.pow(thisPredator.y - otherPredator.y,2));
        //state[1] = Math.sqrt(Math.pow(thisPredator.x - prey.x,2)+Math.pow(thisPredator.y - prey.y,2));

        //Second Try - Just distance between this predator and the prey - Very Bad
        //state[0] = Math.sqrt(Math.pow(thisPredator.x - prey.x,2)+Math.pow(thisPredator.y - prey.y,2));

        //Third Try - relative distance between this predator and the prey - no need for other predator
        state[0] = thisPredator.x - prey.x;
        state[1] = thisPredator.y - prey.y;

        //state[0] = Math.abs(prey.x- thisPredator.x);
        //state[1] = Math.abs(prey.y - thisPredator.y);
        // *** END OF YOUR CODE ********************************************************************

        return state;
    }

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
