package so.coutinho.lucas.graph.methods.minimumspanningtree;

import java.util.List;
import so.coutinho.lucas.graph.Graph;
import so.coutinho.lucas.graph.Vertex;

/**
 *
 * @author lucas.oliveira
 */
public class PrimJarnik extends MinimumSpanningTree {

    @Override
    public void run(Graph graph, Vertex origin) {
        super.initializeAtributes(graph.getNumberOfVertices());
        super.getCost()[origin.getId()] = 0;
        List<Vertex> Q = graph.getVertices();

        while (!Q.isEmpty()) {
            Vertex u = getLowerCost(Q);
            Q.remove(u);

            for (Vertex v : graph.getAdjacents(u)) {
                Integer costEdge = graph.getAdjacencyMatrix()[u.getId()][v.getId()];

                if (Q.contains(v) && (costEdge < getCost()[v.getId()])) {
                    getPredecessor()[v.getId()] = u;
                    getCost()[v.getId()] = costEdge;
                }
            }
        }
    }

    private Vertex getLowerCost(List<Vertex> vertices) {
        if (!vertices.isEmpty()) {
            Vertex lowerVertex = vertices.get(0);
            Integer lowerCost = super.getCost()[lowerVertex.getId()];

            for (Vertex vertex : vertices) {
                if (super.getCost()[vertex.getId()] < lowerCost) {
                    lowerVertex = vertex;
                    lowerCost = super.getCost()[vertex.getId()];
                }
            }

            return lowerVertex;
        }
        throw new RuntimeException("VertexNotFoundException");
    }
}
