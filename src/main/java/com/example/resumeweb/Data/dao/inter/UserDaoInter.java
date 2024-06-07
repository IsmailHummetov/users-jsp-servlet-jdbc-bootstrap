package com.example.resumeweb.Data.dao.inter;

import com.example.resumeweb.Data.bean.IdPassword;
import com.example.resumeweb.Data.bean.User;

import java.util.List;

public interface UserDaoInter {

    List<User> getAll();

    List<User> getByNameSurname(String firstname, String lastname);

    User getbyId(int UserId);

    IdPassword getPasswordByEmail(String email);

    boolean addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(int UserId);
}
