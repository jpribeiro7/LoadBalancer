package Monitor;

import Utils.GetLoad;
import Utils.Request;
import com.google.gson.Gson;
import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Monitor {
   static Gson gson = new Gson();
   static PrintWriter out;
   static  BufferedReader in;

 public static void main(String args[]) throws IOException {

 }
 

 public static boolean isPortInUse(int port){
  // Assume no connection is possible.
 boolean result = false;
 String host = "localhost";
  try {
    (new Socket(host, port)).close();
    System.out.println("THIS IS SOMETHING");
    result = true;
  }
  catch(SocketException e) {
    // Could not connect.
  }  catch (IOException ex) {
         Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
     }

  return result;
}
 
 public static int getLoadFromPort(int port){
  // Assume no connection is possible.
  int load=0;
 String host = "localhost";
  try {
    Socket socket = new Socket(host, port);
    
    GetLoad message = new GetLoad("getload", load);
    
    // socket's output stream
    out= new PrintWriter(socket.getOutputStream(), true);
    out.println(gson.toJson(message));
    
    
    //Receive message
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    String request = in.readLine();
    load = gson.fromJson(request, GetLoad.class).getLoad();
    in.close();
    out.close();
    socket.close();
    
  }
  catch(SocketException e) {
    // Could not connect.
  }  catch (IOException ex) {
         Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
     }

  return load;
}
 
}
