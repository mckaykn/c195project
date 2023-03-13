package nielson.c195projectmkn;

import nielson.c195projectmkn.helper.FruitsQuery;
import nielson.c195projectmkn.helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginform.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 250);
        stage.setTitle("Log-In Form");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        JDBC.openConnection();
        FruitsQuery.select(3);
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