package fr.angers.univ.info.m2.acdi.reseau;

import fr.angers.univ.info.m2.acdi.reseau.models.ClePrivee;
import fr.angers.univ.info.m2.acdi.reseau.models.ClePublique;
import fr.angers.univ.info.m2.acdi.reseau.services.Chiffrement;
import fr.angers.univ.info.m2.acdi.reseau.services.Dechiffrement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import fr.angers.univ.info.m2.acdi.reseau.client.Client;
import fr.angers.univ.info.m2.acdi.reseau.client.ClientMsgReceiver;
import fr.angers.univ.info.m2.acdi.reseau.client.ClientMsgSender;
//import fr.angers.univ.info.m2.acdi.reseau.models.ClePublique;
import fr.angers.univ.info.m2.acdi.reseau.server.Server;

public class Main {

    public static Client client = null;

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        //testFunc();

        // TODO Auto-generated method stub
        // ClePublique c = new ClePublique();
        // Scanner de lecture de la saisie de l'utilisateur sur
        // la console
        // https://dzone.com/articles/interactive-console-applications-in-java
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenue sur l'application ALICE & BOB - CHAT");
        System.out.println("Choisissez votre mode suivant ces instructions");
        System.out.println("[Client] - Tapez 1");
        System.out.println("[Serveur] - Tapez 2");

        int mode;
        do {
            System.out.print("Entrez votre choix : ");
            // Choix du mode entre client et serveur
            mode = scanner.nextInt();
        } while (mode != 1 && mode != 2);

        if (mode == 1) {
            // on se connecte comme étant un client
            System.out.println("\nVotre choix [CLIENT]\n");

            scanner = new Scanner(System.in);

            System.out.print("IP du serveur : ");
            String ip = scanner.nextLine();

            System.out.print("Port sur le serveur : ");
            int port = scanner.nextInt();

            scanner = new Scanner(System.in);
            System.out.print("Votre pseudo sur le réseau : ");
            String name = scanner.nextLine();

            // scanner.close();
            try {
                Socket so = new Socket(ip, port);
                client = new Client();

                System.out.println("Connexion établie");

                // Thread de lecture de messages
                ClientMsgReceiver cmr = new ClientMsgReceiver(so);
                cmr.start();

                // Thread d'écriture de messages
                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);
                ClientMsgSender cms = new ClientMsgSender(so, name, br);
                cms.start();

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // on exécute l'application comme étant un serveur
            System.out.println("\nVotre choix [SERVEUR]\n");
            System.out.print("N° de port : ");
            int port = scanner.nextInt();

            // À l'exécution de l'application, il faut
            // lancer le serveur de communication
            // qui écoutera sur un port donné
            Server.runServer(port);
        }

        // scanner.close();
    }

    public static void testFunc() {
        String testChiffrement = "Bonjour!";
        ClePublique clePublique = new ClePublique();
        ClePrivee clePrivee = new ClePrivee(clePublique);
        System.out.println("Cle Privee (N) : " + clePrivee.getN());
        System.out.println("Cle Privee (U) : " + clePrivee.getU());
        Chiffrement ch = new Chiffrement(clePublique, testChiffrement);
        Dechiffrement dch = new Dechiffrement(clePrivee, ch.getSi());
        System.out.println(testChiffrement);
        System.out.println("Apres dechifrement " + dch.getChaineDechifree());

    }

}
