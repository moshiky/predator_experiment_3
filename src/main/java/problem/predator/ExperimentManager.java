package problem.predator;

import problem.learning.AgentType;
import problem.utils.Logger;

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
        }
    }

    private double runExperimentType(AgentType agentType, int[] objectives) {
        int experiments = 1;
        int episodes = 10000;
        double[][] results = new double[experiments][episodes];
        long start_time = System.currentTimeMillis();
        int loggingInterval = 100;
        double tempSum = 0;

        for (int ex = 0; ex < experiments; ex++) {
            PredatorWorld p = new PredatorWorld(20, 2, agentType, objectives);
            this.m_logger.increaseRound();
            this.m_logger.info("=== Experiment #" + ex + " ===");
            tempSum = 0;

            for (int ep = 0; ep < episodes; ep++) {
                p.reset();
                results[ex][ep] = p.episode();
                this.m_logger.addEpisodeResult(results[ex][ep]);

                if ((ep+1) % loggingInterval == 0) {
                    this.m_logger.info("ex" + ex + "ep" + (ep+1) + " mean: " + tempSum/loggingInterval);
                    tempSum = 0;
                }
                tempSum += results[ex][ep];
            }
        }
        long totalTime = (System.currentTimeMillis() - start_time) / 1000;
        this.m_logger.info("total time: " + totalTime + " secs");
        this.m_logger.addSeriesTime(totalTime);

        double[] means = this.means(results);
        this.m_logger.info(">> Episodes mean: " + Arrays.toString(means));
        this.m_logger.info(">> Experiments mean: " + this.mean(means));
        return this.mean(means);
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
