import java.util.Arrays;
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

        // Tối ưu đường đi giữa nhiều điểm có thứ tự
        List<String> orderedPoints = Arrays.asList("A", "B", "D", "F");
        List<String> optimizedOrderedPath = graph.optimizeRouteOrdered(orderedPoints);
        System.out.println("Optimized ordered path: " + optimizedOrderedPath);

        // Tối ưu đường đi giữa nhiều điểm không có thứ tự
        List<String> unorderedPoints = Arrays.asList("A", "B", "C", "D", "E", "F");
        List<String> optimizedUnorderedPath = graph.optimizeRouteUnordered(unorderedPoints);
        System.out.println("Optimized unordered path: " + optimizedUnorderedPath);

        // Kiểm tra vòng lặp trong đường đi
        List<String> pathWithCircle = Arrays.asList("A", "B", "C", "A");
        boolean hasCircle = graph.checkCircle(pathWithCircle);
        System.out.println("Does the path have a circle? " + hasCircle);

        // Kiểm tra trọng số của đường đi
        int totalWeight = graph.checkSameWeightPath(shortestPath);
        System.out.println("Total weight of the shortest path: " + totalWeight);

        // Kiểm tra xem đồ thị có phải là 1 chiều hay 2 chiều
        boolean isDirected = graph.isDirected();
        System.out.println("Is the graph directed? " + isDirected);


    }
}
