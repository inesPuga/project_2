package com.example.javafx;

import com.example.database.BLL.LogicDataBase;
import com.example.database.BLL.LoteBLL;
import com.example.database.BLL.PeixeBLL;
import com.example.database.BLL.TipoconservaBLL;
import com.example.database.DAL.Lote;
import com.example.database.DAL.Peixe;
import com.example.database.DAL.Tipoconserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConferLotsController implements Initializable {
    @FXML
    private ImageView back;
    @FXML
    private TableView<Lote> lots_table;
    @FXML
    private TableColumn<Lote, Integer> id_c;
    @FXML
    private TableColumn<Lote, Integer> codlot_c;
    @FXML
    private TableColumn<Lote, String> proddate_c;
    @FXML
    private TableColumn<Lote, String> expirationdate_c;
    @FXML
    private TextField proddatef;
    @FXML
    private TextField expirationdatef;
    @FXML
    private ComboBox<Tipoconserva> cb;
    @FXML
    private Label label_info;
    private ObservableList<Tipoconserva> obsList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        loadComboBox();
        lots_table.setEditable(true);
        proddate_c.setCellFactory(TextFieldTableCell.forTableColumn());
        proddate_c.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Lote, String>>() {
            //@Override
            public void handle(TableColumn.CellEditEvent<Lote, String> event) {
                Lote lot = event.getRowValue();
                lot.setDtproducao(event.getNewValue());
                LoteBLL.update(lot);
            }
        });
        expirationdate_c.setCellFactory(TextFieldTableCell.forTableColumn());
        expirationdate_c.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Lote, String>>() {
            //@Override
            public void handle(TableColumn.CellEditEvent<Lote, String> event) {
                Lote lot = event.getRowValue();
                lot.setDtvalidade(event.getNewValue());
                LoteBLL.update(lot);
            }
        });
    }

    public void onCreateButtonClick() {
        Lote lot = new Lote();
        if(!LogicDataBase.verifyDate(proddatef.getText()) || !LogicDataBase.verifyDate(expirationdatef.getText())) {
            label_info.setText("O formato da data é inválido");
        }
        else {
            label_info.setText("");
            lot.setDtproducao(proddatef.getText());
            lot.setDtvalidade(expirationdatef.getText());
            Tipoconserva tp = searchTp(cb.getValue().toString());
            if(tp!=null) {
                lot.setCodtipoconserva(tp.getCodtipoconserva());
                LoteBLL.create(lot);
                label_info.setText("Lote criado com sucesso");
                loadData();
            }
            else {
                label_info.setText("Erro na criação do lote");
                System.out.println("Erro - onCreateButtonClick - ConferLotsController");
            }
        }

    }

    public void loadComboBox() {
        obsList = FXCollections.observableArrayList(TipoconservaBLL.readAll());
        cb.setItems(obsList);
    }

    public void loadData() {
        ObservableList<Lote> list = FXCollections.observableArrayList(LoteBLL.readAll());
        id_c.setCellValueFactory(new PropertyValueFactory<>("codlote"));
        codlot_c.setCellValueFactory(new PropertyValueFactory<>("codtipoconserva"));
        proddate_c.setCellValueFactory(new PropertyValueFactory<>("dtproducao"));
        expirationdate_c.setCellValueFactory(new PropertyValueFactory<>("dtvalidade"));
        lots_table.setItems(list);
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

    public Tipoconserva searchTp(String name) {
        for(Tipoconserva p : TipoconservaBLL.readAll()) {
            if(p.getNome().equals(name)) {
                return p;
            }
        }
        return null;
    }

}
