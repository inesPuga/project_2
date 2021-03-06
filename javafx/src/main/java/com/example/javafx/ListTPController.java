package com.example.javafx;

import com.example.database.BLL.TipoconservaBLL;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListTPController implements Initializable {
    @FXML
    private Label label_username;
    @FXML
    private ImageView back;
    @FXML
    private TableView<Tipoconserva> tpconservas_table;
    @FXML
    private TableColumn<Tipoconserva, Integer> id_c;
    @FXML
    private TableColumn<Tipoconserva, Integer> fish_c;
    @FXML
    private TableColumn<Tipoconserva, String> name_c;
    @FXML
    private TableColumn<Tipoconserva, Double> price_c;
    @FXML
    private TableColumn<Tipoconserva, Integer> stock_c;
    @FXML
    private Label infoLabel;
    private final UserSession userSession = UserSession.getInstance();

    public Utilizador getUser() {
        return userSession.get();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        label_username.setText(getUser().getUsername());
        tpconservas_table.setEditable(true);
        stock_c.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        stock_c.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Tipoconserva, Integer>>() {
            //@Override
            public void handle(TableColumn.CellEditEvent<Tipoconserva, Integer> event) {
                Tipoconserva tpc = event.getRowValue();
                tpc.setQtdstock(event.getNewValue());
                TipoconservaBLL.update(tpc);
            }
        });
    }

    public void backToLogin() {
        back.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    Logic.changePanel(event, "stock_manager-view.fxml", "Conserveira", StockManagerController.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void loadData() {
        id_c.setCellValueFactory(new PropertyValueFactory<>("codtipoconserva"));
        fish_c.setCellValueFactory(new PropertyValueFactory<>("codpeixe"));
        price_c.setCellValueFactory(new PropertyValueFactory<>("precoactvenda"));
        name_c.setCellValueFactory(new PropertyValueFactory<>("nome"));
        stock_c.setCellValueFactory(new PropertyValueFactory<>("qtdstock"));
        for(Tipoconserva i : TipoconservaBLL.readAll()) {
            tpconservas_table.getItems().add(i);
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

}
