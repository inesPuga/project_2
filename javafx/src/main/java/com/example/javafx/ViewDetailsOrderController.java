package com.example.javafx;

import com.example.database.BLL.*;
import com.example.database.DAL.*;
import com.example.javafx.tables.OrderState;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private final UserSession userSession = UserSession.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idorder.setText(String.valueOf(getDataOrder().getCodencomenda()));
        name.setText((Objects.requireNonNull(UtilizadorBLL.readById(getDataOrder().getCodcliente()))).getNome());
        date.setText(getDataOrder().getData());
        price.setText(String.valueOf(getDataOrder().getPrecototal()));
        loadData(orderstate());
        state_table.setEditable(true);
        date_c.setCellFactory(TextFieldTableCell.forTableColumn());
        date_c.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<OrderState, String>>() {
            //@Override
            public void handle(TableColumn.CellEditEvent<OrderState, String> event) {
                OrderState os = event.getRowValue();
                os.setDesc(event.getNewValue());
                for(Estadoencomenda i : EstadoencomendaBLL.readAll()) {
                    if(i.getCodencomenda() == os.getIdorder()) {
                        for(Estadoe j : EstadoeBLL.readAll()) {
                            if(os.getIdstate() == i.getIde()) {
                                i.setDtee(event.getNewValue());
                                EstadoencomendaBLL.update(i);
                            }
                        }
                    }
                }
            }
        });
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

    public List<OrderState> orderstate() {
        List<OrderState> list = new ArrayList<>();
        for(Estadoencomenda i : EstadoencomendaBLL.readAll()) {
            if(i.getCodencomenda() == getDataOrder().getCodencomenda()) {
                for(Estadoe j : EstadoeBLL.readAll()) {
                    OrderState orderState = new OrderState();
                    if(j.getIde() == i.getIde()) {
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
        state_c.setCellValueFactory(new PropertyValueFactory<>("desc"));
        date_c.setCellValueFactory(new PropertyValueFactory<>("date"));
        state_table.setItems(obsList);
    }

}
