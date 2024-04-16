package com.metrosoftwaresolutions.inventory_application;

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
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;
import java.io.IOException;


public class Login_Controller{

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label loginMessageLabel;
    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private TextField usernameTextField;



// login Message will show if invalid input
    @FXML
    void loginButtonOnAction(ActionEvent event) throws IOException{
         loginMessageLabel.setText("Invalid account. Please try again.");
    }
 // you can do database here and if statement for password match


    // create an new Account
    public void registerForm(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Register_View.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    // reset password
    public void resetPassword(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Reset_Password_View.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}