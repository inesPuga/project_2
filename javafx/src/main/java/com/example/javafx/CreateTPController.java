package com.example.javafx;

import com.example.database.BLL.PeixeBLL;
import com.example.database.BLL.TipoconservaBLL;
import com.example.database.DAL.Peixe;
import com.example.database.DAL.Tipoconserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateTPController implements Initializable {
    @FXML
    private TextField namef;
    @FXML
    private TextField pricef;
    @FXML
    private TextField stockf;
    @FXML
    private TextField descf;
    @FXML
    private ComboBox<Peixe> cb;
    private ObservableList<Peixe> obsList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadComboBox();
    }

    public void loadComboBox() {
        obsList = FXCollections.observableArrayList(PeixeBLL.readAll());
        cb.setItems(obsList);
    }

    public void onCreateButtonClick() {
        Tipoconserva tp = new Tipoconserva();
        tp.setNome(namef.getText());
        tp.setPrecoactvenda(Double.parseDouble(pricef.getText()));
        tp.setDescricao(descf.getText());
        tp.setQtdstock(Integer.parseInt(stockf.getText()));
        Peixe p = searchFish(cb.getValue().toString());
        if(p!=null) {
            tp.setCodpeixe(p.getCodpeixe());
            TipoconservaBLL.create(tp);
        }
        else {
            System.out.println("Erro");
        }

    }

    public Peixe searchFish(String name) {
        for(Peixe p : PeixeBLL.readAll()) {
            if(p.getNome().equals(name)) {
                return p;
            }
        }
        return null;
    }

}
