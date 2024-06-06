package com.example.resumeweb.Controller;

import com.example.resumeweb.Data.bean.User;
import com.example.resumeweb.Data.dao.inter.UserDaoInter;
import com.example.resumeweb.Data.main.Context;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserController", value = "/users")
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDaoInter userDao = Context.instanceUserDao();
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        List<User> users = userDao.getByNameSurname(firstname, lastname);
        request.setAttribute("users",users);
        request.getRequestDispatcher("users.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
