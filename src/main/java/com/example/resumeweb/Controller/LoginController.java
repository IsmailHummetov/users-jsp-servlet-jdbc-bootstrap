package com.example.resumeweb.Controller;

import com.example.resumeweb.Data.dao.inter.UserDaoInter;
import com.example.resumeweb.Data.main.Context;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            UserDaoInter userDao = Context.instanceUserDao();
            if (userDao.getPasswordByEmail(email)==null){
                response.sendRedirect("login");
                throw new IllegalArgumentException("There is no user with this email");
            }
            else {
                if (userDao.getPasswordByEmail(email).equals(password)) {
                    request.getSession().setAttribute("test",true);
                    response.sendRedirect("users");
                }
                else {
                    response.sendRedirect("login");
                    throw new IllegalArgumentException("Password is wrong");
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
