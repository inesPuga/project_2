package com.example.javafx;

import com.example.database.DAL.Utilizador;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuManagerController implements Initializable {
    @FXML
    public ImageView back;
    @FXML
    public Label label_username;
    private final UserSession userSession = UserSession.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label_username.setText(getUser().getUsername());
    }

    public Utilizador getUser() {
        return userSession.get();
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

    public void onActionListOrders(javafx.event.Event event) throws IOException {
        Logic.changePanel(event, "list_orders-view.fxml", "Conserveira", ListOrdersController.class);
    }

    public void onActionListTypeOfPreserves(javafx.event.Event event) throws IOException {
        Logic.changePanel(event, "confer_type_of_preserves-view.fxml", "Conserveira", ConferTypeOfPreservesController.class);
    }

    public void onActionListFish(javafx.event.Event event) throws IOException {
        Logic.changePanel(event, "list_fish-view.fxml", "Conserveira", ListFishController.class);
    }

    public void onActionConferLots(javafx.event.Event event) throws IOException {
        Logic.changePanel(event, "confer_lots-view.fxml", "Conserveira", ConferLotsController.class);
    }

    public void onActionListEmployees(javafx.event.Event event) throws IOException {
        Logic.changePanel(event, "list_employees-view.fxml", "Conserveira", ListEmployeesController.class);
    }

}
