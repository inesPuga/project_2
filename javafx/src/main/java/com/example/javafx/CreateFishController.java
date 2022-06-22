package com.example.javafx;

import com.example.database.BLL.PeixeBLL;
import com.example.database.BLL.TipoconservaBLL;
import com.example.database.DAL.Peixe;
import com.example.database.DAL.Tipoconserva;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class CreateFishController {
    public ImageView back;
    @FXML
    private TextField namef;
    @FXML
    private TextField stockf;

    public void onCreateButtonClick() {
        Peixe p = new Peixe();
        p.setNome(namef.getText());
        p.setStock(Integer.parseInt(stockf.getText()));
        PeixeBLL.create(p);
    }

    public void backToListFish() {
        back.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    Logic.changePanel(event, "list_fish-view.fxml", "Conserveira", ListFishController.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}
