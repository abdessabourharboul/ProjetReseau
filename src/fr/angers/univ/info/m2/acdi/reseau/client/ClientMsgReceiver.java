package fr.angers.univ.info.m2.acdi.reseau.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientMsgReceiver extends Thread {
	private Socket socket;
	
	public ClientMsgReceiver(Socket so) {
		super();
		this.socket = so;
	}
	
	public void run() {
		while (true) {
			try {
				InputStream is = this.socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				
				String msg = br.readLine();
				if (msg != null) {
					System.out.println("\n" + msg);
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
