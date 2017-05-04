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
                state[0] = (caller.x - predators[i].x);
                state[1] = (caller.y - predators[i].y);
            }
        }

        state[2] = (caller.x - preys[0].x);
        state[3] = (caller.y - preys[0].y);

        // *** END OF YOUR CODE ********************************************************************

        return state;
    }
}
