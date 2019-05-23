/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.google.gson.annotations.Expose;

/**
 *
 * @author Pedro
 */
public class KillServer {
    @Expose
    private String type;
    @Expose
    private int port;

    public KillServer(String type, int port) {
        this.type = type;
        this.port = port;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    
    

   
    
}
