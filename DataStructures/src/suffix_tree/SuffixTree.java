package suffix_tree;

import java.util.ArrayList;
import java.util.List;

public class SuffixTree
{
    private String text;
    private Node root;
    private static final String endSymbol = "$";

    public SuffixTree(String text)
    {
        this.text = text;
        root = new Node("", -1);
        for (int i = 0; i < text.length(); i++) {
            build(text.substring(i) + endSymbol, i);
        }
    }

    private void build(String suffix, int position)
    {
        List<Node> nodes = getNodesInPath(suffix, root, true);
        if (nodes.size() == 0) {
            root.addNextNodes(new Node(suffix, position));
        } else {
            Node lastNode = nodes.remove(nodes.size() - 1);
            String newText = suffix;
            if (nodes.size() > 0) {
                String existSuffix = nodes.stream().map(Node::getFragment).reduce("", String::concat);
                newText = newText.substring(existSuffix.length());
            }
            updateNode(lastNode, newText, position);
        }
    }

    public List<Integer> search(String query)
    {
        ArrayList<Integer> positions = new ArrayList<>();
        List<Node> nodes = getNodesInPath(query, root, false);
        if (nodes.size() > 0) {
            Node lastNode = nodes.get(nodes.size() - 1);
            if (lastNode != null) {
                positions.addAll(getPositions(lastNode));
            }
        }
        return positions;
    }

    private List<Integer> getPositions(Node node) {
        List<Integer> positions = new ArrayList<>();
        if (node.getFragment().endsWith(endSymbol)) {
            positions.add(node.getPosition());
        }
        for (int i = 0; i < node.getNextNodes().size(); i++) {
            positions.addAll(getPositions(node.getNextNodes().get(i)));
        }
        return positions;
    }

    private List<Node> getNodesInPath(String pattern, Node startNode, boolean allowPartition){
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < startNode.getNextNodes().size(); i++) {
            Node currentNode = startNode.getNextNodes().get(i);
            String currentText = currentNode.getFragment();
            if (currentText.charAt(0) == pattern.charAt(0)){
                if (allowPartition && pattern.length() <= currentText.length()) {
                    nodes.add(currentNode);
                    return nodes;
                }

                int minLength = Math.min(currentText.length(), pattern.length());
                for (int j = 1; j < minLength; j++) {
                    if (pattern.charAt(j) != currentText.charAt(j)) {
                        if (allowPartition) {
                            nodes.add(currentNode);
                        }
                        return nodes;
                    }
                }

                nodes.add(currentNode);
                if (pattern.length() > minLength) {
                    List<Node> nodes2 = getNodesInPath(pattern.substring(minLength), currentNode, allowPartition);
                    if (nodes2.size() > 0) {
                        nodes.addAll(nodes2);
                    } else if (!allowPartition) {
                        nodes.add(null);
                    }
                }
                return nodes;
            }
        }
        return nodes;
    }

    private void updateNode(Node node, String suffix, int position) {
        String currentText = node.getFragment();
        String prefix = getPrefix(currentText, suffix);

        if (!prefix.equals(currentText)) {
            String parentText = currentText.substring(0, prefix.length());
            String childText = currentText.substring(prefix.length());
            splitNode(node, parentText, childText);
        }
        String remainingText = suffix.substring(prefix.length());
        node.addNextNodes(new Node(remainingText, position));
    }

    private String getPrefix(String str1, String str2) {
        int minLength = Math.min(str1.length(), str2.length());
        for (int i = 0; i < minLength; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return str1.substring(0, i);
            }
        }
        return str1.substring(0, minLength);
    }

    private void splitNode(Node node, String parentText, String childText) {
        Node childNode = new Node(childText, node.getPosition());

        while (node.getNextNodes().size() > 0) {
            childNode.getNextNodes().add(node.getNextNodes().remove(0));
        }
        node.addNextNodes(childNode);
        node.setFragment(parentText);
    }

}