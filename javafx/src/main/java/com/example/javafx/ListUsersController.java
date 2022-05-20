package com.example.javafx;

import com.example.database.BLL.UtilizadorBLL;
import com.example.database.DAL.Utilizador;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListUsersController implements Initializable {
    @FXML
    private TableView<Utilizador> users_table;
    @FXML
    private TableColumn<Utilizador, Integer> id_c;
    @FXML
    private TableColumn<Utilizador, String> username_c;
    @FXML
    private TableColumn<Utilizador, String> email_c;
    @FXML
    private TableColumn<Utilizador, String> name_c;
    @FXML
    private TableColumn<Utilizador, Integer> phone_c;
    @FXML
    private TableColumn<Utilizador, String> options_c;
    @FXML
    private TableColumn<Utilizador, String> status_c;
    @FXML
    private ImageView back;
    @FXML
    private ImageView add_user;
    @FXML
    private ImageView edit_user;
    @FXML
    private Label infoLabel;
    private final UserSession userSession = UserSession.getInstance();

    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        users_table.setEditable(true);
        username_c.setCellFactory(TextFieldTableCell.forTableColumn());
        username_c.setOnEditCommit(new EventHandler<CellEditEvent<Utilizador, String>>() {
            //@Override
            public void handle(CellEditEvent<Utilizador, String> event) {
                Utilizador user = event.getRowValue();
                user.setUsername(event.getNewValue());
                UtilizadorBLL.update(user);
            }
        });
        name_c.setCellFactory(TextFieldTableCell.forTableColumn());
        name_c.setOnEditCommit(new EventHandler<CellEditEvent<Utilizador, String>>() {
            //@Override
            public void handle(CellEditEvent<Utilizador, String> event) {
                Utilizador user = event.getRowValue();
                user.setUsername(event.getNewValue());
                UtilizadorBLL.update(user);
            }
        });
        phone_c.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        phone_c.setOnEditCommit(new EventHandler<CellEditEvent<Utilizador, Integer>>() {
            //@Override
            public void handle(CellEditEvent<Utilizador, Integer> event) {
                Utilizador user = event.getRowValue();
                user.setNumtel(event.getNewValue());
                UtilizadorBLL.update(user);
            }
        });
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    // - - - - - - - - - - button clicks - - - - - - - - - - //
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - //
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

    public void goToCreateUser() {
        add_user.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    Logic.changePanel(event, "create_user-view.fxml", "Conserveira", CreateUserController.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void goToEditUser() {
        edit_user.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                Utilizador user_select = users_table.getSelectionModel().getSelectedItem();
                userSession.in(user_select);
                try {
                    Logic.changePanel(event, "change_user-view.fxml", "Conserveira", EditUserController.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void onEnableButtonClick() {
        Utilizador user_select = users_table.getSelectionModel().getSelectedItem();
        if(user_select == null) {
            infoLabel.setText("Selecione um utilizador");
        }
        else {
            infoLabel.setText(" ");
            user_select.setStatus(1);
            UtilizadorBLL.update(user_select);
            //loadData();
            users_table.refresh();
        }
    }

    public void onDisableButtonClick() {
        Utilizador user_select = users_table.getSelectionModel().getSelectedItem();
        if(user_select == null) {
            infoLabel.setText("Selecione um utilizador");
        }
        else {
            infoLabel.setText(" ");
            user_select.setStatus(0);
            UtilizadorBLL.update(user_select);
            //loadData();
            users_table.refresh();
        }
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    // - - - - - - - - - - logic to list users - - - - - - - - - - //
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    public void loadData() {
        id_c.setCellValueFactory(new PropertyValueFactory<>("iduser"));
        username_c.setCellValueFactory(new PropertyValueFactory<>("username"));
        email_c.setCellValueFactory(new PropertyValueFactory<>("email"));
        name_c.setCellValueFactory(new PropertyValueFactory<>("nome"));
        phone_c.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        options_c.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        status_c.setCellValueFactory(new PropertyValueFactory<>("status"));
        for(Utilizador i : UtilizadorBLL.readAll()) {
            users_table.getItems().add(i);
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
