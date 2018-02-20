
/** Edge class represents an Edge in the linked list of edges for a vertex.
 *
 */

class Edge {
    int neightbor;
    int cost;
    Edge next;
    public Edge(int neightbor, int cost, Edge next){
        this.neightbor = neightbor;
        this.cost = cost;
        this.next = next;
    }

    public Edge getNext(){
        return next;
    }

    public int getCost(){
        return cost;
    }

    public int getNeighbor(){
        return neightbor;
    }

    public void print(){
        System.out.println("Neighbor = " + neightbor + " cost = " + cost + " ");
    }

}
