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
        double[] state = new double[6];

        /* Just give the locations
        state[0] = otherPredator.x;
        state[1] = otherPredator.y;
        state[2] = prey.x;
        state[3] = prey.y;
        state[4] = thisPredator.x;*/


        state[0] = thisPredator.x > prey.x ? 1 : 0;
        state[1] = thisPredator.y > prey.y ? 1 : 0;
        state[2] = otherPredator.x > prey.x ? 1 : 0;
        state[3] = otherPredator.y > prey.y ? 1 : 0;
        state[4] = otherPredator.x > thisPredator.x ? 1 : 0;
        state[5] = otherPredator.y > thisPredator.y ? 1 : 0;

/*
        double x0 = thisPredator.x > prey.x ? 1 : 0;
        double y0 = thisPredator.y > prey.y ? 1 : 0;
        double x1 = otherPredator.x > prey.x ? 1 : 0;
        double y1 = otherPredator.y > prey.y ? 1 : 0;
        double x2 = otherPredator.x > thisPredator.x ? 1 : 0;
        double y2 = otherPredator.y > thisPredator.y ? 1 : 0;

        state[0] = x0 + x1;
        state[1] = y0 + y1;
        state[2] = x2;
        state[3] = y2;
*/


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
