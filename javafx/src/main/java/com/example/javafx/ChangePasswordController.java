package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.example.database.DAL.*;
import com.example.database.BLL.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ChangePasswordController implements Initializable {
    @FXML
    private TextField textField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordField1;
    @FXML
    private Button confirmButton;
    @FXML
    private Label label;
    @FXML
    private ImageView back;

    // - - - - initialize - - - - //
    // called to initialize a controller after its root element has been completely processed
    public void initialize(URL url, ResourceBundle rb) {}

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    // - - - - - - - - - - button clicks - - - - - - - - - - //
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - //
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

    public void onConfirmButtonClick(javafx.event.ActionEvent event) throws IOException {
        Utilizador user = search_user(textField.getText());
        if(user == null) {
            label.setText("E-mail incorreto");
        }
        else {
            if(check_passwords(passwordField.getText(), passwordField1.getText())==1) {
                label.setText("As palavras-passe são diferentes");
            }
            else {
                String password = LogicDataBase.passEncrypt(passwordField.getText());
                user.setPassword(password);
                UtilizadorBLL.update(user);
                Logic.changePanel(event, "login-view.fxml", "Conserveira", LoginController.class);
            }
        }
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    // - - - - - - - - - - change password logic - - - - - - - - - - //
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    public int check_passwords(String str1, String str2) {
        if(str1.equals(str2)) {
            return 0;
        }
        return 1; //error
    }

    public Utilizador search_user(String str) {
        for(Utilizador u : UtilizadorBLL.readAll()) {
            if(u.getEmail().equals(str)) {
                return u;
            }
        }
        return null;
    }

}
