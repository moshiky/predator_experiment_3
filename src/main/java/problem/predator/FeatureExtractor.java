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


        // *** YOUR CODE HERE *********************************************************************
        double disFromotherPrey =   Math.sqrt(Math.pow( thisPredator.x - prey.x,2) +  Math.pow(thisPredator.y - prey.y,2));
        double disFromotherPredator =   Math.sqrt(Math.pow( thisPredator.x - otherPredator.x,2) +  Math.pow(thisPredator.y - otherPredator.y,2));
        double guu =   Math.sqrt(Math.pow( otherPredator.x - prey.x,2) +  Math.pow(otherPredator.y - prey.y,2));
        double ppp =   Math.sqrt(Math.pow( thisPredator.x - 0,2) +  Math.pow(thisPredator.y - 0,2));
        double kkk =   Math.sqrt(Math.pow( thisPredator.x - 20,2) +  Math.pow(thisPredator.y - 20,2));
        // *** END OF YOUR CODE ********************************************************************
        //double x = thisPredator.x - otherPredator.x;
        //double y = thisPredator.y - otherPredator.y;
        //state[2] = thisPredator.x - prey.x;
        //state[3] = thisPredator.y - prey.y;
        double[] state = {disFromotherPrey,disFromotherPredator,guu,ppp,kkk};
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
