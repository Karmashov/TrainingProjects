package binary_tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree
{
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void addNode(String data)
    {
        Node newNode = new Node(data);
        if (root != null) {
            Node node = root;
            Node preEnd = null;
            while (node != null) {
                preEnd = node;
                if (data.compareTo(node.getData()) < 0) {
                    node = node.getLeft();
                } else {
                    node = node.getRight();
                }
            }
            newNode.setParent(preEnd);
            if (data.compareTo(preEnd.getData()) < 0) {
                preEnd.setLeft(newNode);
            } else {
                preEnd.setRight(newNode);
            }
        } else {
            root = newNode;
        }
    }

    public List<Node> searchNodes(String data)
    {
        List<Node> nodesList = new ArrayList<>();
        if (root != null) {
            Node node = root;
            while (node != null) {
                if (data.compareTo(node.getData()) < 0) {
                    node = node.getLeft();
                } else if (data.compareTo(node.getData()) > 0) {
                    node = node.getRight();
                } else if (data.compareTo(node.getData()) == 0) {
                    nodesList.add(node);
                    node = node.getRight();
                }
            }
        }
        return nodesList;
    }
}