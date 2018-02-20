
import java.util.LinkedList;

/** A HashTable that stores an array of HashEntry objects.
 * You may use open or closed hashing to resolve collisions. */
public class HashTable {
    // FILL IN CODE: you need to store the size and the array
    private HashEntry array[];
    int size;

    public HashTable(int size){
        this.size = size;
        array = new HashEntry[size];

    }

    /** Returns the hash value for a given key */
    public int hash(String key){

        int hashValue = 0;
        int r = 33;
        char c;

        for(int i = 0; i < key.length();i++){
            c = key.charAt(i);
            hashValue = (int) c + r*hashValue;
        }

        return Math.abs(hashValue) % size;
    }

    public void print(){
        for(int i = 0; i <size;i++) {
            System.out.print("Array = " + i + ": ");
            if(array[i] != null){
                HashEntry temp = array[i];
                while(temp != null){
                    System.out.print("(" + temp.getCityName() + "," + temp.getId() + ") ");
                    temp = temp.Next();
                }
                System.out.println();
            }else{
                System.out.println("EMPTY HASH LOCATION");
            }
        }
    }

    public HashEntry getCity(String city){
        int hashValue = hash(city);
        //System.out.println("HASH VALUE FOR GET CITY = " + hashValue);
        HashEntry current = array[hashValue];
        while(current!=null){
            if(current.getCityName().equals(city)){
                break;
            }else{
                current = current.Next();
            }
        }
        return current;

    }



    public boolean contains(String city){
        //return true if hash table already has that city
        int hash = hash(city);
        HashEntry temp = array[hash];
        if(array[hash] ==  null){
            return false;
        }else{
            while(temp != null){
                if(temp.getCityName().equals(city)){
                    return true;
                }else{
                    temp = temp.Next();
                }
            }
        }
        return false;
    }


    /** Inserts a HashNode with the given city name and id into the hash table */
    public void insert(String cityName, int id){
        int hash = hash(cityName);
        if(this.contains(cityName)){

        }else{
            HashEntry entry = new HashEntry(cityName, id);
            if(array[hash] == null){
                array[hash] = entry;
            }else{
                HashEntry temp = array[hash];
                while(temp.Next() != null){
                    temp = temp.Next();
                }
                temp.setNext(entry);
            }
        }
    }


}
