package com.example.tekushop.model;

import java.time.LocalDate;

public class OrderItem {

    private int orderItemId;
    private int orderID;
    private Clothing clothing;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private LocalDate createdAt;

    public OrderItem(int orderItemId, int orderID, Clothing clothing, int quantity, double unitPrice, double totalPrice, LocalDate createdAt) {
        this.orderItemId = orderItemId;
        this.orderID = orderID;
        this.clothing = clothing;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrder() {
        return orderID;
    }

    public void setOrder(int order) {
        this.orderID = orderID;
    }

    public Clothing getClothing() {
        return clothing;
    }

    public void setClothing(Clothing clothing) {
        this.clothing = clothing;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
