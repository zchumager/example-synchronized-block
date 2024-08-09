package net.me.dev.entities;

import java.util.ArrayList;

public class Costumer implements Runnable {
	
	private final ArrayList<Product> products;
	
	public Costumer(ArrayList<Product> products) {
		this.products = products;
	}

	public void consume(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.fillInStackTrace();
		}
		
		synchronized(this.products) {
			if(this.products.isEmpty()) {
				try {
					System.out.println("The product list is empty so wait "+Thread.currentThread().getName());
					products.wait();
					/*
					* Note:
					* All code below wait method execution will not be executed
					* until the same instance executes the notify method
					* */
				} catch (InterruptedException e) {
					e.fillInStackTrace();
				}
			}
			
			/*
			* Note:
			* This code is being executed when notify is being executed.
			* In this case products.notify() is being called by an instance of Producer class
			* */
			Product product = this.products.get(0);
			this.products.remove(0);
			System.out.println(product+" has been gotten by "+Thread.currentThread().getName());
		} 
	}
	
	public void run() {
		this.consume();
	}
}