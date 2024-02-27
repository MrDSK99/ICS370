module com.example.prototype_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.prototype_1 to javafx.fxml;
    exports com.example.prototype_1;
}