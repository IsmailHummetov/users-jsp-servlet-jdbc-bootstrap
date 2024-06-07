package com.example.resumeweb.Data.bean;

public class IdPassword {
    private String password;
    private Integer id;

    public IdPassword(String password, Integer id) {
        this.password = password;
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
