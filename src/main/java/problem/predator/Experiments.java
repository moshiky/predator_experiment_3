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

    public static void main(String args[]) {

        long currentTime = System.currentTimeMillis();
        Logger logger = new Logger("logs/info__" + currentTime + ".log");
        // logger.initiateLearningCurveDisplay(currentTime);

        // create ExperimentManager instance
        ExperimentManager experimentManager = new ExperimentManager(logger);

        // run selected agent types
        /**
         * with Tile Coding: [
         *      0 - No Shaping
         *      1 - Single Shaping {1}
         *      2 - Single Shaping {2}
         *      3 - Single Shaping {3}
         *      4 - Linear
         *      5 - Best Linear
         *      6 - ROS
         *      7 - AOS
         * ]
         * Basic Q Learning: [
         *      8 - Abstraction
         *      9 - Basic Q Learning [no speedup method]
         *      10 - Similarities
         *      11 - RewardShaping
         *      12 - BasicQLearningAbstraction
         * ]
         */
        int[] agentTypeToRun = new int[]{8};
        for (int agentType : agentTypeToRun) {
            experimentManager.runAgent(agentType);
        }
    }
}
