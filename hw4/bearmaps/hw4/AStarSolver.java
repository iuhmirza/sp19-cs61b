package bearmaps.hw4;

import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex>{
    private DoubleMapPQ<Vertex> queue = new DoubleMapPQ<>();
    private AStarGraph<Vertex> graph;
    private Vertex goal;
    private HashMap<Vertex, Double> distTo = new HashMap<>();
    private Stopwatch timer;
    private double timeTaken;
    private double timeout;
    private SolverOutcome outcome;
    private int visits = 0;
    private HashMap<Vertex, Vertex> edgeTo = new HashMap<>();
    public AStarSolver(
            AStarGraph<Vertex> input,
            Vertex start,
            Vertex end,
            double timeout
    ) {
        timer = new Stopwatch();
        graph = input;
        goal = end;
        queue.add(start, 0 + graph.estimatedDistanceToGoal(start, goal));
        distTo.put(start, 0.0);
        this.timeout = timeout;
        solve();
    }

    private void solve() {
        while (queue.size() > 0) {
            if (goal.equals(queue.getSmallest())) {
                timeTaken = timer.elapsedTime();
                outcome = SolverOutcome.SOLVED;
                return;
            }

            timeTaken = timer.elapsedTime();
            if (timeTaken > timeout) {
                outcome = SolverOutcome.TIMEOUT;
                return;
            }

            visits += 1;
            Vertex p = queue.removeSmallest();
            for (WeightedEdge<Vertex> edge : graph.neighbors(p)) {
                relax(edge);
            }
        }
        timeTaken = timer.elapsedTime();
        outcome = SolverOutcome.UNSOLVABLE;
    }

    private void relax(WeightedEdge<Vertex> edge) {
        Vertex p = edge.from();
        Vertex q = edge.to();

        double distToP = distTo.get(p);
        double distToQ = distTo.containsKey(q) ? distTo.get(q) : Double.POSITIVE_INFINITY;
        double w = edge.weight();

        if (distToP + w < distToQ) {
            distTo.put(q, distToP + w);
            edgeTo.put(q, p);
            double qToGoal = distTo.get(q) + graph.estimatedDistanceToGoal(q, goal);
            if (queue.contains(q)) {
                queue.changePriority(q, qToGoal);
            } else {
                queue.add(q, qToGoal);
            }
        }
    }

    public SolverOutcome outcome() {
        return outcome;
    }

    public List<Vertex> solution() {
        Vertex v = goal;
        ArrayList<Vertex> path = new ArrayList<>();
        while (edgeTo.containsKey(v)) {
            path.add(v);
            v = edgeTo.get(v);
        }
        path.add(v);
        return path;
    }

    public double solutionWeight() {
        double distToGoal = distTo.containsKey(goal) ? distTo.get(goal) : 0;
        return distToGoal;
    }

    public int numStatesExplored() {
        return visits;
    }

    public double explorationTime() {
        return timeout;
    }
}
