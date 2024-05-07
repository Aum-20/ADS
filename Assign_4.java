package ADS;
import java.util.*;
public class Assign_3 implements Comparable<Assign_3>{
    int src, dest, weight;

    public Assign_3(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Assign_3 other) {
        return this.weight - other.weight;
    }
}

class Graph {
    int V;
    ArrayList<Assign_3> edges;

    public Graph(int V) {
        this.V = V;
        edges = new ArrayList<>();
    }

    public void addEdge(int src, int dest, int weight) {
        edges.add(new Assign_3(src, dest, weight));
    }

    public int kruskalMST() {
        Collections.sort(edges);

        int[] parent = new int[V];
        Arrays.fill(parent, -1);

        int mstWeight = 0;
        int edgeCount = 0;

        for (Assign_3 edge : edges) {
            int srcParent = find(parent, edge.src);
            int destParent = find(parent, edge.dest);

            if (srcParent != destParent) {
                mstWeight += edge.weight;
                union(parent, srcParent, destParent);
                edgeCount++;
            }

            if (edgeCount == V - 1)
                break;
        }

        return mstWeight;
    }

    private int find(int[] parent, int vertex) {
        if (parent[vertex] == -1)
            return vertex;
        return find(parent, parent[vertex]);
    }

    private void union(int[] parent, int x, int y) {
        int xParent = find(parent, x);
        int yParent = find(parent, y);
        parent[xParent] = yParent;
    }

    public static void main(String[] args) {
        int V = 4;
        Graph graph = new Graph(V);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);

        System.out.println("Total weight of minimum spanning tree (Kruskal's): " + graph.kruskalMST());
    }
}
