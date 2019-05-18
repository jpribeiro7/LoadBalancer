package Load_Balancer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


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
            System.out.println("Load Balancer received a new message: " + request);
            //out.println("Message sent was:" + request);
            // close everything
            
            String response = sendMessage(request,LoadBalance.getFreeServer().getPort());
            out.println(response);
            out.close();
            in.close();
            socket.close();
            
            
        } catch (IOException ex) {
        }
    }
    public String sendMessage(String message,int port){
        String host = "localhost";
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        
        // open a connection with the server
        try {
            // create a socket
            echoSocket = new Socket("localhost", port);
            // socket's output stream
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            // socket's input stream
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

        } catch (UnknownHostException e) {
            System.err.println("Don't know about " + host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + host);
            System.exit(1);
        }
        System.out.println("Connection is established with the Server");
        out.println(message);
        String response="";
        try {
             response= in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(ThreadEcho.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            echoSocket.close();
            out.close();
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(ThreadEcho.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
        
    }
}
