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

/**
 *
 * @author Nuno Aparicio
 */
public class LoadBalance {

    private static ServerSocket serverSocket = null;
    private static Socket clientSocket = null;
    private static final int port = 5000;
    private List<EchoServer> cluster;

    public LoadBalance(){
        cluster = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        EchoServer[] myThreadAdd = new EchoServer[10];

        for (int i = 0; i < 10; i++) {
            myThreadAdd[i] = new EchoServer(5000 + i+1, 20);
        }

        for (int i = 0; i < 10; i++) {
            myThreadAdd[i].start();
        }

        // create a server socket
        serverSocket = new ServerSocket(port);
        System.out.println("Load Balancer is listening on port: " + port);

        while (true) {
            System.out.println("Load Balancer is accepting a new connection");
            // wait for a new connection/client
            clientSocket = serverSocket.accept();
            // create a new thread to deal with the new client
            ThreadEcho te = new ThreadEcho(clientSocket);
            System.out.println("Load Balancer: " + clientSocket.getLocalPort());
            // Launch the Thread (run).
            te.start();
        }
        

    }

}
