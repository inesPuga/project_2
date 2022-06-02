package com.example.javafx;

import com.example.database.BLL.EncomendaBLL;
import com.example.database.BLL.EstadoeBLL;
import com.example.database.BLL.EstadoencomendaBLL;
import com.example.database.BLL.UtilizadorBLL;
import com.example.database.DAL.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewDetailsOrderController implements Initializable {
    @FXML
    private Label idorder;
    @FXML
    private Label name;
    @FXML
    private Label date;
    @FXML
    private Label price;
    @FXML
    private ImageView back;
    @FXML
    private TableView<OrderState> state_table;
    @FXML
    private TableColumn<OrderState, String> state_c;
    @FXML
    private TableColumn<OrderState, String> date_c;
    private final DataOrder dataOrder = DataOrder.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idorder.setText(String.valueOf(getDataOrder().getCodencomenda()));
        if(searchUser(getDataOrder().getCodcliente())!=null){
            name.setText(searchUser(getDataOrder().getCodcliente()).getNome());
        }
        else System.out.println("Error - view details order");
        date.setText(getDataOrder().getData());
        price.setText(String.valueOf(getDataOrder().getPrecototal()));
        loadData(orderstate());
    }

    public Utilizador searchUser(int id) {
        for(Utilizador i : UtilizadorBLL.readAll()) {
            if(id == i.getIduser()) {
                return i;
            }
        }
        return null;
    }

    public Encomenda getDataOrder() {
        return dataOrder.get();
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

    public Estadoencomenda returnStateOrder() {
        for(Estadoencomenda i : EstadoencomendaBLL.readAll()) {
            if(i.getCodencomenda() == getDataOrder().getCodencomenda()) {
                return i;
            }
        }
        return null;
    }

    public Estadoe returnStateDesc(Estadoencomenda stateorder) {
        for(Estadoe i : EstadoeBLL.readAll()) {
            if(i.getIde() == stateorder.getIde()) {
                return i;
            }
        }
        return null;
    }

    public List<OrderState> orderstate() {
        OrderState orderState = new OrderState();
        List<OrderState> list = new ArrayList<>();
        for(Estadoencomenda i : EstadoencomendaBLL.readAll()) {
            if(i.getCodencomenda() == getDataOrder().getCodencomenda()) {
                for(Estadoe j : EstadoeBLL.readAll()) {
                    if(i.getIde() == i.getIde()) {
                        orderState.setIdorder(i.getCodencomenda());
                        orderState.setIdstate(j.getIde());
                        orderState.setDate(i.getDtee());
                        orderState.setDesc(j.getDescricaoe());
                        list.add(orderState);
                    }
                }
            }
        }
        return list;
    }

    public void loadData(List<OrderState> list) {
        ObservableList<OrderState> obsList = FXCollections.observableArrayList(list);
        state_c.setCellValueFactory(new PropertyValueFactory<>("date"));
        date_c.setCellValueFactory(new PropertyValueFactory<>("desc"));
        state_table.setItems(obsList);
    }

}
