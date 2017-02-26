package net.me.dev.entities;

import java.util.ArrayList;
import java.util.Random;

public class Producer implements Runnable {
	
	private ArrayList<Product> products = new ArrayList<Product>();
	
	public Producer(ArrayList<Product> products) {
		this.products = products;
	}
	
	public void produce() {
		while(!Thread.currentThread().isInterrupted()) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			synchronized(this.products) {
				Product product = new Product("Product "+ (new Random().nextInt(50)+1));
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