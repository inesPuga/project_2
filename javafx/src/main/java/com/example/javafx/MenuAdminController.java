package com.example.javafx;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class MenuAdminController {
    @FXML
    private ImageView back;
    private final UserSession userSession = UserSession.getInstance();

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

    public void onActionListUsers(javafx.event.Event event) throws IOException {
        Logic.changePanel(event, "list_users.fxml", "Conserveira", ListUsersController.class);
    }

    public void onActionListStateOrders(javafx.event.Event event) throws IOException {
        Logic.changePanel(event, "state_order-view.fxml", "Conserveira", StateOrderController.class);
    }

    public void onActionListStatePurchases(javafx.event.Event event) throws IOException {
        //Logic.changePanel(event, "state_order-view.fxml", "Conserveira", StateOrderController.class);
    }

}
