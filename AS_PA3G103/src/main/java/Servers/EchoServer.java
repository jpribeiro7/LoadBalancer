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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
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
    private Scheduler scheduler;
    private int port;
    private boolean running;
    private Gson gson;
    private BufferedReader in = null;
    
    public EchoServer(int port,int queue_limit){
        this.port = port;
        gson = new Gson();
        scheduler = new Scheduler(queue_limit);
        scheduler.start();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            running = true;
        } catch (IOException ex) {
            Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println( "Server is listening on port: " + port );

        while (true ) {
            System.out.println( "Server is accepting a new connection");
            try {
                // wait for a new connection/client
                clientSocket = serverSocket.accept();
                // socket's input stream
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                // wait for a message from the client
                System.out.println("Thread is waiting for a new message");
                String request = in.readLine();
                System.out.println("Server received a new message: "+ request);
                
                Request req = gson.fromJson(request, Request.class);
                
                
               // create a new thread to deal with the new client and send it to the queue
               clientSocket = new Socket("localhost",req.getPorta());
                ThreadEcho te=new ThreadEcho(clientSocket,req);
                add(te);
            
            } catch (IOException ex) {
                if(!running){
                    break;
                }
            }
            
        }
        
    }
    
    public boolean add(ThreadEcho e){
        return scheduler.add(e);
    }
    public int getPort(){
        return this.port;
    }
    public int getLoad(){
        return scheduler.getQueue().size();
    }

    public ArrayList<Request> getQueue() {
        PriorityQueue<ThreadEcho> requests_handler =  scheduler.getQueue();
        ArrayList<Request> requests = new ArrayList<>();
        requests_handler.forEach(cnsmr->requests.add(cnsmr.getRequest()));
        return requests;
    }
    
    public void terminateServer(){
        try {
            running = false;
            serverSocket.close();
            
        } catch (IOException ex) {
            Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
