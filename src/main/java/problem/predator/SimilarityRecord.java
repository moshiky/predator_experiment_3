package problem.predator;

/**
 * Created by Dev on 27/04/2017.
 */
public class SimilarityRecord {
    private double[] m_state;
    private int m_action;
    private double m_similarityFactor;

    public SimilarityRecord(double[] state, int action, double similarityFactor) throws Exception {
        this.m_state = state;
        this.m_action = action;

        if (similarityFactor > 1 || similarityFactor < 0) {
            throw new Exception("Similarity factor must be between 0 and 1. received factor: " + similarityFactor);
        }
        this.m_similarityFactor = similarityFactor;
    }

    public double[] getState() { return this.m_state; }

    public int getAction() { return this.m_action; }

    public double getSimilarityFactor() { return this.m_similarityFactor; }
}
