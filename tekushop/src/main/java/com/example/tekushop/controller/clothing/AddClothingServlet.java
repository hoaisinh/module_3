package com.example.tekushop.controller.clothing;

import com.example.tekushop.common.FileUpload;
import com.example.tekushop.common.FileUploadHandler;
import com.example.tekushop.model.Clothing;
import com.example.tekushop.model.User;
import com.example.tekushop.service.clothing.ClothingService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

        RequestDispatcher dispatcher = request.getRequestDispatcher("/add-clothing.jsp");
        dispatcher.forward(request, response);
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
            int size = Integer.parseInt(req.getParameter("size"));
            int price = Integer.parseInt(req.getParameter("price"));
            String description = req.getParameter("description");
            int stock = Integer.parseInt(req.getParameter("quantity"));
            LocalDate dateAdded = LocalDate.now();
            LocalDate dateModified = LocalDate.now();
            int sold = 1;
            String[] images = new String[1];


            Part filePart = req.getPart("images");

        String filePath = "";
        if (filePart != null) {
                String fileName = filePart.getSubmittedFileName();
                InputStream fileContent = filePart.getInputStream();
                FileUploadHandler fileUploadHandler = new FileUploadHandler();
                String fileUrl = fileUploadHandler.uploadFileToS3(fileName, fileContent);
                if (fileUrl != null) {

                    filePath = fileUrl;
                } else {
                    resp.getWriter().println("Failed to upload file.");
                }
            } else {
                resp.getWriter().println("No file uploaded.");
            }
            images[0] = filePath;

            boolean isAdded = clothingService.addClothes(name, clothesType, color, size,dateAdded,dateModified,images, price, stock, sold, description,  req);
            if(!isAdded){
                Clothing tempClothing = new Clothing(name, clothesType, color, size, dateAdded, dateModified, images, price, stock, sold, description);
                req.setAttribute("clothing", tempClothing);
                req.getRequestDispatcher("/add-clothing.jsp").forward(req, resp);

            }else {
                req.setAttribute("success", "Clothing added successfully");
                RequestDispatcher dispatcher = req.getRequestDispatcher("/add-clothing.jsp");
                dispatcher.forward(req, resp);
            }
            resp.sendRedirect("/clothing-management");
    }
}
