package com.example.tekushop.controller.clothing;

import com.example.tekushop.model.Clothing;
import com.example.tekushop.model.User;
import com.example.tekushop.service.clothing.ClothingService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "DeleteClothingServlet", urlPatterns = {"/clothing-management/delete"})
public class DeleteClothingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, IOException {
        //Check user permission
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole().equals("customer")) {
            response.sendRedirect("/index");
        }
        request.setAttribute("user", user);

        //Get clothing id
        int clothingId = Integer.parseInt(request.getParameter("clothingId"));
        ClothingService clothingService = new ClothingService();
        Clothing clothing = clothingService.findClothes(clothingId, null, null, 0, 9999999, 1, 0).get(0);

        if (clothing == null) {
            response.sendRedirect("/clothing-management");
        }

        //Delete clothing
        clothingService.deleteClothes(clothingId);
        response.sendRedirect("/clothing-management");
     }
    }


