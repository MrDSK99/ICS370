package com.metrosoftwaresolutions.inventory_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Register_Controller {

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
    void registerButtonOnAction(ActionEvent event) {
        registrationMessageLabel.setText("Create new account successfully!");
        registerUser();
    }

    public void registerUser(){
        if (passwordPasswordFiled.getText().equals(reenterPasswordFiled.getText())) {
            reenterPasswordLabel.setText("Match");
        }else {
            reenterPasswordLabel.setText("Password does not match");
        }
    }

}
