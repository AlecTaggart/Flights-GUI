
/** Represents one node in the HashTable. Each HashEntry contains the name of the city and the id.
 * If open hashing is used for resolving collisions, each HashEntry should also contain a
 * pointer to the next HashEntry.
 *
 */
public class HashEntry {

    HashEntry next;
    int id;
    String cityName;
    public HashEntry(String cityName, int id){
        this.next = null;
        this.cityName = cityName;
        this.id = id;
    }

    public HashEntry Next(){
        return this.next;
    }

    public void setNext(HashEntry next){
        this.next = next;
    }


    public String getCityName(){
        return cityName;
    }

    public int getId(){
        return id;
    }
}
