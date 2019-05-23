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
    private String type;
    @Expose
    private int id;
    @Expose
    private int client_id;
    @Expose
    private int code;
    @Expose
    private int iterations;
    @Expose
    private double pi;
    @Expose
    private int deadline;
    @Expose
    private int porta;
    
    
    public Request(String type, int client_id,int request_number, int code, int iterations,double pi, int deadline, int porta){
        this.type = type;
        this.client_id = client_id;
        this.id =client_id*1000 +request_number;
        this.code = code;
        this.iterations = iterations;
        this.pi = pi;
        this.deadline = deadline;
        this.porta = porta;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }


    public double getPi() {
        return pi;
    }

    public void setPi(double pi) {
        this.pi = pi;
    }
    
    @Override
    public int compare(Request t, Request t1) {
        
        if(t.deadline == t1.deadline){
            return t.id - t1.id;
        }
        return t.deadline-t1.deadline;
        
    }

    @Override
    public String toString() {
        return "Request{" + "id=" + id + ", client_id=" + client_id + ", code=" + code + ", iterations=" + iterations + ", deadline=" + deadline + ", porta=" + porta + '}';
    }
    
}
