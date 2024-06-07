package com.example.resumeweb.Data.main;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.resumeweb.Data.bean.User;
import com.example.resumeweb.Data.dao.inter.UserDaoInter;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        UserDaoInter userDao = Context.instanceUserDao();
        User u = userDao.getbyId(9);
        userDao.addUser(u);
    }
}
