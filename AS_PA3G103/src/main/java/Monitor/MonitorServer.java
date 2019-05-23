/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Monitor;

import Clients.*;
import Load_Balancer.LoadBalance;
import Utils.ServerManageRequest;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nuno Aparicio
 */
public class MonitorServer extends Thread{
    
    private ServerSocket serverSocket = null;
    private Socket clientSocket = null;;
    private int port = 1000;
    private PrintWriter out = null;
    private BufferedReader in = null;
    
    public MonitorServer(){
        start();
    }
    
    @Override
    public void run() {
        Gson gson = new Gson();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(MonitorServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println( "MonitorServer is listening on port: " + port );

        while (true ) {
            System.out.println( "MonitorServer is accepting a new connection");
            try {
                // wait for a new connection/client
                clientSocket = serverSocket.accept();
                
            // socket's input stream
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            // wait for a message from the client
            System.out.println("MonitorServer is waiting for a new message");
            String request = in.readLine();
            System.out.println("MonitorServer received a new message: "+ request);
            
            if(request.contains("available_servers")){
                ServerManageRequest server = gson.fromJson(request, ServerManageRequest.class);
                Map<Integer,Integer> ports = server.getServer_ports();
                for(Map.Entry<Integer,Integer> entry: ports.entrySet()){
                    Heartbeat.addServer(entry.getKey(),entry.getValue());
                }
            }
            
            in.close();
            clientSocket.close();
                
            } catch (IOException ex) {

            }           
        }     
    }
}
