package so.coutinho.lucas.graph;

import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author Lucas
 */
public class App {

    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = Graph.buildFromFile(new File("src/main/resources/input.txt"));
    }

}
