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
        state = new double[5];
//        state[0] = thisPredator.x - otherPredator.x;
//        state[1] = thisPredator.y - otherPredator.y;
//        state[2] = thisPredator.x - prey.x;
//        state[3] = thisPredator.y - prey.y;
//        state[4] = thisPredator.y;
//        state[5] = thisPredator.x;

        state[0] = Math.sqrt((thisPredator.x - prey.x)*(thisPredator.x - prey.x)
                + (thisPredator.y - prey.y)*(thisPredator.y - prey.y));
        state[1] = Math.sqrt(thisPredator.x*thisPredator.x + thisPredator.y*thisPredator.y);

        double angle = Math.toDegrees(Math.atan2(thisPredator.y - prey.y, thisPredator.x - prey.x));

        if(angle < 0){
            angle += 360;
        }

        state[2] = angle;

        angle = Math.toDegrees(Math.atan2(thisPredator.y, thisPredator.x));

        if(angle < 0){
            angle += 360;
        }

        state[3] = angle;

        state[4] = Math.sqrt((thisPredator.x - otherPredator.x)*(thisPredator.x - otherPredator.x)
                    + (thisPredator.y - otherPredator.y)*(thisPredator.y - otherPredator.y));


//        angle = Math.toDegrees(Math.atan2(thisPredator.y - otherPredator.y, thisPredator.x - otherPredator.x));
//
//        if(angle < 0){
//            angle += 360;
//        }
//
//        state[5] = angle;

//        state[3] = thisPredator.x - otherPredator.x;
//        state[4] = thisPredator.y - otherPredator.y;
//        state[5] = thisPredator.x - prey.x;
//        state[6] = thisPredator.y - prey.y;
//        state[3] = thisPredator.y;

        if (thisPredator.x == prey.x - 1 && thisPredator.y == prey.y) {
            state[0] = 30;
            state[1] = 30;
            state[2] = 30;
            state[3] = 30;
            state[4] = 30;
//            state[5] = 1;
        } else if (thisPredator.x == prey.x + 1 && thisPredator.y == prey.y) {
            state[0] = 11;
            state[1] = 11;
            state[2] = 11;
            state[3] = 11;
            state[4] = 11;
//            state[5] = 2;
        } else if (thisPredator.x == prey.x && thisPredator.y == prey.y - 1) {
            state[0] = 21;
            state[1] = 21;
            state[2] = 21;
            state[3] = 21;
            state[4] = 21;
//            state[5] = 3;
        } else if (thisPredator.x == prey.x - 1 && thisPredator.y == prey.y + 1) {
            state[0] = 31;
            state[1] = 31;
            state[2] = 31;
            state[3] = 31;
            state[4] = 31;
//            state[5] = 4;
        }



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
