import java.util.List;

public class main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge("A", "B", 5);
        graph.addEdge("A", "C", 10);
        graph.addEdge("B", "C", 2);
        graph.addEdge("B", "D", 4);
        graph.addEdge("C", "D", 3);
        graph.addEdge("D", "E", 8);
        graph.addEdge("E", "F", 3);
        graph.addEdge("C", "F", 7);

        List<String> shortestPath = graph.findShortestPath("A", "F");
        System.out.println("Shortest path from A to F: " + shortestPath);
    }
}
