/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servers;


import Utils.Request;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    private BufferedReader in = null;
    private Gson gson;


    // constructor receives the socket
    public ThreadEcho(Socket socket) {
        this.socket = socket;
        gson = new Gson();
    }
    
    @Override
    public void run() {
        try {
            
            // socket´s output stream
            out = new PrintWriter(socket.getOutputStream(), true);
            // socket's input stream
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // wait for a message from the client
            System.out.println("Thread is waiting for a new message");
            String request = in.readLine();
            System.out.println("Server received a new message: "+ request);
                
            Request req = gson.fromJson(request, Request.class);
            req.setCode(2);
            out.println(gson.toJson(req));
            // close everything
            out.close();
            in.close();
            socket.close();
        } catch (IOException ex) {Logger.getLogger(ThreadEcho.class.getName()).log(Level.SEVERE, null, ex);}
    }
}
