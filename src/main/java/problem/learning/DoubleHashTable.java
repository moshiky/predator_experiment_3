package problem.learning;

/**
 * Created by Lev Levin on 09/01/2017.
 */
public class DoubleHashTable implements IQTable {
    private double[] values;

    public DoubleHashTable(int size){
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
        this.values[index] = value;
    }

    public void reset(){
        for (int i = 0; i < values.length; i++){
            values[i] = 0;
        }
    }
}
