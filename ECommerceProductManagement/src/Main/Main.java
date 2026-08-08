package Main;
import entity.CartItem;
import entity.Order;
import entity.OrderItem;
import entity.Product;
import repository.CustomerRepository;
import repository.ProductRepository;
import service.CartService;
import service.OrderService;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductRepository productRepository = new ProductRepository();
    private static final CustomerRepository customerRepository = new CustomerRepository();
    private static final CartService cartService = new CartService();
    private static final OrderService orderService = new OrderService();
    
    public static void main(String[] args) {
        try {
            Connection conn = DatabaseUtil.getConnection();
            boolean running = true;
            while (running) {
                printMainMenu();
                int choice = readInt();
                switch (choice) {
                    case 1: productCatalogMenu(conn); break;
                    case 2: shoppingCartMenu(conn); break;
                    case 3: placeOrderFlow(conn); break;
                    case 4: orderTrackingMenu(conn); break;
                    case 0: running = false; break;
                    default: System.out.println("invalid choice");
                }
            }
            DatabaseUtil.closeConnection();
            System.out.println("goodbye!");
        } catch (SQLException e) {
            System.err.println("database error: " + e.getMessage());
        }
    }

    private static void printMainMenu() {
        System.out.println("\n===== e-commerce product management =====");
        System.out.println("1. Product Catalog");
        System.out.println("2. Shopping Cart");
        System.out.println("3. Place Order");
        System.out.println("4. Order Tracking");
        System.out.println("0. Exit");
        System.out.print("choose an option: ");
    }

    private static void productCatalogMenu(Connection conn) throws SQLException {
        System.out.println("\n-- product catalog --");
        System.out.println("1. Add Product  2. Bulk Add (batch insert)  3. View All  4. View by Category  0. Back");
        int choice = readInt();
        switch (choice) {
            case 1:
                System.out.print("name: "); String name = scanner.nextLine();
                System.out.print("category: "); String category = scanner.nextLine();
                System.out.print("price: "); double price = Double.parseDouble(scanner.nextLine());
                System.out.print("stock quantity: "); int stock = readInt();
                int id = productRepository.save(conn, new Product(name, category, price, stock));
                System.out.println("added product with id: " + id);
                break;
            case 2:
                bulkAddProducts(conn);
                break;
            case 3:
                productRepository.findAll(conn).forEach(System.out::println);
                break;
            case 4:
                System.out.print("category: "); String cat = scanner.nextLine();
                productRepository.findByCategory(conn, cat).forEach(System.out::println);
                break;
            default: break;
        }
    }
    private static void bulkAddProducts(Connection conn) throws SQLException {
        System.out.print("how many products to add: ");
        int count = readInt();
        List<Product> batch = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            System.out.println("-- product " + i + " --");
            System.out.print("name: "); String name = scanner.nextLine();
            System.out.print("category: "); String category = scanner.nextLine();
            System.out.print("price: "); double price = Double.parseDouble(scanner.nextLine());
            System.out.print("stock quantity: "); int stock = readInt();
            batch.add(new Product(name, category, price, stock));
        }
        int[] results = productRepository.saveBatch(conn, batch);
        System.out.println("batch insert complete, rows affected: " + results.length);
    }
    private static void shoppingCartMenu(Connection conn) throws SQLException {
        System.out.println("\n-- shopping cart --");
        System.out.println("1. Add Item  2. Remove Item  3. View Cart  4. Clear Cart  0. Back");
        int choice = readInt();
        switch (choice) {
            case 1:
                System.out.print("product id: "); int pid = readInt();
                System.out.print("quantity: "); int qty = readInt();
                try {
                    cartService.addToCart(conn, pid, qty);
                    System.out.println("added to cart.");
                } catch (IllegalArgumentException | IllegalStateException ex) {
                    System.out.println("could not add to cart: " + ex.getMessage());
                }
                break;
            case 2:
                System.out.print("product id to remove: "); int rid = readInt();
                cartService.removeFromCart(rid);
                System.out.println("removed if present.");
                break;
            case 3:
                List<CartItem> items = cartService.viewCart();
                if (items.isEmpty()) System.out.println("cart is empty.");
                else {
                    items.forEach(System.out::println);
                    System.out.printf("cart total: %.2f%n", cartService.cartTotal());
                }
                break;
            case 4:
                cartService.clearCart();
                System.out.println("cart cleared.");
                break;
            default: break;
        }
    }
    private static void placeOrderFlow(Connection conn) throws SQLException {
        if (cartService.isEmpty()) {
            System.out.println("cart is empty, add items first.");
            return;
        }
        System.out.print("customer id: ");
        int customerId = readInt();
        if (customerRepository.findById(conn, customerId) == null) {
            System.out.println("no customer with that id.");
            return;
        }

        try {
            int orderId = orderService.placeOrder(conn, customerId, cartService.viewCart());
            System.out.println("order placed successfully, order id: " + orderId);
            cartService.clearCart();
        } catch (IllegalStateException ex) {
            System.out.println("order failed and was rolled back: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("order failed and was rolled back due to a database error: " + ex.getMessage());
        }
    }

    private static void orderTrackingMenu(Connection conn) throws SQLException {
        System.out.println("\n-- order tracking --");
        System.out.println("1. View All Orders (joined)  2. View Order Details  3. History for Customer  4. Update Status  0. Back");
        int choice = readInt();
        switch (choice) {
            case 1:
                orderService.getAllOrders(conn).forEach(System.out::println);
                break;
            case 2:
                System.out.print("order id: "); int oid = readInt();
                Order order = orderService.getOrderSummary(conn, oid);
                if (order == null) { System.out.println("not found."); break; }
                System.out.println(order);
                List<OrderItem> lineItems = orderService.getOrderItems(conn, oid);
                lineItems.forEach(System.out::println);
                break;
            case 3:
                System.out.print("customer id: "); int cid = readInt();
                orderService.getOrderHistoryForCustomer(conn, cid).forEach(System.out::println);
                break;
            case 4:
                System.out.print("order id: "); int uid = readInt();
                System.out.print("new status (PLACED/SHIPPED/DELIVERED/CANCELLED): ");
                String status = scanner.nextLine();
                boolean ok = orderService.advanceOrderStatus(conn, uid, status);
                System.out.println(ok ? "status updated." : "update failed.");
                break;
            default: break;
        }
    }

    private static int readInt() {
        while (!scanner.hasNextInt()) {
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}