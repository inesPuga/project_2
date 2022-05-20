package com.example.javafx;

import com.example.database.DAL.*;
import com.example.database.BLL.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Logic {

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    // - - - - - - - - - - locig java fx - - - - - - - - - - //
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    public static <T> Scene changePanel(Event event, String name_fxml, String name_view, Class<T> cclass) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(cclass.getResource(name_fxml)));
        Scene scene = new Scene(parent, 700, 500);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle(name_view);
        stage.show();

        return scene;
    }

}
