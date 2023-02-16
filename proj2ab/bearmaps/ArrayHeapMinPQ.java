package bearmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    class PriorityNode {
        T item;
        double priority;
        public PriorityNode(T i, double p) {
            item = i;
            priority = p;
        }
    }
    private ArrayList<PriorityNode> queue;
    private HashMap<T, Integer> items;
    private int size;
    public ArrayHeapMinPQ() {
        queue = new ArrayList<>();
        items = new HashMap<>();
        size = 0;
    }

    private int parent(int k) {
        return (k-1) / 2;
    }

    private int leftChild(int k) {
        return 2*k + 1;
    }

    private int rightChild(int k) {
        return 2*k + 2;
    }

    private void swap (int k1, int k2) {
        PriorityNode temp1 = queue.get(k1);
        PriorityNode temp2 = queue.get(k2);
        queue.set(k1, temp2);
        queue.set(k2, temp1);
        items.put(temp1.item, k2);
        items.put(temp2.item, k1);

    }

    private void swimUp(int k) {
        if (queue.get(parent(k)).priority > queue.get(k).priority) {
           swap(k, parent(k));
           swimUp(parent(k));
        }
    }

    private void swimDown(int k) {
        try {
            PriorityNode leftChild = queue.get(leftChild(k));
            PriorityNode rightChild = queue.get(rightChild(k));
            int whichChild;
            if (leftChild.priority > rightChild.priority) {
                whichChild = items.get(rightChild.item);
            } else {
                whichChild = items.get(leftChild.item);
            }
            if (queue.get(k).priority > queue.get(whichChild).priority) {
                swap(k, whichChild);
                swimDown(whichChild);
            }
        } catch (IndexOutOfBoundsException e) {

        }
    }

    private void swimDown2(int k) {
        int left = leftChild(k);
        int right = rightChild(k);
        int childIndex;
        if (left > size) {
            return;
        } else if (right > size) {
            childIndex = left;
        }
        PriorityNode leftChild = queue.get(leftChild(k));
        PriorityNode rightChild = queue.get(rightChild(k));
        PriorityNode whichChild;
        if (leftChild.priority > rightChild.priority) {
            whichChild = rightChild;
        } else {
            whichChild = leftChild;
        }
        childIndex = items.get(whichChild.item);
        if (queue.get(k).priority > queue.get(childIndex).priority) {
            swap(k, childIndex);
            swimDown(childIndex);
        }
    }

    private void swimDown3(int k) {
        int whichChild;
        if (rightChild(k) >= size) {
            if (leftChild(k) >= size) {
                return;
            }
            whichChild = leftChild(k);
            if (queue.get(k).priority > queue.get(whichChild).priority) {
                swap(k, whichChild);
                swimDown(whichChild);
            }
        } else {
            PriorityNode leftChild = queue.get(leftChild(k));
            PriorityNode rightChild = queue.get(rightChild(k));
            PriorityNode whichChild1;
            int childIndex;
            if (leftChild.priority > rightChild.priority) {
                whichChild1 = rightChild;
            } else {
                whichChild1 = leftChild;
            }
            childIndex = items.get(whichChild1.item);
            if (queue.get(k).priority > queue.get(childIndex).priority) {
                swap(k, childIndex);
                swimDown(childIndex);
            }
        }
    }

    public void add(T item, double priority) {
        if (items.containsKey(item)) {
            throw new IllegalArgumentException();
        }
        queue.add(new PriorityNode(item, priority));
        items.put(item, size);
        if (size > 0) {
            swimUp(size);
        } //position item is added into is size
        size++;
    }

    public boolean contains(T item) {
        return items.containsKey(item);
    }

    public T getSmallest() {
        if (items.isEmpty()) {
            throw new NoSuchElementException();
        }
        return queue.get(0).item;
    }

    public T removeSmallest() {
        if (items.isEmpty()) {
            throw new NoSuchElementException();
        }
        PriorityNode root = queue.get(0);
        PriorityNode last = queue.get(size-1);

        queue.set(0, last);
        items.put(last.item, 0);
        queue.remove(size-1); //removes last
        items.remove(root.item);

        size--;
        if (size > 0) {
            swimDown(0);
        }
        return root.item;
    }

    public int size() {
        return size;
    }

    public void changePriority(T item, double priority) { // this does not work
        //if the new priority is greater than the old priority swim up
        //if the new priority is less than the old priority swim down
        int index = items.get(item);
        PriorityNode node = queue.get(index);
        if (node.priority > priority) {
            node.priority = priority;
            swimUp(index);
        } else if (node.priority < priority) {
            node.priority = priority;
            swimDown(index);
        }
    }
}
