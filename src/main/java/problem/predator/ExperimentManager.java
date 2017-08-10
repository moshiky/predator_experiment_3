package problem.predator;

import javafx.util.Pair;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import problem.learning.AgentType;
import problem.learning.StateAction;
import problem.utils.Logger;
import problem.learning.IQTable;
import problem.learning.DoubleHashTable;

import java.util.*;

/**
 * Created by Dev on 05/05/2017.
 */
public class ExperimentManager {

    private Logger m_logger;

    public ExperimentManager(Logger logger) {
        this.m_logger = logger;
    }

    public void runAgent(int agentType) {
        this.runExperimentType(new int[]{0});
    }

    private double runExperimentType(int[] objectives) {
        int experiments = 50;
        int trainEpisodes = 200000;
        int evaluationEpisodes = 10000;
        int trainBatchSize = 10000;
        int maxBatchDuration = 100 * 1000;
        int loggingInterval = 100;

        AgentType[] typesToEvaluate =
                new AgentType[]{AgentType.RewardShaping, AgentType.Similarities, AgentType.SimilaritiesOnRewardShaping};

        double[] evaluationMeanResults = new double[evaluationEpisodes];

        Arrays.fill(evaluationMeanResults, 0.0);

        for (int ex = 0; ex < experiments; ex++) {
            PredatorWorld predatorWorld = new PredatorWorld(20, 2, AgentType.RewardShaping, objectives);
            // this.m_logger.increaseRound();
            this.m_logger.info("=== Experiment #" + ex + " ===");

            // create base table
            DoubleHashTable baseTableP1 = new DoubleHashTable(11567205);
            DoubleHashTable baseTableP2 = new DoubleHashTable(11567205);

            // train the agents
            double episodeResult = 0;

            for (int batchIndex = 0 ; batchIndex < trainEpisodes / trainBatchSize ; batchIndex++) {

                // create batch average result counters
                HashMap<String, Double> batchAvgResult = new HashMap<>();

                // create Q table diff lists
                HashMap<String, HashMap<Integer, Double>> qDiffListP1 = new HashMap<>();
                HashMap<String, HashMap<Integer, Double>> qDiffListP2 = new HashMap<>();

                // for agent type
                for (AgentType agentType : typesToEvaluate) {
                    // initiate variables
                    double batchResultsTotal = 0;

                    // create temporary sum variable
                    double tempSum = 0;

                    // duplicate current Q
                    DoubleHashTable currentQTableP1 = baseTableP1.clone();
                    currentQTableP1.setShouldLogChanges(true);

                    DoubleHashTable currentQTableP2 = baseTableP2.clone();
                    currentQTableP2.setShouldLogChanges(true);

                    // initiate predator world with that Q and current agent type
                    predatorWorld.initiatePredators(agentType, currentQTableP1, currentQTableP2);

                    // run train batch
                    long batchStartTime = System.currentTimeMillis();
                    int episodeIndexInBatch = 0;
                    for ( ; episodeIndexInBatch < trainBatchSize; episodeIndexInBatch++) {
                        // check for timeout
                        if (System.currentTimeMillis() - batchStartTime > maxBatchDuration) {
                            this.m_logger.error("batch timeout");
                            break;
                        }

                        // reset world
                        predatorWorld.reset();

                        // run episode
                        episodeResult = predatorWorld.episode(true);

                        if ((episodeIndexInBatch + 1) % loggingInterval == 0) {
                            this.m_logger.info(
                                    agentType.name() + "_" + "ex" + ex + "bt" + batchIndex +
                                            "ep" + episodeIndexInBatch + " mean: " + tempSum / loggingInterval
                            );
                            tempSum = 0;
                        }
                        tempSum += episodeResult;
                        batchResultsTotal += episodeResult;
                    }

                    // store average result
                    batchAvgResult.put(agentType.name(), batchResultsTotal / episodeIndexInBatch);

                    // store the changes list associated with the current agent type
                    qDiffListP1.put(agentType.name(), currentQTableP1.getDiffList());
                    qDiffListP2.put(agentType.name(), currentQTableP2.getDiffList());
                }

                // select best performance - lowest avg. train score
                double lowestAvgTrainScore = Double.MAX_VALUE;
                String lowestAvgTrainScoreKeyName = "";
                for (String keyName : batchAvgResult.keySet()) {
                    if (batchAvgResult.get(keyName) < lowestAvgTrainScore) {
                        lowestAvgTrainScore = batchAvgResult.get(keyName);
                        lowestAvgTrainScoreKeyName = keyName;
                    }
                }
                this.m_logger.info("best agent: " + lowestAvgTrainScoreKeyName);

                // apply best preforming agent changes list to the original Qs
                HashMap<Integer, Double> changesOfBestP1 = qDiffListP1.get(lowestAvgTrainScoreKeyName);
                for (Integer indexKey : changesOfBestP1.keySet()) {
                    baseTableP1.put(indexKey, changesOfBestP1.get(indexKey));
                }

                HashMap<Integer, Double> changesOfBestP2 = qDiffListP2.get(lowestAvgTrainScoreKeyName);
                for (Integer indexKey : changesOfBestP2.keySet()) {
                    baseTableP2.put(indexKey, changesOfBestP2.get(indexKey));
                }

                // run evaluation session with default agent that just follows the policy and not learn
                this.m_logger.info("-- Evaluation --");
                double evaluationEpisodeResult = 0;
                for (int evalEp = 0; evalEp < evaluationEpisodes; evalEp++) {
                    predatorWorld.reset();
                    // in evaluation mode there is no reason to activate either of the speedup methods
                    evaluationEpisodeResult = predatorWorld.episode(false);
                    evaluationMeanResults[evalEp] = evaluationEpisodeResult;
                }
                this.m_logger.info("evaluation results: " + Arrays.toString(evaluationMeanResults));
                this.m_logger.info("mean result: " + this.mean(evaluationMeanResults));
            }
        }

        return 0;
    }

    //averages the results of a number of runs
    private double[] means(double[][] stats) {
        double[] means = new double[stats[0].length];
        for (int j = 0; j < stats[0].length; j++) {
            for (int i = 0; i < stats.length; i++) {
                means[j] += stats[i][j];
            }
            means[j] = 1.0 * means[j] / (stats.length);
        }
        return means;
    }

    private double mean(double[] stats) {
        double means = 0.0;
        for (int i = 0; i < stats.length; i++) {
            means += stats[i];
        }
        means = 1.0 * means / (stats.length);
        return means;
    }
}
