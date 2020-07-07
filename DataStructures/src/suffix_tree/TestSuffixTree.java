package suffix_tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestSuffixTree {
    private SuffixTree tree;
    private String text = "Some terrible text for testing suffix tree";

    @Test
    public void testSuffixTree(){
        tree = new SuffixTree(text);

        ArrayList<Integer> positions = new ArrayList<>();
        positions.add(5);
        positions.add(14);
        positions.add(23);

        Assert.assertEquals(positions, tree.search("te"));
    }
}
