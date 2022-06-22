package com.example.javafx;

import com.example.database.DAL.Utilizador;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuStockManager implements Initializable {
    public ImageView back;
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

    public void onActionListTypeOfPreserves(javafx.event.Event event) throws IOException {
        Logic.changePanel(event, "confer_type_of_preserves-view.fxml", "Conserveira", ConferTypeOfPreservesController.class);
    }

    public void onActionListFich(javafx.event.Event event) throws IOException {
        userSession.in(getUser());
        Logic.changePanel(event, "list_fish-view.fxml", "Conserveira", ListFishController.class);
    }

}
