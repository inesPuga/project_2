package com.example.javafx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangeTPController implements Initializable {
    @FXML
    private TextField namef;
    @FXML
    private TextField pricef;
    @FXML
    private TextField stockf;
    @FXML
    private TextField descf;
    private final UserSession userSession = UserSession.getInstance();

    public void onConfirmButtonClick() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
