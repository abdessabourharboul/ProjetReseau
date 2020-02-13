package fr.angers.univ.info.m2.acdi.reseau.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import fr.angers.univ.info.m2.acdi.reseau.utils.Constantes;

public class ClientHandler extends Thread {

    private Socket socket;

    public ClientHandler(Socket socket) {
        super();
        this.socket = socket;
    }

    // si on reçoit un message null c'est que le client est déconnecté
    // donc on arrête le Thread
    public void run() {
        System.out.println("Une client vient de se connecter");
        System.out.println("Clients connectés = " + Server.clients.size());

        if (Server.clients.size() >= 2) {
            Server.broadcast("Vous pouvez vous échanger vos clés publiques -> Tapez [#] + [ENTER] pour générer une clé publique à l'autre interlocuteur");
        }

        while (true) {
            try {
                InputStream is = this.socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                // Lecture du message du client
                String msg = br.readLine();
                if (msg != null) {
                    System.out.println("Message envoyé par un client : " + msg);
                    // On envoie le message à l'autre destinataire
                    Server.sendMessage(this.socket, msg);
                } else {
                    System.out.println("Un client vient de se déconnecter");
                    Server.removeClient(this.socket);
                    System.out.println("Clients connectés = " + Server.clients.size());
                    return; // la sortie du Thread
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
