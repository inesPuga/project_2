package com.example.javafx;

import com.example.database.BLL.EncomendaBLL;
import com.example.database.BLL.TipoconservaBLL;
import com.example.database.BLL.UtilizadorBLL;
import com.example.database.DAL.Encomenda;
import com.example.database.DAL.Peixe;
import com.example.database.DAL.Tipoconserva;
import com.example.database.DAL.Utilizador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListEmployeesController implements Initializable {
    @FXML
    public ImageView back;
    @FXML
    private TableView<Utilizador> employees_table;
    @FXML
    private TableColumn<Utilizador, String> name_c;
    @FXML
    private TableColumn<Utilizador, String> email_c;
    @FXML
    private TableColumn<Utilizador, Integer> nphone_c;
    @FXML
    private TableColumn<Utilizador, Integer> role_c;
    @FXML
    private TextField search;
    @FXML
    private Button search_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData(listUsers());
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

    public void loadData(List<Utilizador> list) {
        ObservableList<Utilizador> obsList = FXCollections.observableArrayList(list);
        name_c.setCellValueFactory(new PropertyValueFactory<>("nome"));
        email_c.setCellValueFactory(new PropertyValueFactory<>("email"));
        nphone_c.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        role_c.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        employees_table.setItems(obsList);
        /*name_c.setCellValueFactory(new PropertyValueFactory<>("nome"));
        email_c.setCellValueFactory(new PropertyValueFactory<>("email"));
        nphone_c.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        role_c.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        for(Utilizador i : UtilizadorBLL.readAll()) {
            if(!i.getCargo().equals("A")) {
                if(i.getStatus()==1) {
                    employees_table.getItems().add(i);
                }
            }
        }*/
    }

    public void onClickSearchButton() {
        String search_str = search.getText();
        loadData(searchEngine(search_str));
    }

    public List<Utilizador> listUsers() {
        List<Utilizador> list = new ArrayList<Utilizador>();
        for(Utilizador i : UtilizadorBLL.readAll()) {
            if(!i.getCargo().equals("A")) {
                if(i.getStatus()==1) {
                    list.add(i);
                }
            }
        }
        return list;
    }

    public List<Utilizador> searchEngine(String str) {
        List<Utilizador> list = new ArrayList<>();
        for(Utilizador i : listUsers()) {
            if(str.equals(i.getNome()) || str.equals(i.getEmail())
                    || str.equals(String.valueOf(i.getNumtel())) || str.equals(i.getCargo())) {
                list.add(i);
            }
        }
        return list;
    }

}
