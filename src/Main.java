import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose mode:");
        System.out.println("1. Interactive Menu");
        System.out.println("2. Run Demo");
        System.out.print("Enter choice (1 or 2): ");

        int choice = scanner.nextInt();
        if (choice == 1) {
            // Run interactive menu
            Menu menu = new Menu();
            menu.start();
        } else if (choice == 2) {
            // Run demo code
            runDemo();
        } else {
            System.out.println("Invalid choice. Exiting...");
        }
        scanner.close();
    }

    private static void runDemo() {
        Graph graph = new Graph();

        // Add edges for points A - Z
        graph.addEdge("A", "B", 5);
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

        System.out.println("\n=== Demo Results ===\n");

        // Display graph structure
        System.out.println("Graph as Adjacency Matrix:");
        graph.displayMatrix();
        System.out.println("\nGraph as Adjacency List:");
        graph.display();
        System.out.println();

        // Find shortest path from A to Y
        List<String> shortestPath = graph.findShortestPath("A", "Y");
        System.out.println("Shortest path from A to Y: " + shortestPath);
        int totalWeight = graph.getPathWeight(shortestPath);
        System.out.println("Total weight of the shortest path: " + totalWeight);
        System.out.println();

        // Optimize ordered route
        List<String> orderedPoints = Arrays.asList("A", "B", "D", "F");
        Pair<List<String>, Integer> optimizedOrderedResult = graph.optimizeRouteOrdered(orderedPoints);
        System.out.println("Optimized ordered path through " + orderedPoints + ": " +
                optimizedOrderedResult.getKey());
        System.out.println("Total weight of ordered path: " + optimizedOrderedResult.getValue());
        System.out.println();

        // Optimize unordered route
        List<String> unorderedPoints = Arrays.asList("A", "B", "C", "D", "E", "F");
        Pair<List<String>, Integer> optimizedUnorderedResult = graph.optimizeRouteUnordered(unorderedPoints);
        System.out.println("Optimized unordered path through " + unorderedPoints + ": " +
                optimizedUnorderedResult.getKey());
        System.out.println("Total weight of unordered path: " + optimizedUnorderedResult.getValue());
        System.out.println();

        // Check for circle in path
        List<String> pathWithCircle = Arrays.asList("A", "B", "C", "A");
        boolean hasCircle = graph.checkCircle(pathWithCircle);
        System.out.println("Does the path A-B-C-A have a circle? " + hasCircle);
        System.out.println();

        // Check if graph is directed
        boolean isDirected = graph.isDirected();
        System.out.println("Is the graph directed? " + isDirected);
    }
}