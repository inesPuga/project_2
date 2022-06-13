package com.example.javafx;

import com.example.database.BLL.EncomendaBLL;
import com.example.database.BLL.PeixeBLL;
import com.example.database.DAL.Encomenda;
import com.example.database.DAL.Peixe;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@NamedQueries({
        @NamedQuery(name = "Encomenda.sumprice", query = "SELECT sum(e.precototal) FROM Encomenda e")})
public class GraphicOrderController implements Initializable {
    final static String jan = "Janeiro";
    final static String fev = "Fevereiro";
    final static String mar = "Março";
    final static String abr = "Abril";
    final static String maio = "Maio";
    final static String jun = "Junho";
    final static String jul = "Julho";
    final static String ago = "Agosto";
    final static String set = "Setembro";
    final static String out = "Outubro";
    final static String nov = "Novembro";
    final static String dez = "Dezembro";
    public ImageView nextyear;
    public ImageView backyear;
    public ImageView back;
    @FXML
    private BarChart<String, Number> bar_chart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bar_chart.setAnimated(false);
        bar_chart.setTitle("Valor ganho com encomendas" + " [" + calculateYear() + "]");
        loadbarchart(calculateYear());
    }

    //sum of total orders in a month
    public Double sumTotalOrders(int month, int year) {
        //Double sum = Double.valueOf(0);
        Double sum = Double.valueOf(0);
        for(Encomenda i : EncomendaBLL.readAll()) {
            String[] str = getDropString(i.getData());
            if(year == Integer.parseInt(str[2])) {
                if((month == Integer.parseInt(str[1]))) {
                    sum += i.getPrecototal();
                }
            }
        }
        return sum;
    }

    public void backToLogin() {
        back.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    Logic.changePanel(event, "main_menu_manager-view.fxml", "Conserveira", MainMenuManagerController.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public int calculateYear() {
        bar_chart.setAnimated(false);
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(date);
        String[] str = getDropString(strDate);
        int[] year = {Integer.parseInt(str[2])};
        backyear.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                year[0] = year[0] - 1;
                bar_chart.setTitle("Valor ganho com encomendas" + " [" + year[0] + "]");
                loadbarchart(year[0]);
            }
        });
        nextyear.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                year[0] = year[0] + 1;
                bar_chart.setTitle("Valor ganho com encomendas" + " [" + year[0] + "]");
                loadbarchart(year[0]);
            }
        });
        return year[0];
    }

    public String[] getDropString(String str) {
        StringTokenizer strtok =  new  StringTokenizer(str, "/" );
        String[] retval = new String[strtok.countTokens() ];
        int i = 0;
        while (strtok.hasMoreElements() ) {
            retval[i] = strtok.nextToken();
            i++;
        }
        return retval;
    }

    public void loadbarchart(int year) {
        Platform.runLater(() -> {
            bar_chart.getData().clear();
            bar_chart.layout();
            bar_chart.setLegendVisible(false);
            XYChart.Series series1 = new XYChart.Series();
            series1.getData().add(new XYChart.Data(jan, sumTotalOrders(1, year)));
            series1.getData().add(new XYChart.Data(fev, sumTotalOrders(2, year)));
            series1.getData().add(new XYChart.Data(mar, sumTotalOrders(3, year)));
            series1.getData().add(new XYChart.Data(abr, sumTotalOrders(4, year)));
            series1.getData().add(new XYChart.Data(maio, sumTotalOrders(5, year)));
            series1.getData().add(new XYChart.Data(jun, sumTotalOrders(6, year)));
            series1.getData().add(new XYChart.Data(jul, sumTotalOrders(7, year)));
            series1.getData().add(new XYChart.Data(ago, sumTotalOrders(8, year)));
            series1.getData().add(new XYChart.Data(set, sumTotalOrders(9, year)));
            series1.getData().add(new XYChart.Data(out, sumTotalOrders(10, year)));
            series1.getData().add(new XYChart.Data(nov, sumTotalOrders(11, year)));
            bar_chart.getData().add(series1);
        });
    }
}