package p10305;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

class Main {
    static ArrayList<Integer> vs = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner scanner = new Scanner(new FileInputStream(args[0]));
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();

            if (n == 0 && m == 0) break;
            Graph graph = new Graph(n);

            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt();
                int w = scanner.nextInt();

                graph.addEdge(u - 1, w - 1);
            }

            for (int v = 0; v < graph.numV; v++) {
                if (!graph.visited[v]) {
                    dfs(graph, v);
                }
            }

            List list = vs.stream().map(x -> (x + 1) + "").collect(Collectors.toList());
            String result = String.join(" ", list);
            System.out.println(result);
            vs.clear();
        }

        scanner.close();
    }

    static void dfs(Graph graph, int vertex) {
        graph.visited[vertex] = true;
        for (int v : graph.adj.get(vertex)) {
            if (!graph.visited[v]) {
                dfs(graph, v);
            }
        }
        vs.add(0, vertex);
    }
}

class Graph {
    int numV;
    ArrayList<ArrayList<Integer>> adj;
    boolean[] visited;

    Graph(int numV) {
        this.numV = numV;
        this.adj = new ArrayList<>(numV);
        for (int i = 0; i < numV; i++) {
            adj.add(new ArrayList<>());
        }

        this.visited = new boolean[numV];
    }

    void addEdge(int u, int w) {
        adj.get(u).add(w);
    }

    @Override
    public String toString() {
        return "p10305.Graph{" +
                "adj=" + adj +
                '}';
    }
}