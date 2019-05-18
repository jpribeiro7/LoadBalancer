/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Load_Balancer;

import Servers.EchoServer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class LoadBalance {

    private static ServerSocket serverSocket = null;
    private static final int port = 5000;
    private static List<EchoServer> cluster;

    public LoadBalance(){
        cluster = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        LoadBalance lb = new LoadBalance();
        
        // create a server socket
        serverSocket = new ServerSocket(port);
        
        System.out.println("Load Balancer is listening on port: " + port);
        startCluster(5);
        while (true) {
            System.out.println("Load Balancer is accepting a new connection");
            // wait for a new connection/client
            Socket clientSocket = serverSocket.accept();
            // create a new thread to deal with the new client
            ThreadEcho te = new ThreadEcho(clientSocket);
            System.out.println("Load Balancer: " + clientSocket.getLocalPort());
            // Launch the Thread (run).
            te.start();
        }
        // create queue to store requests
        

    }
    
    public static void addServer(int port, int limit_requests){
        cluster.add(new EchoServer(port, limit_requests));
        
    }
    
    public static EchoServer getFreeServer(){
        return cluster.get(0);
    }
    
    public static void startCluster(int serverNumber){
        for (int i = 1; i <= serverNumber; i++) {
             addServer(port+i, 10);
        }
        cluster.forEach(cnsmr->cnsmr.start());
    }

}
