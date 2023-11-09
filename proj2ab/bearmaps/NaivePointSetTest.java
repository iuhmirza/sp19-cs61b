package bearmaps;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class NaivePointSetTest {

    @Test
    public void SanityNearestTest() {
        Point origin = new Point(0, 0);
        ArrayList<Point> pts = new ArrayList<>();
        pts.add(origin);
        NaivePointSet ps = new NaivePointSet(pts);
        assertEquals(origin, ps.nearest(1, 1));
    }

    @Test
    public void SanityOriginTest() {
        Point origin = new Point(0, 0);
        ArrayList<Point> pts = new ArrayList<>();
        pts.add(origin);
        NaivePointSet ps = new NaivePointSet(pts);
        assertEquals(origin, ps.nearest(0, 0));
    }

    public void addSamePoint() {}

    @Test
    public void randomNearestTest() {
        ArrayList<Point> pts = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Point p = new Point(StdRandom.uniform(i, 100), StdRandom.uniform(i, 100));
            pts.add(p);
        }
        Point origin = new Point(0, 0);
        pts.add(origin);
        NaivePointSet ps = new NaivePointSet(pts);
        assertEquals(origin, ps.nearest(0, 0));
    }
}
