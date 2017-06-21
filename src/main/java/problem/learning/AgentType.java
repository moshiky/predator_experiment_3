/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package problem.learning;

/**
 * @author timbrys
 * @author moshecohen
 */

/*
 * Random: Random agent
 * NoShaping: agent learning on base reward
 * SingleShaping: agent learning on base reward + single shaping
 * Linear: agent learning on the base reward + a scalarization of a number of shapings (uniform weights)
 * BestLinear: agent learning on the base reward + a scalarization of a number of shapings (weights normalized using the shapings' domain)
 * ROS: agent learning the base reward + a number of shapings in parallel, using random objective selection to combine them
 * AOS: agent learning the base reward + a number of shapings in parallel, using adaptive objective selection to combine them
 * Abstraction: agent learning the base reward, but the state representation is based on user-defined characteristics.
 * BasicQLearning: agent learning the base reward, Q values stored in a basic array without TileCoding.
 * Similarities: based on BasicQLearning. agent learning the base reward, but Q value updates are propagate to 'similar' states according to user specification.
 * RewardShaping: same as SingleShaping, just the shaping function is user defined
 * BasicQLearningAbstraction: same as BasicQLearning, just uses AvlTreeBasedQTable fot its q table
 * SimilaritiesOnRewardShaping: both Similarities and RewardShaping are activated at the same time
 * */
public enum AgentType {
    Random, NoShaping, SingleShaping, Linear, BestLinear, ROS, AOS,
    Abstraction, BasicQLearning, Similarities, RewardShaping, BasicQLearningAbstraction, SimilaritiesOnRewardShaping
}
