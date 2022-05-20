package com.example.javafx;

import com.example.database.DAL.Utilizador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuAdminController implements Initializable {
    //Utilizador user;
    Logic logic = new Logic();
    @FXML
    private Button createAccount;
    @FXML
    private Button listusers;

    public MenuAdminController() {}

    //create account
    public void onCreateAccountButtonClick(javafx.event.ActionEvent event) throws IOException {
        Logic.changePanel(event, "create_user-view.fxml", "Conserveira", CreateUserController.class);
        Utilizador user = ((Utilizador) ((Scene)((Node) event.getSource()).getScene()).getUserData());
        System.out.println(user.getNome());
    }

    //list users
    public void onListUsersButtonClick(javafx.event.ActionEvent event) throws IOException {
        Logic.changePanel(event, "list_users-view.fxml", "Conserveira", ListUsersController.class);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
