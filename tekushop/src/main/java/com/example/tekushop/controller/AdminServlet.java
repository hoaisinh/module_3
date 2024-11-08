package com.example.tekushop.controller;

import com.example.tekushop.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AdminServlet", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");
        if(user == null){
            response.sendRedirect(request.getContextPath() + "/login");
        } else if (user.getRole().equals("customer")) {
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }else {
            request.setAttribute("role", user.getRole());
            request.setAttribute("userObj", user);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin.jsp");
            requestDispatcher.forward(request, response);
        }

    }
}
