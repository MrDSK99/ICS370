package com.metrosoftwaresolutions.inventory_application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
         loginMessageLabel.setText("Invalid.Please try again");
    }
 // you can do database here and if statement for password match




    // create an new Account
    public void registerForm(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("Register_View.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 520, 430));
            registerStage.show();

        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    // reset password
    public void resetPassword(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("ResetPassword.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 520, 430));
            registerStage.show();

        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
