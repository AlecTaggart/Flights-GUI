
public class PriorityNode {

    int vertex;
    int priority;

    public PriorityNode(int vertexId, int priority){
        this.vertex = vertexId;
        this.priority = priority;
    }

    public int getVertexId(){
        return vertex;
    }

    public int getPriority(){
        return priority;
    }

    public void setVertexId(int value){
        this.vertex = value;
    }

    public void setPriority(int value){
        this.priority = value;
    }
}
