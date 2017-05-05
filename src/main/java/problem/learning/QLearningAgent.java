/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package problem.learning;

import problem.RNG;
import problem.predator.SimilarityManager;
import problem.predator.SimilarityRecord;
import java.util.*;

/**
 * @author timbrys
 */
public abstract class QLearningAgent extends LearningAgent {

    private double[] m_previousState;
    private AvlTreeBasedQTable m_qTable;

    public QLearningAgent(Problem prob, AgentType type, int[] objectivesToUse) {
        super(prob, type, objectivesToUse);
        this.m_qTable = new AvlTreeBasedQTable();
    }

    public int act() {
        this.m_previousState = this.getState();
        return prevAction;
    }

    //Q(lambda) logic
    public void reward(double reward) {

        // find best action
        double bestNextQValue = Double.MAX_VALUE * -1;
        ArrayList<Integer> bestActions = new ArrayList<>();
        double[] currentState = this.getState();

        for (int i = 0 ; i < prob.getNumActions() ; i++) {
            double currentStateQValue = this.m_qTable.getStateActionValue(currentState, i);
            if (currentStateQValue >= bestNextQValue) {
                if (currentStateQValue > bestNextQValue) {
                    bestNextQValue = currentStateQValue;
                    bestActions.clear();
                }
                bestActions.add(i);
            }
        }

        // shape reward in case needed
        if (AgentType.RewardShaping == type) {
            // r = R(s,a,s') + F(s,a,s')
            reward += shaping(4);
        }

        // calculate delta
        Double previousQValue = this.m_qTable.getStateActionValue(this.m_previousState, this.prevAction);
        double delta = reward + gamma * bestNextQValue - previousQValue;

        // update Q(s,a)
        this.m_qTable.setStateActionValue(
                this.m_previousState, this.prevAction, previousQValue + alpha * delta
        );


        // update similar state-action pairs in case agent type is Similarities
        if (AgentType.Similarities == type) {
            ArrayList<SimilarityRecord> similarityRecords =
                    SimilarityManager.getSimilarityRecords(this.m_previousState, prevAction);

            // update all relevant state-action records in the Q table
            for (SimilarityRecord record : similarityRecords) {
                previousQValue = this.m_qTable.getStateActionValue(record.getState(), record.getAction());
                this.m_qTable.setStateActionValue(
                        record.getState(),
                        record.getAction(),
                        previousQValue + alpha * delta * record.getSimilarityFactor()
                );
            }
        }

        // select next action
        if (RNG.randomDouble() > epsilon) {
            // select greedily
            prevAction = bestActions.get(RNG.randomInt(bestActions.size()));
        }
        else {
            // select random action
            prevAction = RNG.randomInt(prob.getNumActions());
        }
    }
}
