package Load_Balancer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Utils.ServerManageRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;


class ThreadEcho extends Thread {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Gson gson;

    // constructor receives the socket
    public ThreadEcho(Socket socket) {
        this.socket = socket;
        out=null;
        in=null;
        gson = new Gson();
    }

    @Override
    public void run() {
        try {
            // socket's input stream
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String request = in.readLine();
            System.out.println("Request to load balancer from "+socket.getPort()+": "+request);
            if(request.contains("client_id")){
                sendMessage(request,LoadBalance.getFreePort());
            
            }else{
                ServerManageRequest server = gson.fromJson(request, ServerManageRequest.class);
                Map<Integer,Integer> ports = server.getServer_ports();
                for(Entry<Integer,Integer> entry: ports.entrySet()){
                    LoadBalance.addPort(entry.getKey(),entry.getValue());
                }
            }
            in.close();
            socket.close();
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(ThreadEcho.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void sendMessage(String message,int port){
        String host = "localhost";
        Socket echoSocket = null;
        PrintWriter second_out = null;
        // open a connection with the server
        try {
            // create a socket
            echoSocket = new Socket("localhost", port);
            // socket's output stream
            second_out = new PrintWriter(echoSocket.getOutputStream(), true);
            second_out.println(message);
            second_out.flush();
           
            second_out.close();
            echoSocket.close();

        } catch (UnknownHostException e) {
            System.err.println("Don't know about " + host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + host);
            System.exit(1);
        }
        
    }
}
