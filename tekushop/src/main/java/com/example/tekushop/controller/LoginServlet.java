package com.example.tekushop.controller;

import com.example.tekushop.service.UserLoginService;
import com.example.tekushop.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="LoginServlet",value="/login")
public class LoginServlet extends HttpServlet {

        private UserLoginService userLoginService = new UserLoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.jsp");
    }

    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (userLoginService.login(username, password)) {
                User user = userLoginService.getUserByUsername(username);
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                if(user.getRole().equals(("customer"))){
                    response.sendRedirect(request.getContextPath() + "/index");
                }else {
                    response.sendRedirect(request.getContextPath() + "/admin");
                }


            } else {
                response.sendRedirect("login.jsp?error=true");
            }
        }
}
