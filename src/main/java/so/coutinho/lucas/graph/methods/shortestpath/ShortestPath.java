package so.coutinho.lucas.graph.methods.shortestpath;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import so.coutinho.lucas.graph.Edge;
import so.coutinho.lucas.graph.Graph;
import so.coutinho.lucas.graph.Vertex;

/**
 *
 * @author Lucas
 */
@Getter
@Setter
abstract class ShortestPath {

    private Integer[] weight;
    private Vertex[] predecessor;

    protected void initializeAtributes(Integer numberOfVertices) {
        weight = new Integer[numberOfVertices];
        predecessor = new Vertex[numberOfVertices];

        Arrays.fill(weight, Integer.MAX_VALUE);
        Arrays.fill(predecessor, null);
    }

    protected void relaxEdges(List<Edge> edges) {
        for (Edge edge : edges) {
            Integer w = edge.getWeight();
            Vertex u = edge.getOrigin();
            Vertex v = edge.getTarget();

            Integer weightU = getWeight()[u.getId()],
                    weightV = getWeight()[v.getId()];

            if (weightU.equals(Integer.MAX_VALUE)) {
                continue;
            }

            if ((weightU + w) < weightV) {
                getWeight()[v.getId()] = weightU + w;
                getPredecessor()[v.getId()] = u;
            }
        }
    }

    public abstract void run(Graph graph, Vertex origin);

    public void run(Graph graph, Integer originId) {
        run(graph, graph.getVertex(originId));
    }

    public void run(Graph graph, String originLabel) {
        run(graph, graph.getVertex(originLabel));
    }

}
