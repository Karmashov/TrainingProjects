package binary_tree;

import org.junit.Assert;
import org.junit.Test;

public class TestBinaryTree {
    BinaryTree tree = new BinaryTree();

    @Test
    public void testAddNode() {
        tree.addNode("Item 1");
        Assert.assertEquals(tree.getRoot().getData(), "Item 1");
    }

    @Test
    public void testSearchNodes() {
        tree.addNode("Item 5");
        tree.addNode("Item 3");
        tree.addNode("Item 7");
        tree.addNode("Item 2");
        tree.addNode("Item 4");
        tree.addNode("Item 8");
        tree.addNode("Item 1");
        tree.addNode("Item 3");
        tree.addNode("Item 4");

        Assert.assertEquals(tree.searchNodes("Item 4").size(), 2);
    }
}
