package com.example.javafx;

import com.example.database.BLL.*;
import com.example.database.DAL.*;
import com.example.javafx.tables.OrderClient;
import com.example.javafx.tables.OrderState;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListOrdersController implements Initializable {
    @FXML
    public ImageView back;
    @FXML
    private TableView<Encomenda> orders_table;
    @FXML
    private TableColumn<Encomenda, Integer> id_c;
    @FXML
    private TableColumn<Encomenda, String> data_c;
    @FXML
    private TableColumn<Encomenda, Double> price_c;
    @FXML
    private TableColumn<Encomenda, String> idclient_c;
    @FXML
    private Label label_info;
    //private final UserSession userSession = UserSession.getInstance();
    private final DataOrder dataOrder = DataOrder.getInstance();
    private final UserSession userSession = UserSession.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

    public Utilizador getUser() {
        return userSession.get();
    }

    public void backToLogin() {
        back.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    Logic.changePanel(event, "main_menu_manager-view.fxml", "Conserveira", MainMenuManagerController.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void viewDetails(javafx.event.Event event) throws IOException {
        Encomenda order_select = orders_table.getSelectionModel().getSelectedItem();
        if(order_select == null) {
            label_info.setText("Selecione uma encomenda");
        }
        else {
            label_info.setText("");
            dataOrder.in(order_select);
            userSession.in(getUser());
            Logic.changePanel(event, "view_details_order-view.fxml", "Conserveira", ViewDetailsOrderController.class);
        }
    }

    public void loadData() {
        ObservableList<Encomenda> orderClients = FXCollections.observableArrayList(EncomendaBLL.readAll());
        id_c.setCellValueFactory(new PropertyValueFactory<>("codencomenda"));
        data_c.setCellValueFactory(new PropertyValueFactory<>("data"));
        price_c.setCellValueFactory(new PropertyValueFactory<>("precototal"));
        idclient_c.setCellValueFactory(new PropertyValueFactory<>("codcliente"));
        //for(Encomenda i : EncomendaBLL.readAll()) {
        orders_table.setItems(orderClients);
        //}
    }

}