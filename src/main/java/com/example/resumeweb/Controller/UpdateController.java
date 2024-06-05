package com.example.resumeweb.Controller;

import com.example.resumeweb.bean.User;
import com.example.resumeweb.dao.inter.UserDaoInter;
import com.example.resumeweb.main.Context;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "UpdateController", value = "/userupdate")
public class UpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userIdStr = request.getParameter("id");
            if (userIdStr == null || userIdStr.trim().isEmpty()) {
                throw new IllegalArgumentException("id is not specified");
            }
            Integer userId = Integer.parseInt(userIdStr);
            UserDaoInter userDao = Context.instanceUserDao();
            User u = userDao.getbyId(userId);
            if (u == null) {
                throw new IllegalArgumentException("There is no user with this id");
            }
            request.setAttribute("user",u);
            request.getRequestDispatcher("userupdate.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("error?msg=" + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstname = request.getParameter("firstname");
        String lastname=request.getParameter("lastname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String profileDescription = request.getParameter("profile_description");
        String address = request.getParameter("address");
        UserDaoInter userDao = Context.instanceUserDao();
        User u = userDao.getbyId(id);
        u.setFirstname(firstname);
        u.setLastname(lastname);
        u.setEmail(email);
        u.setPhone(phone);
        u.setProfileDescription(profileDescription);
        u.setAddress(address);
        userDao.updateUser(u);
        response.sendRedirect("users");
        System.out.println(u);
    }
}
