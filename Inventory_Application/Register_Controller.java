package com.metrosoftwaresolutions.inventory_application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Register_Controller {
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
            registrationMessageLabel.setText("Create new account successfully!");
        }else {
            reenterPasswordLabel.setText("Password does not match");
        }
    }


// need database for registerUser, also you can modifier registerButtonOnAction if needed
    public void registerUser(){

    }



    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

}
