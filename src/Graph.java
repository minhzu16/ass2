import java.util.*;

public class Graph {
    // A set of vertices in the graph
    Set<Vertex> vertices = new TreeSet<>(Comparator.comparing(vertex -> vertex.label));

    // Default constructor
    public Graph() {}

    // Constructor to initialize the graph from an adjacency matrix
    public Graph(int[][] matrix) {
        String[] vertexLabels = new String[matrix.length];
        // Assigning alphabetical labels to vertices (A, B, C, etc.)
        for (int i = 0; i < vertexLabels.length; i++) {
            vertexLabels[i] = String.valueOf((char) ('A' + i));
        }

        // Building graph edges based on non-zero entries in the matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] != 0 && i != j) {
                    this.addEdge(vertexLabels[i], vertexLabels[j], matrix[i][j]);
                }
            }
        }
    }

    // Adds a vertex to the graph if it doesn't already exist
    public Vertex addVertex(String label) {
        Vertex existingVertex = this.getVertex(label);
        if (existingVertex == null) {
            Vertex newVertex = new Vertex(label);
            this.vertices.add(newVertex);
            return newVertex;
        }
        return existingVertex;
    }

    // Removes a vertex by label
    public Vertex removeVertex(String label) {
        Vertex vertexToRemove = this.getVertex(label);
        this.vertices.remove(vertexToRemove);
        return vertexToRemove;
    }

    // Adds an edge between two vertices with a specified weight
    public void addEdge(String label1, String label2, int weight) {
        Vertex v1 = this.getVertex(label1);
        Vertex v2 = this.getVertex(label2);

        if (v1 == null) v1 = this.addVertex(label1);
        if (v2 == null) v2 = this.addVertex(label2);

        // Adds edges in both directions to create an undirected graph
        v1.adjList.put(v2, weight);
        v2.adjList.put(v1, weight);
    }

    // Retrieves a vertex by label
    public Vertex getVertex(String label) {
        return this.vertices.stream().filter(vertex -> vertex.label.equals(label)).findFirst().orElse(null);
    }

    // Displays the adjacency matrix of the graph
    public void displayMatrix() {
        int n = this.vertices.size();
        int[][] adjMatrix = new int[n][n];
        ArrayList<Vertex> vertexList = new ArrayList<>(this.vertices);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (vertexList.get(i).adjList.containsKey(vertexList.get(j))) {
                    adjMatrix[i][j] = vertexList.get(i).adjList.get(vertexList.get(j));
                }
            }
        }

        System.out.print("   ");
        for (Vertex vertex : this.vertices) System.out.print(vertex.label + " ");
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.print(vertexList.get(i).label + "  ");
            for (int j = 0; j < n; j++) {
                System.out.print(adjMatrix[i][j] + "  ");
            }
            System.out.println();
        }
    }

    // Displays the graph as an adjacency list
    public void display() {
        for (Vertex vertex : this.vertices) {
            System.out.print(vertex.label + " -> ");
            for (Map.Entry<Vertex, Integer> entry : vertex.adjList.entrySet()) {
                System.out.print("(" + entry.getKey().label + ", " + entry.getValue() + ") ");
            }
            System.out.println();
        }
    }

    // Finds the shortest path from startLabel to endLabel using Dijkstra's algorithm
    public List<String> findShortestPath(String startLabel, String endLabel) {
        Vertex start = getVertex(startLabel);
        Vertex end = getVertex(endLabel);

        if (start == null || end == null) {
            System.out.println("One or both vertices not found.");
            return Collections.emptyList();
        }

        // Maps to hold the shortest distances and previous vertices
        Map<Vertex, Integer> distances = new HashMap<>();
        Map<Vertex, Vertex> previous = new HashMap<>();

        // Initialize distances as infinity, with the start vertex distance set to 0
        for (Vertex v : vertices) {
            distances.put(v, Integer.MAX_VALUE);
            previous.put(v, null);
        }
        distances.put(start, 0);

        // Set of unvisited vertices
        Set<Vertex> unvisited = new HashSet<>(vertices);

        // Main loop of Dijkstra's algorithm
        while (!unvisited.isEmpty()) {
            Vertex current = getMinDistanceVertex(unvisited, distances);
            if (current == null) break;

            unvisited.remove(current);

            if (current == end) break; // Exit if the shortest path to `end` is found

            // Update distances to each neighbor of the current vertex
            for (Map.Entry<Vertex, Integer> neighborEntry : current.adjList.entrySet()) {
                Vertex neighbor = neighborEntry.getKey();
                int edgeWeight = neighborEntry.getValue();

                int newDist = distances.get(current) + edgeWeight;
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, current);
                }
            }
        }

        // Build the shortest path by tracing back from the end vertex
        List<String> path = new ArrayList<>();
        for (Vertex at = end; at != null; at = previous.get(at)) {
            path.add(at.label);
        }
        Collections.reverse(path); // Reverse to get path from start to end

        return path;
    }

    public int getPathWeight(List<String> path) {
        int weight = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            Vertex v1 = getVertex(path.get(i));
            Vertex v2 = getVertex(path.get(i + 1));
            if (v1 != null && v2 != null && v1.adjList.containsKey(v2)) {
                weight += v1.adjList.get(v2);
            }
        }
        return weight;
    }

    // Finds the vertex with the minimum distance in the set of unvisited vertices
    public Vertex getMinDistanceVertex(Set<Vertex> unvisited, Map<Vertex, Integer> distances) {
        Vertex minVertex = null;
        int minDistance = Integer.MAX_VALUE;
        for (Vertex vertex : unvisited) {
            int distance = distances.get(vertex);
            if (distance < minDistance) {
                minDistance = distance;
                minVertex = vertex;
            }
        }
        return minVertex;
    }

    // Tối ưu đường đi qua nhiều điểm có thứ tự
    public List<String> optimizeRouteOrdered(List<String> points) {
        List<String> optimizedRoute = new ArrayList<>();
        for (int i = 0; i < points.size() - 1; i++) {
            optimizedRoute.addAll(findShortestPath(points.get(i), points.get(i + 1)));
        }
        return optimizedRoute;
    }


    public List<String> optimizeRouteUnordered(List<String> points) {
        List<String> bestPath = new ArrayList<>();
        int bestWeight = Integer.MAX_VALUE;

        // Tạo tất cả các hoán vị của điểm
        List<List<String>> permutations = generatePermutations(points);
        for (List<String> permutation : permutations) {
            int currentWeight = 0;
            List<String> currentPath = new ArrayList<>();
            for (int i = 0; i < permutation.size() - 1; i++) {
                List<String> pathSegment = findShortestPath(permutation.get(i), permutation.get(i + 1));
                currentPath.addAll(pathSegment);
                currentWeight += getPathWeight(pathSegment);
            }

            if (currentWeight < bestWeight) {
                bestWeight = currentWeight;
                bestPath = new ArrayList<>(currentPath);
            }
        }

        return bestPath;
    }

    // Hàm sinh các hoán vị của một danh sách điểm
    public List<List<String>> generatePermutations(List<String> points) {
        List<List<String>> permutations = new ArrayList<>();
        permute(points, 0, permutations);
        return permutations;
    }

    public void permute(List<String> points, int start, List<List<String>> result) {
        if (start == points.size() - 1) {
            result.add(new ArrayList<>(points));
        } else {
            for (int i = start; i < points.size(); i++) {
                Collections.swap(points, i, start);
                permute(points, start + 1, result);
                Collections.swap(points, i, start);
            }
        }
    }
    
    
    // Hàm kiểm tra vòng lặp trên đường đi đã đi
    public boolean checkCircle(List<String> path) {
        Set<String> visited = new HashSet<>();
        for (String point : path) {
            if (!visited.add(point)) {
                return true; // Có vòng lặp
            }
        }
        return false;
    }

    // Hàm kiểm tra đường có cùng trọng số (tổng trọng số ngắn nhất)
    public int checkSameWeightPath(List<String> path) {
        return getPathWeight(path); // Trả về tổng trọng số của đường đi
    }


    // Kiểm tra đồ thị có hướng hay vô hướng
    public boolean isDirected() {
        for (Vertex v : vertices) {
            for (Map.Entry<Vertex, Integer> edge : v.adjList.entrySet()) {
                Vertex neighbor = edge.getKey();
                if (!neighbor.adjList.containsKey(v) || !neighbor.adjList.get(v).equals(edge.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

}