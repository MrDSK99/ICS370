package demo.management;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

        @FXML
        private Button loginButton;

        @FXML
        private Label loginMessageLabel;
        @FXML
        private PasswordField passwordPasswordField;

        @FXML
        private CheckBox rememberCheckBox;

        @FXML
        private TextField usernameTextField;


        public void loginButtonAction(ActionEvent e) {
            if (usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false) {
                    loginMessageLabel.setText("You try to login!");
            }else {
                    loginMessageLabel.setText("Please enter name and password");
            }
        }


}