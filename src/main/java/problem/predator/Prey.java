/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package problem.predator;

import java.util.ArrayList;

import problem.RNG;
import problem.learning.AgentType;

/**
 * @author timbrys
 */
public class Prey extends Animal {

    private int size;
    protected PredatorWorld pw;

    public Prey(PredatorWorld pw, int size, int x, int y) {
        super(pw, AgentType.NoShaping, new int[]{0}, x, y);
        this.size = size;
        this.pw = pw;
    }

    public double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public void simMove(Direction dir, double[] coords) {
        switch (dir) {
            case Up:
                if (coords[1] > 0) {
                    coords[1]--;
                }
                break;
            case Down:
                if (coords[1] < size - 1) {
                    coords[1]++;
                }
                break;
            case Left:
                if (coords[0] > 0) {
                    coords[0]--;
                }
                break;
            case Right:
                if (coords[0] < size - 1) {
                    coords[0]++;
                }
                break;
        }
    }

    @Override
    public int act() {
        if (RNG.randomDouble() < 0.2) {
            return RNG.randomInt(Direction.values().length);
        }

        Animal[] predators = pw.getPredators();
        Animal[] preys = pw.getPreys();
        double best = 0;
        boolean isFirstValue = true;
        ArrayList<Integer> ibest = new ArrayList<Integer>();
        for (int i = 0; i < Direction.values().length; i++) {
            double[] coords = new double[]{x, y};
            simMove(Direction.values()[i], coords);
            double dist = distance(coords[0], coords[1], predators[0].x, predators[0].y)
                    + distance(coords[0], coords[1], predators[1].x, predators[1].y);
            if (dist >= best || isFirstValue) {
                if (dist > best || isFirstValue) {
                    ibest.clear();
                }
                ibest.add(i);
                best = dist;
                isFirstValue = false;
            }
        }
        return ibest.get(RNG.randomInt(ibest.size()));
    }

    @Override
    public double[] getState() {
        return new double[0];
    }

    public int[] getIntState() {
        return new int[0];
    }

    @Override
    public int getStateSize() {
        return 0;
    }

    @Override
    public double shaping(int s) {
        return 0.0;
    }

    @Override
    public double shapingNormalization(int s) {
        return 1.0;
    }
}
