package nielson.c195projectmkn.Models;

/**
 @author mckaykn
 The Month class represents a calendar month.
 */
public class Month {

    /**

     The ID of the month.
     */
    public int id;
    /**

     The name of the month.
     */
    public String name;
    /**

     Constructs a Month object with the given ID and name.
     @param id the ID of the month.
     @param name the name of the month.
     */
    public Month(int id, String name) {
        this.id = id;
        this.name = name;
    }
    /**

     Returns the name of the month.
     @return the name of the month.
     */
    public String getName() {
        return name;
    }
    /**

     Returns the ID of the month.
     @return the ID of the month.
     */
    public int getId() {
        return id;
    }
    /**

     Returns the name of the month as a string.
     @return the name of the month as a string.
     */
    @Override
    public String toString() {
        return name;
    }
}