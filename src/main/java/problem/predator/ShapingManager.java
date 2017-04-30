package problem.predator;

/**
 * Created by Dev on 30/04/2017.
 */
public class ShapingManager {

    private PredatorWorld m_predatorWorld;
    private Predator m_caller;

    // *** YOUR CODE HERE **********************************************************************
    // Here you can add custom members, if needed

    // *** END OF YOUR CODE ********************************************************************


    public ShapingManager (PredatorWorld predatorWorld, Predator caller) {
        this.m_predatorWorld = predatorWorld;
        this.m_caller = caller;

        // *** YOUR CODE HERE **********************************************************************
        // Here you can add custom members initialization, if needed

        // *** END OF YOUR CODE ********************************************************************
    }

    public double getShapingReward() {
        Animal[] predators = this.m_predatorWorld.getPredators();
        Animal[] preys = this.m_predatorWorld.getPreys();
        double rewardShaping = 0.0;

        // *** YOUR CODE HERE **********************************************************************


        // *** END OF YOUR CODE ********************************************************************

        return rewardShaping;
    }
}
