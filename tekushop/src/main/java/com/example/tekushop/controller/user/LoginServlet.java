package com.example.tekushop.controller.user;

import com.example.tekushop.service.UserService;
import com.example.tekushop.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="LoginServlet",value="/login")
public class LoginServlet extends HttpServlet {

        private UserService userLoginService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.jsp");
    }

    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String username = request.getParameter("username");
            User user = userLoginService.getUserByUsername(username,request);
            if(user == null){
                request.setAttribute("error","User not found");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request,response);
            }else {
                String password = request.getParameter("password");
                if (!userLoginService.login(username, password)) {
                    request.setAttribute("error", "Password is incorrect");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                    dispatcher.forward(request, response);
                }else {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    if(user.getRole().equals(("customer"))){
                        response.sendRedirect(request.getContextPath() + "/index");
                    }else {
                        response.sendRedirect(request.getContextPath() + "/admin");
                    }
                }
            }

        }
}
