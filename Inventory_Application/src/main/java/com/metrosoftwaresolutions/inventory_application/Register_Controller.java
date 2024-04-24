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

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;


public class Register_Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private PasswordField reenterPasswordField;


    @FXML
    private Button registerButton;

    @FXML
    private Label registrationMessageLabel;

    @FXML
    private TextField usernameField;


    @FXML
    public void registerButtonOnAction(ActionEvent event) {

        String username = usernameField.getText();
        String password = passwordPasswordField.getText();
        String reenteredPassword = reenterPasswordField.getText();

        if (!password.equals(reenteredPassword)) {
            registrationMessageLabel.setText("Passwords do not match.");
            return;
        }

        JSONArray userList = new JSONArray();

        // Read existing user data from the file if it exists
        try (FileReader reader = new FileReader("users.json")) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(reader);
            if (obj instanceof JSONArray) {
                userList = (JSONArray) obj;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        // Create JSON object for the new user
        JSONObject newUser = new JSONObject();
        newUser.put("username", username);
        newUser.put("password", password);

        // Append the new user to the existing user list
        userList.add(newUser);

        // Write the updated user list back to the file
        try (FileWriter file = new FileWriter("users.json")) {
            file.write(userList.toJSONString());
            registrationMessageLabel.setText("Registration successful.");
        } catch (IOException e) {
            registrationMessageLabel.setText("Error: Registration failed.");
            e.printStackTrace();
        }
    }

    @FXML
    void backButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login_View.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}


