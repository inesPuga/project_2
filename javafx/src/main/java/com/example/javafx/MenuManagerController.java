package com.example.javafx;

import com.example.database.DAL.Utilizador;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuManagerController implements Initializable {
    @FXML
    private Label label_username;
    @FXML
    private ImageView back;
    private final UserSession userSession = UserSession.getInstance();

    public MenuManagerController() {}

    public Utilizador getUsername() {
        return userSession.get();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label_username.setText(getUsername().getUsername());
    }

    public void backToLogin() {
        back.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    Logic.changePanel(event, "login-view.fxml", "Conserveira", LoginController.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}
