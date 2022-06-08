package com.example.javafx;

import com.example.database.BLL.*;
import com.example.database.DAL.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuClientController implements Initializable {
    @FXML
    private ImageView back;
    @FXML
    private ComboBox<Tipoconserva> cb;
    private ObservableList<Tipoconserva> obsList;
    @FXML
    private Label label_username;
    @FXML
    private TextField qtdf;
    @FXML
    private TextField portaf;
    @FXML
    private TextField ruaf;
    @FXML
    private TextField cpf;
    @FXML
    private TextField datenascf;
    @FXML
    private TextField nif;
    @FXML
    private TextField ccf;
    private final UserSession userSession = UserSession.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadComboBox();
        label_username.setText(getUser().getUsername());
    }

    public Utilizador getUser() {
        return userSession.get();
    }

    public void loadComboBox() {
        obsList = FXCollections.observableArrayList(TipoconservaBLL.readAll());
        cb.setItems(obsList);
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

    public void order() {
        //if(search(cb.getValue().toString())!=null) {
        Encomenda e = new Encomenda();
        Cliente client_temp = searchclient(getUser().getIduser());
        if(client_temp!=null) {
            e.setCodcliente(client_temp.getCodcliente());
        }
        else {
            System.out.println("Erro - order menu client");
        }
        if(LogicDataBase.verifyDate("08/06/2022")) {
            e.setData("08/06/2022");
        }
        else {
            System.out.println("Erro - order menu client");
        }
        e.setPrecototal(0);
        EncomendaBLL.create(e);
        Tipoconservaencomenda te = new Tipoconservaencomenda();
        te.setCodencomenda(e.getCodencomenda());
        Tipoconserva tp = search(cb.getValue().toString());
        if(tp!=null) {
            te.setCodtipoconserva(tp.getCodtipoconserva());
        }
        else {
            System.out.println("Erro - order menu client");
        }
        te.setQtd(Integer.parseInt(qtdf.getText()));
        TipoconservaencomendaBLL.create(te);
        //}
        //else System.out.println("Erro - order menu client");
    }

    public void more_info() {
        Cliente c = searchclient(getUser().getIduser());
        c.setNumporta(Integer.parseInt(portaf.getText()));
        c.setNomerua(ruaf.getText());
        c.setNumcc(Integer.parseInt(ccf.getText()));
        c.setDtnascimento(datenascf.getText());
        c.setCodpostal(cpf.getText());
        Cpostal cp = new Cpostal();
        cp.setLocalidade("Viana do Castelo");
        cp.setCodpostal(cpf.getText());
        CPostalBLL.create(cp);
        c.setNif(Integer.parseInt(nif.getText()));
        ClienteBLL.update(c);
    }

    public void order_test() {
        //if(search(cb.getValue().toString())!=null) {
        Encomenda e = new Encomenda();
        e.setCodcliente(1);
        if(LogicDataBase.verifyDate("08/06/2022")) {
            e.setData("08/06/2022");
        }
        else {
            System.out.println("Erro - order menu client");
        }
        e.setPrecototal(0);
        EncomendaBLL.create(e);
        Tipoconservaencomenda te = new Tipoconservaencomenda();
        te.setCodencomenda(e.getCodencomenda());
        Tipoconserva tp = search(cb.getValue().toString());
        if(tp!=null) {
            te.setCodtipoconserva(tp.getCodtipoconserva());
        }
        else {
            System.out.println("Erro - order menu client");
        }
        te.setQtd(Integer.parseInt(qtdf.getText()));
        TipoconservaencomendaBLL.create(te);
        //}
        //else System.out.println("Erro - order menu client");
    }

    public Cliente searchclient(int id) {
        for(Cliente i : ClienteBLL.readAll()) {
            if(i.getIduser() == id) {
                return i;
            }
        }
        return null;
    }

    public Tipoconserva search(String str) {
        for(Tipoconserva i : TipoconservaBLL.readAll()) {
            if(i.getNome().equals(str)) {
                return i;
            }
        }
        return null;
    }

}