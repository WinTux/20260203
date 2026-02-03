package com.pepe.j;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PrimaryController {
	@FXML
    private Button primaryButton;
	@FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;
    @FXML
    private void switchToSecondary() throws IOException {
        String usr, pwd;
        usr = txtUsername.getText();
    	pwd = txtPassword.getText();
    	if(usr.equals("Pepe")&& pwd.equals("Patito123"))
    		App.setRoot("secondary");
    }
    @FXML
    void cambioAmayuscula(MouseEvent event) {
    	primaryButton.setText("INGRESAR");
    }

    @FXML
    void cambioAminuscula(MouseEvent event) {
    	primaryButton.setText("Ingresar");
    }
}
