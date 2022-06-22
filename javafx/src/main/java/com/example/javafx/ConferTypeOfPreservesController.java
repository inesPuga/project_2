package com.example.javafx;

import com.example.database.BLL.PeixeBLL;
import com.example.database.BLL.TipoconservaBLL;
import com.example.database.DAL.Peixe;
import com.example.database.DAL.Tipoconserva;
import com.example.database.DAL.Utilizador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ConferTypeOfPreservesController implements Initializable {
    public Button create;
    public Label label;
    public Button details;
    @FXML private ImageView imageView;
    @FXML
    private ImageView back;
    @FXML
    private TableView<Tipoconserva> tpconservas_table;
    @FXML
    private TableColumn<Tipoconserva, Integer> id_c;
    @FXML
    private TableColumn<Tipoconserva, Integer> fish_c;
    @FXML
    private TableColumn<Tipoconserva, String> name_c;
    @FXML
    private TableColumn<Tipoconserva, Double> price_c;
    @FXML
    private TableColumn<Tipoconserva, Integer> stock_c;
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
    @FXML
    private Label label_info;
    private ObservableList<Peixe> obsList;

    private Tipoconserva tp = new Tipoconserva();

    private final UserSession userSession = UserSession.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        loadComboBox();
        tpconservas_table.setEditable(true);
        if(getUser().getCargo().equals("GS")) {
            imageView.setVisible(false);
            descf.setVisible(false);
            namef.setVisible(false);
            pricef.setVisible(false);
            stockf.setVisible(false);
            tpconservas_table.setLayoutX(126);
            tpconservas_table.setLayoutY(118);
            create.setVisible(false);
            details.setLayoutX(472);
            details.setLayoutY(390);
            cb.setVisible(false);
            label.setVisible(false);
            stock_c.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            stock_c.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Tipoconserva, Integer>>() {
                //@Override
                public void handle(TableColumn.CellEditEvent<Tipoconserva, Integer> event) {
                    Tipoconserva tpc = event.getRowValue();
                    tpc.setQtdstock(event.getNewValue());
                    TipoconservaBLL.update(tpc);
                }
            });
        }
        else {
            stock_c.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            stock_c.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Tipoconserva, Integer>>() {
                //@Override
                public void handle(TableColumn.CellEditEvent<Tipoconserva, Integer> event) {
                    Tipoconserva tpc = event.getRowValue();
                    tpc.setQtdstock(event.getNewValue());
                    TipoconservaBLL.update(tpc);
                }
            });
            price_c.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            price_c.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Tipoconserva, Double>>() {
                //@Override
                public void handle(TableColumn.CellEditEvent<Tipoconserva, Double> event) {
                    Tipoconserva tpc = event.getRowValue();
                    tpc.setPrecoactvenda(event.getNewValue());
                    TipoconservaBLL.update(tpc);
                }
            });
            name_c.setCellFactory(TextFieldTableCell.forTableColumn());
            name_c.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Tipoconserva, String>>() {
                //@Override
                public void handle(TableColumn.CellEditEvent<Tipoconserva, String> event) {
                    Tipoconserva tpc = event.getRowValue();
                    tpc.setNome(event.getNewValue());
                    TipoconservaBLL.update(tpc);
                }
            });
            //image view
            imageView.setOnMouseClicked(mouseEvent -> {
                FileChooser fileChooser = new FileChooser();
                File f = fileChooser.showOpenDialog(((Node) mouseEvent.getSource()).getScene().getWindow());
                if (f == null) return;

                try {
                    FileInputStream inputStream = new FileInputStream(f);
                    imageView.setImage(new Image(inputStream));
                    tp.setImagem(inputStream.readAllBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        
        
    }

    public void loadComboBox() {
        obsList = FXCollections.observableArrayList(PeixeBLL.readAll());
        cb.setItems(obsList);
    }

    public void onCreateButtonClick() {
        tp.setNome(namef.getText());
        tp.setPrecoactvenda(Double.parseDouble(pricef.getText()));
        tp.setDescricao(descf.getText());
        tp.setQtdstock(Integer.parseInt(stockf.getText()));
        Peixe p = searchFish(cb.getValue().toString());
        if(p!=null) {
            tp.setCodpeixe(p.getCodpeixe());
            TipoconservaBLL.create(tp);
            label_info.setText("Tipo de conserva criado com sucesso");
            loadData();
        }
        else {
            label_info.setText("Erro na criação do tipo de conserva");
            System.out.println("Erro - onCreateButtonClick - ListTypeOfPreservesController");
        }
    }

    public Utilizador getUser() {
        return userSession.get();
    }

    public void backToLogin() {
        back.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    if(getUser().getCargo().equals("G")) {
                        Logic.changePanel(event, "main_menu_manager-view.fxml", "Conserveira", MainMenuManagerController.class);
                    }
                    else Logic.changePanel(event, "menu_stock_man-view.fxml", "Conserveira", MenuStockManager.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void loadData() {
        ObservableList<Tipoconserva> list = FXCollections.observableArrayList(TipoconservaBLL.readAll());
        id_c.setCellValueFactory(new PropertyValueFactory<>("codtipoconserva"));
        fish_c.setCellValueFactory(new PropertyValueFactory<>("codpeixe"));
        price_c.setCellValueFactory(new PropertyValueFactory<>("precoactvenda"));
        name_c.setCellValueFactory(new PropertyValueFactory<>("nome"));
        stock_c.setCellValueFactory(new PropertyValueFactory<>("qtdstock"));
        tpconservas_table.setItems(list);
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
