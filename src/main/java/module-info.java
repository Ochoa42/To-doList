module com.example.tolist {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.tolist to javafx.fxml;
    exports com.example.tolist;
    exports Controllers;
    opens Controllers to javafx.fxml;
}