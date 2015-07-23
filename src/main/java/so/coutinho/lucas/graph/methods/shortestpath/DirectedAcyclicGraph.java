package so.coutinho.lucas.graph.methods.shortestpath;

import java.util.List;
import so.coutinho.lucas.graph.Edge;
import so.coutinho.lucas.graph.Graph;
import so.coutinho.lucas.graph.Vertex;
import so.coutinho.lucas.graph.methods.search.BreadthFirstSearch;
import so.coutinho.lucas.graph.methods.search.DepthFirstSearch;

/**
 *
 * @author Lucas
 */
public class DirectedAcyclicGraph extends ShortestPath {

    DepthFirstSearch dfs = new DepthFirstSearch();

    @Override
    public void run(Graph graph, Vertex origin) {
        dfs.walkingOnTheGraph(graph);

        super.initializeAtributes(graph.getNumberOfVertices());
        super.getWeight()[origin.getId()] = 0;

        for (Vertex vertex : dfs.getTopologicalSorting()) {
            relaxEdges(graph.getEdges(vertex));
        }
    }

}
