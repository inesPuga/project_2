package com.example.javafx;

import com.example.database.BLL.TipoconservaBLL;
import com.example.database.BLL.UtilizadorBLL;
import com.example.database.DAL.Peixe;
import com.example.database.DAL.Tipoconserva;
import com.example.database.DAL.Utilizador;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class MenuManagerController implements Initializable {
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
    private final UserSession userSession = UserSession.getInstance();
    private final TypeCChangePanel typeCChangePanel = TypeCChangePanel.getInstance();

    public MenuManagerController() {}

    public Utilizador getUsername() {
        return userSession.get();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        label_username.setText(getUsername().getUsername());
        tpconservas_table.setEditable(true);
        price_c.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        price_c.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Tipoconserva, Double>>() {
            //@Override
            public void handle(TableColumn.CellEditEvent<Tipoconserva, Double> event) {
                Tipoconserva tpc = event.getRowValue();
                tpc.setPrecoactvenda(event.getNewValue());
                TipoconservaBLL.update(tpc);
            }
        });
        name_c.setCellFactory(TextFieldTableCell.forTableColumn());
        name_c.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Tipoconserva, String>>() {
            //@Override
            public void handle(TableColumn.CellEditEvent<Tipoconserva, String> event) {
                Tipoconserva tpc = event.getRowValue();
                tpc.setNome(event.getNewValue());
                TipoconservaBLL.update(tpc);
            }
        });
    }

    public void onAddButtonClick(javafx.event.Event event) throws IOException {
        Logic.changePanel(event, "create_tp-view.fxml", "Conserveira", CreateTPController.class);
    }

    public void onEditButtonClick(javafx.event.Event event) throws IOException {
        //typeCChangePanel.in(tp);
        Logic.changePanel(event, "change_tp-view.fxml", "Conserveira", ChangeTPController.class);
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
