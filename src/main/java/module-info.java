module nielson.c195project {
    requires javafx.controls;
    requires javafx.fxml;


    opens nielson.c195project to javafx.fxml;
    exports nielson.c195project;
    exports nielson.c195project.Controllers;
    opens nielson.c195project.Controllers to javafx.fxml;
}