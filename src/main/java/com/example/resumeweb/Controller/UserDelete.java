package com.example.resumeweb.Controller;

import com.example.resumeweb.Data.bean.User;
import com.example.resumeweb.Data.dao.inter.UserDaoInter;
import com.example.resumeweb.Data.main.Context;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "UserDelete", value = "/userdelete")
public class UserDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = Integer.parseInt(request.getParameter("id"));
        UserDaoInter userDao = Context.instanceUserDao();
        User u = userDao.getbyId(userId);
        userDao.deleteUser(userId);
        response.sendRedirect("users");
    }
}
