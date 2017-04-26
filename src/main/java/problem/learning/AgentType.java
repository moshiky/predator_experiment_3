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
 * */
public enum AgentType {
    Random, NoShaping, SingleShaping, Linear, BestLinear, ROS, AOS,
    Abstraction
}
