package com.example.javafx;

import com.example.database.BLL.GerenteBLL;
import com.example.database.BLL.GestorcomprasBLL;
import com.example.database.BLL.LogicDataBase;
import com.example.database.BLL.UtilizadorBLL;
import com.example.database.DAL.Gerente;
import com.example.database.DAL.Gestorcompras;
import com.example.database.DAL.Utilizador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateUserController implements Initializable {
    @FXML
    private TextField tf1;
    @FXML
    private TextField tf2;
    @FXML
    private TextField tf3;
    @FXML
    private TextField tf4;
    @FXML
    private Button button;
    @FXML
    private PasswordField pf;
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private Label l4;
    @FXML
    private Label l5;
    @FXML
    private Label l6;
    @FXML
    private Label l7;
    @FXML
    private ComboBox<TypeUsers> cb;
    @FXML
    private Label label;
    @FXML
    private ImageView back;
    private List<TypeUsers> typeUsersList = new ArrayList<>();
    private ObservableList<TypeUsers> obsList;

    public void initialize(URL url, ResourceBundle rb) {
        loadComboBox();
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    // - - - - - - - - - - button clicks - - - - - - - - - - //
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    public void backToListUsers() {
        back.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    Logic.changePanel(event, "list_users-view.fxml", "Conserveira", ListUsersController.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void onCreateButtonClick() {
        Utilizador user = new Utilizador();
        user.setNome(tf1.getText());
        user.setEmail(tf2.getText());
        user.setNumtel(Integer.parseInt(tf3.getText()));
        user.setUsername(tf4.getText());
        user.setStatus(1);
        String passEncrypt = LogicDataBase.passEncrypt(pf.getText());
        user.setPassword(passEncrypt);
        if("Administrador".equals(cb.getValue().toString())) {
            user.setCargo("A");
            if(UtilizadorBLL.checkUsername(user) == 1) {
                label.setText("Este nome de utilizador já existe");
            }
            else {
                label.setText("");
            }
        }
        if("Gerente".equals(cb.getValue().toString())) {
            user.setCargo("G");
            if(UtilizadorBLL.checkUsername(user) == 0) {
                label.setText("");
                Gerente g = new Gerente();
                g.setIduser(user.getIduser());
                GerenteBLL.create(g);
            }
            else {
                label.setText("Este nome de utilizador já existe");
            }
        }
        if("Gestor de Compras".equals(cb.getValue().toString())) {
            user.setCargo("GC");
            UtilizadorBLL.create(user);
            Gestorcompras gc = new Gestorcompras();
            gc.setIduser(user.getIduser());
            GestorcomprasBLL.create(gc);
        }
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    // - - - - - - - - - - logic to create account - - - - - - - - - - //
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    public void loadComboBox() {
        // - - - - items for combo box - - - - //
        TypeUsers typeUsers1 = new TypeUsers(1, "Administrador");
        TypeUsers typeUsers2 = new TypeUsers(2, "Gerente");
        TypeUsers typeUsers3 = new TypeUsers(3, "Gestor de Compras");
        TypeUsers typeUsers4 = new TypeUsers(4, "Gestor de Stock");
        TypeUsers typeUsers5 = new TypeUsers(5, "Gestor de Vendas");
        TypeUsers typeUsers6 = new TypeUsers(6, "Responsável pelo Armazém");
        TypeUsers typeUsers7 = new TypeUsers(7, "Responsável pela Qualidade");
        // - - - - sum items in list - - - - //
        typeUsersList.add(typeUsers1);
        typeUsersList.add(typeUsers2);
        typeUsersList.add(typeUsers3);
        typeUsersList.add(typeUsers4);
        typeUsersList.add(typeUsers5);
        typeUsersList.add(typeUsers6);
        typeUsersList.add(typeUsers7);

        obsList = FXCollections.observableArrayList(typeUsersList);

        cb.setItems(obsList);
    }

}