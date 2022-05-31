package com.example.javafx;

import com.example.database.BLL.UtilizadorBLL;
import com.example.database.DAL.Utilizador;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;

import static javax.xml.bind.DatatypeConverter.parseInt;

public class EditUserController implements Initializable {
    @FXML
    private TextField username_field;
    @FXML
    private TextField name_field;
    @FXML
    private TextField phone_field;
    @FXML
    private Button confirmButton;
    private final UserSession userSession = UserSession.getInstance();

    public void onConfirmButtonClick(javafx.event.ActionEvent event) {
        Utilizador user = userSession.get();
        user.setNumtel(Integer.parseInt(phone_field.getText()));
        user.setNome(name_field.getText());
        user.setUsername(username_field.getText());
        UtilizadorBLL.update(user);
        try {
            Logic.changePanel(event, "list_users.fxml", "Conserveira", ListUsersController.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Utilizador user = userSession.get();
        username_field.setText(user.getUsername());
        name_field.setText(user.getNome());
        phone_field.setText(String.valueOf(user.getNumtel()));
    }

}
