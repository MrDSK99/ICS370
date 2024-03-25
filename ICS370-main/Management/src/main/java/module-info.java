module demo.management {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens demo.management to javafx.fxml;
    exports demo.management;
}