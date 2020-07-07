package single_linked_list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestSingleLinkedList {
    SingleLinkedList list;

    @Before
    public void setUp() {
        list = new SingleLinkedList();
        list.push(new ListItem("Item 1"));
        list.push(new ListItem("Item 2"));
        list.push(new ListItem("Item 3"));
        list.push(new ListItem("Item 4"));
        list.push(new ListItem("Item 5"));
    }

    @Test
    public void testPop() {
        ListItem item = list.pop();

        Assert.assertEquals(item.getData(), "Item 5");
    }

    @Test
    public void testRemoveTop() {
        list.removeTop();
        ListItem item = list.pop();

        Assert.assertEquals(item.getData(), "Item 4");
    }

    @Test
    public void testRemoveLast() {
        list.removeLast();
        ListItem item = list.pop();
        ListItem newLast = null;
        while (item != null) {
            newLast = item;
            item = list.pop();
        }

        Assert.assertEquals(newLast.getData(), "Item 2");
    }
}
