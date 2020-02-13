package fr.angers.univ.info.m2.acdi.reseau.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import fr.angers.univ.info.m2.acdi.reseau.Main;
import fr.angers.univ.info.m2.acdi.reseau.models.ClePublique;
import fr.angers.univ.info.m2.acdi.reseau.services.Chiffrement;
import fr.angers.univ.info.m2.acdi.reseau.services.Dechiffrement;
import fr.angers.univ.info.m2.acdi.reseau.utils.Constantes;

public class ClientMsgReceiver extends Thread {

	private Socket socket;

	public ClientMsgReceiver(Socket so) {
		super();
		this.socket = so;
	}

	public void run() {
		while (true) {
			String msg = "";
			try {
				InputStream is = this.socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);

				msg = br.readLine();
				if (msg != null) {
					if (msg.startsWith(Constantes.MOTIF_CHIFFREMENT)) {
						throw new JsonSyntaxException(msg);
					}
					Gson gson = new Gson();
					ClePublique userObject = gson.fromJson(msg, ClePublique.class);
					Main.client.setDist_client_pubKey(userObject);
					System.out.println("La clé publique de l'autre client a été recu avec succès");
					System.out.println(userObject);
				}
			} catch (JsonSyntaxException je) {
				if (Main.client.getDist_client_pubKey() != null) {
					if (msg.startsWith(Constantes.MOTIF_CHIFFREMENT)) {
						System.out.println(msg);
						String substring = msg.substring(1);
						System.out.println("Message after # : " + substring);
						Gson gson = new Gson();
						Chiffrement chif = gson.fromJson(substring, Chiffrement.class);
						Dechiffrement dch = new Dechiffrement(Main.client.getM_clePrivee(), chif.getSi());
						System.out.println(dch.getChaineDechifree());

					}
					// on déchiffre
					// Dechiffrement dch = new Dechiffrement(Main.client.getM_clePrivee(),
					// ch.getSi());
				} else {
					System.out.println("\n" + msg);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
