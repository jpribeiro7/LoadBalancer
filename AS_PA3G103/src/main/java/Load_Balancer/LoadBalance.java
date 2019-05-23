/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Load_Balancer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class LoadBalance {

    private static ServerSocket serverSocket = null;
    private static final int port = 9000;
    private static Map<Integer,Integer> cluster = new HashMap<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        
        // create a server socket
        serverSocket = new ServerSocket(port);
        
        System.out.println("Load Balancer is listening on port: " + port);
        while (true) {
            System.out.println("Load Balancer is accepting a new connection");
            // wait for a new connection/client
            Socket clientSocket = serverSocket.accept();
            // create a new thread to deal with the new client
            ThreadEcho te = new ThreadEcho(clientSocket);
            // Launch the Thread (run).
            te.start();
           
            
        }
        // create queue to store requests
        

    }
   
    public static void addPort(Integer port, Integer load){
        cluster.put(port,load);
    }
    
    public static void removePort(Integer port){
        if (cluster.containsKey(port)){
            cluster.remove(port);
        }
         
     }
    
    public static Integer getFreePort(){
        if(cluster.isEmpty()){
            return -1;
        }
        int min_load= 100;
        int current_port=0;
        for(Map.Entry<Integer,Integer> entry: cluster.entrySet()){
                   if(entry.getValue()<min_load){
                       min_load = entry.getValue();
                       current_port=entry.getKey();
                   }
                }
        return current_port;
    }
    

    
}
