package problem.learning;

import javafx.util.Pair;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Lev Levin on 09/01/2017.
 */
public class DoubleHashTable implements IQTable {
    private double[] values;
    private HashMap<Integer, Double> m_diffList;
    private boolean m_shouldLogChanges;

    public DoubleHashTable(int size){
        this(size, false);
    }

    public DoubleHashTable(int size, boolean shouldLogChanges) {
        this.m_shouldLogChanges = shouldLogChanges;
        if (shouldLogChanges) {
            this.m_diffList = new HashMap<>();
        }
        this.values = new double[size];
    }

    public double getKeyValue(double[] state, int action) {
        return this.get(this.makeStateActionKey(state, action));
    }

    public void setKeyValue(double[] state, int action, double value) {
        this.put(this.makeStateActionKey(state, action), value);
    }

    private StateAction makeStateActionKey(double[] state, int action) {
        return new StateAction(
                new State((int)state[0], (int)state[1], (int)state[2], (int)state[3]),
                action
        );
    }

    public double get(StateAction key) {
        int index = key.hashCode();
        return this.values[index];
    }

    public void put(StateAction key, double value){
        int index = key.hashCode();
        this.put(index, value);
    }

    public void put(int index, double value){
        if (this.m_shouldLogChanges) {
            if (this.values[index] != value) {
                this.m_diffList.put(index, value);
            }
        }

        this.values[index] = value;
    }

    public void reset(){
        for (int i = 0; i < values.length; i++){
            values[i] = 0;
        }
    }

    public DoubleHashTable clone() {
        DoubleHashTable clonedInstance = new DoubleHashTable(this.values.length);
        clonedInstance.setValues(this.values);
        return clonedInstance;
    }

    public void setValues(double[] pValues) {
        this.values = pValues.clone();
    }

    public HashMap<Integer, Double> getDiffList() {
        return this.m_diffList;
    }

    public void setShouldLogChanges(boolean shouldLogChanges) {
        this.m_shouldLogChanges = shouldLogChanges;

        if (shouldLogChanges) {
            this.m_diffList = new HashMap<>();
        }
        else {
            this.m_diffList = null;
        }
    }
}
