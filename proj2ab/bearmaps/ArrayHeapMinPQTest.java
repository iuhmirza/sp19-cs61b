package bearmaps;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.HashSet;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;


public class ArrayHeapMinPQTest {
    @Test
    public void sanitySizeTest() {
        ArrayHeapMinPQ<Integer> PQ = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 500; i++) {
            assertEquals(i, PQ.size());
            PQ.add(i, i);
            assertEquals(i+1, PQ.size());
        }

        for (int i = 500; i > 0; i--) {
            assertEquals(i, PQ.size());
            PQ.removeSmallest();
            assertEquals(i-1, PQ.size());
        }
    }
    @Test
    public void randomAddTest() {
        ArrayHeapMinPQ<Integer> PQ = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 500; i++) {
            assertEquals(i, PQ.size());
            PQ.add(i, StdRandom.uniform(0, 10));
            assertEquals(i+1, PQ.size());
        }

        for (int i = 500; i > 0; i--) {
            assertEquals(i, PQ.size());
            PQ.removeSmallest();
            assertEquals(i-1, PQ.size());
        }
    }

    @Test
    public void sanityRemoveTest() {
        ArrayHeapMinPQ<Character> PQ = new ArrayHeapMinPQ<>();
        assertEquals(0, PQ.size());
        PQ.add('a', 10);
        assertEquals('a', (Object) PQ.getSmallest());
        assertEquals(1, PQ.size());
        assertEquals('a', (Object) PQ.removeSmallest());
        assertEquals(0, PQ.size());
    }

    @Test
    public void randomRemoveTest() {
        ArrayHeapMinPQ<Integer> PQ = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 100; i++) {
            PQ.add(i, StdRandom.uniform(0, 10));
        }
        for (int i = 0; i < 25; i++) {
            assertEquals(PQ.getSmallest(), PQ.removeSmallest());
        }
    }

    @Test
    public void randomAddRemoveTest() {
        ArrayHeapMinPQ<Integer> PQ = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 100000; i++) {
            int randVal = StdRandom.uniform(0, 3);
            switch(randVal) {
                case 0:
                    PQ.add(i, StdRandom.uniform(0, 5));
                    break;
                case 1:
                    try {
                        PQ.removeSmallest();
                    } catch (NoSuchElementException e) {
                        PQ.add(i, StdRandom.uniform(0,5));
                    }
                    break;
                case 2:
                    PQ.add(i, 10);
                    break;
            }

        }
    }

    @Test
    public void addTest(){
        NaiveMinPQ<Integer> NHeap = new NaiveMinPQ<>();
        ArrayHeapMinPQ<Integer> AHeap = new ArrayHeapMinPQ<>();

        for(int x = 0; x < 100; x++){
            AHeap.add(x,x);
            NHeap.add(x,x);
        }
        assertEquals(AHeap.size(),NHeap.size());
        assertEquals(AHeap.getSmallest(),NHeap.getSmallest());
    }

    @Test
    public void containsTest(){
        NaiveMinPQ<Integer> NHeap = new NaiveMinPQ<>();
        ArrayHeapMinPQ<Integer> AHeap = new ArrayHeapMinPQ<>();

        for(int x = 2; x < 5; x++){
            AHeap.add(x,x);
            NHeap.add(x,x);
        }
        assertEquals(NHeap.getSmallest(),AHeap.getSmallest());
        assertEquals(NHeap.getSmallest(),AHeap.getSmallest());
        for (int x = 2; x < 5; x++){
            assertTrue(AHeap.contains(x));
            assertTrue(NHeap.contains(x));
        }
        AHeap.add(12,0.5);
        NHeap.add(12,0.5);
        assertEquals(NHeap.getSmallest(),AHeap.getSmallest());
        System.out.println(AHeap.getSmallest());
    }


    @Test
    public void getSmallestTest(){
        ArrayHeapMinPQ<Integer> AHeap = new ArrayHeapMinPQ<>();
        AHeap.add(5,1);
        AHeap.add(6,2);
        AHeap.add(7,2);
        AHeap.add(8,2);
        assertEquals(5,(int) AHeap.getSmallest());

    }
    @Test
    public void removeSmallestTest(){
        ArrayHeapMinPQ<Integer> AHeap = new ArrayHeapMinPQ<>();
        AHeap.add(5,1);
        AHeap.add(6,2);
        AHeap.add(7,3);
        AHeap.add(8,4);
        assertEquals((int) AHeap.removeSmallest(),5);
        assertEquals((int)AHeap.getSmallest(),6);
        assertEquals((int)AHeap.removeSmallest(),6);
        assertEquals((int)AHeap.getSmallest(),7);
        assertFalse(AHeap.contains(6));
        assertFalse(AHeap.contains(5));
    }
    @Test
    public void randomConsistencyTest(){
        ArrayHeapMinPQ<Integer> NHeap = new ArrayHeapMinPQ<>();
        ArrayHeapMinPQ<Integer> AHeap = new ArrayHeapMinPQ<>();
        int randomCount;
        for(int x = 0; x < 500; x ++){
            randomCount = StdRandom.uniform(0,4);
            int randKey = StdRandom.uniform(0,100);
            int randPriority = StdRandom.uniform(0,50);

            System.out.println("----");
            System.out.println(randomCount);
            System.out.println(randKey + " " + randPriority);
            System.out.println(NHeap.size() + " " + AHeap.size());
            if (NHeap.size() > 0 || AHeap.size() > 0) {
                System.out.println(NHeap.getSmallest() + " " + AHeap.getSmallest());
            }

            if (randomCount == 0 && !AHeap.contains(randKey)){
                NHeap.add(randKey, randPriority);
                AHeap.add(randKey, randPriority);
            } else if (randomCount == 1 && AHeap.contains(randKey) && AHeap.size() > 5){
                AHeap.changePriority(randKey, randPriority);
                NHeap.changePriority(randKey, randPriority);
            } else if (randomCount == 2 && AHeap.size() > 5){
                assertEquals(NHeap.getSmallest(), AHeap.getSmallest());
            } else if (randomCount == 3 && AHeap.size() > 5){
                assertEquals(NHeap.removeSmallest(), AHeap.removeSmallest());
            } else {

            }
        }
    }

    @Test
    public void randomNaiveComparisonTest(){
        NaiveMinPQ<Integer> NHeap = new NaiveMinPQ<>();
        ArrayHeapMinPQ<Integer> AHeap = new ArrayHeapMinPQ<>();
        HashSet<Integer> prioritySet = new HashSet<>();
        int randomCount;
        for(int x = 0; x < 500; x ++){
            randomCount = StdRandom.uniform(0,4);
            int randKey = StdRandom.uniform(0,100);
            int randPriority = StdRandom.uniform(0,10000);
            while (prioritySet.contains(randPriority)) {
                randPriority = StdRandom.uniform(0,10000);
            }
            prioritySet.add(randPriority);

            System.out.println("----");
            System.out.println(randomCount);
            System.out.println(randKey + " " + randPriority);
            System.out.println(NHeap.size() + " " + AHeap.size());
            if (NHeap.size() > 0 || AHeap.size() > 0) {
                System.out.println(NHeap.getSmallest() + " " + AHeap.getSmallest());
            }

            if (randomCount == 0 && !AHeap.contains(randKey)){
                NHeap.add(randKey, randPriority);
                AHeap.add(randKey, randPriority);
            } else if (randomCount == 1 && AHeap.contains(randKey) && AHeap.size() > 5){
                AHeap.changePriority(randKey, randPriority);
                NHeap.changePriority(randKey, randPriority);
            } else if (randomCount == 2 && AHeap.size() > 5){
                assertEquals(NHeap.getSmallest(), AHeap.getSmallest());
            } else if (randomCount == 3 && AHeap.size() > 5){
                assertEquals(NHeap.removeSmallest(), AHeap.removeSmallest());
            } else {

            }
        }
    }

    @Test
    public void sanityChangePriorityTest() {
        ArrayHeapMinPQ<Integer> PQ = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 100; i++) {
            if (i == 48) {
                PQ.add(255, 0);
                continue;
            }
            PQ.add(i, StdRandom.uniform(10, 20));
        }
        assertEquals(255, (int) PQ.removeSmallest());
    }

    @Test
    public void randomChangePriorityTest() {
        for (int i = 0; i < 100; i++) {
            ArrayHeapMinPQ<Integer> PQ = new ArrayHeapMinPQ<>();
            int randomKey = StdRandom.uniform(0, 100);
            for(int j = 0; j < 100; j++) {
                if (j == randomKey) {
                    PQ.add(j, 0);
                    continue;
                }
                PQ.add(j, StdRandom.uniform(10,20));
            }
            assertEquals(randomKey, (int) PQ.removeSmallest());
        }
    }

    @Test
    public void decreasingPriorityTest() {
        ArrayHeapMinPQ<Integer> PQ = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 100; i++) {
            PQ.add(i, -i);
        }
        for (int i = 99; i >= 0; i--) {
            assertEquals(i, (int) PQ.removeSmallest());
        }
    }
}
