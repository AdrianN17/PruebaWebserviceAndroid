package com.example.modelo;

import java.io.Serializable;

@SuppressWarnings("serial")

public class usuario implements Serializable {
    private String user;
    private String pass;

    public usuario() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
