package problem.predator;

import java.util.ArrayList;

/**
 * Created by Dev on 27/04/2017.
 */
public class SimilarityManager
{

    /**
     *
     * @param state represents the state:
     *              [0] X: predator <-> other predator
     *              [1] Y: predator <-> other predator
     *              [2] X: predator <-> pray
     *              [3] Y: predator <-> pray
     * @param action represent the action:
     *               0: UP
     *               1: DOWN
     *               2: LEFT
     *               3: RIGHT
     *               4: STAY
     * @return
     */
    public static ArrayList<SimilarityRecord> getSimilarityRecords(double[] state, int action)
    {
        ArrayList<SimilarityRecord> similarityRecords = new ArrayList<>();

        double distp= Math.sqrt( Math.pow(state[2], 2) + Math.pow(state[3], 2));
        double distf= Math.sqrt( Math.pow(state[0], 2) + Math.pow(state[1], 2));
        double [] temp= new double[4];
        SimilarityRecord sm= new SimilarityRecord(state, action, 1);

        for(int x=0; x<20; x++) {

            for (int y = 0; y < 20; y++) {

                for (int i = 0; i < 20; i++) {
                    for (int j = 0; j < 20; j++) {
                        double distOp = Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2));
                        double distOf = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

                        if (distp == distOp || distp == -distOp) {
                            temp[2] = state[2];
                            temp[3] = state[3];
                        }
                    }
                }
            }
        }
        /*for(int i=0; i<20; i++)
        {
            for(int j=0; j<20; j++)
            {
                //if(j+ )
                if(((int)state[2] == i || (int)state[2] == -i) && ((int)state[3] == j || (int)state[3] == -j))
                {
                    //SimilarityRecord sm= new SimilarityRecord(state, action, 1);
                    similarityRecords.add(new SimilarityRecord(state, action, 1));


                }
            }
        }

        for(int i=0; i<similarityRecords.size(); i++)
        {
            if(!((int)state[0] == similarityRecords.get(i).getState()[0] || (int)state[0] == -similarityRecords.get(i).getState()[0]))
            {
                similarityRecords.remove(i);
            }
        }

        for(int i=0; i<similarityRecords.size(); i++)
        {
            if(!((int)state[1] == similarityRecords.get(i).getState()[1] || (int)state[1] == -similarityRecords.get(i).getState()[1]))
            {
                similarityRecords.remove(i);
            }
        }*/
        return similarityRecords;
    }
}
