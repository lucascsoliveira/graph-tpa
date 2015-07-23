package so.coutinho.lucas.graph.methods.search;

import com.sun.java.accessibility.util.EventID;
import java.util.ArrayList;
import java.util.Collections;
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
public class DepthFirstSearch extends GraphSearch {
    
    private Integer[][] time;
    private List<Vertex> topologicalSorting;
    
    @Override
    public void walkingOnTheGraph(Graph graph) {
        initializeAtributes(graph.getNumberOfVertices());
        int time = 1;
        for (int index = 0; index < graph.getVertices().size(); index++) {
            if (super.getColor()[index] == 'B') {
                visitDFS(graph, index, time);
            }
        }
        
        Collections.reverse(topologicalSorting);
    }
    
    private int visitDFS(Graph graph, int index, int time) {
        List<Integer> indexAdjacents = new ArrayList();
        super.getColor()[index] = 'C';
        this.time[index][0] = time;

        // Monta uma lista de adjacentes de um no especifico
        // List<Vertex> adjacents = graph.getVertices().get(index).getAdjacents();
        List<Vertex> adjacents = graph.getAdjacents(graph.getVertex(index));

        /*
         Dado uma lista de nos adjacentes,
         monta um lista com os indices dos nos adjacentes
         */
        for (Vertex node : adjacents) {
            // int indexNode = graph.getIndexVertice(node);
            // if (indexNode != -1) {
            //     indexAdjacents.add(indexNode);
            // }
            indexAdjacents.add(node.getId());
        }

        /*
         Loop para recursao do metodo visita
         */
        for (int indexAdj : indexAdjacents) {
            if (super.getColor()[indexAdj] == 'B') {
                super.getPredecessor()[indexAdj] = graph.getVertices().get(index);
                time = visitDFS(graph, indexAdj, ++time);
            }
        }
        
        super.getColor()[index] = 'P';
        this.time[index][1] = ++time;
        topologicalSorting.add(graph.getVertex(index));
        
        return time;
    }
    
    @Override
    protected void initializeAtributes(Integer numberOfVertices) {
        super.initializeAtributes(numberOfVertices);
        
        time = new Integer[numberOfVertices][2];
        topologicalSorting = new ArrayList<>();
    }
    
}
