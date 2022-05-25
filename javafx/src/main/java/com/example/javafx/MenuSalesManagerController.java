package com.example.javafx;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class MenuSalesManagerController {
    @FXML
    private ImageView back;

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
