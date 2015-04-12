/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;

/**
 *
 * @author OBS
 */
public class OrderedProduct implements Serializable {
    private static int opId;
    
    private int id;
    private int pizzaId;
    private String name;
    private String description;
    private double price;
    private int quantity;

    public OrderedProduct(int pizzaId) {
        synchronized(this) {
            this.id = ++opId;
        }
        this.pizzaId = pizzaId;
        this.name = Pizza.getNameById(pizzaId);
        this.description = Pizza.getDescriptionById(pizzaId);
        this.price = Pizza.getPriceById(pizzaId);
        incrementQuantity();
    }
    
    public int getId() {
        return id;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    
    public void setId(int id) {
        this.id = id;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    public void decrementQuantity() {
        this.quantity--;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
