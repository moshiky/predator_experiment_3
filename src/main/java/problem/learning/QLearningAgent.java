/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package problem.learning;

import problem.RNG;
import problem.predator.SimilarityManager;
import problem.predator.SimilarityRecord;
import sun.management.Agent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author timbrys
 */
public abstract class QLearningAgent extends LearningAgent {

    private double[] m_previousState;
    private Map<String, Double> m_qTable;

    public QLearningAgent(Problem prob, AgentType type, int[] objectivesToUse) {
        super(prob, type, objectivesToUse);

        if (AgentType.Similarities == type) {
            this.m_qTable = new HashMap<>();
        }
    }

    public int act() {
        if (AgentType.Similarities == type) {
            this.m_previousState = getState();
        }
        else {
            //makes sure in first iteration that the previous potential and activated tiles are initialized
            if (prevPot == null) {
                prevPot = new double[nrObjectives];
                if (type == AgentType.Linear || type == AgentType.BestLinear) {
                    prevPot[0] = scalarizedShaping();
                } else {
                    for (int o = 0; o < nrObjectives; o++) {
                        prevPot[o] = shaping(objectivesToUse[o]);
                    }
                }
            }
            if (prevFa == null) {
                prevFa = tileCoding(getState(), prevAction);
            }
        }

        return prevAction;
    }

    private Double getQValue(String stateActionKey) {
        if (!this.m_qTable.containsKey(stateActionKey)) {
            this.m_qTable.put(stateActionKey, 0.0);
        }

        return this.m_qTable.get(stateActionKey);
    }

    //Q(lambda) logic
    public void reward(double reward) {

        if (AgentType.Similarities == type) {
            // find best action
            Double bestNextQValue = Double.MAX_VALUE * -1;
            ArrayList<Integer> bestActions = new ArrayList<>();
            double[] currentState = getState();

            for (int i = 0 ; i < prob.getNumActions() ; i++) {
                Double currentStateQValue = this.getQValue(getStateActionStringKey(currentState, i));
                if (currentStateQValue >= bestNextQValue) {
                    if (currentStateQValue > bestNextQValue) {
                        bestNextQValue = currentStateQValue;
                        bestActions.clear();
                    }
                    bestActions.add(i);
                }
            }

            // simplified q table, no tile coding
            String previousStateActionKey = getStateActionStringKey(this.m_previousState, this.prevAction);

            // calculate delta
            Double previousQValue = this.getQValue(previousStateActionKey);
            double delta = reward + gamma * bestNextQValue - previousQValue;

            // get similar state-action records
            ArrayList<SimilarityRecord> similarityRecords =
                    SimilarityManager.getSimilarityRecords(this.m_previousState, prevAction);
            similarityRecords.add(new SimilarityRecord(this.m_previousState, prevAction, 1));

            // update all relevant state-action records in the Q table
            for (SimilarityRecord record : similarityRecords) {
                String stateActionKey = getStateActionStringKey(record.getState(), record.getAction());
                previousQValue = this.getQValue(stateActionKey);
                this.m_qTable.put(stateActionKey, previousQValue + alpha * delta * record.getSimilarityFactor());
            }

            // update previous state
            this.m_previousState = currentState;

            // select next action
            if (RNG.randomDouble() > epsilon) {
                // select greedily
                prevAction = bestActions.get(RNG.randomInt(bestActions.size()));
            }
            else {
                prevAction = RNG.randomInt(prob.getNumActions());
            }
        }
        else {
            //holds potential for each shaping
            double[] curPot = new double[nrObjectives];
            if (type == AgentType.Linear || type == AgentType.BestLinear) {
                curPot[0] = scalarizedShaping();
            } else {
                for (int o = 0; o < nrObjectives; o++) {
                    curPot[o] = shaping(objectivesToUse[o]);
                }
            }

            //applies each time a different shaping to the base reward
            double[] delta = new double[nrObjectives];
            //delta = r + gamma F(s') - F(s)
            for (int o = 0; o < nrObjectives; o++) {
                delta[o] = reward + gamma * curPot[o] - prevPot[o];
            }

            //delta = r + gamma F(s') - F(s) - Q(s,a)
            for (int i = 0; i < prevFa.length; i++) {
                for (int o = 0; o < nrObjectives; o++) {
                    delta[o] -= theta[o][prevFa[i]];
                }
            }

            double[] state = getState();

            //finds activated weights for each action
            int[][] Fas = new int[prob.getNumActions()][];
            for (int i = 0; i < prob.getNumActions(); i++) {
                Fas[i] = tileCoding(state, i);
            }

            //will store Q-values for each objective-action pair (given current state)
            double Qs[][] = new double[nrObjectives][prob.getNumActions()];
            double[] best = new double[nrObjectives];
            for (int o = 0; o < nrObjectives; o++) {
                best[o] = -Double.MAX_VALUE;
            }

            //calculates Q-values and stores best for each objective
            for (int i = 0; i < prob.getNumActions(); i++) {
                for (int o = 0; o < nrObjectives; o++) {
                    for (int j = 0; j < Fas[i].length; j++) {
                        Qs[o][i] += theta[o][Fas[i][j]];
                    }
                    if (Qs[o][i] > best[o]) {
                        best[o] = Qs[o][i];
                    }
                }
            }

            //delta = r + gamma F(s') - F(s) + gamma max_a Q(s',a) - Q(s,a)
            for (int o = 0; o < nrObjectives; o++) {
                delta[o] += gamma * best[o];
            }

            //update weights theta = alpha delta e
            for (int o = 0; o < nrObjectives; o++) {
                for (int i = 0; i < theta[o].length; i++) {
                    theta[o][i] += alpha * delta[o] * es[o][i];
                }
            }

            //action selection
            int action = 0;
            //greedy
            if (RNG.randomDouble() > epsilon) {
                Qs = new double[nrObjectives][prob.getNumActions()];

                //each tile separately
                double weights[][][] = new double[nrObjectives][prob.getNumActions()][nrTiles];

                for (int i = 0; i < prob.getNumActions(); i++) {
                    for (int j = 0; j < Fas[i].length; j++) {
                        for (int o = 0; o < nrObjectives; o++) {
                            Qs[o][i] += theta[o][Fas[i][j]];
                            weights[o][i][j] = theta[o][Fas[i][j]];
                        }
                    }
                }
                //adaptive or random objective selection + action selection
                if (type == AgentType.AOS || type == AgentType.ROS) {
                    action = adaptiveObjectiveSelection(Qs, weights);
                    //regular action selection
                } else if (type != AgentType.Random) {
                    action = actionSelection(Qs);
                }

                //decay traces
                for (int o = 0; o < nrObjectives; o++) {
                    for (int i = 0; i < es[o].length; i++) {
                        es[o][i] *= gamma * lambda;
                        if (es[o][i] < 0.000000001) {
                            es[o][i] = 0;
                        }
                    }
                }
                //random
            } else {
                action = RNG.randomInt(prob.getNumActions());
                //resets all traces. we should check whether the random action
                //happens to be greedy wrt to one of the objectives
                resetEs();
            }

            //s' = s
            prevFa = tileCoding(state, action);
            prevAction = action;

            //store previous potentials
            if (type == AgentType.Linear || type == AgentType.BestLinear) {
                prevPot[0] = scalarizedShaping();
            } else {
                for (int o = 0; o < nrObjectives; o++) {
                    prevPot[o] = shaping(objectivesToUse[o]);
                }
            }

            //update traces
            for (int o = 0; o < nrObjectives; o++) {
                for (int i = 0; i < prevFa.length; i++) {
                    es[o][prevFa[i]] = 1;
                }
            }
        }
    }
}
