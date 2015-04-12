/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

        
import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int id;

    static ArrayList<Order> orderList;
    
    public static void createOrderList() {
        orderList = new ArrayList<>();
        id = 0;
    }
    
    public static ArrayList<Order> getOrderList() {
        if (orderList == null) {
            createOrderList();
        }
        
        return orderList;
    }
    
    public static Order addOrder(String sessionId, int customerId, String status, String deliveryAddressStreet, int deliveryAddressZipcode, String deliveryAddressCity) {
        id++;
        Order o = new Order(sessionId, customerId, status, deliveryAddressStreet, deliveryAddressZipcode, deliveryAddressCity);
        
        ArrayList l = getOrderList();
        l.add(o);
        return o;
    }
    
    public static Order getOrder(String sessionId) {
        if (orderList == null) {
            return null;
        }
        for (Order orderList1 : orderList) {
            if (orderList1.getSessionId().equals(sessionId)) {
                return orderList1;
            }
        }
        return null;
    }

    public static void removeOrder(Order order) {
        if (orderList == null) {
            return;
        }
        for (Order orderList1 : orderList) {
            if (orderList1.equals(order)) {
                orderList.remove(orderList1);
                return;
            }
        }
        return;
    }
    
    public static boolean updateOrderedProductQuantity(Order order, int pizzaId, int quantity) {
        if (orderList == null) {
            return false;
        }
        
        for (Order orderList1 : orderList) {
            if (orderList1.getSessionId()== order.getSessionId()) {
                return orderList1.setOrderedProductQuantity(pizzaId, quantity);
            }
        }
        return false;
    }
    
    
    private String sessionId;
    private int customerId;
    private String status;
    private double orderTotal;
    private String deliveryAddressStreet;
    private int deliveryAddressZipcode;
    private String deliveryAddressCity;

    private ArrayList<OrderedProduct> opList;

    
    public boolean addProductToOrder(int pizzaId) {
        if (!status.equalsIgnoreCase("OPEN")) {
            return false;
        }
        OrderedProduct op = new OrderedProduct(pizzaId);
        for (OrderedProduct opList1 : opList) {
            if (opList1.getPizzaId() == pizzaId) {
                opList1.incrementQuantity();
                orderTotal += (opList1.getQuantity() * opList1.getPrice());
                System.out.println("Increased quantity");
                return true;
            }
        }
        System.out.println("Added product");
        orderTotal += (op.getQuantity() * op.getPrice());
        opList.add(op);
        return true;
    }
    
    public void updateOrderTotal() {
        orderTotal = 0;
        for (OrderedProduct opList1 : opList) {
            orderTotal += (opList1.getQuantity() * opList1.getPrice());
        }
    }
    
    public ArrayList<OrderedProduct> getOrderedProductList() {
        return opList;
    }
    
    private boolean setOrderedProductQuantity(int pizzaId, int quantity) {
        for (OrderedProduct op1 : opList) {
            if (op1.getPizzaId() == pizzaId) {
                op1.setQuantity(quantity);
                updateOrderTotal();
                return true;
            }
        }
        return false;
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Get and Set methods. Click on the + sign on the left to edit the code.">
    public int getId() {
        return id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getStatus() {
        return status;
    }

    public String getDeliveryAddressStreet() {
        return deliveryAddressStreet;
    }

    public int getDeliveryAddressZipcode() {
        return deliveryAddressZipcode;
    }

    public String getDeliveryAddressCity() {
        return deliveryAddressCity;
    }

    public double getOrderTotal() {
        return orderTotal;
    }
    
    
    public void setId(int id) {
        this.id = id;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDeliveryAddressStreet(String deliveryAddressStreet) {
        this.deliveryAddressStreet = deliveryAddressStreet;
    }

    public void setDeliveryAddressZipcode(int deliveryAddressZipcode) {
        this.deliveryAddressZipcode = deliveryAddressZipcode;
    }

    public void setDeliveryAddressCity(String deliveryAddressCity) {
        this.deliveryAddressCity = deliveryAddressCity;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors. Click on the + sign on the left to edit the code.">
    public Order() {
    }

    public Order(String sessionId, int customerId, String status, String deliveryAddressStreet, int deliveryAddressZipcode, String deliveryAddressCity) {
        this.sessionId = sessionId;
        this.customerId = customerId;
        this.status = status;
        this.orderTotal = 0;
        this.deliveryAddressStreet = deliveryAddressStreet;
        this.deliveryAddressZipcode = deliveryAddressZipcode;
        this.deliveryAddressCity = deliveryAddressCity;
        
        this.opList = new ArrayList<>();
    }
    // </editor-fold>

    @Override
    public String toString() {
        return "sessionId: "+ sessionId + "customerId: " + customerId + "status: " + status + "orderTotal: " + orderTotal + "deliveryAddressStreet: " 
                + deliveryAddressStreet + "deliveryAddressZipcode: " + deliveryAddressZipcode + "deliveryAddressCity: " +deliveryAddressCity;

    }
    
    
}
