
import java.util.ArrayList;
import java.io.*;
import java.util.Collection;
import java.util.Collections;


public class Dijkstra {

    // FILL IN CODE: add other instance variables

    private int sourceVertex = -1; // store the id of the source vertex
    private USAGraph graph;
    private HashTable table;
    int[] path;
    int[] dist;
    int[] known;
    int max = Integer.MAX_VALUE;
    public Dijkstra(String filename) {
        loadGraph(filename);
    }

    public USAGraph getGraph() {
        return graph;
    }

    /**
     * Compute all the shortest paths from the source vertex to all the other
     * vertices in the graph; This function is called from GUIApp, when the user
     * clicks on the city.
     */
    public void computePaths(CityNode vSource) {
        System.out.println("\nSource = " + vSource.getCity() + " " + table.getCity(vSource.getCity()).getId());
        System.out.println(" ");
        path = new int[graph.numNodes()];
        dist = new int[graph.numNodes()];
        known = new int[graph.numNodes()];


        for(int i = 0; i < graph.numNodes(); i++){
            path[i] = -1;
            dist[i] = max;
            known[i] = 0;
        }

        dist[table.getCity(vSource.getCity()).getId()] = 0;
        PriorityQueue Q = new PriorityQueue(graph.numNodes());

        for(int j = 0; j < graph.numNodes(); j++){
            int id = table.getCity(graph.getNode(j).getCity()).getId();
            int distance = dist[id];
            Q.insert(id, distance);
        }


//        Q.print();
//        System.out.println("After initial insertion into PQueue ^^^^^\n");

        while(!Q.isEmpty()){
            int v = Q.removeMin();
            //System.out.println("v after remove min = " + v);
            known[v] = 1;
            Edge temp = graph.getEdge(v);

            while(temp != null){

                int tempCost = temp.getCost();
                int neighborDist = dist[temp.getNeighbor()];
                int tempDist = dist[v];
                //System.out.println("tempDist = "+tempDist + " at location = " + v);
                if(neighborDist > tempCost + tempDist) {
                    dist[temp.getNeighbor()] = tempDist + tempCost;
                    Q.reduceKey(temp.getNeighbor(), tempCost + tempDist);
                    path[temp.getNeighbor()] = v;
                }
                temp = temp.getNext();
            }
        }
//        System.out.println("path: ");
//        for(int i =0; i < path.length;i++){
//            System.out.print(path[i]+ ", ");
//        }
//        System.out.println("\ndist: ");
//        for(int i =0; i < dist.length;i++){
//            System.out.print(dist[i]+ ", ");
//        }
    }

    /**
     * Returns the shortest path between the source vertex and this vertex.
     * Returns the array of node id-s on the path
     */
    public ArrayList<Integer> shortestPath(CityNode vTarget) {
        // FILL IN CODE
        // called from the GUIApp class
        //String cityName = vTarget.getCity();
        int id = table.getCity(vTarget.getCity()).getId();
        ArrayList<Integer> paths = new ArrayList<Integer>();
        paths.add(id);
        while(path[id] != sourceVertex){
            paths.add(path[id]);
            id = path[id];
        }
        Collections.reverse(paths);
        //System.out.println(paths.toString());
        return paths;
    }

    /**
     * Loads graph info from the text file into USAGraph
     *
     * @param filename
     */
    public void loadGraph(String filename) {
        // FILL IN CODE
        String sCurrentLine;
        FileReader fileReader;
        BufferedReader reader;
        try{
            fileReader = new FileReader(filename);
            reader = new BufferedReader(fileReader);
        }catch(FileNotFoundException e){
            System.err.println("file cannot be found");
            return;
        }
        try{
            sCurrentLine = reader.readLine();
            sCurrentLine = reader.readLine();
            int num = Integer.parseInt(sCurrentLine);
            graph = new USAGraph(num);
            table = new HashTable(num);


            for(int i = 0; i<num;i++){
                sCurrentLine = reader.readLine();
                //System.out.println(sCurrentLine);
                String[] infoLine = sCurrentLine.split(" ");
                CityNode city = new CityNode(infoLine[0],Double.parseDouble(infoLine[1]), Double.parseDouble(infoLine[2]));
                graph.addNode(city);
                table.insert(infoLine[0], i);
            }
            sCurrentLine = reader.readLine();
            while((sCurrentLine = reader.readLine()) != null){
                String[] infoLine = sCurrentLine.split(" ");
                int orginId = table.getCity(infoLine[0]).getId();
                int destId = table.getCity(infoLine[1]).getId();
                int cost = Integer.parseInt(infoLine[2]);
                Edge edgeOne = new Edge(orginId, cost, null);
                Edge edgeTwo = new Edge(destId, cost, null);

                graph.addEdge(destId, edgeOne);
                graph.addEdge(orginId, edgeTwo);
            }
//            System.out.println("EDGES PRINT::::::::::::::::::::");
//            graph.printEdges();
//            System.out.println("TABLE PRINT::::::::::::::::::::");
//            table.print();
//            reader.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // Create an instance of the Dijkstra class
        // The parameter is the name of the file
        Dijkstra dijkstra = new Dijkstra(args[0]);

        // create the GUI window with the panel
        GUIApp app = new GUIApp(dijkstra);

    }
}
