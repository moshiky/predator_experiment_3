package problem.learning;

/**
 * Created by Dev on 04/05/2017.
 */
public class AvlTreeBasedQTable {

    private AvlTree m_tree;

    public AvlTreeBasedQTable() {
        this.m_tree = new AvlTree();
    }

    private AvlNode getStateNode(double[] state) {
        AvlTree currentTree = this.m_tree;
        AvlNode currentNode = null;

        int featuresSoFar = 0;
        for (double featureValue : state) {
            currentNode = this.getNodeAtValue(currentTree, featureValue);

            featuresSoFar ++;
            if (null == currentNode.nextTree && featuresSoFar < state.length) {
                currentNode.nextTree = new AvlTree();
            }

            currentTree = currentNode.nextTree;
        }

        return currentNode;
    }

    public double getStateActionValue(double[] state, int action) {
        return this.getActionValue(this.getStateNode(state), action);
    }

    public void setStateActionValue(double[] state, int action, double value) {
        this.setActionValue(this.getStateNode(state), action, value);
    }

    private AvlNode getNodeAtValue(AvlTree tree, double value) {
        AvlNode valueNode = tree.findNode(value);

        if (null == valueNode) {
            tree.insert(value);
            valueNode = tree.getLastInsertedNode();
        }

        return valueNode;
    }

    private double getActionValue(AvlNode node, int action) {
        if (node.actionValues == null) {
            node.actionValues = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
        }
        return node.actionValues[action];
    }

    private void setActionValue(AvlNode node, int action, double value) {
        if (node.actionValues == null) {
            node.actionValues = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
        }
        node.actionValues[action] = value;
    }

}
