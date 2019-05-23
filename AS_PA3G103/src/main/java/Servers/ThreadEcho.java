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
            System.out.println("Executing request with id "+request.getId());
            // socket´s output stream
            out = new PrintWriter(socket.getOutputStream(), true);
            request.setCode(2);
            request.setPi(computePI(request.getIterations()));
            out.println(gson.toJson(request));
            out.flush();
            // close everything
            out.close();
            socket.close();
            
        } catch (Exception e) {Logger.getLogger(ThreadEcho.class.getName()).log(Level.SEVERE, null, e);}
    }
    
    //perguntar se prof deu formula
   public double computePI(int iterations){
        double pi =0;
        double denominator = 1;
       for (int x = 0; x < iterations; x++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadEcho.class.getName()).log(Level.SEVERE, null, ex);
            }
         if (x % 2 == 0) {
            pi = pi + (1 / denominator);
         } else {
            pi = pi - (1 / denominator);
         }
         denominator = denominator + 2;
      }
      return pi * 4;
    }
    public Request getRequest() {
        return request;
    }

    @Override
    public String toString() {
        return "ThreadEcho{" + "request=" + request + '}';
    }
    
    
}
