package ADS;
import java.util.*;
class Edge implements Comparable<Edge> {
    int src;
    int dest;
    int weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

class Graph {
    int v;
    List<Edge> graph[];

    Graph(int v) {
        this.v = v;
        graph = new ArrayList[v];
        // initialize the arraylist of each vertex as empty arraylist
        for (int i = 0; i < v; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    public void addEdge(int src, int dest, int weight) {
        graph[src].add(new Edge(src, dest, weight));
        graph[dest].add(new Edge(src, dest, weight));
    }

    public int minSpanningTree() {
        boolean inMST[] = new boolean[v]; // to check whether it is present in mst or not
        int parent[] = new int[v]; // to store the parent vertex of respective vertex
        int key[] = new int[v]; // to store the minimum weight to that vertex

        Arrays.fill(key, Integer.MAX_VALUE); // initially all vertices are at infinity

        PriorityQueue<Edge> pq = new PriorityQueue<>(); // sort the edges according to weight
        key[0] = 0;
        pq.add(new Edge(0, 0, 0));

        while (!pq.isEmpty()) {
            int u = pq.poll().dest;
            inMST[u] = true;

            for (int i = 0; i < graph[u].size(); i++) {
                Edge e = graph[u].get(i);

                int v = e.dest;
                int weight = e.weight;

                if (!inMST[v] && weight < key[v]) { // new weight is less than previous weight then replace it
                    key[v] = weight;
                    pq.add(new Edge(u, v, key[v]));
                    parent[v] = u;
                }
            }
        }

        for (int i = 1; i < v; i++) {
            System.out.println(parent[i] + " - " + i + " : " + key[i]);
        }

        // minimum weight is sum of key array
        int totalWeight = 0;
        for (int i = 1; i < v; i++) {
            totalWeight += key[i];
        }
        return totalWeight;
    }
}

class Assignment3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------------------------------------------------");
        System.out.println("\t*** Finding MST using Prim's Algorithm ***\t");
        System.out.println("------------------------------------------------------------");

        System.out.print("Enter the no. of vertices : ");
        int v = sc.nextInt();

        Graph G1 = new Graph(v);

        System.out.print("Enter the no. of edges : ");
        int e = sc.nextInt();

        System.out.println("Enter the details of each edge (source, destination, weight):");
        for (int i = 0; i < e; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int weight = sc.nextInt();
            G1.addEdge(src, dest, weight);
        }

        System.out.println("The one of the spanning tree is : ");
        int totalWeight = G1.minSpanningTree();
        System.out.println("Total weight of the minimum spanning tree is : " + totalWeight);
    }
}

