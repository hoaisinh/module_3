package com.example.tekushop.controller.clothing;

import com.example.tekushop.common.FileUploadHandler;
import com.example.tekushop.common.Validation;
import com.example.tekushop.model.Clothing;
import com.example.tekushop.model.User;
import com.example.tekushop.service.clothing.ClothingService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "EditClothingServlet", urlPatterns = "/edit-clothing")
@MultipartConfig
public class EditClothingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if(user == null || user.getRole().equals("customer")) {
            resp.sendRedirect("/index");
        } else {
            ClothingService clothingService = new ClothingService();
            String idParam = req.getParameter("id");
            if(!Validation.isPrimaryId(idParam)){
                resp.sendRedirect("/index");
            }

            int clothingId = Integer.parseInt(idParam);
            Clothing clothing = clothingService.getClothingById(clothingId);
            if(clothing == null) {
                resp.sendRedirect("/index");
            }else {
                req.setAttribute("clothing", clothing);
                req.getRequestDispatcher(req.getContextPath()+"/edit-clothing.jsp").forward(req, resp);

            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Edit clothing
        int clothingId = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String clothesType = request.getParameter("clothesType");
        String color = request.getParameter("color");
        int size = Integer.parseInt(request.getParameter("size"));
        int price = Integer.parseInt(request.getParameter("price"));
        String description = request.getParameter("description");
        int stock = Integer.parseInt(request.getParameter("quantity"));

        String oldImagePath = request.getParameter("oldImages");
        System.out.println("oldImagePath: "+oldImagePath);
        String imagePath = null;
        imagePath = oldImagePath;
        if(request.getPart("images").getSize() == 0) {
            imagePath = oldImagePath;
        }else {
            Part filePart = request.getPart("images");
            String fileName = filePart.getSubmittedFileName();
            InputStream fileContent = filePart.getInputStream();
            FileUploadHandler fileUploadHandler = new FileUploadHandler();
            String fileUrl = fileUploadHandler.uploadFileToS3(fileName, fileContent);
            if (fileUrl != null) {
                imagePath = fileUrl;
            } else {
                response.getWriter().println("Failed to upload file.");
            }
        }
        Clothing tempClothing = new Clothing(clothingId, name, clothesType, color, size, null, null, new String[]{imagePath}, price, stock, 0, description);
        ClothingService clothingService = new ClothingService();
        Boolean isEdited = clothingService.updateClothes(clothingId, name, clothesType, color, size, price, stock, description, imagePath,request);
        if(!isEdited) {

            request.setAttribute("clothing", tempClothing);
            request.getRequestDispatcher("/edit-clothing.jsp").forward(request, response);
        }else {
            response.sendRedirect("/clothing-management");
        }


    }
}
