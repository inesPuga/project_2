package com.example.javafx;

import com.example.database.BLL.RequisicaoBLL;
import com.example.database.BLL.TipoconservaBLL;
import com.example.database.DAL.Requisicao;
import com.example.database.DAL.Tipoconserva;
import com.example.database.DAL.Utilizador;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListReqController implements Initializable {
    @FXML
    private Label label_username;
    @FXML
    private ImageView back;
    @FXML
    private TableView<Requisicao> reqs_table;
    @FXML
    private TableColumn<Requisicao, Integer> id_c;
    @FXML
    private TableColumn<Requisicao, Integer> codgs_c;
    @FXML
    private TableColumn<Requisicao, String> date_c;
    @FXML
    private TableColumn<Requisicao, Integer> qtd_c;
    @FXML
    private Label infoLabel;
    private final UserSession userSession = UserSession.getInstance();

    public Utilizador getUser() {
        return userSession.get();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        label_username.setText(getUser().getUsername());
    }

    public void backToLogin() {
        back.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    Logic.changePanel(event, "stock_manager-view.fxml", "Conserveira", StockManagerController.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void loadData() {
        id_c.setCellValueFactory(new PropertyValueFactory<>("codrequisicao"));
        codgs_c.setCellValueFactory(new PropertyValueFactory<>("codgs"));
        date_c.setCellValueFactory(new PropertyValueFactory<>("data"));
        qtd_c.setCellValueFactory(new PropertyValueFactory<>("qtdtotal"));
        for(Requisicao i : RequisicaoBLL.readAll()) {
            reqs_table.getItems().add(i);
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
