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

        // *** YOUR CODE HERE **********************************************************************
        //OPTION ONE
       /* double[] state = new double[3];
        state[0] = Math.pow(thisPredator.x - otherPredator.x,2) +Math.pow( thisPredator.y - otherPredator.y,2);
       // state[1] = Math.sqrt( Math.pow( thisPredator.x - prey.x,2) +Math.pow( thisPredator.y - prey.y,2));

        state[1] = thisPredator.x - prey.x;
        state[2] = thisPredator.y - prey.y;*/
        // *** END OF YOUR CODE ********************************************************************
        //CHECK 1 -- sum up the manhaten distances -- not good
           /* double[] state = new double[2];
            state[0] = Math.abs(thisPredator.x - prey.x)+ Math.abs(thisPredator.y - prey.y);
            state[1] = Math.abs(thisPredator.x - otherPredator.x)+ Math.abs(thisPredator.y - otherPredator.y);*/


        //CHECK 2 --dis x and y from pray and all in all distance from other predetor -- around 32
       /* double[] state = new double[3];
        state[0] = thisPredator.x - prey.x;
        state[1] = thisPredator.y - prey.y;
        state[2] =  Math.abs(thisPredator.x - otherPredator.x)+ Math.abs(thisPredator.y - otherPredator.y);*/

       //CHECK 3  --> 25 steps
       /* double[] state = new double[2];
        state[0] = thisPredator.x - prey.x;
        state[1] = thisPredator.y - prey.y;*/
       // state[2] =  Math.abs(thisPredator.x - otherPredator.x)+ Math.abs(thisPredator.y - otherPredator.y);

        double[] state = new double[2];
        state[0] = thisPredator.x - prey.x;
        state[1] = thisPredator.y - prey.y;
       // state[2] = Math.sqrt( Math.abs(thisPredator.x - otherPredator.x)+ Math.abs(thisPredator.y - otherPredator.y));

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
