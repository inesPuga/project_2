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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StockManagerController implements Initializable {
    @FXML
    private TableView<Peixe> fish_table;
    @FXML
    private TableColumn<Peixe, Integer> id_c;
    @FXML
    private TableColumn<Peixe, String> name_c;
    @FXML
    private TableColumn<Peixe, Integer> stock_c;
    @FXML
    private ImageView back;
    @FXML
    private Label label_username;
    @FXML
    private Label labelinfo;
    @FXML
    private Label infoLabel;
    @FXML
    private TextField data;
    @FXML
    private TextField qtdfish;
    @FXML
    private ComboBox<Peixe> cb;
    @FXML
    private Button plusbutton;
    private ObservableList<Peixe> obsList;
    private final UserSession userSession = UserSession.getInstance();

    public Utilizador getUser() {
        return userSession.get();
    }
    private final FishChangePanel fishChangePanel = FishChangePanel.getInstance();

    public void loadComboBox() {
        obsList = FXCollections.observableArrayList(PeixeBLL.readAll());
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadComboBox();
        loadData();
        label_username.setText(getUser().getUsername());
        fish_table.setEditable(true);
        stock_c.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        //@Override
        stock_c.setOnEditCommit(event -> {
            Peixe p = event.getRowValue();
            p.setStock(event.getNewValue());
            PeixeBLL.update(p);
        });
    }

    public void loadData() {
        id_c.setCellValueFactory(new PropertyValueFactory<>("codpeixe"));
        name_c.setCellValueFactory(new PropertyValueFactory<>("nome"));
        stock_c.setCellValueFactory(new PropertyValueFactory<>("stock"));
        for(Peixe i : PeixeBLL.readAll()) {
            fish_table.getItems().add(i);
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

    public Requisicao onClickCreateReq() {
        Requisicao req = new Requisicao();
        if(!LogicDataBase.verifyDate(data.getText())) labelinfo.setText("Data inválida");
        if(LogicDataBase.verifyDate(data.getText())) labelinfo.setText("");
        req.setData(data.getText());
        RequisicaoBLL.create(req);
        return req;
        /*plusbutton.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println("here");
                Peixe p = searchFish(cb.getValue().toString());
                if(p!=null) {
                    reqfish.setCodpeixe(p.getCodpeixe());
                    reqfish.setCodrequisicao(req.getCodrequisicao());
                    reqfish.setQtd(Integer.parseInt(qtdfish.getText()));
                    PeixerequisicaoBLL.create(reqfish);
                }
                else {
                    System.out.println("Erro");
                }
            }
        });*/
    }

    public void onAddButtonClick(javafx.event.Event event) throws IOException {
        Logic.changePanel(event, "create_fish-view.fxml", "Conserveira", CreateFishController.class);
    }

    public void onClickListTP(javafx.event.Event event) throws IOException {
        Logic.changePanel(event, "list_tp-view.fxml", "Conserveira", ListTPController.class);
    }

    public void onClickListReqs(javafx.event.Event event) throws IOException {
        Logic.changePanel(event, "list_req-view.fxml", "Conserveira", ListReqController.class);
    }

    public void onPlusClick() {
        Requisicao req = onClickCreateReq();
        if (req!=null) {
            Peixe p = searchFish(cb.getValue().toString());
            Peixerequisicao reqfish = new Peixerequisicao();
            if(p!=null) {
                reqfish.setCodpeixe(p.getCodpeixe());
                reqfish.setCodrequisicao(req.getCodrequisicao());
                reqfish.setQtd(Integer.parseInt(qtdfish.getText()));
                PeixerequisicaoBLL.create(reqfish);
            }
            else {
                System.out.println("Erro");
            }
        }
        else System.out.printf("Erro");
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
