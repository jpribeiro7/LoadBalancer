/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servers;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * This is a Server listening on port 5000.
 * In this example, the server launches a new thread for
 * every new arriving connection. The thread is responsible
 * for echoing all messages. The connection is closed
 * when a null message is received.
 * @author Óscar Pereira
 */
public class EchoServer1 {

    private static ServerSocket serverSocket = null;
    private static Socket clientSocket = null;;
    private static final int port = 5001;

    
    public static void main( String[] args ) throws Exception{
        // create a srevr socket
        serverSocket = new ServerSocket( port );
        System.out.println( "Server is listening on port: " + port );

        while (true ) {
            System.out.println( "Server is accepting a new connection");
            // wait for a new connection/client
            clientSocket = serverSocket.accept();
            // create a new thread to deal with the new client
            ThreadEcho te=new ThreadEcho(clientSocket);
            System.out.println("Socket: " + clientSocket.getLocalPort());
            // Launch the Thread (run).
            te.start();
        }
    }
    
    
}
