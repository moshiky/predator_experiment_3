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
        double[] state = null;

        // *** YOUR CODE HERE **********************************************************************

        state = new double[16];
        state[0] = align( thisPredator.x - otherPredator.x );
        state[1] = align( thisPredator.y - otherPredator.y);
        state[2] = align( thisPredator.x - prey.x);
        state[3] = align( thisPredator.y - prey.y);
        state[4] = align(thisPredator.x - otherPredator.x - (thisPredator.y - otherPredator.y)) ;
        state[5] = align( thisPredator.x - prey.x - (thisPredator.y - prey.y)) ;
        state[6] = align ( otherPredator.x - prey.x );
        state[7] = align( otherPredator.y - prey.y );
        state[8] = align(thisPredator.x - prey.x - (otherPredator.x - prey.x) );
        state[9] = align(thisPredator.y - prey.y - (otherPredator.y - prey.y) );
        state[10] = align( Math.abs(thisPredator.x - prey.x)  - 2);
        state[11] = align( Math.abs(thisPredator.y - prey.y)  - 2);
        state[12]= prey.x == 0 ? 0 : 1;
        state[13]= prey.y == 0 ? 0 : 1;
        state[14]= prey.x == 19 ? 0 : 1;
        state[15]= prey.y == 19 ? 0 : 1;
//        state[14]= otherPredator.x == 0 ? 0 : 1;
//        state[15]= otherPredator.y == 0 ? 0 : 1;
//        state[16]= otherPredator.x == 19 ? 0 : 1;
//        state[17]= otherPredator.y == 19 ? 0 : 1;
//        state[18]= thisPredator.x == 0 ? 0 : 1;
//        state[19]= thisPredator.y == 0 ? 0 : 1;
//        state[20]= thisPredator.x == 19 ? 0 : 1;
//        state[21]= thisPredator.y == 19 ? 0 : 1;



        // *** END OF YOUR CODE ********************************************************************

        return state;
    }

    private static double align(double a)
    {
        if (a < 0)
            return -1;
        else
            return a > 0 ? 1 : 0;
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
