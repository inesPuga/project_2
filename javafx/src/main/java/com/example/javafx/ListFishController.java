package com.example.javafx;

import com.example.database.BLL.PeixeBLL;
import com.example.database.BLL.TipoconservaBLL;
import com.example.database.DAL.Peixe;
import com.example.database.DAL.Tipoconserva;
import com.example.database.DAL.Utilizador;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListFishController implements Initializable {
    public Button create;
    @FXML
    private TableView<Peixe> fish_table;
    @FXML
    private TableColumn<Peixe, Integer> id_c;
    @FXML
    private TableColumn<Peixe, String> name_c;
    @FXML
    private TableColumn<Peixe, Integer> stock_c;
    @FXML
    private ImageView back;
    private final UserSession userSession = UserSession.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        if (getUser().getCargo().equals("GS")) {
            fish_table.setEditable(true);
            stock_c.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            stock_c.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Peixe, Integer>>() {
                //@Override
                public void handle(TableColumn.CellEditEvent<Peixe, Integer> event) {
                    Peixe f = event.getRowValue();
                    f.setStock(event.getNewValue());
                    PeixeBLL.update(f);
                }
            });
        }
        else create.setVisible(false);
    }

    public Utilizador getUser() {
        return userSession.get();
    }

    public void backToLogin() {
        back.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    if(getUser().getCargo().equals("G")) {
                        Logic.changePanel(event, "main_menu_manager-view.fxml", "Conserveira", MainMenuManagerController.class);
                    }
                    else Logic.changePanel(event, "menu_stock_man-view.fxml", "Conserveira", MenuStockManager.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void loadData() {
        id_c.setCellValueFactory(new PropertyValueFactory<>("codpeixe"));
        name_c.setCellValueFactory(new PropertyValueFactory<>("nome"));
        stock_c.setCellValueFactory(new PropertyValueFactory<>("stock"));
        for(Peixe i : PeixeBLL.readAll()) {
            fish_table.getItems().add(i);
        }
    }

    public void onAddButtonClick(javafx.event.Event event) throws IOException {
        Logic.changePanel(event, "create_fish-view.fxml", "Conserveira", CreateFishController.class);
    }

}
