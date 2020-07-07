package suffix_tree;

import java.util.ArrayList;

public class Node
{
    private String fragment;
    private ArrayList<Node> nextNodes;
    private int position;
    public Node(String fragment, int position)
    {
        this.fragment = fragment;
        nextNodes = new ArrayList<>();
        this.position = position;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;
    }

    public String getFragment() {
        return fragment;
    }

    public ArrayList<Node> getNextNodes() {
        return nextNodes;
    }

    public void addNextNodes(Node nextNode) {
        this.nextNodes.add(nextNode);
    }

    public int getPosition() {
        return position;
    }
}