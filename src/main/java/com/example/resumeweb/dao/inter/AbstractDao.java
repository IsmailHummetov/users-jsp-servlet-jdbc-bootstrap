package com.example.resumeweb.dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;


public class AbstractDao {

    public Connection connection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/resume";
        String username = "root";
        String password="12345";
        Connection c = DriverManager.getConnection(url,username,password);
        return c;
    }

}
