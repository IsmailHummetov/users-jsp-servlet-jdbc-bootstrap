package com.example.resumeweb.dao.inter;

import com.example.resumeweb.bean.User;

import java.util.List;

public interface UserDaoInter {

    List <User> getAll();
    List <User> getByNameSurname(String firstname,String lastname);
    User getbyId (int UserId);
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(int UserId);
}
