package com.example.tekushop.repository;

import com.example.tekushop.model.Clothing;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClothingRepo implements ClothingInterfaceRepo<Clothing> {


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
    public List<Clothing> findClothes(int id, String clothesType, String color, int minPrice, int maxPrice, int limit, int offset) {
        BaseDatabase baseDatabase = new BaseDatabase();
        Connection connection = baseDatabase.getConnection();
        List<Clothing> clothingList = new ArrayList<>();

        String query = "call getClothing(?,?,?,?,?,?,?)";
        try {
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, id);
            callableStatement.setString(2, clothesType);
            callableStatement.setString(3, color);
            callableStatement.setInt(4, minPrice);
            callableStatement.setInt(5, maxPrice);
            callableStatement.setInt(6, limit);
            callableStatement.setInt(7, offset);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int clothing_id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String clothesType1 = resultSet.getString("clothesType");
                String clothesColor = resultSet.getString("color");
                int size = resultSet.getInt("size");
                LocalDate dateAdded = resultSet.getDate("dateAdded").toLocalDate();
                LocalDate dateModified = resultSet.getDate("dateModified").toLocalDate();
                String[] images = resultSet.getString("images").split(",");
                int price = resultSet.getInt("price");
                int stock = resultSet.getInt("stock");
                int sold = resultSet.getInt("sold");
                String description = resultSet.getString("description");
                Clothing clothing = new Clothing(clothing_id, name, clothesType1, clothesColor, size, dateAdded, dateModified, images, price, stock, sold, description);
                clothingList.add(clothing);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
//        System.out.println(clothingList);
        return clothingList;
    }
}