package entity;

import java.sql.Timestamp;

public class Order {
    private int orderId;
    private int customerId;
    private Timestamp orderDate;
    private double totalAmount;
    private String status;
    private String customerName;

    public Order() {}

    public Order(int orderId, int customerId, Timestamp orderDate, double totalAmount, String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public Timestamp getOrderDate() { return orderDate; }
    public void setOrderDate(Timestamp orderDate) { this.orderDate = orderDate; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String toString() {
        if (customerName != null) {
            return String.format("Order[id=%d, customer=%s, date=%s, total=%.2f, status=%s]",
                    orderId, customerName, orderDate, totalAmount, status);
        }
        return String.format("Order[id=%d, customerId=%d, date=%s, total=%.2f, status=%s]",
                orderId, customerId, orderDate, totalAmount, status);
    }
}