module nielson.c195projectmkn {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires javafx.graphics;


    opens nielson.c195projectmkn to javafx.fxml;
    exports nielson.c195projectmkn;
    exports nielson.c195projectmkn.Controllers;
    exports nielson.c195projectmkn.Models;
    exports nielson.c195projectmkn.helper;
    opens nielson.c195projectmkn.Controllers to javafx.fxml;
    opens nielson.c195projectmkn.Models to javafx.base;
}