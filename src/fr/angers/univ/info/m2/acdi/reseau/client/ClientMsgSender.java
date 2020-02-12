package fr.angers.univ.info.m2.acdi.reseau.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

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
		while (true) {		
			String msg;
			try {
				msg = br.readLine();
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
		}		
	}
}
