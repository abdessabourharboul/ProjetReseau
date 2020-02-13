package fr.angers.univ.info.m2.acdi.reseau.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
//import java.util.Scanner;

import com.google.gson.Gson;

import fr.angers.univ.info.m2.acdi.reseau.Main;
import fr.angers.univ.info.m2.acdi.reseau.services.Chiffrement;
import fr.angers.univ.info.m2.acdi.reseau.utils.Constantes;

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
		// InputStreamReader isr = new InputStreamReader(System.in);
		// BufferedReader br = new BufferedReader(isr);

		while (true) {
			// InputStreamReader isr = new InputStreamReader(System.in);
			// BufferedReader br = new BufferedReader(isr);
			// Scanner scanner = new Scanner(System.in);
			// System.out.print("[ " + this.name + " ] : ");

			String msg;
			try {
				msg = br.readLine();
				// msg = scanner.next();
				if (this.socket.isConnected()) {
					// Envoi du message
					Gson gson = new Gson();
					if (msg.equals("#")) {
						System.out.println("Clé publique envoyé !");

						String pubKeyAsJson = gson.toJson(Main.client.getM_clePublique());
						OutputStream os = this.socket.getOutputStream();
						PrintWriter pw = new PrintWriter(os, true);
						pw.println(pubKeyAsJson);
					} else {
						OutputStream is = this.socket.getOutputStream();
						PrintWriter pw = new PrintWriter(is, true);
						if (Main.client.getDist_client_pubKey() != null) {
							Chiffrement chiffrement = new Chiffrement(Main.client.getDist_client_pubKey(), msg);
							String pubKeyAsJson = gson.toJson(chiffrement);
							pw.println(Constantes.MOTIF_CHIFFREMENT + pubKeyAsJson);
						}
					}
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
