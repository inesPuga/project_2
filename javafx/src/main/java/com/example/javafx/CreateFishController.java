package com.example.javafx;

import com.example.database.BLL.PeixeBLL;
import com.example.database.BLL.TipoconservaBLL;
import com.example.database.DAL.Peixe;
import com.example.database.DAL.Tipoconserva;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateFishController {
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
}
