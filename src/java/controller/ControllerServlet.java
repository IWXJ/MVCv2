package controller;

import Entity.Customer;
import Entity.Pizza;
import Entity.Session;
import Entity.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author OBS
 */
@WebServlet(name="ControllerServlet",
            loadOnStartup = 1,
            urlPatterns = {"/cart.jsp",
                           "/index.jsp",
                           "/menu.jsp",
                           "/login.jsp",
                           "/menuadmin.jsp",
                           "/registeruser.jsp",
                           "/login.do",
                           "/registerUser.do",
                           "/order.do",
                           "/deleteFromList.do",
                           "/addItemToList.do",
                           "/menuSort.do",
                           "/validateemail.jsp",
                           "/validateemail.do",
                           "/cancelOrder.do",
                           "/confirmOrder.do",
                           "/alternativeDeliveryAddress.do",
                           "/updateProductQuantity.do",
                           "/filterMenu.do",
                           "/logout.do"
            })
public class ControllerServlet extends HttpServlet {
        PrintWriter pw;
        String userPath;
        
        String pageIndex = "/index.jsp";
        String pageMenu = "/menu.jsp";
        String pageCart = "/cart.jsp";
        String pageLogin = "/login.jsp";
        String pageMenuAdmin = "/menuadmin.jsp";
        String pageRegisterUser = "/registeruser.jsp";
        String pageCheckOut = "/checkout.jsp";
        String pageValidateEmail = "/validateemail.jsp";
        boolean redirect = true;
          
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        userPath = request.getRequestURI().substring(request.getContextPath().length());
        
        redirect = true;
        switch (userPath) {
            case "/cart.jsp":                           showCart(request, response); break;
            case "/menu.jsp":                           showMenu(request, response); break;
            case "/menuadmin.jsp":                      showMenuadmin(request, response); break;
            case "/registeruser.jsp":                   break;
            case "/login.jsp":                          showLogin(request, response); break;
            case "/login.do":                           doLoginUser(request, response); break;
            case "/order.do":                           doAddOrder(request, response); break;
            case "/deleteFromList.do":                  doDeleteItemFromList(request, response); break;
            case "/registerUser.do":                    doRegisterUser(request, response); break;
            case "/addItemToList.do":                   doAddItemToList(request, response); break;
            case "/menuSort.do":                        doSortMenu(request, response); break;
            case "/validateemail.do":                   doValidateEmail(request, response); break;
            case "/cancelOrder.do":                     doCancelOrder(request, response); break;
            case "/confirmOrder.do":                    doConfirmOrder(request, response); break;
            case "/alternativeDeliveryAddress.do":      doAlternativeDeliveryAddress(request, response); break;
            case "/updateProductQuantity.do":           doUpdateProductQuantity(request, response); break;
            case "/filterMenu.do":                      showMenu(request, response); break;
            case "/logout.do":                          doLogout(request, response); break;
            case "/index.jsp":
            default:                    userPath = "/index.jsp"; break;
        }
        
        if (redirect) {
            String url = "/WEB-INF/jsp" + userPath;

            try {
                request.getRequestDispatcher(url).forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void showCart(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Session custSession = (Session) session.getAttribute("custSession");
        if (custSession == null) {
            userPath = pageLogin;
            return;
        }
        
        Order order = null;
        order = Order.getOrder(custSession.getSessionId());
        if (order == null || (!order.getStatus().equals("OPEN"))) {
            userPath = pageIndex;
            return;
        }
        
// TO DO: 
// Kode til at håndtere bladring
        session.setAttribute("orderList", order);
        userPath = pageCart;
    }    

    private void showMenu(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            userPath = pageLogin;
            return;
        }

        String name = null;
        String desc = null;
        Double price = null;   
        String sortOrder = "";
        Object pizzaList = null;

        String minPrice = null;
        String maxPrice = null;
        boolean filterYes = false;
        
        if(request.getParameter("sortOrder") != null) {
            sortOrder = request.getParameter("sortOrder");
        } else {
            sortOrder = "name";
        }

        
        double min = 0;
        double max = 99999.00;
        minPrice = request.getParameter("minPrice");
        maxPrice = request.getParameter("maxPrice");
        
        if(minPrice != null && minPrice != "") {
            try {
                min = Double.parseDouble(minPrice);
                filterYes = true;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }        
        if(maxPrice != null && maxPrice != "") {
            try {
                max = Double.parseDouble(maxPrice);
                if (max >= min) {
                    filterYes = true;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } 
        
        if (filterYes && (min != 0 || max != 0)) {
            pizzaList = Pizza.getPizzaList(min, max);
        } else {
            pizzaList = Pizza.getPizzaList();
        }
        
// TO DO: 
// Kode til at håndtere bladring
        if (pizzaList != null) {
            session.setAttribute("pizzaList", pizzaList);
            userPath = pageMenu;
        } else {
            userPath = pageIndex;
            session.removeAttribute("pizzaList");
        }
    }    

    private void showMenuadmin(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Object pizzaList = null;
        pizzaList = Pizza.getPizzaList();
        
// TO DO: 
// Kode til at håndtere bladring
        session.setAttribute("pizzaList", pizzaList);
        userPath = pageMenuAdmin;

    }    

    private void showLogin(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("loginError");
        userPath = pageLogin;
    }    

    private void doAddOrder(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            userPath = pageLogin;
            return;
        }

        Session custSession = (Session) session.getAttribute("custSession");
        if (custSession == null) {
            userPath = pageLogin;
            return;
        }
        
        Order order = (Order) session.getAttribute("order");
        if (order == null) {
            Customer cust = Customer.getCustomer(custSession.getCustomerId());
            order = Order.addOrder(custSession.getSessionId(), custSession.getCustomerId(), "OPEN", cust.getStreet(), cust.getZipcode(), cust.getCity());
        } else if (!"OPEN".equals(order.getStatus())) {
            session.removeAttribute("order");
            userPath = pageIndex;
            return;
        }
        
        int pizzaId = Integer.parseInt(request.getParameter("pizzaId"));
        if(order.addProductToOrder(pizzaId)) {                                 // If product could not be added to order list
            session.setAttribute("custSession", custSession);
            session.setAttribute("order", order);
            userPath = pageMenu;
            return;
        } else {
            order = null;
            custSession = null;
            session.removeAttribute("custSession");
            session.removeAttribute("order");
            userPath = pageIndex;
            return;
        }
    }
    
    private void doDeleteItemFromList(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String pId = request.getParameter("pizzaId");
        if (pId == null || pId == "") {
            return;
        }

        int pizzaId = Integer.parseInt(pId);
        
        showMenuadmin(request, response);
    }
    
    private void doRegisterUser(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        Integer zipcode = Integer.parseInt(request.getParameter("zipcode"));
        String city = request.getParameter("city");
        Integer phone = Integer.parseInt(request.getParameter("phone"));

        Object cust = null;
        try {
            cust = Customer.addCustomer(email, password, name, address, city, zipcode, phone, false);
        } catch (Customer.errorUserAlreadyRegistered ex) {
            Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("errorRegisterUser", ex.getMessage());
            userPath = pageRegisterUser;
        }

        if (cust != null) {
            session.setAttribute("customer", cust);
            userPath = pageValidateEmail;
        }
    }
    
    private void doLoginUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Object returnObject = null;
            
        String userid = request.getParameter("email");
        String password = request.getParameter("password");

        Session custSession = (Session) session.getAttribute("custSession");
        Customer cust = null;
        if (custSession == null) {
            cust = Customer.loginCustomer(userid, password);
            if (cust == null) {
                returnObject = "loginError";
                session.setAttribute("loginError", returnObject);
                userPath = pageLogin;
                return;
            }
        }
        custSession = new Session(session.getId(), cust.getCustomerId());
        session.setAttribute("custSession", custSession);
        session.removeAttribute("loginError");
        showMenu(request, response);
        if (cust.getStatusAdmin()) {
            userPath = pageMenuAdmin;
        } else {
            userPath = pageMenu;
        }

        returnObject = "OK";
        session.setAttribute("returnObject", returnObject);
    }
    
    private void doAddItemToList(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        
        if (session == null) {
            userPath = pageLogin;
            return;
        }

        String name = null;
        String desc = null;
        Double price = null;   
        Object result = null;

        if(request.getParameter("name") != null) {
            name = request.getParameter("name");
        } 

        if(request.getParameter("description") != null) {
            desc = request.getParameter("description");
        } 

        if(request.getParameter("name") != null) {
            price = Double.parseDouble(request.getParameter("price"));
        }
        

        showMenuadmin(request, response);
    }
    
    private void doSortMenu(HttpServletRequest request, HttpServletResponse response) {
        showMenu(request, response);
    }

    private void doValidateEmail(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            userPath = pageIndex;
            return;
        }
        
        String email = (String) request.getParameter("email");
        if(Customer.validateCustomer(email))
            userPath = pageLogin;
        else
            userPath = pageIndex;
    }
    
    private void doCancelOrder(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        Order order = (Order) session.getAttribute("order");
        Order.removeOrder(order);
        session.removeAttribute("order");
        
        userPath = pageIndex;
        return;
    }
    
    private void doConfirmOrder(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            userPath = pageLogin;
            return;
        }

        Session custSession = (Session) session.getAttribute("custSession");
        if (custSession == null) {
            userPath = pageLogin;
            return;
        }
        
        Order order = (Order) session.getAttribute("order");
        if (order == null) {
            userPath = pageLogin;
            return;
        }
        
        order.setStatus("CLOSED");
        session.setAttribute("custSession", custSession);
        session.setAttribute("orderList", order);
        
        userPath = pageCheckOut;
    }

    private void doAlternativeDeliveryAddress(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            userPath = pageLogin;
            return;
        }

        Session custSession = (Session) session.getAttribute("custSession");
        if (custSession == null) {
            userPath = pageLogin;
            return;
        }
        
        Order order = (Order) session.getAttribute("order");
        if (order == null) {
            userPath = pageLogin;
            return;
        }
        
        int alternativeAddressZipcode = 0;
        String alternativeAddressStreet = request.getParameter("alternativeAddressStreet");
        try {
            alternativeAddressZipcode = Integer.parseInt(request.getParameter("alternativeAddressZipcode"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String alternativeAddressCity = request.getParameter("alternativeAddressCity");
        
        if (alternativeAddressStreet != null && alternativeAddressZipcode != 0 && alternativeAddressCity != null) {
            order.setDeliveryAddressStreet(alternativeAddressStreet);
            order.setDeliveryAddressZipcode(alternativeAddressZipcode);
            order.setDeliveryAddressCity(alternativeAddressCity);
            session.setAttribute("custSession", custSession);
            session.setAttribute("orderList", order);
            System.out.println("Alternative delivery address: " + order.getDeliveryAddressStreet() + ", " + order.getDeliveryAddressCity());
        }
        
        String result = "Your pizzas will be delivered at this address: " + alternativeAddressStreet + ", " + alternativeAddressZipcode + " " + alternativeAddressCity;
        session.setAttribute("result", result);
        
        userPath = pageCheckOut;
//        redirect = false;
    }

    private void doLogout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        session.removeAttribute("custSession");
        session.removeAttribute("order");
        userPath = pageIndex;
    }
    
    private void doUpdateProductQuantity(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            userPath = pageLogin;
            return;
        }

        Session custSession = (Session) session.getAttribute("custSession");
        if (custSession == null) {
            userPath = pageLogin;
            return;
        }
        
        Order order = (Order) session.getAttribute("order");
        if (order == null) {
            userPath = pageLogin;
            return;
        }
        
        int pizzaId =  Integer.parseInt(request.getParameter("pizzaId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        if (Order.updateOrderedProductQuantity(order, pizzaId, quantity)) {
            order = Order.getOrder(custSession.getSessionId());
            session.setAttribute("custSession", custSession);
            session.setAttribute("orderList", order);
            userPath = pageCart;
        } else {
            session.removeAttribute("orderList");
            userPath = pageMenu;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
