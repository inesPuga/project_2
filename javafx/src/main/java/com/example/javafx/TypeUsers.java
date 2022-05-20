package com.example.javafx;

import java.util.List;

public class TypeUsers {

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    // - - - - - - - - - - users for combo box - - - - - - - - - - //
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //

    private int id;
    private String name;

    public TypeUsers(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

}
