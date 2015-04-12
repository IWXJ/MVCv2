package Entity;

import java.util.ArrayList;

public class Customer {
    private static int id;
    
    private int customerId;
    private String email;
    private String password;
    private String name;
    private String street;
    private String city;
    private int zipcode; 
    private int phone;
    private boolean validated;
    private boolean statusAdmin;
    

    static ArrayList<Customer> customerList = null;
    
    public static class errorUserAlreadyRegistered extends Exception {
        public errorUserAlreadyRegistered(String message) {
            super(message);
        }
    }
    
    public static void createCustomerList() {
        customerList = new ArrayList<>();
        customerList.add(new Customer("admin@lapizzeria.dk", "123", "Admin", "Street", "City", 2500, 41111111, true));
        customerList.add(new Customer("obs@hotmail.dk", "N4k123.", "Ole", "Test", "Test", 2500, 51111111, false));
    }
    
    public static ArrayList<Customer> getCustomerList() {
        if (customerList == null) {
            createCustomerList();
        }
        
        return customerList;
    }
    
    public static Customer addCustomer(String email, String password, String name, String street, String city, int zipcode, int phone, boolean statusAdmin) throws errorUserAlreadyRegistered {
        Customer c = new Customer(email.toLowerCase(), password, name, street, city, zipcode, phone, statusAdmin);
        
        ArrayList l = getCustomerList();
        for (Object l1 : l) {
            Customer cust = (Customer) l1;
            if (cust.getEmail().equals(email.toLowerCase())) {
                throw new errorUserAlreadyRegistered("User already registered.");
            }
        }
        l.add(c);
        return c;
    }
    
    public static boolean validateCustomer(String email) {
        if (customerList == null) {
            createCustomerList();
        }

        for (Customer customerList1 : customerList) {
            if(customerList1.getEmail().toLowerCase().equals(email)) {
                return true;
            }
        }
        return false;
    }
    
    public static Customer loginCustomer(String email, String password) {
        if (customerList == null) {
            createCustomerList();
        }

        for (Customer customerList1 : customerList) {
            if(customerList1.getEmail().toLowerCase().equals(email) && customerList1.getPassword().equals(password)) {
                return customerList1;
            }
        }
        return null;
    }
    
    public static Customer getCustomer(String email) {
        if (customerList == null) {
            createCustomerList();
        }

        for (Customer customerList1 : customerList) {
            if(customerList1.getEmail().toLowerCase().equals(email)) {
                return customerList1;
            }
        }
        return null;
    }
    
    public static Customer getCustomer(int custId) {
        if (customerList == null) {
            createCustomerList();
        }

        for (Customer customerList1 : customerList) {
            if(customerList1.getCustomerId() == custId) {
                return customerList1;
            }
        }
        return null;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Constructors. Click on the + sign on the left to edit the code.">
    public Customer(){
        if (customerList == null) {
            createCustomerList();
        }

        synchronized(this) {
            this.customerId = ++id;
        }
        
        this.email = "";
        this.password = "";
        this.name = "";
        this.street = "";
        this.city = "";
        this.zipcode = 0;
        this.phone = 0;
        this.validated = false;
        this.statusAdmin = false;
    }

    public Customer(String email, String password, String name, String street, String city, int zipcode, int phone, boolean statusAdmin){         // instantiate the object with all variables
        if (customerList == null) {
            createCustomerList();
        }

        synchronized(this) {
            this.customerId = ++id;
        }

        this.email = email;
        this.password = password;
        this.name = name;
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
        this.phone = phone;
        this.validated = false;
        this.statusAdmin = statusAdmin;
    }
    //</editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="Get and Set methods. Click on the + sign on the left to edit the code.">
    
    public int getCustomerId() {
        return customerId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
    
    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }
    
    public int getZipcode() {
        return zipcode;
    }

    public int getPhone() {
        return phone;
    }

    public boolean getStatusAdmin() {
        return statusAdmin;
    }

    public void setValidated() {
        this.validated = true;
    }
    //</editor-fold>
}

