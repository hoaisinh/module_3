package com.example.tekushop.service.clothing;

import java.util.List;

public interface ClothingInterfaceService<Clothing> {
    void addClothes(Clothing clothing);
    void deleteClothes(int id);
    void updateClothes(Clothing clothing);
    List<Clothing> findClothes(int id, String clothesType,String color, int minPrice,int maxPricen,int limit,int offset);
    List<String> listClothesType();
    List<String> listClothesColor();
}
