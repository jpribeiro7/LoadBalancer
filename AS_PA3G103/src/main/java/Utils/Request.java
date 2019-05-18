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
    private int code;
    @Expose
    private int iterations;
    @Expose
    private int deadline;
    
    
    public Request( int client_id,int request_number, int code, int iterations, int deadline){
        this.iterations = iterations;
        this.client_id = client_id;
        this.id =client_id*1000 +request_number;
        this.code = code;
        this.iterations = iterations;
        this.deadline = deadline;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    
    
    
    @Override
    public int compare(Request t, Request t1) {
        
        if(t.deadline == t1.deadline){
            return t.id - t1.id;
        }
        return t.deadline-t1.deadline;
        
    }
}
