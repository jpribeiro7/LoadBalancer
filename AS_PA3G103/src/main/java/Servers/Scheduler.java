/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servers;

import Utils.Request;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro
 */
public class Scheduler extends Thread{
    private int queue_limit;
    private PriorityQueue<ThreadEcho> queue;
    private final static int max_threads_running = 5;
    private int threads_running;
    private ThreadPoolExecutor second;

    public Scheduler(int queue_limit) {
        this.queue_limit = queue_limit;
        queue = new PriorityQueue<>(queue_limit, new Comparator<ThreadEcho>(){
            @Override
            public int compare(ThreadEcho t, ThreadEcho t1) {
                return t.getRequest().compare(t.getRequest(), t1.getRequest());
            }
            
        });
        
    }
    
    
    @Override
    public void run() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(max_threads_running);
        while(true){
            if(executor.getActiveCount()<max_threads_running && !queue.isEmpty()){
                Thread te = queue.poll();
                if(te!=null){
                    executor.execute(te);
                }
            }else{
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                
            
            
        }
    }
    
    public boolean add(ThreadEcho e){
        if(queue.size()+1 <= queue_limit){
            queue.add(e);
            return true;
        }
        return false;
    }

    public PriorityQueue<ThreadEcho> getQueue() {
        return queue;
    }
    
}
