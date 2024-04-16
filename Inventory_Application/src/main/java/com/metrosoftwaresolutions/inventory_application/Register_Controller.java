package com.metrosoftwaresolutions.inventory_application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Register_Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button closeButton;

    @FXML
    private Button registerButton;

    @FXML
    private Label registrationMessageLabel;

    @FXML
    private PasswordField passwordPasswordFiled;

    @FXML
    private PasswordField reenterPasswordFiled;

    @FXML
    private Label reenterPasswordLabel;

    @FXML
    private TextField usernameFiled;

    @FXML
    public void registerButtonOnAction(ActionEvent event) {
        if (passwordPasswordFiled.getText().equals(reenterPasswordFiled.getText())) {
            registerUser();
            registrationMessageLabel.setText("Created new account successfully!");
        }else {
            reenterPasswordLabel.setText("Password does not match.");
        }
    }


// need database for registerUser, also you can modifier registerButtonOnAction if needed
    @FXML
    public void registerUser(){

    }

    @FXML
    private void backButton(ActionEvent event) throws IOException {
        //this code can be put in a method getScene(scene.fxml), used 3 times
        root = FXMLLoader.load(getClass().getResource("Login_View.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}