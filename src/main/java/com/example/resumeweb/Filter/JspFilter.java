package com.example.resumeweb.Filter;

import com.example.resumeweb.Util.ControllerUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "JspFilter", urlPatterns = {"*"})
public class JspFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletResponse res = (HttpServletResponse) response;
            HttpServletRequest req = (HttpServletRequest) request;
            if (req.getRequestURI().contains(".jsp")) {
                ControllerUtil.errorPage(res, new IllegalArgumentException("JSP page not found"));
            }
            if (!req.getRequestURI().contains("/login") && req.getSession().getAttribute("test") == null) {
                ControllerUtil.errorPage(res, new IllegalArgumentException("Not found!!!"));
            } else {
                filterChain.doFilter(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
