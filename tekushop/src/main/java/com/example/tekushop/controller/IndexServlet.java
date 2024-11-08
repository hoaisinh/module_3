package com.example.tekushop.controller;

import com.example.tekushop.model.Clothing;
import com.example.tekushop.service.clothing.ClothingService;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name="IndexServlet", value="/index")
public class IndexServlet extends HttpServlet
{
    ClothingService clothingService = new ClothingService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");


        Map<String,List<Clothing>> indexData = new HashMap<>();
       List<Clothing> clothingList = clothingService.findClothes(0,"%","%",0,0,999,0);
//        System.out.println(clothingList);
        indexData.put("clothingList", clothingList);

        request.setAttribute("indexData", indexData);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

}
