package nielson.c195projectmkn.helper;


import java.sql.Connection;
import java.sql.DriverManager;

public abstract class JDBC {

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcURL = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String userName = "sqlUser";
    private static final String password = "Passw0rd!";
    public static Connection connection;

    public static void openConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection Successful!");
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void closeConnection(){
        try {
           connection.close();
           System.out.println("Connection Closed!");
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }

    }
}
