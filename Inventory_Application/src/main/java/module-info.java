module com.metrosoftwaresolutions.inventory_application {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.metrosoftwaresolutions.inventory_application to javafx.fxml;
    exports com.metrosoftwaresolutions.inventory_application;
}