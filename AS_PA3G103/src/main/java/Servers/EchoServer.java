/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is a Server listening on port 5000.
 * In this example, the server launches a new thread for
 * every new arriving connection. The thread is responsible
 * for echoing all messages. The connection is closed
 * when a null message is received.
 * @author Óscar Pereira
 */
public class EchoServer extends Thread{
    
    private ServerSocket serverSocket = null;
    private Socket clientSocket = null;;
    private int port;

    public EchoServer(int port) throws IOException{
        this.port = port;
        serverSocket = new ServerSocket(port);  
    }

    @Override
    public void run() {
           
        System.out.println( "Server is listening on port: " + port );

        while (true ) {
            System.out.println( "Server is accepting a new connection");
            try {
                // wait for a new connection/client
                clientSocket = serverSocket.accept();
            } catch (IOException ex) {
                Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            // create a new thread to deal with the new client
            ThreadEcho te=new ThreadEcho(clientSocket);
            System.out.println("Socket: " + clientSocket.getLocalPort());
            // Launch the Thread (run).
            te.start();
        }
        
    }
    
    
}
