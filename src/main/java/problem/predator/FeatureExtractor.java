package problem.predator;

/**
 * Created by Dev on 26/04/2017.
 */
public class FeatureExtractor {
    public static double[] getStateRepresentation(Animal[][] worldGrid, Animal[] predators, Animal[] preys, Animal caller) {
        double[] state = null;

        // *** YOUR CODE HERE **********************************************************************
        Animal other = null;
        if (caller==predators[0])
            other=predators[1];
        else
            other=predators[0];
        Animal prey = preys[0];

        double avg_prd_x = (caller.x+other.x)/2.0;
        double avg_prd_y = (caller.y+other.y)/2.0;
        double mean_dist_to_prey = (prey.x-avg_prd_x)+(prey.y-avg_prd_y);
        double dist_to_prey_1 = (prey.x-caller.x)+(prey.y-caller.y);
        double dist_to_prey_2 = (prey.x-other.x)+(prey.y-other.y);
        state = new double[2];
        state[0] = mean_dist_to_prey;
        state[1] = Math.min(dist_to_prey_1,dist_to_prey_2);
        // *** END OF YOUR CODE ********************************************************************

        return state;
    }
}
