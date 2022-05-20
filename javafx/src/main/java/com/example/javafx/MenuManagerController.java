package com.example.javafx;

import com.example.database.DAL.Utilizador;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuManagerController {
    Utilizador user;
    @FXML
    private Label label_hello;

    public MenuManagerController() {}

    public Utilizador getCurrentUser(javafx.event.ActionEvent event) {
        return ((Utilizador) ((Scene)((Node) event.getSource()).getScene()).getUserData());
    }

}
