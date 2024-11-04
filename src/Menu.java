import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Graph graph;
    private Scanner scanner;

    public Menu() {
        this.graph = new Graph();
        this.scanner = new Scanner(System.in);
        initializeDefaultGraph();
    }

    private void initializeDefaultGraph() {
        String[] vertices = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"};

        // Add default edges
        addDefaultEdges();

        System.out.println("Default graph initialized with vertices A through Z");
        System.out.println("Type 'help' at any prompt to see available commands");
    }

    private void addDefaultEdges() {
        graph.addEdge("A", "C", 10);
        graph.addEdge("B", "C", 2);
        graph.addEdge("B", "D", 4);
        graph.addEdge("C", "D", 3);
        graph.addEdge("D", "E", 8);
        graph.addEdge("E", "F", 3);
        graph.addEdge("C", "F", 7);
        graph.addEdge("F", "G", 4);
        graph.addEdge("G", "H", 6);
        graph.addEdge("H", "I", 1);
        graph.addEdge("I", "J", 9);
        graph.addEdge("J", "K", 2);
        graph.addEdge("K", "L", 3);
        graph.addEdge("L", "M", 7);
        graph.addEdge("M", "N", 5);
        graph.addEdge("N", "O", 4);
        graph.addEdge("O", "P", 6);
        graph.addEdge("P", "Q", 8);
        graph.addEdge("Q", "R", 3);
        graph.addEdge("R", "S", 9);
        graph.addEdge("S", "T", 2);
        graph.addEdge("T", "U", 4);
        graph.addEdge("U", "V", 7);
        graph.addEdge("V", "W", 5);
        graph.addEdge("W", "X", 6);
        graph.addEdge("X", "Y", 8);
        graph.addEdge("Y", "Z", 3);
    }

    public void start() {
        boolean running = true;
        while (running) {
            displayMainMenu();
            String choice = scanner.nextLine().trim().toLowerCase();

            switch (choice) {
                case "1":  // Graph Operations
                    handleGraphOperations();
                    break;
                case "2":  // Path Finding
                    handlePathFinding();
                    break;
                case "3":  // Route Optimization
                    handleRouteOptimization();
                    break;
                case "4":  // Graph Analysis
                    handleGraphAnalysis();
                    break;
                case "5":  // Visualization
                    handleVisualization();
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting program...");
                    break;
                case "help":
                    displayHelp();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private void displayMainMenu() {
        System.out.println("\n=== Graph Management System ===");
        System.out.println("1. Graph Operations (Add/Remove)");
        System.out.println("2. Path Finding");
        System.out.println("3. Route Optimization");
        System.out.println("4. Graph Analysis");
        System.out.println("5. Visualization");
        System.out.println("0. Exit");
        System.out.println("Type 'help' for more information");
        System.out.print("\nEnter your choice: ");
    }

    private void handleGraphOperations() {
        while (true) {
            System.out.println("\n=== Graph Operations ===");
            System.out.println("1. Add edge");
            System.out.println("2. Add multiple edges");
            System.out.println("3. Remove vertex");
            System.out.println("4. List all vertices");
            System.out.println("0. Back to main menu");
            System.out.print("\nEnter choice: ");

            String choice = scanner.nextLine().trim();
            if (choice.equals("0")) break;

            switch (choice) {
                case "1":
                    addSingleEdge();
                    break;
                case "2":
                    addMultipleEdges();
                    break;
                case "3":
                    removeVertex();
                    break;
                case "4":
                    listVertices();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void handlePathFinding() {
        while (true) {
            System.out.println("\n=== Path Finding ===");
            System.out.println("1. Find shortest path");
            System.out.println("2. Get path weight");
            System.out.println("3. Check path for cycles");
            System.out.println("0. Back to main menu");
            System.out.print("\nEnter choice: ");

            String choice = scanner.nextLine().trim();
            if (choice.equals("0")) break;

            switch (choice) {
                case "1":
                    findShortestPath();
                    break;
                case "2":
                    getPathWeight();
                    break;
                case "3":
                    checkPathForCycles();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void handleRouteOptimization() {
        while (true) {
            System.out.println("\n=== Route Optimization ===");
            System.out.println("1. Optimize ordered route");
            System.out.println("2. Optimize unordered route");
            System.out.println("0. Back to main menu");
            System.out.print("\nEnter choice: ");

            String choice = scanner.nextLine().trim();
            if (choice.equals("0")) break;

            switch (choice) {
                case "1":
                    optimizeOrderedRoute();
                    break;
                case "2":
                    optimizeUnorderedRoute();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void handleGraphAnalysis() {
        while (true) {
            System.out.println("\n=== Graph Analysis ===");
            System.out.println("1. Check if directed");
            System.out.println("2. Analyze connectivity");
            System.out.println("3. Get graph statistics");
            System.out.println("0. Back to main menu");
            System.out.print("\nEnter choice: ");

            String choice = scanner.nextLine().trim();
            if (choice.equals("0")) break;

            switch (choice) {
                case "1":
                    System.out.println("Graph is " + (graph.isDirected() ? "directed" : "undirected"));
                    break;
                case "2":
                    analyzeConnectivity();
                    break;
                case "3":
                    displayGraphStats();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void handleVisualization() {
        while (true) {
            System.out.println("\n=== Visualization ===");
            System.out.println("1. Display as matrix");
            System.out.println("2. Display as adjacency list");
            System.out.println("0. Back to main menu");
            System.out.print("\nEnter choice: ");

            String choice = scanner.nextLine().trim();
            if (choice.equals("0")) break;

            switch (choice) {
                case "1":
                    graph.displayMatrix();
                    break;
                case "2":
                    graph.display();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    // Helper methods for operations
    private void addSingleEdge() {
        try {
            System.out.print("Enter first vertex (e.g., A): ");
            String v1 = scanner.nextLine().trim().toUpperCase();
            System.out.print("Enter second vertex (e.g., B): ");
            String v2 = scanner.nextLine().trim().toUpperCase();
            System.out.print("Enter weight: ");
            int weight = Integer.parseInt(scanner.nextLine().trim());

            graph.addEdge(v1, v2, weight);
            System.out.println("Edge added successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid weight format. Please enter a number.");
        } catch (Exception e) {
            System.out.println("Error adding edge: " + e.getMessage());
        }
    }

    private void addMultipleEdges() {
        System.out.println("Enter edges in format 'v1 v2 weight' (one per line)");
        System.out.println("Enter blank line to finish");

        while (true) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) break;

            try {
                String[] parts = line.split("\\s+");
                if (parts.length != 3) {
                    System.out.println("Invalid format. Use: v1 v2 weight");
                    continue;
                }

                String v1 = parts[0].toUpperCase();
                String v2 = parts[1].toUpperCase();
                int weight = Integer.parseInt(parts[2]);

                graph.addEdge(v1, v2, weight);
                System.out.println("Edge added: " + v1 + " - " + v2);
            } catch (NumberFormatException e) {
                System.out.println("Invalid weight format");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void removeVertex() {
        System.out.print("Enter vertex to remove: ");
        String vertex = scanner.nextLine().trim().toUpperCase();
        try {
            graph.removeVertex(vertex);
            System.out.println("Vertex removed successfully!");
        } catch (Exception e) {
            System.out.println("Error removing vertex: " + e.getMessage());
        }
    }

    private void listVertices() {
        System.out.println("\nCurrent vertices in graph:");
        for (Vertex v : graph.vertices) {
            System.out.println(v.label + " - Connected to: " +
                    v.adjList.keySet().stream()
                            .map(vertex -> vertex.label)
                            .reduce("", (a, b) -> a + " " + b));
        }
    }

    private void findShortestPath() {
        System.out.print("Enter start vertex: ");
        String start = scanner.nextLine().trim().toUpperCase();
        System.out.print("Enter end vertex: ");
        String end = scanner.nextLine().trim().toUpperCase();

        List<String> path = graph.findShortestPath(start, end);
        if (!path.isEmpty()) {
            System.out.println("Shortest path: " + path);
            System.out.println("Total weight: " + graph.getPathWeight(path));
        } else {
            System.out.println("No path found between " + start + " and " + end);
        }
    }

    private void getPathWeight() {
        System.out.println("Enter vertices in path (space-separated): ");
        String input = scanner.nextLine().trim().toUpperCase();
        List<String> path = Arrays.asList(input.split("\\s+"));
        System.out.println("Path weight: " + graph.getPathWeight(path));
    }

    private void checkPathForCycles() {
        System.out.println("Enter path vertices (space-separated): ");
        String input = scanner.nextLine().trim().toUpperCase();
        List<String> path = Arrays.asList(input.split("\\s+"));
        boolean hasCycle = graph.checkCircle(path);
        System.out.println("Path " + (hasCycle ? "contains" : "does not contain") + " cycles");
    }

    private void optimizeOrderedRoute() {
        System.out.println("Enter vertices in order (space-separated): ");
        String input = scanner.nextLine().trim().toUpperCase();
        List<String> points = Arrays.asList(input.split("\\s+"));

        Pair<List<String>, Integer> result = graph.optimizeRouteOrdered(points);
        System.out.println("Optimized path: " + result.getKey());
        System.out.println("Total weight: " + result.getValue());
    }

    private void optimizeUnorderedRoute() {
        System.out.println("Enter vertices (space-separated): ");
        String input = scanner.nextLine().trim().toUpperCase();
        List<String> points = Arrays.asList(input.split("\\s+"));

        Pair<List<String>, Integer> result = graph.optimizeRouteUnordered(points);
        System.out.println("Optimized path: " + result.getKey());
        System.out.println("Total weight: " + result.getValue());
    }

    private void analyzeConnectivity() {
        // Count number of connected components
        System.out.println("\nConnectivity Analysis:");
        for (Vertex v : graph.vertices) {
            System.out.println("Vertex " + v.label + " is connected to: " +
                    v.adjList.size() + " other vertices");
        }
    }

    private void displayGraphStats() {
        int vertexCount = graph.vertices.size();
        int edgeCount = graph.vertices.stream()
                .mapToInt(v -> v.adjList.size())
                .sum() / 2; // Divide by 2 for undirected graph

        System.out.println("\nGraph Statistics:");
        System.out.println("Number of vertices: " + vertexCount);
        System.out.println("Number of edges: " + edgeCount);
        System.out.println("Average degree: " +
                String.format("%.2f", (double)edgeCount * 2 / vertexCount));
    }

    private void displayHelp() {
        System.out.println("\n=== Help Menu ===");
        System.out.println("1. Graph Operations: Add or remove vertices and edges");
        System.out.println("2. Path Finding: Find shortest paths and analyze existing paths");
        System.out.println("3. Route Optimization: Optimize routes through multiple points");
        System.out.println("4. Graph Analysis: Analyze graph properties and structure");
        System.out.println("5. Visualization: Display graph in different formats");
        System.out.println("\nTips:");
        System.out.println("- Vertex names are case-insensitive");
        System.out.println("- Use space-separated values for multiple inputs");
        System.out.println("- Type '0' to return to previous menu");
        System.out.println("- Type 'help' at any time to see this menu");
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.start();
    }
}