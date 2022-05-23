package com.example.javafx;

import com.example.database.DAL.Tipoconserva;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangeTPController implements Initializable {
    @FXML
    private TextField namef;
    @FXML
    private TextField pricef;
    @FXML
    private TextField stockf;
    @FXML
    private TextField descf;
    private final TypeCChangePanel typeCChangePanel = TypeCChangePanel.getInstance();

    public void onConfirmButtonClick() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Tipoconserva tp = typeCChangePanel.get();
        namef.setText(tp.getNome());
        pricef.setText(String.valueOf(tp.getPrecoactvenda()));
        stockf.setText(String.valueOf(tp.getQtdstock()));
        descf.setText(tp.getDescricao());
    }
}
