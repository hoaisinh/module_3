package com.example.tekushop.service.clothing;

import com.example.tekushop.common.Validation;
import com.example.tekushop.model.Clothing;
import com.example.tekushop.repository.clothing.ClothingRepo;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

public class ClothingService implements ClothingInterfaceService<Clothing>{

    @Override
    public boolean addClothes(String name, String clothesType, String color, int size, LocalDate dateAdded, LocalDate dateModified, String[] images, int price, int stock, int sold, String description, HttpServletRequest request) {

        if(!Validation.isCorrectLength(name, 5, 100)){
            request.setAttribute("error", "Name must be between 5 and 100 characters");
            return false;
        }
        if(!Validation.isCorrectLength(clothesType, 3, 50)){
            request.setAttribute("error", "Clothes Type must be between 3 and 50 characters");
            return false;
        }
        if(!Validation.isCorrectLength(color, 3, 50)){
            request.setAttribute("error", "Color must be between 3 and 50 characters");
            return false;
        }
        if(!Validation.isCorrectLength(description, 5, 2000)){
            request.setAttribute("error", "Description must be between 5 and 2000 characters");
            return false;
        }
        if(!Validation.isNumberInRange(size, 20, 100)){
            request.setAttribute("error", "Size must be between 20 and 100");
            return false;
        }
        if(!Validation.isNumberInRange(price, 1, 20000)){
            request.setAttribute("error", "Price must be between 1 and 20000");
            return false;
        }
        if(!Validation.isNumberInRange(stock, 1, 10000)){
            request.setAttribute("error", "Stock must be between 1 and 10000");
            return false;
        }

        Clothing clothing = new Clothing(name, clothesType, color, size, dateAdded, dateModified, images, price, stock, sold, description);
        ClothingRepo clothingRepo = new ClothingRepo();
        clothingRepo.addClothes(clothing);
        return true;
    }

    @Override
    public void deleteClothes(int id) {
        ClothingRepo clothingRepo = new ClothingRepo();
        clothingRepo.deleteClothes(id);
    }

    @Override
    public Boolean updateClothes(int id, String name, String clothesType, String color, int size, int price, int stock, String description, String imagePath, HttpServletRequest request) {

        //Validate input
        if(!Validation.isCorrectLength(name, 5, 100)){
            request.setAttribute("error", "Name must be between 5 and 100 characters");
            return false;
        }
        if(!Validation.isCorrectLength(clothesType, 3, 50)){
            request.setAttribute("error", "Clothes Type must be between 3 and 50 characters");
            return false;
        }
        if(!Validation.isCorrectLength(color, 3, 50)){
            request.setAttribute("error", "Color must be between 3 and 50 characters");
            return false;
        }
        if(!Validation.isCorrectLength(description, 5, 2000)){
            request.setAttribute("error", "Description must be between 5 and 2000 characters");
            return false;
        }
        if(!Validation.isNumberInRange(size, 20, 100)){
            request.setAttribute("error", "Size must be between 20 and 100");
            return false;
        }
        if(!Validation.isNumberInRange(price, 1, 20000)){
            request.setAttribute("error", "Price must be between 1 and 20000");
            return false;
        }
        if(!Validation.isNumberInRange(stock, 1, 10000)){
            request.setAttribute("error", "Stock must be between 1 and 10000");
            return false;
        }
        LocalDate dateAdded = LocalDate.now();
        LocalDate dateModified = LocalDate.now();
        Clothing clothing = new Clothing(id, name, clothesType, color, size,dateAdded,dateModified ,new String[]{imagePath}, price, stock, 0, description);
        ClothingRepo clothingRepo = new ClothingRepo();
        clothingRepo.updateClothes(clothing);
        return true;
    }


    @Override
    public List<Clothing> findClothes(int id, String clothesType, String color,  int minPrice, int maxPrice,int limit,int offset) {
        ClothingRepo clothingRepo = new ClothingRepo();
        if(clothesType == null || clothesType.equals("")){
            clothesType = "%";
        }
        if(color == null || color.equals("")){
            color = "%";
        }
        if(limit == 0){
            limit = 10;
        }

        return clothingRepo.findClothes(id, clothesType, color, minPrice, maxPrice, limit, offset);
    }

    @Override
    public List<String> listClothesType() {
        ClothingRepo clothingRepo = new ClothingRepo();
        return clothingRepo.listClothesType();
    }

    @Override
    public List<String> listClothesColor() {
        ClothingRepo clothingRepo = new ClothingRepo();
        return clothingRepo.listClothesColor();
    }
    public Clothing getClothingById(int id){
        List<Clothing> clothingList = findClothes(id, null, null, 0, 0, 0, 0);
        if(clothingList.size() > 0){
            return clothingList.get(0);
        }else {
            return null;
        }
    }


}
