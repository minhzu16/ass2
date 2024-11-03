import java.util.Arrays;
import java.util.List;

public class main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        // Thêm các cạnh cho các điểm A - F
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

        List<String> shortestPath = graph.findShortestPath("A", "Y");
        System.out.println("Shortest path from A to Z : " + shortestPath);
        // Kiểm tra trọng số của đường đi
        int totalWeight = graph.checkSameWeightPath(shortestPath);
        System.out.println("Total weight of the shortest path: " + totalWeight);

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

        // Kiểm tra xem đồ thị có phải là 1 chiều hay 2 chiều
        boolean isDirected = graph.isDirected();
        System.out.println("Is the graph directed? " + isDirected);
    }
}
