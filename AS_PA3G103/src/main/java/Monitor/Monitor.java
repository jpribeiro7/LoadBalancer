package Monitor;

import Utils.Request;
import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Monitor {


 public static void main(String args[]) throws IOException {

 }
 

 public static boolean isPortInUse(int port){
  // Assume no connection is possible.
 boolean result = false;
 String host = "localhost";
  try {
    (new Socket(host, port)).close();
    result = true;
  }
  catch(SocketException e) {
    // Could not connect.
  }  catch (IOException ex) {
         Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
     }

  return result;
}
}
