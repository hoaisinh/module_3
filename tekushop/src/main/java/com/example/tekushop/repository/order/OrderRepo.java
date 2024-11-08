package com.example.tekushop.repository.order;

import com.example.tekushop.common.DateTime;
import com.example.tekushop.model.Clothing;
import com.example.tekushop.model.Order;
import com.example.tekushop.model.OrderItem;
import com.example.tekushop.model.User;
import com.example.tekushop.repository.BaseDatabase;
import com.example.tekushop.repository.clothing.ClothingRepo;
import com.example.tekushop.repository.UserRepo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderRepo implements IOrderRepo {
    public Clothing getClothing(int productId) {
        ClothingRepo ClothingRepo = new ClothingRepo();
        List<Clothing> clothingList = null;
        clothingList = ClothingRepo.findClothes(productId, "%", "%", 0, 999, 999, 0);
        return clothingList.get(0);

    }

    @Override
    public boolean addOrder(int userId, int productId, int quantity) {
        BaseDatabase baseDatabase = new BaseDatabase();
        Connection connection = baseDatabase.getConnection();
        Clothing clothing = getClothing(productId);
        int last_order_id = 0;
        boolean check = true;

        int total_amount = clothing.getPrice() * quantity;
        String status = "pending";
        LocalDate order_date = LocalDate.now();
        String payment_method = "cash";
        String query = "INSERT INTO Orders (customer_id, order_date, status, total_amount, payment_method ) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setDate(2, Date.valueOf(order_date));
            statement.setString(3, status);
            statement.setInt(4, total_amount);
            statement.setString(5, payment_method);
            statement.executeUpdate();
            query = "SELECT MAX(order_id) as id FROM Orders";
            PreparedStatement statement1 = connection.prepareStatement(query);
            ResultSet resultSet = statement1.executeQuery();
            while (resultSet.next()) {
                last_order_id = resultSet.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // insert into order_details
        query = "INSERT INTO order_items (order_id, clothing_id,quantity,unit_price,total_price) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, last_order_id);
            statement.setInt(2, productId);
            statement.setInt(3, quantity);
            statement.setInt(4, clothing.getPrice());
            statement.setInt(5, total_amount);
            int row = statement.executeUpdate();
            if (row == 0) {
                check = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;

    }

    @Override
    public void updateOrder(int orderId, String status) {

    }

//    @Override
//    public void showOrder(int userId) {
//        BaseDatabase baseDatabase = new BaseDatabase();
//        Connection connection = baseDatabase.getConnection();
//        List<Order> orderList = new ArrayList<>();
//        String query = "SELECT * FROM Orders WHERE customer_id = ?";
//
//
//    }
    public List<Order> getListOrder(int userId){
        UserRepo userRepo = new UserRepo();
        BaseDatabase baseDatabase = new BaseDatabase();
        Connection connection = baseDatabase.getConnection();
        List<Order> orderList = new ArrayList<>();
        String query = "SELECT * FROM Orders WHERE customer_id = ? OR ? = 0";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int orderID = resultSet.getInt("order_id");
                int customerID = resultSet.getInt("customer_id");
                User user = userRepo.findById(customerID);
                LocalDate orderDate = DateTime.convertStringToLocalDate(resultSet.getString("order_date"));
                String status = resultSet.getString("status");
                Double totalAmount = (double) resultSet.getInt("total_amount");
                String paymentStatus = resultSet.getString("payment_status");
                String paymentMethod = resultSet.getString("payment_method");
                LocalDate createdAt = DateTime.convertStringToLocalDate(resultSet.getString("created_at"));
                LocalDate updatedAt = DateTime.convertStringToLocalDate(resultSet.getString("updated_at"));
                List<OrderItem> orderItems = getListOrderItem(orderID);
                Order order = new Order(orderID, user, orderDate, status, totalAmount, paymentStatus, paymentMethod, createdAt, updatedAt, orderItems);
                orderList.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  orderList;
    }
    public List<OrderItem> getListOrderItem(int orderID) {
        BaseDatabase baseDatabase = new BaseDatabase();
        Connection connection = baseDatabase.getConnection();
        List<OrderItem> orderItemList = new ArrayList<>();
        String query = "SELECT * FROM order_items WHERE order_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, orderID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int orderItemID = resultSet.getInt("order_item_id");
                int orderID1 = resultSet.getInt("order_id");
                int clothingID = resultSet.getInt("clothing_id");
                Clothing clothing = getClothing(clothingID);
                int quantity = resultSet.getInt("quantity");
                int unitPrice = resultSet.getInt("unit_price");
                int totalPrice = resultSet.getInt("total_price");
                LocalDate createdAt = DateTime.convertStringToLocalDate(resultSet.getString("created_at"));
                OrderItem orderItem = new OrderItem(orderItemID, orderID1, clothing, quantity, unitPrice, totalPrice, createdAt);
                orderItemList.add(orderItem);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderItemList;
    }
}


