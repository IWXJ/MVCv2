/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;

public class Session implements Serializable {
    private static int id = 0;
    private String sessionId;
    private int customerId;

    public Session(String sessionId, int customerId) {
//        synchronized(this) {
//            id++;
//        }
        this.sessionId = sessionId;
        this.customerId = customerId;
    }

    
    public String getSessionId() {
        return sessionId;
    }

    public int getCustomerId() {
        return customerId;
    }
}
