package com.example.tekushop.controller;

import com.example.tekushop.common.FileUpload;
import com.example.tekushop.model.Clothing;
import com.example.tekushop.model.User;
import com.example.tekushop.service.clothing.ClothingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "AddClothingServlet", urlPatterns = {"/clothing-management/add"})
@MultipartConfig
public class AddClothingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Check user permission
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null || user.getRole().equals("customer")){
            response.sendRedirect("/index");
        }
        request.setAttribute("user",user);

        response.sendRedirect("/add-clothing.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            if(user == null || user.getRole().equals("customer")){
                resp.sendRedirect("/index");
            }
            req.setAttribute("user",user);
            ClothingService clothingService = new ClothingService();

            //Add clothing
            String name = req.getParameter("name");
            String clothesType = req.getParameter("clothesType");
            String color = req.getParameter("color");
        System.out.println(req.getParameter("color"));
            int size = Integer.parseInt(req.getParameter("size"));
            int price = Integer.parseInt(req.getParameter("price"));
            String description = req.getParameter("description");
            int stock = Integer.parseInt(req.getParameter("quantity"));

            Part part = req.getPart("images");
            String[] images = new String[1];

            LocalDate dateAdded = LocalDate.now();
            LocalDate dateModified = LocalDate.now();

            String uploadPath = req.getServletContext().getRealPath("") + File.separator+"uploads"+File.separator+"image";
            String filePath = FileUpload.uploadFile(part, uploadPath);
            images[0] = filePath;
            Clothing clothing = new Clothing(name, clothesType, color, size, dateAdded,dateModified,images, price,stock,1, description);
            clothingService.addClothes(clothing);

            resp.sendRedirect("/clothing-management");
    }
}
