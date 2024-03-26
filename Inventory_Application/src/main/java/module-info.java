module com.metrosoftwaresolutions.inventory_application {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens com.metrosoftwaresolutions.inventory_application to javafx.fxml;
    exports com.metrosoftwaresolutions.inventory_application;
}