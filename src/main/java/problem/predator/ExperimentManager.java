package problem.predator;

import problem.learning.AgentType;
import problem.utils.Logger;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by Dev on 05/05/2017.
 */
public class ExperimentManager {

    private Logger m_logger;

    public ExperimentManager(Logger logger) {
        this.m_logger = logger;
    }

    public void runAgent(int agentType) {
        switch (agentType) {
            case 0:
                m_logger.setActiveSeries("NoShaping_WithTileCoding");
                this.runExperimentType(AgentType.NoShaping, new int[]{0});
                break;
            case 1:
                m_logger.setActiveSeries("SingleShaping{1}");
                this.runExperimentType(AgentType.SingleShaping, new int[]{1});
                break;
            case 2:
                m_logger.setActiveSeries("SingleShaping{2}");
                this.runExperimentType(AgentType.SingleShaping, new int[]{2});
                break;
            case 3:
                m_logger.setActiveSeries("SingleShaping{3}");
                this.runExperimentType(AgentType.SingleShaping, new int[]{3});
                break;
            case 4:
                m_logger.setActiveSeries("Linear");
                this.runExperimentType(AgentType.Linear, new int[]{1, 2, 3});
                break;
            case 5:
                m_logger.setActiveSeries("BestLinear");
                this.runExperimentType(AgentType.BestLinear, new int[]{1, 2, 3});
                break;
            case 6:
                m_logger.setActiveSeries("ROS");
                this.runExperimentType(AgentType.ROS, new int[]{1, 2, 3});
                break;
            case 7:
                m_logger.setActiveSeries("AOS");
                this.runExperimentType(AgentType.AOS, new int[]{1, 2, 3});
                break;
            case 8:
                m_logger.setActiveSeries("Abstraction");
                this.runExperimentType(AgentType.Abstraction, new int[]{0});
                break;
            case 9:
                m_logger.setActiveSeries("BasicQLearning");
                this.runExperimentType(AgentType.BasicQLearning, new int[]{0});
                break;
            case 10:
                m_logger.setActiveSeries("Similarities");
                this.runExperimentType(AgentType.Similarities, new int[]{0});
                break;
            case 11:
                m_logger.setActiveSeries("RewardShaping");
                this.runExperimentType(AgentType.RewardShaping, new int[]{4});
                break;
            case 12:
                m_logger.setActiveSeries("BasicQLearningAbstraction");
                this.runExperimentType(AgentType.BasicQLearningAbstraction, new int[]{0});
                break;
            case 13:
                m_logger.setActiveSeries("SimilaritiesOnRewardShaping");
                this.runExperimentType(AgentType.SimilaritiesOnRewardShaping, new int[]{0});
                break;
        }
    }

    private double runExperimentType(AgentType agentType, int[] objectives) {
        int experiments = 50;
        int trainEpisodes = 200000;
        int evaluationEpisodes = 10000;
        int evaluationInterval = 10000;
        double[] trainMeanResults = new double[trainEpisodes];
        double[] evaluationMeanResults = new double[evaluationEpisodes];
        long startTime = System.currentTimeMillis();
        int loggingInterval = 100;
        double tempSum = 0;

        Arrays.fill(trainMeanResults, 0.0);
        Arrays.fill(evaluationMeanResults, 0.0);

        for (int ex = 0; ex < experiments; ex++) {
            PredatorWorld p = new PredatorWorld(20, 2, agentType, objectives);
            // this.m_logger.increaseRound();
            this.m_logger.info("=== Experiment #" + ex + " ===");
            tempSum = 0;

            // train the agents
            double episodeResult = 0;
            for (int ep = 0; ep < trainEpisodes; ep++) {
                p.reset();
                episodeResult = p.episode(true);
                trainMeanResults[ep] = ((trainMeanResults[ep] * ex) + episodeResult) / (ex + 1.0);

                if ((ep + 1) % loggingInterval == 0) {
                    this.m_logger.info("ex" + ex + "ep" + ep + " mean: " + tempSum / loggingInterval);
                    tempSum = 0;
                }
                tempSum += episodeResult;

                if (((ep + 1) % evaluationInterval) == 0) {
                    // evaluate performance
                    this.m_logger.info("-- Evaluation --");
                    double evaluationEpisodeResult = 0;
                    for (int evalEp = 0; evalEp < evaluationEpisodes; evalEp++) {
                        p.reset();
                        evaluationEpisodeResult = p.episode(false);
                        evaluationMeanResults[evalEp] = evaluationEpisodeResult;
                    }
                    this.m_logger.info("evaluation results: " + Arrays.toString(evaluationMeanResults));
                    this.m_logger.info("mean result: " + this.mean(evaluationMeanResults));
                }
            }
        }

        double totalTime = (System.currentTimeMillis() - startTime) / 1000.0;
        this.m_logger.info("total time: " + totalTime + " secs");
        this.m_logger.addSeriesTime(totalTime);

        this.m_logger.info("Train episodes mean: " + Arrays.toString(trainMeanResults));
        double trainGlobalMean = this.mean(trainMeanResults);
        this.m_logger.info("Train experiments mean: " + trainGlobalMean);

        // this.m_logger.closeCurveDisplay();

        return trainGlobalMean;
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
