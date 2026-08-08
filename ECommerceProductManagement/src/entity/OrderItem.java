package entity;

public class OrderItem {
    private int orderItemId;
    private int orderId;
    private int productId;
    private int quantity;
    private double unitPrice;
    private String productName;

    public OrderItem(int orderItemId, int orderId, int productId, int quantity, double unitPrice) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public OrderItem(int orderId, int productId, int quantity, double unitPrice) {
        this(0, orderId, productId, quantity, unitPrice);
    }

    public int getOrderItemId() { return orderItemId; }
    public void setOrderItemId(int orderItemId) { this.orderItemId = orderItemId; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String toString() {
        if (productName != null) {
            return String.format("OrderItem[orderId=%d, product=%s, qty=%d, unitPrice=%.2f, lineTotal=%.2f]",
                    orderId, productName, quantity, unitPrice, quantity * unitPrice);
        }
        return String.format("OrderItem[orderId=%d, productId=%d, qty=%d, unitPrice=%.2f]",
                orderId, productId, quantity, unitPrice);
    }
}