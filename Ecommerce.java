import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// Base class for Product
class Product {
    private int id;
    private String name;
    private double price;
    private String category;

    public Product(int id, String name, double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}

// Subclass for Electronics
class Electronics extends Product {
    private String brand;
    private String model;

    public Electronics(int id, String name, double price, String brand, String model) {
        super(id, name, price, "Electronics");
        this.brand = brand;
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return super.toString() + ", brand='" + brand + '\'' + ", model='" + model + '\'';
    }
}

// Subclass for Clothing
class Clothing extends Product {
    private String size;
    private String color;

    public Clothing(int id, String name, double price, String size, String color) {
        super(id, name, price, "Clothing");
        this.size = size;
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }
    @Override
    public String toString() {
        return super.toString() + ", size='" + size + '\'' + ", color='" + color + '\'';
    }
}

// Subclass for Grocery
class Grocery extends Product {
    private double weight;
    private String expirationDate;

    public Grocery(int id, String name, double price, double weight, String expirationDate) {
        super(id, name, price, "Grocery");
        this.weight = weight;
        this.expirationDate = expirationDate;
    }

    public double getWeight() {
        return weight;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    @Override
    public String toString() {
        return super.toString() + ", weight=" + weight + ", expirationDate='" + expirationDate + '\'';
    }
}

// Base class for User
class User {
    private int id;
    private String name;
    private String email;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
        @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

// Subclass for Customer
class Customer extends User {
    private String address;

    public Customer(int id, String name, String email, String address) {
        super(id, name, email);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
        @Override
    public String toString() {
        return super.toString() +  ", address='" + address + '\'';
    }
}

// Subclass for Admin
class Admin extends User {
    private String role;

    public Admin(int id, String name, String email, String role) {
        super(id, name, email);
        this.role = role;
    }

    public String getRole() {
        return role;
    }
     @Override
    public String toString() {
        return super.toString() + ", role='" + role + '\'';
    }
}

// Class representing a customer's order
class Order {
    private int id;
    private Customer customer;
    private List<Product> products;
    private Date orderDate;
    private double totalAmount;

    public Order(int id, Customer customer) {
        this.id = id;
        this.customer = customer;
        this.products = new ArrayList<>();
        this.orderDate = new Date();
        this.totalAmount = 0.0;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void addProduct(Product product) {
        this.products.add(product);
        this.totalAmount += product.getPrice();
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
        this.totalAmount -= product.getPrice();
    }

    public double calculateTotal() {
        this.totalAmount = 0.0;
        for (Product product : products) {
            this.totalAmount += product.getPrice();
        }
        return this.totalAmount;
    }

    public void applyDiscount(double discountPercentage) {
        if (discountPercentage > 0 && discountPercentage < 1) {
            this.totalAmount *= (1 - discountPercentage);
        }
    }

    public void applyTax(double taxRate) {
        if (taxRate > 0) {
            this.totalAmount *= (1 + taxRate);
        }
    }

    public double calculateShippingFees(ShippingCostCalculator calculator) {
        return calculator.calculate(this.products);
    }
        @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", products=" + products +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                '}';
    }
}

// Functional interface for shipping cost calculation
interface ShippingCostCalculator {
    double calculate(List<Product> products);
}

public class Main {
    public static void main(String[] args) {
        // Initialize products
        Electronics laptop = new Electronics(1, "Laptop", 1200.0, "Dell", "XPS 15");
        Electronics smartphone = new Electronics(2, "Smartphone", 1000.0, "Apple", "iPhone 13");
        Clothing tShirt = new Clothing(3, "T-Shirt", 25.0, "M", "Blue");
        Clothing jeans = new Clothing(4, "Jeans", 50.0, "L", "Black");
        Grocery milk = new Grocery(5, "Milk", 3.0, 1.0, "2024-05-20");
        Grocery bread = new Grocery(6, "Bread", 4.0, 0.5, "2024-05-18");

        List<Product> productList = Arrays.asList(laptop, smartphone, tShirt, jeans, milk, bread);


        // Initialize users
        Customer customer1 = new Customer(101, "Alice", "alice@example.com", "123 Main St");
        Customer customer2 = new Customer(102, "Bob", "bob@example.com", "456 Oak Ave");
        Admin admin1 = new Admin(201, "AdminUser", "admin@example.com", "SuperAdmin");

        // Initialize orders
        Order order1 = new Order(1001, customer1);
        order1.addProduct(laptop);
        order1.addProduct(tShirt);

        Order order2 = new Order(1002, customer2);
        order2.addProduct(smartphone);
        order2.addProduct(jeans);
        order2.addProduct(bread);

        List<Order> orderList = Arrays.asList(order1, order2);


        // Lambdas:

        // Filter products by category using a lambda expression
        Predicate<Product> electronicsFilter = product -> product.getCategory().equals("Electronics");
        List<Product> electronicsProducts = productList.stream()
                .filter(electronicsFilter)
                .collect(Collectors.toList());
        System.out.println("\nElectronics Products: " + electronicsProducts);

        // Apply a 10% discount to the first order using a lambda
        order1.applyDiscount(0.10);
        System.out.println("\nOrder 1 Total after 10% discount: " + order1.getTotalAmount());

        // Apply a tax rate of 8% to the second order using a lambda
        order2.applyTax(0.08);
        System.out.println("Order 2 Total after 8% tax: " + order2.getTotalAmount());

        // Calculate shipping fees based on the number of products using a lambda
        ShippingCostCalculator flatRateCalculator = products -> products.size() * 2.5;
        double shippingCost1 = order1.calculateShippingFees(flatRateCalculator);
        System.out.println("Order 1 Shipping Cost: " + shippingCost1);

        ShippingCostCalculator weightBasedCalculator = products -> {
            double totalWeight = 0;
            for (Product product : products) {
                if (product instanceof Grocery) {
                    totalWeight += ((Grocery) product).getWeight();
                }
            }
            return totalWeight * 1.5;
        };
        double shippingCost2 = order2.calculateShippingFees(weightBasedCalculator);
        System.out.println("Order 2 Shipping Cost: " + shippingCost2);

        // Search and sort functionality using streams:

        // Search for products by name (case-insensitive)
        String searchName = "t-shirt";
        List<Product> foundProducts = productList.stream()
                .filter(product -> product.getName().toLowerCase().contains(searchName.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println("\nProducts found with name containing '" + searchName + "': " + foundProducts);

        // Sort orders by order date
        List<Order> sortedOrders = orderList.stream()
                .sorted(Comparator.comparing(Order::getOrderDate))
                .collect(Collectors.toList());
        System.out.println("\nOrders sorted by date: " + sortedOrders);

        //Demonstrate total calculation
        System.out.println("\nOrder1 Details: " + order1);
        System.out.println("Order2 Details: " + order2);


    }
}
