package com.example.tekushop.service.order;

import com.example.tekushop.model.Order;

import java.util.List;

public interface IOrderService {
    boolean addOrder(int userId, int productId, int quantity);
    void updateOrder(int orderId, String status);
    List<Order> getListOrder(int userId);
}
