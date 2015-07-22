package so.coutinho.lucas.graph.methods.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class BreadthFirstSearch extends GraphSearch {

    private Integer[] distances;

    @Override
    public void walkingOnTheGraph(Graph graph) {
        initializeAtributes(graph.getNumberOfVertices());

        for (int vertex = 0; vertex < graph.getNumberOfVertices(); vertex++) {
            if (super.getColor()[vertex] == 'B') {
                visitBFS(graph, vertex);
            }
        }
    }

    private void visitBFS(Graph graph, int index) {
        //ListQ é a variavel que limita enquanto o algoritmo ira rodar
        List<Integer> listaQ = new ArrayList<>();

        super.getColor()[index] = 'C';
        this.distances[index] = 0;
        listaQ.add(index);

        while (!listaQ.isEmpty()) {
            List<Integer> indexAdjacents = new ArrayList<>();

            int verticeU = listaQ.get(0);
            listaQ.remove(0);

            List<Vertex> adjacents = graph.getAdjacents(graph.getVertex(verticeU));

            /*
             Dado uma lista de nos adjacentes,
             monta um lista com os indices dos nos adjacentes
             */
            for (int i = 0; i < adjacents.size(); i++) {
                //int indexNode = graph.getIndexVertice(adjacents.get(i));
                //if (indexNode != -1) {
                //    indexAdjacents.add(indexNode);
                //}
                indexAdjacents.add(adjacents.get(i).getId());
            }

            for (int i = 0; i < indexAdjacents.size(); i++) {
                if (super.getColor()[indexAdjacents.get(i)] == 'B') {
                    super.getColor()[indexAdjacents.get(i)] = 'C';
                    this.distances[indexAdjacents.get(i)] = this.distances[verticeU] + 1;
                    super.getPredecessor()[indexAdjacents.get(i)] = graph.getVertices().get(verticeU);
                    listaQ.add(indexAdjacents.get(i));
                }
            }
        }
    }

    @Override
    protected void initializeAtributes(Integer numberOfVertices) {
        super.initializeAtributes(numberOfVertices);

        distances = new Integer[numberOfVertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
    }

}
