package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet{
    private List<Point> points;
    public NaivePointSet(List<Point> p) {
        points = p;
    }

    public Point nearest(double x, double y) {
        double smallest = Double.POSITIVE_INFINITY;
        int index = -1;
        Point givenPoint = new Point(x, y);
        for (int i = 0; i < points.size(); i++) {
            double distance = Point.distance(points.get(i), givenPoint);
            if (smallest > distance) {
                smallest = distance;
                index = i;
            }
        }
        return points.get(index);
    }
}
