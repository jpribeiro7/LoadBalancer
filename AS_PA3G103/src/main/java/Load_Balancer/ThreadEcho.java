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
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


class ThreadEcho extends Thread {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    // constructor receives the socket
    public ThreadEcho(Socket socket) {
        this.socket = socket;
        out=null;
        in=null;
    }

    @Override
    public void run() {
        try {
            
            // socket's input stream
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String request = in.readLine();
            System.out.println("Request to load balancer from "+socket.getPort()+": "+request);
            //out.println("Message sent was:" + request);
            // close everything
            
            String response = sendMessage(request,LoadBalance.getFreeServer().getPort());
            
            System.out.println("Load balancer has received: "+response);
            // socket´s output stream
            out = new PrintWriter(socket.getOutputStream(),true);
            out.println(response);
            
            out.flush();
            
            in.close();
            out.close();
            socket.close();
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(ThreadEcho.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String sendMessage(String message,int port){
        String host = "localhost";
        Socket echoSocket = null;
        PrintWriter second_out = null;
        BufferedReader in = null;
        String response="";
        // open a connection with the server
        try {
            // create a socket
            echoSocket = new Socket("localhost", port);
            // socket's output stream
            second_out = new PrintWriter(echoSocket.getOutputStream(), true);
            // socket's input stream
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            second_out.println(message);
            second_out.flush();
            response= in.readLine();
           
            
            second_out.close();
            in.close();
            echoSocket.close();

        } catch (UnknownHostException e) {
            System.err.println("Don't know about " + host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + host);
            System.exit(1);
        }
        
        return response;
        
    }
}
