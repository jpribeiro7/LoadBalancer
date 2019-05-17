/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clients;

/**
 *
 * @author Nuno Aparicio
 */
public class Main {
    
        public static void main( String[] args ) throws Exception{
            EchoClient cliente = new EchoClient();
            cliente.prepareRace("Hello server 5", 5005);
        }
    
}
