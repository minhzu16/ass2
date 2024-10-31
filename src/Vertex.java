import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// Lớp Vertex - Đại diện cho một đỉnh trong đồ thị
public class Vertex {
    String label;
    Map<Vertex, Integer> adjList = new HashMap<>();

    public Vertex(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vertex vertex = (Vertex) obj;
        return Objects.equals(label, vertex.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

}
