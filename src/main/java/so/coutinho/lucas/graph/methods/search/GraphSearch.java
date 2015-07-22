package so.coutinho.lucas.graph.methods.search;

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
abstract class GraphSearch {

    private Character[] color;
    private Vertex[] predecessor;

    public abstract void walkingOnTheGraph(Graph graph);

    protected void initializeAtributes(Integer numberOfVertices) {
        color = new Character[numberOfVertices];
        predecessor = new Vertex[numberOfVertices];

        Arrays.fill(color, 'B');
        Arrays.fill(predecessor, null);
    }

}
