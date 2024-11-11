package com.example.tekushop.controller.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogouServlet", urlPatterns = "/logout")
public class LogouServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/index");
    }
}
