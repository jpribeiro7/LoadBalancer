/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clients;

import Utils.Request;
import java.util.Random;

/**
 *
 * @author Pedro
 */
public class Client {
    private static int n_clients=0;                              //total clients
    private static EchoClient echo = new EchoClient();             //echo client
    
    
    private int id=0;                                  //client id is sequential
    private int request_number;                          //client request number
    

    public Client() {
        n_clients++;
        id = n_clients;
        request_number = 0;
    }
    
    public void computePI(){
        Random r = new Random();
        Request message = new Request(id,request_number,01,10,r.nextInt(11)+10);
        echo.sendMessage(message);
        request_number++;
    }
    
    
}
