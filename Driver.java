
public class Driver {

    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue(10);
        queue.insert(1, 1);
        queue.insert(2, 2);
        queue.insert(3, 3);
        queue.insert(4, 4);
        queue.insert(5, 5);
        queue.insert(6, 6);
        queue.insert(7, 7);
        System.out.println("Check to see if heap is stored correctly in index array");
        queue.print();

//        System.out.println("Remove min");
//        queue.removeMin();
//        queue.print();
//
//        System.out.println("Remove min");
//        queue.removeMin();
//        queue.print();


        System.out.println("Reduce id = 3 to priority 8");
        queue.reduceKey(3, 8);
        queue.print();

//        System.out.println("Reduce id = 1 to priority 22");
//        queue.reduceKey(1, 7);
//        queue.print();




       HashTable table = new HashTable(7);
       table.insert("temp", 1);
       table.insert("temp2", 2);
       table.insert("temp3", 3);
       table.insert("temp4", 4);
       table.insert("temp5", 5);
       table.insert("temp6", 6);
       table.insert("temp7", 7);
       table.print();



    }
}
