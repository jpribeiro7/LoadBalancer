/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Load_Balancer;

import Servers.EchoServer;
import java.io.IOException;

/**
 *
 * @author Nuno Aparicio
 */
public class LoadBalance {
    
    //recebe os requests
    //guarada numa fila em que os prazos de deadline mais curtos sao os que tem prioridade
    //distribuir de forma balanceada pelos servidores
    
     public static void main( String[] args ) throws IOException, InterruptedException {
         EchoServer[] myThreadAdd = new EchoServer[10];
         for(int i=0; i<10;i++){
          myThreadAdd[i] = new EchoServer(5000+i);
         } 
         
         for(int i=0; i<10;i++){
          myThreadAdd[i].start();
         } 
         
        for(int i=0; i<10;i++){
          myThreadAdd[i].join();
         }  
         
     }
    
}
