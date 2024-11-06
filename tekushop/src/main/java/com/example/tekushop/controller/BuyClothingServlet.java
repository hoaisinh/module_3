package com.example.tekushop.controller;

import com.example.tekushop.model.Order;
import com.example.tekushop.model.User;
import com.example.tekushop.service.order.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BuyClothingServlet", value = "/buy-clothing")
public class BuyClothingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        OrderService orderService = new OrderService();
        if (user == null ) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else if(user.getRole().equals("customer")) {
            int productId = Integer.parseInt(request.getParameter("id"));
            int userId = user.getId();
            List<Order> listOrder = orderService.getListOrder(userId);
            System.out.println(listOrder);
            request.setAttribute("listOrder", listOrder);
            request.setAttribute("product_id", productId);
            request.setAttribute("user_id", userId);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("buy-clothing.jsp");
            requestDispatcher.forward(request, response);
        }else {
            response.sendRedirect(request.getContextPath() + "/admin");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderService orderService = new OrderService();
        int user_id = Integer.parseInt(req.getParameter("user_id"));
        int product_id = Integer.parseInt(req.getParameter("product_id"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        Boolean isOrder = orderService.addOrder(user_id, product_id, quantity);
        if(isOrder) {
            req.setAttribute("message", "Order is successful");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("buy-clothing.jsp");
            requestDispatcher.forward(req, resp);
        }else {
            resp.sendRedirect(req.getContextPath() + "/error");
        }
    }
}
