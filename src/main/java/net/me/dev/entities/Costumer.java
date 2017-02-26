package net.me.dev.entities;

import java.util.ArrayList;

public class Costumer implements Runnable {
	
	private ArrayList<Product> products;
	
	public Costumer(ArrayList<Product> products) {
		this.products = products;
	}

	public void consume(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		synchronized(this.products) {
			if(this.products.isEmpty()) {
				try {
					System.out.println("The product list is empty so wait "+Thread.currentThread().getName());
					products.wait();
					//Todo código debajo de wait no se ejecuta hasta que el objeto en espera es notificado
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			//código que se ejecuta después de que el objeto en espera es notificado
			Product product = this.products.get(0);
			this.products.remove(0);
			System.out.println(product+" has been gotten by "+Thread.currentThread().getName());
		} 
	}
	
	public void run() {
		this.consume();
	}
}


/*
 * para poder suspender un thread es necesario elegir un objeto monitor que conecte al suspendido y al despertador
 * en este caso ese objeto monitor es la lista de productos.
 * 
 * El objeto que actua como monitor debe de sincronizarse en el oyente y el despertador, 
 * el bloque synchronize es como un tunel de comunicacion entre el objeto oyente y el objeto despertador
 * 
 * Logica, el codigo primero va el wait y abajo de este va el codigo que se ejecutaria normalmente
 * 
 * Todo lo que esta debajo de wait no se ejecuta
 * 
 * El notify ejecuta lo que esta debajo del wait
 * 
 */