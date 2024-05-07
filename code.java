package ADS;
import java.util.*;

class Edge{
    int destination;
    int weight;

    public Edge(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}

class Graph {
    int V;
    List<List<Edge>> adjList;

    public Graph(int V) {
        this.V = V;
        adjList = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int weight) {
        adjList.get(source).add(new Edge(destination, weight));
        // For undirected graph, uncomment the line below
        // adjList.get(destination).add(new Edge(source, weight));
    }

    public List<Integer> dijkstraShortestPath(int source, int destination) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[] { source, 0 });

        int[] distances = new int[V];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        int[] parent = new int[V];
        Arrays.fill(parent, -1);

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0];
            int dist = curr[1];

            if (u == destination) {
                break; // Shortest path to destination found
            }

            if (dist > distances[u]) {
                continue; // Skip outdated distances
            }

            for (Edge edge : adjList.get(u)) {
                int v = edge.destination;
                int weight = edge.weight;

                if (dist + weight < distances[v]) {
                    distances[v] = dist + weight;
                    parent[v] = u;
                    pq.offer(new int[] { v, distances[v] });
                }
            }
        }

        List<Integer> shortestPath = new ArrayList<>();
        int current = destination;
        while (current != -1) {
            shortestPath.add(current);
            current = parent[current];
        }
        Collections.reverse(shortestPath);

        return shortestPath;
    }
}

 class Assignment5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph graph = null;

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Create graph");
            System.out.println("2. Find shortest path");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the number of vertices in the graph: ");
                    int V = scanner.nextInt();
                    graph = new Graph(V);

                    System.out.print("Enter the number of edges in the graph: ");
                    int E = scanner.nextInt();

                    System.out.println("Enter the details of each edge (source, destination, weight):");
                    for (int i = 0; i < E; i++) {
                        int src = scanner.nextInt();
                        int dest = scanner.nextInt();
                        int weight = scanner.nextInt();
                        graph.addEdge(src, dest, weight);
                    }
                    break;
                case 2:
                    if (graph == null) {
                        System.out.println("Create a graph first!");
                        break;
                    }

                    int source, destination;
                    System.out.print("Enter the source node: ");
                    source = scanner.nextInt();
                    System.out.print("Enter the destination node: ");
                    destination = scanner.nextInt();

                    List<Integer> shortestPath = graph.dijkstraShortestPath(source, destination);

                    System.out.println("Shortest path from source " + source + " to destination " + destination + ":");
                    for (int node : shortestPath) {
                        System.out.print(node + " ");
                    }
                    break;
                case 3:
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
    }
}
