package com.example.resumeweb.Util;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ControllerUtil {
    public static void errorPage(HttpServletResponse response , Exception ex){
        try {
            response.sendRedirect("error?msg="+ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
