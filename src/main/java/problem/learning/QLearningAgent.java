/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package problem.learning;

import problem.RNG;
import problem.predator.SimilarityManager;
import problem.predator.SimilarityRecord;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author timbrys
 */
public abstract class QLearningAgent extends LearningAgent {

    private double[][] m_similarityFactor;

    public QLearningAgent(Problem prob, AgentType type, int[] objectivesToUse) {
        super(prob, type, objectivesToUse);

        if (AgentType.Similarities == type) {
            this.resetSimilarityFactorArray();
        }
    }

    private void resetSimilarityFactorArray() {
        this.m_similarityFactor = new double[nrObjectives][maxNrTiles];
        for (double[] row : this.m_similarityFactor) {
            Arrays.fill(row, 0);
        }
    }

    public int act() {
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
        return prevAction;
    }

    //Q(lambda) logic
    public void reward(double reward) {

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

        // update similar state-action pairs if needed
        if (AgentType.Similarities == type) {
            // get similar states
            ArrayList<SimilarityRecord> similarityRecords = SimilarityManager.getSimilarityRecords(state, prevAction);

            // gather all update factors
            double[] similarityFactors = new double[maxNrTiles];
            Arrays.fill(similarityFactors, 0);

            for (SimilarityRecord similarityRecord : similarityRecords) {
                int[] stateFas = tileCoding(similarityRecord.getState(), similarityRecord.getAction());
                for (int tile : stateFas) {
                    similarityFactors[tile] = Math.max(similarityFactors[tile], similarityRecord.getSimilarityFactor());
                }
            }

            // update according to similarity factors
            for (int o = 0; o < nrObjectives; o++) {
                for (int i = 0; i < theta[o].length; i++) {
                    theta[o][i] += alpha * delta[o] * similarityFactors[i];
                }
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
