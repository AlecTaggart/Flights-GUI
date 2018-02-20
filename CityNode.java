
/** A class that represents a node of the USAGraph. Contains the name of the city and
 * the location on the "map".
 */
import java.awt.Point;

public class CityNode {
    private final String city;
    private Point location;

    /**g
     * Call this method when you are reading the graph file to create a node.
     * A constructor for the class.
     * @param cityName
     *            cityName
     * @param x
     *            x
     * @param y
     *            y
     */
    public CityNode(String cityName, double x, double y) {

        this.city = cityName;
        int xint = (int) (507 * x / 7.0);
        int yint = (int) (289 - 289 * y / 4.0);
        this.location = new Point(xint, yint);
    }

    public Point getLocation() {
        return location;
    }

    public String getCity() {
        return city;
    }

}
