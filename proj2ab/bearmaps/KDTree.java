package bearmaps;

import java.util.List;

public class KDTree {
    SubspaceNode root;
     private class SubspaceNode {
        Point point;
        SubspaceNode left;
        SubspaceNode right;
        public SubspaceNode(Point p, SubspaceNode l, SubspaceNode r) {
            point = p;
            left = l;
            right = r;
        }

        public SubspaceNode() {
            point = null;
            left = null;
            right = null;
        }

    }
    private SubspaceNode makeSentinel() {
         return new SubspaceNode();
    }

    public KDTree(List<Point> p) {
        root = new SubspaceNode(p.get(0), makeSentinel(), makeSentinel());
        p.remove(0);
        insertPoints(p);
    }

    private void insertPoints(List<Point> points) {
        for (Point p : points) {
            insert(p);
        }
    }
    private void insert(Point p) {
        SubspaceNode currentNode = root;
        int toggle = 0;
        while (currentNode.point != null) {
            if (toggle == 0) {
                if (currentNode.point.getX() > p.getX()) {
                    currentNode = currentNode.left;
                } else {
                    currentNode = currentNode.right;
                }
            } else {
                if (currentNode.point.getY() > p.getY()) {
                    currentNode = currentNode.left;
                } else {
                    currentNode = currentNode.right;
                }
            }
            toggle = 1 - toggle;
        }
        currentNode.point = p;
        currentNode.left = makeSentinel();
        currentNode.right = makeSentinel();
    }


    private static SubspaceNode nearestHelper(SubspaceNode N, Point goal, SubspaceNode best, int toggle) {
         if (N.point == null) {
             return best;
         }
         if (Point.distance(N.point, goal) < Point.distance(best.point, goal)) {
             best = N;
         }
         SubspaceNode goodSide;
         SubspaceNode badSide;

        if (toggle == 0) {
            if (N.point.getX() > goal.getX()) {
                goodSide = N.left;
                badSide = N.right;
            } else {
                goodSide = N.right;
                badSide = N.left;
            }
        } else {
            if (N.point.getY() > goal.getY()) {
                goodSide = N.left;
                badSide = N.right;
            } else {
                goodSide = N.right;
                badSide = N.left;
            }
        }

        toggle = 1 - toggle;

         best = nearestHelper(goodSide, goal, best, toggle);
         if (bestPossibleDistanceGreen(N, goal, 1 - toggle) < Point.distance(best.point, goal)) { //if bad side can have something useful
             best = nearestHelper(badSide, goal, best, toggle);
         }
         return best;
    }

    private static double bestPossibleDistanceGreen(SubspaceNode N, Point goal, int toggle) {
        if (toggle == 0) {
            return Point.distance(goal, new Point(N.point.getX(), goal.getY()));
        } else {
            return Point.distance(goal, new Point(N.point.getY(), goal.getX()));
        }
    }

    public Point nearest(double x, double y) {
        return nearestHelper(root, new Point(x, y), root, 0).point;
    }
}
