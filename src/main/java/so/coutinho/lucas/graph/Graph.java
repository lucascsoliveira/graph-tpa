package so.coutinho.lucas.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Lucas
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Graph {

    public static final Integer DIRECTED = 2;
    public static final Integer UNDIRECTED = 1;

    private String name;
    private Integer graphType;
    private Integer[][] adjacencyMatrix;
    private String[] labelsOfVertices;

    public static Graph buildFromFile(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file, "UTF-8");

        String nameOfGraph = input.nextLine();
        Integer typeOfGraph = input.nextInt();
        Integer numberOfVertices = input.nextInt();
        String[] labelsOfVertices = new String[numberOfVertices];
        Integer[][] adjacencyMatrix = new Integer[numberOfVertices][numberOfVertices];

        // TERMINA DE LER A LINHA ATUAL
        input.nextLine();

        for (int i = 0; i < numberOfVertices; i++) {
            labelsOfVertices[i] = input.nextLine();
        }

        for (int origin = 0; origin < numberOfVertices; origin++) {
            for (int target = 0; target < numberOfVertices; target++) {
                adjacencyMatrix[origin][target] = input.nextInt();
            }
        }

        return new Graph(nameOfGraph, typeOfGraph, adjacencyMatrix, labelsOfVertices);
    }

    public Integer getNumberOfVertices() {
        return adjacencyMatrix.length;
    }

    public Vertex getVertex(Integer id) {
        return new Vertex(id, labelsOfVertices[id]);
    }

    public Vertex getVertex(String label) {
        return getVertex(getVertexId(label));
    }

    public Integer getVertexId(String vertexLabel) {
        int id = 0;
        for (String label : labelsOfVertices) {
            if (label.equals(vertexLabel)) {
                return (id);
            }
            id++;
        }

        throw new IndexOutOfBoundsException();
    }

    public boolean isAdjacent(Integer vertexOrigin, Integer vertexTarget) {
        return adjacencyMatrix[vertexOrigin][vertexTarget] != 0;
    }

    public boolean isAdjacent(String vertexOrigin, String vertexTarget) {
        return isAdjacent(getVertexId(vertexOrigin), getVertexId(vertexTarget));
    }

    public boolean isAdjacent(Vertex vertexOrigin, Vertex vertexTarget) {
        return isAdjacent(vertexOrigin.getId(), vertexTarget.getId());
    }

    public List<Vertex> getAdjacents(Integer vertexId) {
        List<Vertex> vertices = new ArrayList<>();

        for (int targetId = 0; targetId < getNumberOfVertices(); targetId++) {
            if (isAdjacent(vertexId, targetId)) {
                vertices.add(new Vertex(targetId, labelsOfVertices[targetId]));
            }
        }

        return vertices;
    }

    public List<Vertex> getAdjacents(String vertexLabel) {
        return getAdjacents(getVertexId(vertexLabel));
    }

    public List<Vertex> getAdjacents(Vertex vertex) {
        return getAdjacents(vertex.getId());
    }

    public List<Vertex> getVertices() {
        List<Vertex> vertices = new ArrayList<>();

        for (String label : labelsOfVertices) {
            vertices.add(getVertex(label));
        }

        return vertices;
    }

    public List<Edge> getEdges() {
        List<Edge> edges = new ArrayList<>();

        for (int origin = 0; origin < getNumberOfVertices(); origin++) {
            for (int target = 0; target < getNumberOfVertices(); target++) {
                if (isAdjacent(origin, target)) {
                    edges.add(new Edge(getVertex(origin), getVertex(target), adjacencyMatrix[origin][target]));
                }
            }
        }

        return edges;
    }

    private void validateUndirectedGraph() {
        if (graphType.equals(UNDIRECTED)) {
            int origin, target;

            for (origin = 0; origin < getNumberOfVertices(); origin++) {
                for (target = 0; target < getNumberOfVertices(); target++) {
                    if (!adjacencyMatrix[origin][target].equals(adjacencyMatrix[target][origin])) {
                        throw new RuntimeException("InvalidUndirectedGraph");
                    }
                }
            }
        }
    }

}
