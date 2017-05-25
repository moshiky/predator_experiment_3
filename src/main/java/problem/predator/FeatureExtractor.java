package problem.predator;

import org.apache.commons.lang3.ArrayUtils;

import java.util.LinkedList;
import java.util.List;

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
        List<Double> list = new LinkedList<>();
//        list.add((double) (thisPredator.x - otherPredator.x));
//        list.add((double) (thisPredator.y - otherPredator.y));
        int dist_bin = (thisPredator.x + otherPredator.x - prey.x) > 0 &&
                (thisPredator.x + otherPredator.x - prey.x) < 3 &&
                (thisPredator.y + otherPredator.y - prey.y) > 0 &&
                (thisPredator.y + otherPredator.y - prey.y) < 3 ? 1: 0;
        list.add((double) dist_bin);
        dist_bin = (thisPredator.x + otherPredator.x - prey.x) > 5 &&
                (thisPredator.y + otherPredator.y - prey.y) > 5 ? 1: 0;
        list.add((double) dist_bin);
        dist_bin = (thisPredator.x + otherPredator.x - prey.x) > 10 &&
                (thisPredator.y + otherPredator.y - prey.y) > 10 ? 1: 0;
        list.add((double) dist_bin);
//        list.add((double) (thisPredator.x - prey.x));
//        list.add((double) (thisPredator.y - prey.y));
//        list.add((double) (otherPredator.x - prey.x));
//        list.add((double) (otherPredator.y - prey.y));

        int pin = (prey.x == 19 && prey.y == 19) ? 1: 0;
        list.add(new Double(pin));
        pin = (prey.x == 0 && prey.y == 0) ? 1: 0;
        list.add(new Double(pin));
        pin = (prey.x == 0 && prey.y == 19) ? 1: 0;
        list.add(new Double(pin));
        pin = (prey.x == 19 && prey.y == 0) ? 1: 0;
        list.add(new Double(pin));

        /*pin = prey.x == 0 ? 1: 0;
        list.add(new Double(pin));
        pin = prey.x == 19 ? 1: 0;
        list.add(new Double(pin));
        pin = prey.y == 0 ? 1: 0;
        list.add(new Double(pin));
        pin = prey.y == 19 ? 1: 0;
        list.add(new Double(pin));*/

        pin = (prey.x == thisPredator.x && prey.x == otherPredator.x && thisPredator.y < otherPredator.y) ? 1: 0;
        list.add(new Double(pin));
        pin = (prey.x == thisPredator.x && prey.x == otherPredator.x && thisPredator.y > otherPredator.y) ? 1: 0;
        list.add(new Double(pin));
        pin = (prey.y == thisPredator.y && prey.y == otherPredator.y && thisPredator.x > otherPredator.x) ? 1: 0;
        list.add(new Double(pin));
        pin = (prey.y == thisPredator.y && prey.y == otherPredator.y && thisPredator.x < otherPredator.x) ? 1: 0;
        list.add(new Double(pin));

        /*pin = (thisPredator.y == otherPredator.y) ? 1: 0;
        list.add(new Double(pin));
        pin = (thisPredator.x == otherPredator.x) ? 1: 0;
        list.add(new Double(pin));*/


//        state = ArrayUtils.toPrimitive((Double[])list.toArray());
        Double[] temp = list.toArray(new Double[list.size()]);
        state = ArrayUtils.toPrimitive(temp);
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
