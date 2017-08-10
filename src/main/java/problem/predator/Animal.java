/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package problem.predator;

import problem.learning.AgentType;
import problem.learning.IQTable;
import problem.learning.QLearningAgent;

/**
 * @author timbrys
 */
public abstract class Animal extends QLearningAgent {
    public boolean predator;
    public int x;
    public int y;

    public Animal(PredatorWorld pw, AgentType type, int[] objectives, int x, int y, IQTable qTable) {
        super(pw, type, objectives, qTable);
        this.predator = false;
        this.x = x;
        this.y = y;
    }
}
