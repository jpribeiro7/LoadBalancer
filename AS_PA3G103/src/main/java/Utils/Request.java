/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.google.gson.annotations.Expose;
import java.util.Comparator;

/**
 *
 * @author Pedro
 */
public class Request implements Comparator<Request>{
    @Expose
    private int id;
    @Expose
    private int client_id;
    @Expose
    private int time;
    
    public Request(int request_number, int client_id, int time){
        this.time = time;
        this.client_id = client_id;
        this.id =client_id*1000 +request_number;
    }

    @Override
    public int compare(Request t, Request t1) {
        
        if(t.time == t1.time){
            return t.id - t1.id;
        }
        return t.time-t1.time;
        
    }
}
