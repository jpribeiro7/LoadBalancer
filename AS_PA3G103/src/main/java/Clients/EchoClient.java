/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * This is a client aimed to contact a server
 * on localhost and port 5000. User may insert a line to
 * be sent to the server. The server will answer with the same
 * message. Connection is closed if a null message is sent
 * to the server.
 * @author Óscar Pereira
 */
public class EchoClient {
        
    private static Socket echoSocket;
    private static PrintWriter out;
    private static BufferedReader in;
    private static String host;
    //private static int port;
    
    public EchoClient(){
        echoSocket = null;
        out = null;
        in = null;
        host = "localhost";
        //port = 5000;
    }
    
    public void prepareRace(String message, int port) throws IOException{
        // open a connection with the server
        try {
            // create a socket
            echoSocket = new Socket( host, port );
            // socket's output stream
            out = new PrintWriter( echoSocket.getOutputStream(), true );
            // socket's input stream
            in = new BufferedReader( new InputStreamReader( echoSocket.getInputStream() ) );

        } catch( UnknownHostException e ) {
            System.err.println( "Don't know about " + host );
            System.exit( 1 );
        }
        catch (IOException e ) {
            System.err.println( "Couldn't get I/O for the connection to: " + host );
            System.exit( 1 );
        }
        System.out.println( "Connection is established with the Server" );
        
        // input stream from the console (messages to be sento to the server)
        BufferedReader stdIn = new BufferedReader( new InputStreamReader( System.in ) );
        BufferedReader stdIn1 = new BufferedReader( new InputStreamReader( System.in ) );
        
        
                       
            // send the message to the server
            out.println(message);           
            
            // wait for echo
            String txt = in.readLine();
            // print echo
            System.out.println( "Client received echo: " + txt );

        
        // empty message -> close connection
        out.close();
        in.close();
        echoSocket.close();
        System.out.println( "Client closed the connection" );
        //System.exit( 0 );
    }
}
