package com.metrosoftwaresolutions.inventory_application;

import javafx.application.Application;
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


public class Login_Controller extends Application {
    @FXML
    private Label loginMessageLabel;
    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private TextField usernameTextField;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login_View.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root, 520, 400));
        stage.show();

    }
// login action button is not working yet
    @FXML
    void loginButtonOnAction(ActionEvent event) throws IOException{
         loginMessageLabel.setText("Invalid.Please try again");
    }
// database here



// create Account Form
    public void accountForm(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("Register_View.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 520, 400));
            registerStage.show();

        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
