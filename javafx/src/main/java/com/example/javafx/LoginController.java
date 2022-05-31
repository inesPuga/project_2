package com.example.javafx;

import com.example.database.DAL.Utilizador;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.example.database.BLL.*;

import java.io.IOException;
import java.util.List;

public class LoginController {
    @FXML
    private TextField textField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label msgLabel;
    private final UserSession userSession = UserSession.getInstance();

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    // - - - - - - - - - - button clicks - - - - - - - - - - //
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    @FXML
    public void onLoginButtonClick(javafx.event.ActionEvent event) throws IOException {
        String password = LogicDataBase.passEncrypt(passwordField.getText());
        int res = verifyLogin(textField.getText(), password);
        Utilizador user = searchuserbylogin(textField.getText(), password);
        /*if(user.getStatus() == 0) {
            msgLabel.setText("O utilizador está desativado");
        }*/
        //if(user.getStatus() == 1) {
        if(res == 0) {
            //msgLabel.setText("Dados corretos");
            if(user.getStatus() == 0) {
                msgLabel.setText("O utilizador está desativado");
            }
            else {
                // - - - - admin menu - - - - //
                if(user.getCargo().equals("A")) {
                    //userSession.in(user);
                    Scene scene_menuAdmin = Logic.changePanel(event, "list_users.fxml", "Conserveira", ListUsersController.class);
                    // move the user to the next panel
                }
                // - - - - manager menu - - - - //
                if(user.getCargo().equals("G")) {
                    userSession.in(user);
                    Logic.changePanel(event, "menu_manager-view.fxml", "Conserveira", MenuManagerController.class);
                    // move the user to the next panel
                    //scene_menuManager.setUserData(user);
                }
                if(user.getCargo().equals("GV")) {
                    userSession.in(user);
                    Logic.changePanel(event, "menuSalesManager-view.fxml", "Conserveira", MenuSalesManagerController.class);
                    // move the user to the next panel
                    //scene_menuManager.setUserData(user);
                }
                if(user.getCargo().equals("GS")) {
                    userSession.in(user);
                    Logic.changePanel(event, "stock_manager-view.fxml", "Conserveira", StockManagerController.class);
                    // move the user to the next panel
                    //scene_menuManager.setUserData(user);
                }
            }
        }
        if(res == 1) {
            msgLabel.setText("Dados incorretos");
        }
        //}

    }

    // - - - - hyper link click (change password) - - - - //
    public void onHyperLinkClick(javafx.event.ActionEvent event) throws IOException {
        Logic.changePanel(event, "change_password-view.fxml", "Conserveira", ChangePasswordController.class);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - //
    // - - - - - - - - - - login logic - - - - - - - - - - //
    // - - - - - - - - - - - - - - - - - - - - - - - - - - //
    public int verifyLogin(String text1, String text2) {
        List<Utilizador> users = UtilizadorBLL.readAll();
        System.out.println(users);
        for(Utilizador i : users) {
            if(i.getUsername().equals(text1) || i.getEmail().equals(text1)) {
                if(i.getPassword().equals(text2)) {
                    return 0; //user exist -> login -> success
                }
            }
        }
        return 1;   //error
    }

    public Utilizador searchuserbylogin(String text1, String text2) {
        List <Utilizador> users = UtilizadorBLL.readAll();
        System.out.println(users);
        for(Utilizador i : users) {
            if(i.getUsername().equals(text1) || i.getEmail().equals(text1)) {
                if(i.getPassword().equals(text2)) {
                    return i;
                }
            }
        }
        return null;
    }

}
