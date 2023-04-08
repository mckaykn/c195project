package nielson.c195projectmkn.Models;

public class Month {
    public int id;
    public String name;
    public Month(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
