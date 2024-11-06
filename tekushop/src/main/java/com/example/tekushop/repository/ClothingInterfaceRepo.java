package com.example.tekushop.repository;

import java.util.List;

public interface ClothingInterfaceRepo <Clothing>{
    void addClothes(Clothing clothing);
    void deleteClothes(int id);
    void updateClothes(Clothing clothing);
    List<Clothing> findClothes(int id,String clothesType,String color,  int minPrice,int maxPrice, int limit,int offset);

}
