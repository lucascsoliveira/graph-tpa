package so.coutinho.lucas.graph;

import java.io.File;
import java.io.FileNotFoundException;
import so.coutinho.lucas.graph.methods.minimumspanningtree.PrimJarnik;
import so.coutinho.lucas.graph.methods.search.BreadthFirstSearch;
import so.coutinho.lucas.graph.methods.search.DepthFirstSearch;
import so.coutinho.lucas.graph.methods.shortestpath.BellmanFord;
import so.coutinho.lucas.graph.methods.shortestpath.DirectedAcyclicGraph;

/**
 *
 * @author Lucas
 */
public class App {

    public static void main(String[] args) throws FileNotFoundException {
//        Graph graph = Graph.buildFromFile(new File("src/main/resources/input.txt"));
//        Graph graph = Graph.buildFromFile(new File("src/main/resources/gt1_10v.txt"));
//        Graph graph = Graph.buildFromFile(new File("src/main/resources/gt2_5v.txt"));
        Graph graph = Graph.buildFromFile(new File("src/main/resources/gt3_10vA.txt"));
        Integer index;

//        BreadthFirstSearch bfs = new BreadthFirstSearch();
//        bfs.walkingOnTheGraph(graph);
//        System.out.println(" ############# BFS ##############");
//        //IMPRIMIR PREDECESSORES | DISTANCIAS
//        index = 0;
//        System.out.println("DISTANCIAS");
//        for (Integer integer : bfs.getDistances()) {
//            System.out.println(index++ + "\t" + integer);
//        }
//
//        index = 0;
//        System.out.println("PREDECESSORES");
//        for (Vertex vertex : bfs.getPredecessor()) {
//            System.out.println(index++ + "\t" + vertex);
//        }
//
//        DepthFirstSearch dfs = new DepthFirstSearch();
//        dfs.walkingOnTheGraph(graph);
//        System.out.println(" ############# DFS ##############");
//        //IMPRIMIR TEMPO DE DESCOBERTA | TEMPO DE TERMINO | PREDECESSORES
//        System.out.println("TEMPOS");
//        for (index = 0; index < graph.getNumberOfVertices(); index++) {
//            System.out.println(index + "\t" + dfs.getTime()[index][0] + "\t" + dfs.getTime()[index][1]);
//        }
//
//        index = 0;
//        System.out.println("PREDECESSORES");
//        for (Vertex vertex : dfs.getPredecessor()) {
//            System.out.println(index++ + "\t" + vertex);
//        }
//
//        BellmanFord bel = new BellmanFord();
//        bel.run(graph, 0);
//        System.out.println(" ########## BELLMAN-FORD ########");
//        //IMPRIMIR PREDECESSORES | PESOS
//        index = 0;
//        System.out.println("PREDECESSORES");
//        for (Vertex vertex : bel.getPredecessor()) {
//            System.out.println(index++ + "\t" + vertex);
//        }
//
//        index = 0;
//        System.out.println("PESOS");
//        for (Integer integer : bel.getWeight()) {
//            System.out.println(index++ + "\t" + integer);
//        }
//
//        DirectedAcyclicGraph dag = new DirectedAcyclicGraph();
//        dag.run(graph, 0);
//        System.out.println(" #### DIRECTED ACYCLIC GRAPH ###");
//        //IMPRIMIR PREDECESSORES | PESOS
//        index = 0;
//        System.out.println("PREDECESSORES");
//        for (Vertex vertex : dag.getPredecessor()) {
//            System.out.println(index++ + "\t" + vertex);
//        }
//
//        index = 0;
//        System.out.println("PESOS");
//        for (Integer integer : dag.getWeight()) {
//            System.out.println(index++ + "\t" + integer);
//        }
//
        PrimJarnik pj = new PrimJarnik();
        pj.run(graph, 0);
        System.out.println(" ####       PRIM-JARNIK      ###");
        //IMPRIMIR PREDECESSORES | CUSTOS
        index = 0;
        System.out.println("PREDECESSORES");
        for (Vertex vertex : pj.getPredecessor()) {
            String indexLabel = graph.getVertex(index++).getLabel();
            String vertexLabel = "-";
            if (vertex != null) {
                vertexLabel = vertex.getLabel();
            }

            System.out.println(indexLabel + "\t" + vertexLabel);
        }

        index = 0;
        System.out.println("CUSTOS");
        for (Integer integer : pj.getCost()) {
            String indexLabel = graph.getVertex(index++).getLabel();
            System.out.println(indexLabel + "\t" + integer);
        }

    }

}
