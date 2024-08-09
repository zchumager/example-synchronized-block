package net.me.dev.entities;

import java.util.ArrayList;
import java.util.Random;

public class Producer implements Runnable {
	
	private final ArrayList<Product> products;
	private final long timeout = 18000;

	public Producer(ArrayList<Product> products) {
		this.products = products;
	}
	
	public void produce() {
		long stopTime = System.currentTimeMillis() + this.timeout;

		while(System.currentTimeMillis() < stopTime) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.fillInStackTrace();
            }

            synchronized(this.products) {
				Product product = new Product("Product "+ (new Random().nextInt(1500)+1));
				System.out.println(product+" has been added");
				this.products.add(product);
				this.products.notify();
			}
		}
	}
	
	public void run() {
		this.produce();
	}
}

//http://www.journaldev.com/1037/java-thread-wait-notify-and-notifyall-example