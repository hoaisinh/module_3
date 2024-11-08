package com.example.tekushop.repository.clothing;

import com.example.tekushop.model.Clothing;
import com.example.tekushop.repository.BaseDatabase;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClothingRepo implements ClothingInterfaceRepo<Clothing> {


    @Override
    public void addClothes(Clothing clothing) {
        BaseDatabase baseDatabase = new BaseDatabase();
        Connection connection = baseDatabase.getConnection();
        String query = "INSERT INTO clothing (name, clothesType, color, size, dateAdded, dateModified, images, price, stock, sold, description) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, clothing.getName());
            preparedStatement.setString(2, clothing.getClothesType());
            preparedStatement.setString(3, clothing.getColor());
            preparedStatement.setInt(4, clothing.getSize());
            preparedStatement.setDate(5, Date.valueOf(clothing.getDateAdded()));
            preparedStatement.setDate(6, Date.valueOf(clothing.getDateModified()));
            preparedStatement.setString(7, String.join(",", clothing.getImages()));
            preparedStatement.setInt(8, clothing.getPrice());
            preparedStatement.setInt(9, clothing.getStock());
            preparedStatement.setInt(10, clothing.getSold());
            preparedStatement.setString(11, clothing.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteClothes(int id) {
        BaseDatabase baseDatabase = new BaseDatabase();
        Connection connection = baseDatabase.getConnection();
        String query = "DELETE FROM clothing WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
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

    @Override
    public List<String> listClothesType() {
        BaseDatabase baseDatabase = new BaseDatabase();
        Connection connection = baseDatabase.getConnection();
        List<String> clothingTypeList = new ArrayList<>();
        
        String query = "SELECT DISTINCT clothesType FROM clothing";
        try {
            CallableStatement callableStatement = connection.prepareCall(query);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String clothesType = resultSet.getString("clothesType");
                clothingTypeList.add(clothesType);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return clothingTypeList;
    }

    @Override
    public List<String> listClothesColor() {
        BaseDatabase baseDatabase = new BaseDatabase();
        Connection connection = baseDatabase.getConnection();
        List<String> clothingColorList = new ArrayList<>();

        String query = "SELECT DISTINCT color FROM clothing";
        try {
            CallableStatement callableStatement = connection.prepareCall(query);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String clothesColor = resultSet.getString("color");
                clothingColorList.add(clothesColor);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return clothingColorList;
    }
}