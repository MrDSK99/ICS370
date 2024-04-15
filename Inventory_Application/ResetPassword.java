package com.metrosoftwaresolutions.inventory_application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ResetPassword {

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
    public void closeButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }
}


