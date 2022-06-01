package com.example.javafx;

import com.example.database.BLL.PeixeBLL;
import com.example.database.DAL.Peixe;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListFishController implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
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

    public void loadData() {
        id_c.setCellValueFactory(new PropertyValueFactory<>("codpeixe"));
        name_c.setCellValueFactory(new PropertyValueFactory<>("nome"));
        stock_c.setCellValueFactory(new PropertyValueFactory<>("stock"));
        for(Peixe i : PeixeBLL.readAll()) {
            fish_table.getItems().add(i);
        }
    }

}
