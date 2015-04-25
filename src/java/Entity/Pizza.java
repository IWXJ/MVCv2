/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author OBS
 */
public class Pizza implements Serializable {
    
    static ArrayList<Pizza> pizzaList;
    private static int idGenerator = 0;
    
    static void createPizzaList() {
    
        pizzaList = new ArrayList<Pizza>();
        pizzaList.add(new Pizza("test1", "test1", 15.00));
        pizzaList.add(new Pizza("test2", "test2", 25.00));
        pizzaList.add(new Pizza("test3", "test3", 35.00));
  }
    
    public static ArrayList<Pizza> getPizzaList() {
        if (pizzaList == null) {
            createPizzaList();
        }
        
        return pizzaList;
    }

    public static ArrayList<Pizza> getPizzaList(double min, double max) {
        if (pizzaList == null) {
            createPizzaList();
        }
        
        ArrayList<Pizza> pList = new ArrayList<>();
        for (Pizza pl : pizzaList) {
            if(pl.getPrice() >= min && pl.getPrice() <= max) {
                pList.add(pl);
            }
        }
        return pList;
    }

    public static boolean addPizzaToList(Pizza p) {
        return pizzaList.add(p);
    }
    
    public static boolean removePizzaFromList(int id) {
        int i = 0;
        for (Pizza pizzaList1 : pizzaList) {
            if (pizzaList1.getId() == id) {
                pizzaList.remove(i);
                return true;
            }
            i++;
        }
        return false;
    }
    
    int id;
    String name;
    String description;
    double price;

    public Pizza(String name, String description, double price) {
        synchronized(this) {
            this.id = ++idGenerator;
        }
        this.name = name;
        this.description = description;
        this.price = price;
    }
    
    public int getId() {
        return id;
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

    public static String getNameById(int id) {
        for (Pizza pizzaList1 : pizzaList) {
            if (pizzaList1.getId() == id) {
                return pizzaList1.getName();
            }
        }
        return null;
    }
    
    public static String getDescriptionById(int id) {
        for (Pizza pizzaList1 : pizzaList) {
            if (pizzaList1.getId() == id) {
                return pizzaList1.getDescription();
            }
        }
        return null;
    }
    
    public static double getPriceById(int id) {
        for (Pizza pizzaList1 : pizzaList) {
            if (pizzaList1.getId() == id) {
                return pizzaList1.getPrice();
            }
        }
        return 0;
    }
    
}
