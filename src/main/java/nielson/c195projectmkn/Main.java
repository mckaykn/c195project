package nielson.c195projectmkn;

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
        if (Locale.getDefault() == Locale.ENGLISH) {
            stage.setTitle("Log-In Form");
        }
        if (Locale.getDefault() == Locale.FRENCH) {
            stage.setTitle("Connexion");
        }
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        JDBC.openConnection();
        launch();
        JDBC.closeConnection();
    }
}