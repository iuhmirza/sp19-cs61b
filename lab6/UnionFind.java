public class UnionFind {

    int[] elements;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        elements = new int[n];
        for (int i = 0; i < n; i++) {
            elements[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex < 0) {
            throw new Error();
        }
    }
    public int root(int v1) {
        int curr = v1;
        while (parent(curr) >= 0) {
            curr = parent(curr);
        }
        return curr;
    }
    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        return elements[root(v1)];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        return elements[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        int x = root(v1);
        int y = root(v2);
        return root(v1) == root(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        if(connected(v1, v2)) {
            return;
        }
        if (sizeOf(v1) > sizeOf(v2)) {
            elements[root(v2)] += elements[root(v1)];
            elements[root(v1)] = root(v2);
        } else {
            elements[root(v1)] += elements[root(v2)];
            elements[root(v2)] = root(v1);
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        return -1;
    }

}
