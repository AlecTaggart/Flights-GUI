
/** The Priority Queue class to be used in Dijkstra's algorithm */
public class PriorityQueue {

    // FILL IN THE CODE : you need to store your min heap here
    private PriorityNode[] Heap;
    private int[] indexArray;
    private int maxsize;
    private int size;


    public PriorityQueue(int maxsize){
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new PriorityNode[maxsize+1];
        Heap[0] = new PriorityNode(0, Integer.MIN_VALUE);
        indexArray = new int[maxsize+1];
        indexArray[0] = Integer.MIN_VALUE;
    }

    private int leftchild(int pos) {
        return 2 * pos;
    }

    private int rightchild(int pos) {
        return 2 * pos + 1;
    }

    private int parent(int pos) {
        return pos / 2;
    }

    private boolean isLeaf(int pos) {
        return ((pos > size / 2) && (pos <= size));
    }

    private void swap(int pos1, int pos2) {

        PriorityNode tmp;
        if(pos1 < 0 || pos2 < 0 || Heap[pos1].getVertexId() < 0 || Heap[pos2].getVertexId() < 0){
            return;
        }
        indexArray[Heap[pos1].getVertexId()] = pos2;
        indexArray[Heap[pos2].getVertexId()] = pos1;
        tmp = Heap[pos1];
        Heap[pos1] = Heap[pos2];
        Heap[pos2] = tmp;
    }


    public void print() {
        int i;
        for (i = 1; i <= size; i++)
            System.out.print("(" + Heap[i].getVertexId() + "," + Heap[i].getPriority()+ ")");


        System.out.println(" ");
        for (i = 0; i < size; i++) {

                System.out.print("(" + i + "," + indexArray[i] + ") ");
        }
        System.out.println();
    }

    public boolean isEmpty(){
        if(size!= 0){
            return false;

        }else{
            return true;
        }
    }

    private void pushdown(int position) {
        int smallestchild;
        while (!isLeaf(position)){
            smallestchild = leftchild(position);
            if((smallestchild < size) && (Heap[smallestchild].getPriority() > Heap[smallestchild + 1].getPriority()))
                smallestchild = smallestchild + 1;
            if(smallestchild > size || Heap[position].getPriority() <= Heap[smallestchild].getPriority())
                return;

            swap(position, smallestchild);
            position = smallestchild;
        }
    }



    /**
     * Method that takes vertex id and priority and adds it to the min heap
     *
     * @param vertexId to be added (vertexId) fr
     *
     * @param priority of the element
     */
    public void insert(int vertexId, int priority){
        size++;
        PriorityNode insert = new PriorityNode(vertexId, priority);
        Heap[size] = insert;
        indexArray[insert.getVertexId()] = size;
        int current = size;
        while (Heap[current].getPriority() < Heap[parent(current)].getPriority()) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    /**
     * Method that removes the min element from the heap.
     *
     * @return the min element from the heap (vertexID with the smallest priority)
     */
    public int removeMin(){

        swap(1, size);

        size--;
        if (size != 0)
            pushdown(1);


        return Heap[size + 1].getVertexId();
    }

    /**
     * Method that takes vertex id and newPriority. It sets the priority to the new priority.
     *
     * Needs to update the min heap accordingly
     */

    public void reduceKey(int vertexId, int newPriority) {


        int index = indexArray[vertexId];
        int prevPriority = Heap[index].getPriority();
        Heap[index].setPriority(newPriority);
        if(newPriority > prevPriority)
            this.pushdown(index);
        else{
            int parentIndex = this.parent(index);
            while(Heap[parentIndex].getPriority() > newPriority){
                swap(parentIndex, index);
                index = parentIndex;
                parentIndex = parent(index);
            }
        }
    }
}
