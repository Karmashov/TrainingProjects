package double_linked_list;

public class DoubleLinkedList
{
    private ListItem head;
    private ListItem tail;

    public ListItem getHeadElement()
    {
        return head;
    }

    public ListItem getTailElement()
    {
        return tail;
    }

    public ListItem popHeadElement()
    {
        ListItem item = head;
        if(head != null)
        {
            head = head.getNext();
            head.setPrev(null);
            item.setNext(null);
        }
        return item;
    }

    public ListItem popTailElement()
    {
        ListItem item = tail;
        if(tail != null)
        {
            tail = item.getPrev();
            tail.setNext(null);
        }
        return item;
    }

    public void removeHeadElement()
    {
        if(head != null) {
            head = head.getNext();
            head.setPrev(null);
        }
    }

    public void removeTailElement()
    {
        if(tail != null) {
            ListItem preLast = tail.getPrev();
            tail.setPrev(null);
            tail = preLast;
            tail.setNext(null);
        }
    }

    public void addToHead(ListItem item)
    {
        if(head != null) {
            item.setNext(head);
            head.setPrev(item);
        } else {
            tail = item;
        }
        head = item;
    }

    public void addToTail(ListItem item)
    {
        if(tail != null) {
            tail.setNext(item);
            item.setPrev(tail);
        } else {
            head = item;
        }
        tail = item;
    }
}