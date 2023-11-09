import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestUnionFind {

    @Test
    public void sanityInitialUFTest() {
        UnionFind UF = new UnionFind(8);
        for (int i = 0; i < 8; i++) {
            assertEquals(-1, UF.sizeOf(i));
            assertEquals(-1, UF.parent(i));
            assertEquals(i, UF.root(i));
        }
    }

    @Test
    public void fixedUnionOfDifferentSet() {
        UnionFind U = new UnionFind(8);
        for (int i = 0; i < 3; i++) {
            U.union(0, i);
        }
        for (int i = 5; i < 8; i++) {
            U.union(5, i);
        }
        U.union(2, 6);
        assertTrue(U.connected(2, 6));
    }

    @Test
    public void fixedUnionOfSameSet() {
        UnionFind U = new UnionFind(8);
        for (int i = 0; i < 3; i++) {
            U.union(0, i);
        }
        for (int i = 5; i < 8; i++) {
            U.union(5, i);
        }
        U.union(2, 6);
        assertTrue(U.connected(2, 6));
        U.union(2, 6);
        assertTrue(U.connected(2, 6));
    }

    @Test
    public void randomUnionTest() {
        UnionFind U = new UnionFind(8);
        for (int i = 0; i < 8; i++) {
            int randInt = (int) (Math.random() * 8);
            U.union(i, randInt);
            assertTrue(U.connected(i, randInt));
        }
    }
    @Test
    public void hundredTimesRandomUnionTest() {
        for (int i = 0; i < 100; i++) {
            UnionFind U = new UnionFind(8);
            for (int j = 0; j < 8; j++) {
                int randInt = (int) (Math.random() * 8);
                U.union(j, randInt);
                assertTrue(U.connected(j, randInt));
            }
        }
    }

    @Test
    public void oneMillionTimesRandomUnionTest() {
        for (int i = 0; i < 1000000; i++) {
            UnionFind U = new UnionFind(8);
            for (int j = 0; j < 8; j++) {
                int randInt = (int) (Math.random() * 8);
                U.union(j, randInt);
                assertTrue(U.connected(j, randInt));
            }
        }
    }

}
