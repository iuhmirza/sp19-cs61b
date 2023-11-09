package bearmaps;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    @Test
    public void DemoTest() {
        ArrayList<Point> pts = new ArrayList<>();
        pts.add(new Point(2, 3));
        pts.add(new Point(4, 2));
        //pts.add(new Point(4, 2)); duplicate is part of demo, ignore for now
        pts.add(new Point(4, 5));
        pts.add(new Point(3, 3));
        pts.add(new Point(1, 5));
        pts.add(new Point(4, 4));
        KDTree twoDimPlane = new KDTree(pts);

        Point a = twoDimPlane.nearest(0, 7);
        assertEquals(1, (int) a.getX());
        assertEquals(5, (int) a.getY());

        Point b = twoDimPlane.nearest(6, -1);
        assertEquals(4, (int) b.getX());
        assertEquals(2, (int) b.getY());

        pts.clear();
    }
}
