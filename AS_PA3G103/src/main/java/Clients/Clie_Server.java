/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clients;

import Utils.Request;
import Clients.Cliente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.Gson;

/**
 *
 * @author Nuno Aparicio
 */
public class Clie_Server extends Thread{
    
    private ServerSocket serverSocket = null;
    private Socket clientSocket = null;;
    private int port;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private Request request;
    private Gson gson;
    
    public Clie_Server(int port){
        this.port = port;
        gson = new Gson();
        start();
    }

    public Utils.Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
    
    
    
    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(Clie_Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println( "Client_Server is listening on port: " + port );

        while (true ) {
            System.out.println( "Client_Server is accepting a new connection");
            try {
                // wait for a new connection/client
                clientSocket = serverSocket.accept();
                
                // socket´s output stream
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            // socket's input stream
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            // wait for a message from the client
            System.out.println("Client_Server is waiting for a new message");
            String message = in.readLine();
            
            Request req = gson.fromJson(message, Request.class);
            
            this.setRequest(req);
            System.out.println("Client_Server received a new message: "+ message);
            
            // close everything
            out.close();
            in.close();
            clientSocket.close();
                
            } catch (IOException ex) {

            }           
        }     
    }
}
