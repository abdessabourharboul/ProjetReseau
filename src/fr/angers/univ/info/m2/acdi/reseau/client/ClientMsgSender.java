package fr.angers.univ.info.m2.acdi.reseau.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
//import java.util.Scanner;

public class ClientMsgSender extends Thread {
	private Socket socket;
	private String name;
	private BufferedReader br;
	
	public ClientMsgSender(Socket socket, String name, BufferedReader br) {
		super();
		this.socket = socket;
		this.name = name;
		this.br = br;
	}
	
	public void run() {
		//InputStreamReader isr = new InputStreamReader(System.in);
		//BufferedReader br = new BufferedReader(isr);
		
		while (true) {
			//InputStreamReader isr = new InputStreamReader(System.in);
			//BufferedReader br = new BufferedReader(isr);
			//Scanner scanner = new Scanner(System.in);
			//System.out.print("[ " + this.name + " ] : ");
			
			String msg;
			try {
				msg = br.readLine();
				//msg = scanner.next();
				if (this.socket.isConnected()) {
					// Envoi du message
					OutputStream is = this.socket.getOutputStream();
					PrintWriter pw = new PrintWriter(is, true);
					pw.println("[ " + this.name + " ] " + msg);
				} else {
					System.out.println("Le serveur est déconnecté !!!");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
				return; // on arrête le Thread
			}
			
			/*while(scanner.hasNextLine() && msg == null){
				System.out.println("Je passe ici");
			    msg = scanner.nextLine();
			}*/
			
			//String msg = scanner.nextLine();
			
			/*if (this.socket.isConnected()) {
				// Envoi du message
				try {
					OutputStream is = this.socket.getOutputStream();
					PrintWriter pw = new PrintWriter(is, true);
					pw.println("[ " + this.name + " ] " + msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Le serveur est déconnecté !!!");
			}*/
			//scanner.close();
		}		
	}
}
