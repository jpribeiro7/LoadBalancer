package Monitor;

import Utils.Request;
import java.io.*;
import java.net.*;
import java.util.Random;


public class Monitor {


 public static void main(String args[]) throws IOException {

 }
 

 public boolean isPortInUse(int port) throws IOException {
  // Assume no connection is possible.
  boolean result = false;
    boolean x = false;
    String host = "localhost";
  try {
    (new Socket(host, port)).close();
    result = true;
  }
  catch(SocketException e) {
    // Could not connect.
  }

  return result;
}
}
