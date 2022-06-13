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
import javafx.scene.control.*;
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
    public ComboBox<Estadoe> cb;
    public Button graphbutton;
    public Button okbutton;
    @FXML
    private TableView<OrderClient> orders_table;
    @FXML
    private TableColumn<OrderClient, Integer> id_c;
    @FXML
    private TableColumn<OrderClient, String> data_c;
    @FXML
    private TableColumn<OrderClient, Double> price_c;
    @FXML
    private TableColumn<OrderClient, String> idclient_c;
    @FXML
    private Label label_info;
    private final DataOrder dataOrder = DataOrder.getInstance();
    private final UserSession userSession = UserSession.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData(orderclient());
        if(!getUser().getCargo().equals("GV")) {
            cb.setVisible(false);
            okbutton.setVisible(false);
        }
        if(!getUser().getCargo().equals("G")) {
            graphbutton.setVisible(false);
            cb.setLayoutX(126);
            cb.setLayoutY(392);
        }
    }

    public Utilizador getUser() {
        return userSession.get();
    }

    public void backToLogin() {
        back.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                if(getUser().getCargo().equals("G")) {
                    try {
                        Logic.changePanel(event, "main_menu_manager-view.fxml", "Conserveira", MainMenuManagerController.class);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(getUser().getCargo().equals("GV")) {
                    try {
                        Logic.changePanel(event, "menu_sales_manager-view.fxml", "Conserveira", MenuSalesManagerController.class);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public void viewDetails(javafx.event.Event event) throws IOException {
        OrderClient order_select = orders_table.getSelectionModel().getSelectedItem();
        if(order_select == null) {
            label_info.setText("Selecione uma encomenda");
        }
        else {
            label_info.setText("");
            dataOrder.in(EncomendaBLL.readById(order_select.getIdorder()));
            userSession.in(getUser());
            Logic.changePanel(event, "view_details_order-view.fxml", "Conserveira", ViewDetailsOrderController.class);
        }
    }

    public void onActionGraphics(javafx.event.Event event) throws IOException {
        Logic.changePanel(event, "graphic_orders_view.fxml", "Conserveira", GraphicOrderController.class);
    }

    public List<OrderClient> orderclient() {
        List<OrderClient> list = new ArrayList<>();
        for(Encomenda i : EncomendaBLL.readAll()) {
            for(Cliente j : ClienteBLL.readAll()) {
                if(i.getCodcliente() == j.getCodcliente()) {
                    for(Utilizador k : UtilizadorBLL.readAll()) {
                        OrderClient orderClient = new OrderClient();
                        if(k.getIduser()==j.getIduser()) {
                            orderClient.setName(k.getNome());
                            orderClient.setIdorder(i.getCodencomenda());
                            orderClient.setDate(i.getData());
                            orderClient.setIdclient(j.getCodcliente());
                            orderClient.setPrice(i.getPrecototal());
                            list.add(orderClient);
                        }
                    }
                }
            }
        }
        return list;
    }

    public void loadData(List<OrderClient> list) {
        /*ObservableList<Encomenda> orderClients = FXCollections.observableArrayList(EncomendaBLL.readAll());
        id_c.setCellValueFactory(new PropertyValueFactory<>("codencomenda"));
        data_c.setCellValueFactory(new PropertyValueFactory<>("data"));
        price_c.setCellValueFactory(new PropertyValueFactory<>("precototal"));
        idclient_c.setCellValueFactory(new PropertyValueFactory<>("codcliente"));
        //for(Encomenda i : EncomendaBLL.readAll()) {
        orders_table.setItems(orderClients);
        //}*/
        ObservableList<OrderClient> orderClients = FXCollections.observableArrayList(list);
        id_c.setCellValueFactory(new PropertyValueFactory<>("idorder"));
        data_c.setCellValueFactory(new PropertyValueFactory<>("date"));
        price_c.setCellValueFactory(new PropertyValueFactory<>("price"));
        idclient_c.setCellValueFactory(new PropertyValueFactory<>("name"));
        //for(Encomenda i : EncomendaBLL.readAll()) {
        orders_table.setItems(orderClients);
        //}
    }

}