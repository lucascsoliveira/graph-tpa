package so.coutinho.lucas.graph.methods.minimumspanningtree;

import java.util.Arrays;
import lombok.Getter;
import lombok.Setter;
import so.coutinho.lucas.graph.Graph;
import so.coutinho.lucas.graph.Vertex;

/**
 *
 * @author lucas.oliveira
 */
@Getter
@Setter
abstract class MinimumSpanningTree {

    private Integer[] cost;
    private Vertex[] predecessor;

    protected void initializeAtributes(Integer numberOfVertices) {
        cost = new Integer[numberOfVertices];
        predecessor = new Vertex[numberOfVertices];

        Arrays.fill(cost, Integer.MAX_VALUE);
        Arrays.fill(predecessor, null);
    }

    public abstract void run(Graph graph, Vertex origin);

    public void run(Graph graph, Integer originId) {
        run(graph, graph.getVertex(originId));
    }

    public void run(Graph graph, String originLabel) {
        run(graph, graph.getVertex(originLabel));
    }

}
