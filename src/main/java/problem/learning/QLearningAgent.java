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
        /* if (AgentType.BasicQLearning == type || AgentType.Similarities == type) {
            this.m_qTable = new AvlTreeBasedQTable();
        } */
    }

    public int act() {
        /* if (AgentType.BasicQLearning == type || AgentType.Similarities == type) {
            this.m_previousState = getIntState();
        }
        else {
            //makes sure in first iteration that the previous potential and activated tiles are initialized
            if (prevPot == null && AgentType.RewardShaping != type) {
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
        } */
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

        /*if (AgentType.BasicQLearning == type || AgentType.Similarities == type) {
            // find best action
            Double bestNextQValue = Double.MAX_VALUE * -1;
            ArrayList<Integer> bestActions = new ArrayList<>();
            int[] currentState = getIntState();

            for (int i = 0 ; i < prob.getNumActions() ; i++) {
                Double currentStateQValue = this.m_qTable.get(getStateActionStringKey(currentState, i));
                if (currentStateQValue >= bestNextQValue) {
                    if (currentStateQValue > bestNextQValue) {
                        bestNextQValue = currentStateQValue;
                        bestActions.clear();
                    }
                    bestActions.add(i);
                }
            }

            // simplified q table, no tile coding
            StateAction previousStateActionKey = getStateActionStringKey(this.m_previousState, this.prevAction);

            // calculate delta
            Double previousQValue = this.m_qTable.get(previousStateActionKey);
            double delta = reward + gamma * bestNextQValue - previousQValue;

            // update Q(s,a)
            this.m_qTable.put(previousStateActionKey, previousQValue + alpha * delta);


            // update similar state-action pairs in case agent type is Similarities
            if (AgentType.Similarities == type) {
                ArrayList<SimilarityRecord> similarityRecords =
                        SimilarityManager.getSimilarityRecords(this.m_previousState, prevAction);

                // update all relevant state-action records in the Q table
                for (SimilarityRecord record : similarityRecords) {
                    StateAction stateActionKey = getStateActionStringKey(record.getState(), record.getAction());
                    previousQValue = this.m_qTable.get(stateActionKey);
                    this.m_qTable.put(stateActionKey, previousQValue + alpha * delta * record.getSimilarityFactor());
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
        else {
            double[] delta = new double[nrObjectives];

            if (type != AgentType.RewardShaping) {
                //holds potential for each shaping
                double[] curPot = new double[nrObjectives];

                if (type == AgentType.Linear || type == AgentType.BestLinear) {
                    curPot[0] = scalarizedShaping();
                }
                else {
                    for (int o = 0; o < nrObjectives; o++) {
                        curPot[o] = shaping(objectivesToUse[o]);
                    }
                }

                //applies each time a different shaping to the base reward
                //delta = r + gamma F(s') - F(s)
                for (int o = 0; o < nrObjectives; o++) {
                    delta[o] = reward + gamma * curPot[o] - prevPot[o];
                }
            }
            else {
                // agent type is RewardShaping, so there is just one objective, which is user defined
                delta[0] = reward + shaping(objectivesToUse[0]);
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
            } else if (type != AgentType.RewardShaping) {
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
        } */
    }
}
