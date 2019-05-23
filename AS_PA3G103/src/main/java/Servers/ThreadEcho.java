/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servers;


import Utils.Request;
import com.google.gson.Gson;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements the thread responsible for
 * dealing with the new incoming clients
 * @author Óscar Pereira
 */
class ThreadEcho extends Thread {

    private Socket socket;
    private PrintWriter out = null;
    private Gson gson;
    private Request request;


    // constructor receives the socket
    public ThreadEcho(Socket socket, Request request) {
        this.socket = socket;
        out=null;
        gson = new Gson();
        this.request = request;
    }
    
    @Override
    public void run() {
        try {
            // socket´s output stream
            out = new PrintWriter(socket.getOutputStream(), true);
            request.setCode(2);
            out.println(gson.toJson(request));
             out.flush();
            // close everything
            out.close();
            socket.close();
            
            request.setCode(2);
            System.out.println("Executing request with id "+request.getId());
            Thread.sleep(6000);
        } catch (Exception e) {Logger.getLogger(ThreadEcho.class.getName()).log(Level.SEVERE, null, e);}
    }
    
    //perguntar se prof deu formula
    public void computePI(int iterations){
        
    }

    public Request getRequest() {
        return request;
    }

    @Override
    public String toString() {
        return "ThreadEcho{" + "request=" + request + '}';
    }
    
    
}
