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
        logger.initiateLearningCurveDisplay();

        // create ExperimentManager instance
        ExperimentManager experimentManager = new ExperimentManager(logger);

        // run selected agent types
        int[] agentTypeToRun = new int[]{8};
        for (int agentType : agentTypeToRun) {
            experimentManager.runAgent(agentType);
        }
    }
}
