/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servers;

import Utils.Request;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.PriorityQueue;
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
    private Socket clientSocket = null;
    private PriorityQueue<Request> queue;
    private int queue_limit;
    private int port;

    public EchoServer(int port,int queue_limit){
        this.port = port;
        queue = new PriorityQueue(queue_limit);
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println( "Server is listening on port: " + port );

        while (true ) {
            System.out.println( "Server is accepting a new connection");
            try {
                // wait for a new connection/client
                clientSocket = serverSocket.accept();
               // create a new thread to deal with the new client
            ThreadEcho te=new ThreadEcho(clientSocket);
            // Launch the Thread (run).
            te.start();
            } catch (IOException ex) {
                Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    public void add(Request req){
        if( queue.size()+1 <= queue_limit ){
            queue.add(req);
        }
    }
    public int getPort(){
        return this.port;
    }
}
