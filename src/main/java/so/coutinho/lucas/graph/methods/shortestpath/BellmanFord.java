package so.coutinho.lucas.graph.methods.shortestpath;

import so.coutinho.lucas.graph.Edge;
import so.coutinho.lucas.graph.Graph;
import so.coutinho.lucas.graph.Vertex;

/**
 *
 * @author Lucas
 */
public class BellmanFord extends ShortestPath {

    @Override
    public void run(Graph graph, Vertex source) {
        int numberOfVertices = graph.getNumberOfVertices();
        // Passo 1: Inicializar tabelas
        super.initializeAtributes(numberOfVertices);
        super.getWeight()[source.getId()] = 0;

        // Passo 2: Relaxar arestas repetidamente
        for (int i = 0; i < numberOfVertices - 1; i++) {
            relaxEdges(graph.getEdges());
        }

        // Passo 3: Verificar ciclos com weight negativos
        checkNegativeWeightCycles(graph);
    }

    private void checkNegativeWeightCycles(Graph graph) {
        for (Edge edge : graph.getEdges()) {
            Integer w = edge.getWeight();
            Vertex u = edge.getOrigin();
            Vertex v = edge.getTarget();

            Integer weightU = super.getWeight()[u.getId()],
                    weightV = super.getWeight()[v.getId()];

            if ((weightU + w) < weightV) {
                throw new RuntimeException("NegativeWeightCycleException");
            }
        }
    }

}
