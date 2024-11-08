package com.example.tekushop.service.clothing;

import com.example.tekushop.model.Clothing;
import com.example.tekushop.repository.clothing.ClothingRepo;

import java.util.List;

public class ClothingService implements ClothingInterfaceService<Clothing>{

    @Override
    public void addClothes(Clothing clothing) {
    ClothingRepo clothingRepo = new ClothingRepo();
    clothingRepo.addClothes(clothing);
    }

    @Override
    public void deleteClothes(int id) {
        ClothingRepo clothingRepo = new ClothingRepo();
        clothingRepo.deleteClothes(id);
    }

    @Override
    public void updateClothes(Clothing clothing) {

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


}
