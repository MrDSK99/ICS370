package com.metrosoftwaresolutions.inventory_application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main_Application extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main_Application.class.getResource("Login_View.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("Inventory Management");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        Inventory inventory = Inventory.getInstance();

        // Load existing inventory data from JSON file
        inventory.loadInventory();

        //start handler threads
//        Delivery_Handler delivery_handler = new Delivery_Handler();
//        new Thread(delivery_handler).start();
//        Purchase_Handler purchase_handler = new Purchase_Handler();
//        new Thread(purchase_handler).start();

        //launch the GUI
        launch();

        // Call saveInventory() method to save the updated inventory data
        Inventory.getInstance().saveInventory();
    }
}
