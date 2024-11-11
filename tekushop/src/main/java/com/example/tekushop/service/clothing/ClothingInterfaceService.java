package com.example.tekushop.service.clothing;

import org.apache.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

public interface ClothingInterfaceService<Clothing> {

    boolean addClothes(String name, String clothesType, String color, int size, LocalDate dateAdded, LocalDate dateModified, String[] images, int price, int stock, int sold, String description, HttpServletRequest request);

    void deleteClothes(int id);
    Boolean updateClothes(int id, String name, String clothesType, String color, int size, int price, int stock, String description, String imagePath, HttpServletRequest request);
    List<Clothing> findClothes(int id, String clothesType,String color, int minPrice,int maxPricen,int limit,int offset);
    List<String> listClothesType();
    List<String> listClothesColor();
}
