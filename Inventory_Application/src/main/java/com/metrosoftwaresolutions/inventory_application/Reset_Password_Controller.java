package com.metrosoftwaresolutions.inventory_application;

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

public class Reset_Password_Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private PasswordField confirmPasswordFiled;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private Label resetMessageLabel;

    @FXML
    private Button resetButton;

    @FXML
    private Button closeButton;

    @FXML
    private TextField usernameField;

    @FXML
    void resetButtonOnAction(ActionEvent event) {

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