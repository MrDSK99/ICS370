package com.example.inventory;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddItemsApplication extends Application {

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AddItemsApplication.class.getResource("AddItems-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("Inventory Management");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        Inventory inventory = Inventory.getInstance();

        //mock inventory set here
        inventory.addItem("apples", 22);
        inventory.addItem("bananas", 11);
        inventory.addItem("watermelons", 5);
        inventory.addItem("oranges", 19);
        inventory.addItem("mangoes", 9);
        inventory.addItem("carrots", 33);
        inventory.addItem("avocados", 14);
        inventory.addItem("onions", 12);
        inventory.addItem("potatoes", 25);
        inventory.addItem("peppers", 3);

        //launch the GUI
        launch();
    }
}