package com.example.javafx;

import com.example.database.BLL.TipoconservaBLL;
import com.example.database.DAL.Tipoconserva;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChangeTPController implements Initializable {
    @FXML
    private TextField namef;
    @FXML
    private TextField pricef;
    @FXML
    private TextField descf;
    @FXML
    private ImageView back;
    private final TypeCChangePanel typeCChangePanel = TypeCChangePanel.getInstance();

    public void onConfirmButtonClick(javafx.event.Event event) {
        Tipoconserva tp = typeCChangePanel.get();
        tp.setNome(namef.getText());
        tp.setPrecoactvenda(Double.parseDouble(pricef.getText()));
        //tp.setQtdstock(Integer.parseInt(stockf.getText()));
        tp.setDescricao(descf.getText());
        TipoconservaBLL.update(tp);
        try {
            Logic.changePanel(event, "menu_manager-view.fxml", "Conserveira", MenuManagerController.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void backToLogin() {
        back.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    Logic.changePanel(event, "menu_manager-view.fxml", "Conserveira", MenuManagerController.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Tipoconserva tp = typeCChangePanel.get();
        namef.setText(tp.getNome());
        pricef.setText(String.valueOf(tp.getPrecoactvenda()));
        //stockf.setText(String.valueOf(tp.getQtdstock()));
        descf.setText(tp.getDescricao());
    }
}
