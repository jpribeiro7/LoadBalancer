/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clients;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nuno Aparicio
 */
public class Clie_Server extends Thread{
    
    private ServerSocket serverSocket = null;
    private Socket clientSocket = null;;
    private int port;
    
    public Clie_Server(int port){
        this.port = port;
        start();
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
                
               // create a new thread to deal with the new client
            ThreadEcho_ClieServer te=new ThreadEcho_ClieServer(clientSocket);
            // Launch the Thread (run).
            te.start();
            } catch (IOException ex) {

            }           
        }     
    }
}
