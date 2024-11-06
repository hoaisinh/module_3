package com.example.tekushop.service.order;

import com.example.tekushop.model.Order;
import com.example.tekushop.repository.order.OrderRepo;

import java.util.List;

public class OrderService implements IOrderService {
    @Override
    public boolean addOrder(int userId, int productId, int quantity) {
        OrderRepo orderRepo = new OrderRepo();
       return orderRepo.addOrder(userId, productId, quantity);
    }

    @Override
    public void updateOrder(int orderId, String status) {

    }

    @Override
    public List<Order> getListOrder(int userId) {
        OrderRepo orderRepo = new OrderRepo();
        return orderRepo.getListOrder(userId);
    }


}
