package com.example.javafx;

import com.example.database.DAL.Utilizador;

public class UserSession {
    private static UserSession uniqueInstance = null;
    private Utilizador user;

    private UserSession() {}

    public static UserSession getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new UserSession();
        return uniqueInstance;
    }

    public void in(Utilizador user) {
        this.user = user;
    }

    public void out() {
        user = null;
    }

    public Utilizador get() {
        return user;
    }
}
