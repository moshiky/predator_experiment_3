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
        double[] state = new double[4];

        // *** YOUR CODE HERE **********************************************************************
        state[2] = 0.1 * (Math.sqrt(Math.pow(thisPredator.x - otherPredator.x , 2) + Math.pow(thisPredator.y - otherPredator.y , 2)));
        state[3] = 0.4 *(Math.sqrt(Math.pow(thisPredator.x - prey.x , 2) + Math.pow(thisPredator.y - prey.y , 2)));
    //    state[4] = 0.1 * (Math.sqrt(Math.pow(prey.x - otherPredator.x , 2) + Math.pow(otherPredator.y - prey.y , 2)));
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        dotProduct += thisPredator.x * otherPredator.x;
        normA += Math.pow(thisPredator.x, 2);
        normB += Math.pow(otherPredator.x, 2);
        dotProduct += thisPredator.y * otherPredator.y;
        normA += Math.pow(thisPredator.y, 2);
        normB += Math.pow(otherPredator.y, 2);
        state[0] = 0.1 * (dotProduct / (Math.sqrt(normA) * Math.sqrt(normB)));

        dotProduct = 0.0;
        normA = 0.0;
        normB = 0.0;
        dotProduct += thisPredator.x * prey.x;
        normA += Math.pow(thisPredator.x, 2);
        normB += Math.pow(prey.x, 2);
        dotProduct += thisPredator.y * prey.y;
        normA += Math.pow(thisPredator.y, 2);
        normB += Math.pow(prey.y, 2);
        state[1] = 0.4*(dotProduct / (Math.sqrt(normA) * Math.sqrt(normB)));

//        dotProduct = 0.0;
//        normA = 0.0;
//        normB = 0.0;
//        dotProduct += otherPredator.x * prey.x;
//        normA += Math.pow(otherPredator.x, 2);
//        normB += Math.pow(prey.x, 2);
//        dotProduct += otherPredator.y * prey.y;
//        normA += Math.pow(otherPredator.y, 2);
//        normB += Math.pow(prey.y, 2);
//        state[5] = 0.1 * (dotProduct / (Math.sqrt(normA) * Math.sqrt(normB)));




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
