package com.fabricgroup;


public class DieLikeADog {
    private static Object s = new Object();
    private static int count = 0;
    public static void main(String[] argv){
        for(;;){
            new Thread(new Runnable(){
                    public void run(){
                    	
                    	int mb = 1024*1024;
                        
                        //Getting the runtime reference from system
                        Runtime runtime = Runtime.getRuntime();
                         
                        
                        synchronized(s){
                            count += 1;
                            System.out.println("##### Heap utilization statistics [MB] #####");
                            
                            //Print used memory
                            System.out.println("Used Memory:"
                                + (runtime.totalMemory() - runtime.freeMemory()) / mb);
                     
                            //Print free memory
                            System.out.println("Free Memory:"
                                + runtime.freeMemory() / mb);
                             
                            //Print total available memory
                            System.out.println("Total Memory:" + runtime.totalMemory() / mb);
                     
                            //Print Maximum available memory
                            System.out.println("Max Memory:" + runtime.maxMemory() / mb);
                            
                            System.err.println("New thread #"+count);
                        }
                        for(;;){
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e){
                                System.err.println(e);
                            }
                        }
                    }
                }).start();
        }
    }
}