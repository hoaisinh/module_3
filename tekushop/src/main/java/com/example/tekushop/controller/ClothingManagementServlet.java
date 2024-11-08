package com.example.tekushop.controller;

import com.example.tekushop.model.Clothing;
import com.example.tekushop.model.User;
import com.example.tekushop.service.clothing.ClothingService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ClothingManagementServlet", urlPatterns = {"/clothing-management"})
public class ClothingManagementServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Check user permission
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null || user.getRole().equals("customer")){
            response.sendRedirect("/index");
        }
        request.setAttribute("user",user);
        ClothingService clothingService = new ClothingService();

        //create search form attribute
        List<String> clothesTypeList = clothingService.listClothesType();
        List<String> colorList = clothingService.listClothesColor();
        request.setAttribute("clothesTypeList",clothesTypeList);
        request.setAttribute("colorList",colorList);
        Map<String,String> map = new HashMap<>();
        map.put("minPrice","Min Price");
        map.put("maxPrice","Max Price");
        int limit = 999;
        int offset = 0;
        request.setAttribute("searchFromAtt",map);

        //Get clothing list
        String clothesType = request.getParameter("clothesType");
        String color = request.getParameter("color");
        int minPrice = ((request.getParameter("minPrice") == null) || (request.getParameter("minPrice") == "")) ? 0 : Integer.parseInt(request.getParameter("minPrice"));
        int maxPrice = ((request.getParameter("maxPrice") == null) || (request.getParameter("maxPrice") == "")) ? 9999999 : Integer.parseInt(request.getParameter("maxPrice"));

        List<Clothing> clothingList = clothingService.findClothes(0,clothesType,color,minPrice,maxPrice,limit,offset);
        request.setAttribute("clothingList",clothingList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("clothing-management.jsp");
        dispatcher.forward(request,response);
    }
}
