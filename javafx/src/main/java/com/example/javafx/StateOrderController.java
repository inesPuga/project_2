package com.example.javafx;

import com.example.database.BLL.EncomendaBLL;
import com.example.database.BLL.EstadoeBLL;
import com.example.database.BLL.LogicDataBase;
import com.example.database.BLL.LoteBLL;
import com.example.database.DAL.Encomenda;
import com.example.database.DAL.Estadoe;
import com.example.database.DAL.Lote;
import com.example.database.DAL.Tipoconserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StateOrderController implements Initializable {
    @FXML
    private TableView<Estadoe> stateorder_table;
    @FXML
    private TableColumn<Estadoe, Integer> id_c;
    @FXML
    private TableColumn<Estadoe, String> desc_c;
    @FXML
    private TextField descf;
    @FXML
    private ImageView back;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

    public void loadData() {
        ObservableList<Estadoe> list = FXCollections.observableArrayList(EstadoeBLL.readAll());
        id_c.setCellValueFactory(new PropertyValueFactory<>("ide"));
        desc_c.setCellValueFactory(new PropertyValueFactory<>("descricaoe"));
        stateorder_table.setItems(list);
    }

    public void onCreateButtonClick() {
        Estadoe stateorder = new Estadoe();
        //label_info.setText("");
        stateorder.setDescricaoe(descf.getText());
        EstadoeBLL.create(stateorder);
        //label_info.setText("Erro na criação do lote");
        loadData();
    }

    public void backToLogin() {
        back.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    Logic.changePanel(event, "menu_admin-view.fxml", "Conserveira", MenuAdminController.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}
