package p11060;

import java.util.*;
import java.util.stream.Collectors;

class Main {
    static Map<String, Integer> mappings = new LinkedHashMap<>();
    private static int caseNum = 1;

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String beverage;
            String[] relation;

            while(true) {
                String line = scanner.nextLine();

                if (line.isEmpty()) break;

                int n = Integer.parseInt(line);

                for (int i = 0; i < n; i++) {
                    beverage = scanner.nextLine();
                    mappings.put(beverage, i);
                }

                Graph graph = new Graph(n);

                int m = Integer.parseInt(scanner.nextLine());

                for (int i = 0; i < m; i++) {
                    relation = scanner.nextLine().split(" ");

                    graph.addEdge(
                            getV(relation[0]),
                            getV(relation[1]));
                }

                Map<Integer, String> mappingsInversed =
                        mappings.entrySet()
                                .stream()
                                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

                ArrayList<Integer> result = new ArrayList<>();
                // NOTE: PQ is important here, since we want to handle vertices which appear first.
                Queue<Integer> queue = new PriorityQueue<>();

                // Step 1: Find vertices with 0 in-degree
                for (int i = 0; i < n; i++) {
                    if (graph.inDegree[i] == 0) {
                        queue.add(i);
                    }
                }

                // Step 2: For each 0 degree vertex, remove the edge and decrement in-degree
                while (!queue.isEmpty()) {
                    int u = queue.poll();

                    for (int v : graph.adj.get(u)) {
                        graph.inDegree[v]--;

                        if (graph.inDegree[v] == 0) {
                            queue.add(v);
                        }
                    }

                    result.add(u);
                }

                String resultStr = result
                        .stream()
                        .map(mappingsInversed::get)
                        .collect(Collectors.joining(" "));

                System.out.println("Case #" + caseNum + ": Dilbert should drink beverages in this order: " + resultStr + ".");

                reset();
                scanner.nextLine();
            }

        } catch (Exception ex) {

        }
    }

    static int getV(String beverage) {
        return mappings.get(beverage);
    }

    static void reset() {
        mappings.clear();
        caseNum++;
        System.out.println();
    }
}

class Graph {
    int numV;
    ArrayList<ArrayList<Integer>> adj;
    boolean[] visited;
    int[] inDegree;

    Graph(int n) {
        numV = n;
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        visited = new boolean[numV];
        inDegree = new int[numV];

        for (int i = 0; i < n; i++) {
            inDegree[i] = 0;
        }
    }

    void addEdge(int u, int v) {
        adj.get(u).add(v);
        inDegree[v]++;
    }
}
