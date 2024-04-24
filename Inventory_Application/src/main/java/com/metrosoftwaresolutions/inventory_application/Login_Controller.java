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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
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


    private void navigateToHomeScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Home_Screen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    void loginButtonOnAction(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordPasswordField.getText();

        try (FileReader reader = new FileReader("users.json")) {
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);

            for (Object obj : jsonArray) {
                JSONObject userObject = (JSONObject) obj;
                String savedUsername = (String) userObject.get("username");
                String savedPassword = (String) userObject.get("password");

                if (username.equals(savedUsername) && password.equals(savedPassword)) {
                    // Proceed to main application window
                    navigateToHomeScreen(event);
                    return;
                }
            }
            loginMessageLabel.setText("Invalid username or password.");
        } catch (IOException | ParseException e) {
            loginMessageLabel.setText("Error: Failed to read user data.");
            e.printStackTrace();
        }
    }



    public void registerForm(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Register_View.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void resetPassword(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Reset_Password_View.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}