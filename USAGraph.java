
/** A class representing a graph. Stores the array of nodes and the adjacency list.
 *
 */
import sun.security.provider.certpath.AdjacencyList;

import java.awt.Point;
import java.util.Iterator;

public class USAGraph {

    private CityNode[] nodes;

    // for each vertex store a linked list of edges
    private Edge[] adjacencyList;

    private int maxNumNodes; // the maximum number of nodes in the graph
    private int numNodes = 0; // how many nodes have been added to the graph so
    // far
    private int numEdges = 0; // how many edges have been added to the graph so
    // far

    public final int EPS_DIST = 5; // how close to the actual location of the
    // city the user has to click to select it
    // on the image

    public USAGraph(int maxNumNodes) {
        this.maxNumNodes = maxNumNodes;
        // FILL IN CODE
        // Allocate memory for the nodes array and the adjacencyList array
        nodes = new CityNode[maxNumNodes];
        adjacencyList = new Edge[maxNumNodes];

        // Note: do not assign a value to numNodes. It should be incremented in
        // the addNode() method.
    }

    public void printEdges(){
        for(int e = 0; e < numNodes; e++) {
            Edge temp = adjacencyList[e];
            System.out.println(" ");
            System.out.println("Location = " + e +": ");
            while(temp != null) {
                temp.print();
                temp = temp.getNext();
            }

        }
    }

    /**
     * Returns a node with index i
     */
    public CityNode getNode(int i) {
        // FILL IN CODE

        return nodes[i];
    }

    /**
     * Returns the head of the linked list of edges for the vertex with id = i
     */
    public Edge getEdge(int i) {

        return adjacencyList[i];
    }

    /**
     * Adds a node to the graph
     *
     * @param node
     */
    public void addNode(CityNode node) {
        nodes[numNodes++] = node;

    }

    public int numNodes() {

        return numNodes;
    }

    /**
     * Adds the edge to the linked list for this vertexId
     *
     * @param nodeId
     * @param edge
     */
    public void addEdge(int nodeId, Edge edge) {
        // FILL IN CODE
        if(adjacencyList[nodeId] == null){
            adjacencyList[nodeId] = edge;
            numEdges++;
        }else{
            Edge temp = adjacencyList[nodeId];
            edge.next = temp;
            adjacencyList[nodeId] = edge;
            numEdges++;

        }
    }

    /**
     * Given the location of the click, returns the node of the graph at this
     * location.
     */
    public CityNode getVertex(Point loc) {
        for (CityNode v : nodes) {
            Point p = v.getLocation();
            if ((Math.abs(loc.x - p.x) < EPS_DIST) && (Math.abs(loc.y - p.y) < EPS_DIST))
                return v;
        }
        return null;
    }

    /**
     * Returns the iterator over the node locations. Used by the GUIApp class to
     * draw little circles at the locations of the nodes, as well as for drawing
     * the path.
     */
    public Iterator<Point> getNodeLocations() {
        return new NodeIterator();
    }

    /** The inner class, the iterator over node locations */
    private class NodeIterator implements Iterator<Point> {
        private int nodeId;

        public NodeIterator() {
            nodeId = 0;
        }

        @Override
        public boolean hasNext() {
            if (nodeId < numNodes)
                return true;
            else
                return false;
        }

        @Override
        public Point next() {
            // FILL IN CODE:
            // if hasNext returns false, print an error message and return null
            // Otherwise return the location of the "current" node ; increment nodeId
            if(!hasNext()){
                System.out.println("ERR: no next node available");
                return null;
            }else{
                return nodes[nodeId++].getLocation();

            }

        }

    } // end of the private class NodeIterator

    /**
     * Returns the array of all edges for drawing: each element in the array
     * corresponds to one edge and is the array of two Point objects
     * (corresponding to the locations of the two nodes connected by this edge).
     */
    public Point[][] getEdges() {
        Point[][] edges = new Point[numEdges][2];
        // FILL IN CODE
        Point pointOne;
        Point pointTwo;

        int i = 0;


        for(int e = 0; e < numNodes; e++) {
            Edge temp = adjacencyList[e];
            while(temp != null) {

                pointOne = nodes[e].getLocation();
                edges[i][0] = pointOne;
                pointTwo = nodes[temp.getNeighbor()].getLocation();
                edges[i][1] = pointTwo;
                temp = temp.getNext();

                i++;
            }

        }
        return edges;

    }

    /**
     * Returns the array of cities corresponding to the vertices of this graph
     * in the array
     *
     * @return
     */
    public String[] getCities() {
        String[] labels = new String[numNodes];
        for (int i = 0; i < numNodes;i++){
            labels[i] = nodes[i].getCity();
        }
        return labels;
    }

} // class MapGraph
