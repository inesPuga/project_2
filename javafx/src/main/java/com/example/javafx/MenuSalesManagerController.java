package com.example.javafx;

import com.example.database.BLL.EncomendaBLL;
import com.example.database.BLL.TipoconservaBLL;
import com.example.database.DAL.Encomenda;
import com.example.database.DAL.Tipoconserva;
import com.example.database.DAL.Utilizador;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuSalesManagerController implements Initializable {
    @FXML
    private ImageView back;
    @FXML
    private Label label_username;
    private final UserSession userSession = UserSession.getInstance();

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label_username.setText(getUser().getUsername());
    }

    public void order(javafx.event.Event event) throws IOException {
        Logic.changePanel(event, "list_orders-view.fxml", "Conserveira", ListOrdersController.class);
    }

}
