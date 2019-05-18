/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clients;

public class Main {

    public static void main(String[] args) throws Exception {
        
        int argsize = 5;
        Thread myThreads[] = new Thread[argsize];
        for (int j = 0; j < argsize; j++) {
            myThreads[j] = new Thread(){
                public void run(){
                  Client client = new Client();
                  client.computePI();
                }
  };
            myThreads[j].start();
            
        }
        for (int j = 0; j < argsize; j++) {
            myThreads[j].join(); //todo add catch exception
        }
        
    }

}
