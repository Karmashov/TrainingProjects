package rabin_karp;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestRabinKarp {
    private RabinKarpExtended rabinKarpAlgorithm;
    private String text = "Some terrible text for testing suffix tree";

    @Test
    public void testRabinKarp(){
        rabinKarpAlgorithm = new RabinKarpExtended(text);

        ArrayList<Integer> positions = new ArrayList<>();
        positions.add(5);
        positions.add(14);
        positions.add(23);

        Assert.assertEquals(positions, rabinKarpAlgorithm.search("te"));
    }
}
