package problem.predator;

/**
 * Created by Dev on 26/04/2017.
 */
public class FeatureExtractor {
    public static double[] getStateFeatures(Animal[][] worldGrid, Animal[] predators, Animal[] preys, Animal caller) {
        double[] state;

        // *** YOUR CODE HERE **********************************************************************

        int totalNumberOfOtherAnimals = predators.length + preys.length - 1;
        state = new double[totalNumberOfOtherAnimals * 2];

        // calculate distance to other predators
        int cellIndex = 0;
        for (int i = 0 ; i < predators.length ; i++) {
            if (predators[i].x != caller.x && predators[i].y != caller.y) {
                state[cellIndex++] = (caller.x - predators[i].x) / 10.0;
                state[cellIndex++] = (caller.y - predators[i].y) / 10.0;
            }
        }

        // calculate distance to preys
        for (int i = 0; i < preys.length ; i++) {
            state[cellIndex++] = (caller.x - preys[i].x) / 10.0;
            state[cellIndex++] = (caller.y - preys[i].y) / 10.0;
        }

        // *** END OF YOUR CODE ********************************************************************

        return state;
    }
}
