package com.epam.jwd.carrentproject.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * The {@code Order} class represents an order entity
 */
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private int orderId;
    private int userId;
    private int carId;
    private LocalDate pickUpDate;
    private LocalDate dropOffDate;
    private String status;

    public static class OrderBuilder {

        private Order newOrder;

        public OrderBuilder() {
            newOrder = new Order();
        }

        public OrderBuilder withOrderId(int orderId) {
            newOrder.orderId = orderId;
            return this;
        }

        public OrderBuilder withUserId(int userId) {
            newOrder.userId = userId;
            return this;
        }

        public OrderBuilder withCarId(int carId) {
            newOrder.carId = carId;
            return this;
        }

        public OrderBuilder withPickUpDate(LocalDate pickUpDate) {
            newOrder.pickUpDate = pickUpDate;
            return this;
        }

        public OrderBuilder withDropUpDate(LocalDate dropOffDate) {
            newOrder.dropOffDate = dropOffDate;
            return this;
        }

        public OrderBuilder withStatus(String status) {
            newOrder.status = status;
            return this;
        }

        public Order build() {
            return newOrder;
        }

    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalDate getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(LocalDate dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (userId != order.userId) return false;
        if (carId != order.carId) return false;
        if (pickUpDate != null ? !pickUpDate.equals(order.pickUpDate) : order.pickUpDate != null) return false;
        if (dropOffDate != null ? !dropOffDate.equals(order.dropOffDate) : order.dropOffDate != null) return false;
        return status != null ? status.equals(order.status) : order.status == null;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + userId;
        result = 31 * result + carId;
        result = 31 * result + (pickUpDate != null ? pickUpDate.hashCode() : 0);
        result = 31 * result + (dropOffDate != null ? dropOffDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +" {" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", carId=" + carId +
                ", pickUpDate=" + pickUpDate +
                ", dropOffDate=" + dropOffDate +
                ", status='" + status + '\'' +
                '}';
    }
}
