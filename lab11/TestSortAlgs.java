import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.Queue;

import edu.princeton.cs.algs4.Quick;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<Integer> q = new Queue<>();
        q.enqueue(4);
        q.enqueue(3);
        q.enqueue(67);
        q.enqueue(2);
        q.enqueue(45);
        q.enqueue(43);
        q.enqueue(43);
        System.out.println(QuickSort.quickSort(q));
        assertTrue(isSorted(QuickSort.quickSort(q)));
    }

    @Test
    public void testMergeSort() {
        Queue<Integer> q = new Queue<>();
        q.enqueue(4);
        q.enqueue(3);
        q.enqueue(67);
        q.enqueue(2);
        q.enqueue(45);
        q.enqueue(43);
        q.enqueue(43);
        System.out.println(q);
        assertTrue(isSorted(MergeSort.mergeSort(q)));
        System.out.println(MergeSort.mergeSort(q));
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
