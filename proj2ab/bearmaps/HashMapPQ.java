package bearmaps;

import java.util.HashMap;

public class MyPQ<T> implements ExtrinsicMinPQ<T>{

    private HashMap<T, Double> queue;
    private T smallest;
    private int size;
    public MyPQ() {
        queue = new HashMap<>();
        size = 0;
    }

    public boolean contains(T item) {
        return queue.containsKey(item);
    }

    public void add (T item, double priority) {
        if (queue.containsKey(item)) {
            throw new IllegalArgumentException();
        }
        if (queue.get(smallest) > priority) { //bug: queue does not contain smallest when first initialized
            smallest = item;
        }
        queue.put(item, priority);
        size++;
    }

    public T getSmallest() {
        return smallest;
    }

    public T removeSmallest() {
        queue.remove(smallest);
        return smallest; //bug: smallest is now not in the queue
    }

    public int size() {
        return size;
    }

    public void changePriority(T item, double priority) {
        queue.put(item, priority);
    }
}
