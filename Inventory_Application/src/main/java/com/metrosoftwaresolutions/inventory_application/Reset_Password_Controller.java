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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Reset_Password_Controller {

    public Button backButton;

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
        String username = usernameField.getText();
        String newPassword = newPasswordField.getText();
        String confirmedPassword = confirmPasswordFiled.getText();

        if (!newPassword.equals(confirmedPassword)) {
            resetMessageLabel.setText("Passwords do not match.");
            return;
        }

        try (FileReader reader = new FileReader("users.json")) {
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);

            for (Object obj : jsonArray) {
                JSONObject userObject = (JSONObject) obj;
                String savedUsername = (String) userObject.get("username");

                if (username.equals(savedUsername)) {
                    userObject.put("password", newPassword);
                    updateJsonFile(jsonArray);
                    resetMessageLabel.setText("Password reset successful.");
                    return;
                }
            }
            resetMessageLabel.setText("Username not found.");
        } catch (IOException | ParseException e) {
            resetMessageLabel.setText("Error: Failed to read user data.");
            e.printStackTrace();
        }
    }

    private void updateJsonFile(JSONArray jsonArray) throws IOException {
        try (FileWriter file = new FileWriter("users.json")) {
            file.write(jsonArray.toJSONString());
        }
    }

    @FXML
    public void backButton (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login_View.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


}