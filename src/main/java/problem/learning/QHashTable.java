package problem.learning;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author moshecohen
 */
public class QHashTable {
    private double[][][][][] values;
    private int m_size;

    public QHashTable(int size){

        this.values = new double[size*size][4][size*size][4][5];
        this.m_size = size;
    }

    public double get(StateAction key) {
        int[] indexSet = this.getIndexSet(key);
        return this.values[indexSet[0]][indexSet[1]][indexSet[2]][indexSet[3]][indexSet[4]];
    }

    public void put(StateAction key, double value){
        int[] indexSet = this.getIndexSet(key);
        this.values[indexSet[0]][indexSet[1]][indexSet[2]][indexSet[3]][indexSet[4]] = value;
    }

    public void reset(){
        for (int i = 0; i < values.length; i++) {
            for (int j = 0 ; j < values[i].length ; j++) {
                for (int k = 0 ; k < values[i][j].length ; k++) {
                    for (int l = 0 ; l < values[i][j][k].length ; l++) {
                        Arrays.fill(values[i][j][k][l], 0);
                    }
                }
            }
        }
    }

    public int[] getIndexSet(StateAction key) {
        int[] indexSet = new int[5];
        State state = key.getState();

        indexSet[0] = Math.abs(state.predDistX) * this.m_size + Math.abs(state.predDistY);
        indexSet[1] = this.getRelationIndex(state.predDistX, state.predDistY);
        indexSet[2] = Math.abs(state.preyDistX) * this.m_size + Math.abs(state.preyDistY);
        indexSet[3] = this.getRelationIndex(state.preyDistX, state.preyDistY);
        indexSet[4] = key.getAction();

        return indexSet;
    }

    private int getRelationIndex(int x, int y) {
        if (x < 0) {
            if (y < 0) {
                return 0;
            }
            return 1;
        }
        else {
            if (y < 0) {
                return 2;
            }
            return 3;
        }
    }
}
