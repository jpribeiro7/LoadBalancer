/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servers;


import com.sun.jmx.snmp.tasks.ThreadService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javafx.scene.paint.Stop;
import sun.dc.pr.PRError;

/**
 * This class implements the thread responsible for
 * dealing with the new incoming clients
 * @author Óscar Pereira
 */
class ThreadEcho extends Thread {

    private final Socket socket;
    private PrintWriter out = null;
    private BufferedReader in = null;


    // constructor receives the socket
    public ThreadEcho(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        try {
            // socket´s output stream
            out = new PrintWriter(socket.getOutputStream(), true);
            // socket's input stream
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
                // wait for a message from the client
                System.out.println("Thread is waiting for a new message");
                String request = in.readLine();
                System.out.println("Server received a new message: "+ request);
                out.println("Message sent was:" + request);


            
            // close everything
            socket.close();
            out.close();
            in.close();
        } catch (IOException ex) {}
    }
}
