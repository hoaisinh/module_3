package com.example.tekushop.service;

import com.example.tekushop.model.Clothing;
import com.example.tekushop.repository.ClothingRepo;

import java.util.List;

public class ClothingService implements ClothingInterfaceService<Clothing>{

    @Override
    public void addClothes(Clothing clothing) {

    }

    @Override
    public void deleteClothes(int id) {

    }

    @Override
    public void updateClothes(Clothing clothing) {

    }

    @Override
    public List<Clothing> findClothes(int id, String clothesType, String color,  int minPrice, int maxPrice,int limit,int offset) {
        ClothingRepo clothingRepo = new ClothingRepo();
        if(clothesType == null){
            clothesType = "%";
        }
        if(color == null){
            color = "%";
        }
        if(limit == 0){
            limit = 10;
        }
        return clothingRepo.findClothes(id, clothesType, color, minPrice, maxPrice, limit, offset);
    }


}
