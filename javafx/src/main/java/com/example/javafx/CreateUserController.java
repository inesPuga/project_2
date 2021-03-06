package com.example.javafx;

import com.example.database.BLL.*;
import com.example.database.DAL.*;
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
                    Logic.changePanel(event, "list_users.fxml", "Conserveira", ListUsersController.class);
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
                label.setText("Este nome de utilizador j?? existe");
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
                label.setText("Este nome de utilizador j?? existe");
            }
        }
        if("Gestor de Compras".equals(cb.getValue().toString())) {
            user.setCargo("GC");
            if(UtilizadorBLL.checkUsername(user) == 0) {
                label.setText("");
                Gestorcompras gc = new Gestorcompras();
                gc.setIduser(user.getIduser());
                GestorcomprasBLL.create(gc);
            }
            else {
                label.setText("Este nome de utilizador j?? existe");
            }
        }
        if("Gestor de Vendas".equals(cb.getValue().toString())) {
            user.setCargo("GV");
            if(UtilizadorBLL.checkUsername(user) == 0) {
                label.setText("");
                Gestorvendas g = new Gestorvendas();
                g.setIduser(user.getIduser());
                GestorvendasBLL.create(g);
            }
            else {
                label.setText("Este nome de utilizador j?? existe");
            }
        }
        if("Gestor de Stock".equals(cb.getValue().toString())) {
            user.setCargo("GS");
            if(UtilizadorBLL.checkUsername(user) == 0) {
                label.setText("");
                Gestorstock g = new Gestorstock();
                g.setIduser(user.getIduser());
                GestorstockBLL.create(g);
            }
            else {
                label.setText("Este nome de utilizador j?? existe");
            }
        }
        if("Cliente".equals(cb.getValue().toString())) {
            user.setCargo("C");
            if(UtilizadorBLL.checkUsername(user) == 0) {
                label.setText("");
                Cliente c = new Cliente();
                c.setIduser(user.getIduser());
                ClienteBLL.create(c);
            }
            else {
                label.setText("Este nome de utilizador j?? existe");
            }
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
        //TypeUsers typeUsers6 = new TypeUsers(6, "Respons??vel pelo Armaz??m");
        //TypeUsers typeUsers7 = new TypeUsers(7, "Respons??vel pela Qualidade");
        TypeUsers typeUsers8 = new TypeUsers(8, "Cliente");
        // - - - - sum items in list - - - - //
        typeUsersList.add(typeUsers1);
        typeUsersList.add(typeUsers2);
        typeUsersList.add(typeUsers3);
        typeUsersList.add(typeUsers4);
        typeUsersList.add(typeUsers5);
        //typeUsersList.add(typeUsers6);
        //typeUsersList.add(typeUsers7);
        typeUsersList.add(typeUsers8);

        obsList = FXCollections.observableArrayList(typeUsersList);

        cb.setItems(obsList);
    }

}