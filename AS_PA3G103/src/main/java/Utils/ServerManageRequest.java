/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.google.gson.annotations.Expose;
import java.util.Map;

/**
 *
 * @author Pedro
 */
public class ServerManageRequest {
    @Expose
    private Map<Integer,Integer> server_ports;

    public ServerManageRequest(Map<Integer,Integer> server_ports) {
        this.server_ports = server_ports;
    }

    public Map<Integer,Integer> getServer_ports() {
        return server_ports;
    }

    public void setServer_ports(Map<Integer,Integer> server_ports) {
        this.server_ports = server_ports;
    }
 
    
    
}
