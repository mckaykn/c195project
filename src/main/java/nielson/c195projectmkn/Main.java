package nielson.c195projectmkn;

import nielson.c195projectmkn.helper.ClientQuery;
import nielson.c195projectmkn.helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LogInForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 250);
        stage.setTitle("Log-In Form");
        stage.setScene(scene);
        //Locale.setDefault(Locale.CANADA_FRENCH);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        JDBC.openConnection();
//        ClientQuery.select();
//        Locale initial = Locale.getDefault();
//        System.out.println(initial);

//        Locale.setDefault(Locale.CANADA_FRENCH);
//        System.out.println(Locale.CANADA_FRENCH);

//        Locale.setDefault(initial);
//        System.out.println(Locale.getDefault());
//        int rowsAffected = FruitsQuery.delete(7);
//        if(rowsAffected > 0){
//            System.out.println("Delete Successful");
//        }
//        else{
//            System.out.println("Delete Failed!");
//        }
        launch();
        JDBC.closeConnection();
    }
}