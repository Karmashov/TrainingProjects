package double_linked_list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDoubleLinkedList {
    DoubleLinkedList list;

    @Before
    public void setUp() {
        list = new DoubleLinkedList();
        list.addToHead(new ListItem("Item 1"));
        list.addToHead(new ListItem("Item 2"));
        list.addToHead(new ListItem("Item 3"));
        list.addToHead(new ListItem("Item 4"));
        list.addToHead(new ListItem("Item 5"));
    }

    @Test
    public void testAddToHead() {
        String s = "Item 6";
        list.addToHead(new ListItem(s));

//        assert list.getHeadElement().getData().equals(s);
        Assert.assertEquals(list.getHeadElement().getData(), s);
    }

    @Test
    public void testAddToTail() {
        String s = "Item 0";
        list.addToTail(new ListItem(s));

        Assert.assertEquals(list.getTailElement().getData(), s);
    }

    @Test
    public void testPopHeadElement() {
        ListItem item = list.popHeadElement();

        Assert.assertEquals(item.getData(), "Item 5");
        Assert.assertEquals(list.getHeadElement().getData(), "Item 4");

    }

    @Test
    public void testPopTailElement() {
        ListItem item = list.popTailElement();

        Assert.assertEquals(item.getData(), "Item 1");
        Assert.assertEquals(list.getTailElement().getData(), "Item 2");
    }

    @Test
    public void testRemoveHeadElement() {
        list.removeHeadElement();

        Assert.assertEquals(list.getHeadElement().getData(), "Item 4");
    }

    @Test
    public void testRemoveTailElement() {
        list.removeTailElement();

        Assert.assertEquals(list.getTailElement().getData(), "Item 2");
    }
}
