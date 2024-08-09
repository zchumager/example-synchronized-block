package net.mazatlab.dev.consumer_producer;

import java.util.ArrayList;
import net.me.dev.entities.*;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Consumer-Producer Application" );
        
        ArrayList<Product> products = new ArrayList<Product>();
        
        Costumer c1 = new Costumer(products);
        Costumer c2 = new Costumer(products);
        Costumer c3 = new Costumer(products);

        Producer p = new Producer(products);
        
        Thread t1 = new Thread(c1, "Maria");
        Thread t2 = new Thread(c2, "Pedro");
        Thread t3 = new Thread(c3, "Victor");

        Thread t4 = new Thread(p);
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}