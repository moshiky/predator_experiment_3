/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package problem.predator;
import java.util.Arrays;

import problem.learning.AgentType;
import problem.utils.Logger;

/**
 * @author timbrys
 * @author moshecohen
 */
public class Experiments {

    private static Logger m_logger;

    public static void main(String args[]) {

        long currentTime = System.currentTimeMillis();
        m_logger = new Logger("logs/info__" + currentTime + ".log");
        m_logger.initiateLearningCurveDisplay();

        //run experiments with one of the possible variants
        //int[] agentTypeToRun = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
        int[] agentTypeToRun = new int[]{9, 10};

        for (int agentType : agentTypeToRun) {
            switch (agentType) {
                case 0:
                    m_logger.setActiveSeries("NoShaping_WithTileCoding");
                    typeExperiment(AgentType.NoShaping, new int[]{0});
                    break;
                case 1:
                    m_logger.setActiveSeries("SingleShaping{1}");
                    typeExperiment(AgentType.SingleShaping, new int[]{1});
                    break;
                case 2:
                    m_logger.setActiveSeries("SingleShaping{2}");
                    typeExperiment(AgentType.SingleShaping, new int[]{2});
                    break;
                case 3:
                    m_logger.setActiveSeries("SingleShaping{3}");
                    typeExperiment(AgentType.SingleShaping, new int[]{3});
                    break;
                case 4:
                    m_logger.setActiveSeries("Linear");
                    typeExperiment(AgentType.Linear, new int[]{1, 2, 3});
                    break;
                case 5:
                    m_logger.setActiveSeries("BestLinear");
                    typeExperiment(AgentType.BestLinear, new int[]{1, 2, 3});
                    break;
                case 6:
                    m_logger.setActiveSeries("ROS");
                    typeExperiment(AgentType.ROS, new int[]{1, 2, 3});
                    break;
                case 7:
                    m_logger.setActiveSeries("AOS");
                    typeExperiment(AgentType.AOS, new int[]{1, 2, 3});
                    break;
                case 8:
                    m_logger.setActiveSeries("Abstraction_WithTileCoding");
                    typeExperiment(AgentType.Abstraction, new int[]{0});
                    break;
                case 9:
                    m_logger.setActiveSeries("BasicQLearning");
                    typeExperiment(AgentType.BasicQLearning, new int[]{0});
                    break;
                case 10:
                    m_logger.setActiveSeries("Similarities");
                    typeExperiment(AgentType.Similarities, new int[]{0});
                    break;
            }
        }
    }

    public static double typeExperiment(AgentType type, int[] objectives) {
        int experiments = 1;
        int episodes = 5000;
        double[][] results = new double[experiments][episodes];
        for (int ex = 0; ex < experiments; ex++) {
            PredatorWorld p = new PredatorWorld(20, 2, type, objectives);
            m_logger.info("=== Experiment #" + ex + " ===");
            for (int ep = 0; ep < episodes; ep++) {
                p.reset();
                results[ex][ep] = p.episode();
                m_logger.info("ex" + ex + "ep" + ep + ": " + results[ex][ep]);
                m_logger.addEpisodeResult(results[ex][ep]);
            }
        }
        double[] means = means(results);
        m_logger.info(">> Episodes mean: " + Arrays.toString(means));
        m_logger.info(">> Experiments mean: " + mean(means));
        return mean(means);
    }

    //averages the results of a number of runs
    public static double[] means(double[][] stats) {
        double[] means = new double[stats[0].length];
        for (int j = 0; j < stats[0].length; j++) {
            for (int i = 0; i < stats.length; i++) {
                means[j] += stats[i][j];
            }
            means[j] = 1.0 * means[j] / (stats.length);
        }
        return means;
    }

    public static double mean(double[] stats) {
        double means = 0.0;
        for (int i = 0; i < stats.length; i++) {
            means += stats[i];
        }
        means = 1.0 * means / (stats.length);
        return means;
    }

}
