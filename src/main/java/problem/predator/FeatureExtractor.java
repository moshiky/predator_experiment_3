package problem.predator;

/**
 * Created by Dev on 26/04/2017.
 */
public class FeatureExtractor {
    public static double[] getStateRepresentation(Animal[][] worldGrid, Animal[] predators, Animal[] preys, Animal caller) {
        double[] state = null;

        // *** YOUR CODE HERE **********************************************************************

        state = new double[4];
        for (int i = 0; i < predators.length; i++) {
            if ((predators[i].x != caller.x) && (predators[i].y != caller.y)) {
                state[0] = (int)((caller.x - predators[i].x) / 5.0);
                state[1] = (int)((caller.y - predators[i].y) / 5.0);
            }
        }

        state[2] = (int)((caller.x - preys[0].x) / 5.0);
        state[3] = (int)((caller.y - preys[0].y) / 5.0);

        // *** END OF YOUR CODE ********************************************************************

        return state;
    }
}
