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
    private TableView<Encomenda> orders_table;
    @FXML
    private TableColumn<Encomenda, Integer> id_c;
    @FXML
    private TableColumn<Encomenda, String> data_c;
    @FXML
    private TableColumn<Encomenda, Double> price_c;
    @FXML
    private TableColumn<Encomenda, Integer> idclient_c;
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

    public void loadData() {
        id_c.setCellValueFactory(new PropertyValueFactory<>("codencomenda"));
        data_c.setCellValueFactory(new PropertyValueFactory<>("data"));
        price_c.setCellValueFactory(new PropertyValueFactory<>("precototal"));
        idclient_c.setCellValueFactory(new PropertyValueFactory<>("codcliente"));
        for(Encomenda i : EncomendaBLL.readAll()) {
            orders_table.getItems().add(i);
            /*if(i.getCargo().equals("A")) {
                options_c.setCellValueFactory(users_table -> new SimpleStringProperty("Administrador"));
            }
            if(i.getCargo().equals("G")) {
                options_c.setCellValueFactory(users_table -> new SimpleStringProperty("Gerente"));
            }
            if(i.getStatus() == 1) status_c.setCellValueFactory(users_table -> new SimpleStringProperty("Ativo"));
            else status_c.setCellValueFactory(users_table -> new SimpleStringProperty("Desativo"));*/
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        label_username.setText(getUser().getUsername());
    }
}
