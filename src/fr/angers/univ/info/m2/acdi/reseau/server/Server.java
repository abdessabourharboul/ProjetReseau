package fr.angers.univ.info.m2.acdi.reseau.server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

public class Server {

    public static final ArrayList<Socket> clients = new ArrayList<Socket>();

    public static final void addClient(Socket so) {
        // On 'ajoute pas un client qui existe déjà
        if (!clients.contains(so)) {
            clients.add(so);
        }
    }

    public static final void removeClient(Socket so) {
        if (clients.contains(so)) {
            clients.remove(so);
        }
    }

    public static void sendMessage(Socket so, String msg) {
        // Cette méthode se comporte comme un broadcast
        // sauf qu'il n'existe qu'un seul destinataire
        System.out.println("size " + clients.size());
        for (Socket client : clients) {
            if (client != so) {
                try {
                    System.out.println("je vais envoyé ca :\n " + msg);
                    OutputStream os = client.getOutputStream();
                    PrintWriter pw = new PrintWriter(os, true);
                    pw.println(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static void broadcast(String msg) {
        for (Socket client : clients) {
            try {
                OutputStream os = client.getOutputStream();
                PrintWriter pw = new PrintWriter(os, true);
                pw.println(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("resource")
    public static void runServer(int port) {
        try {
            // le SocketServe qui sera lancé tournera sur le port "PORT"
            ServerSocket ss = new ServerSocket(port);

            System.out.println("\n*******************************************");
            System.out.println("*******************************************");

            System.out.println("Serveur lancé avec succès !");
            System.out.println("Port : " + port + "\n");

            // Affichage des adresses
            Enumeration<?> n = null;
            try {
                n = NetworkInterface.getNetworkInterfaces();
            } catch (SocketException e) {
                e.printStackTrace();
            }
            for (; n.hasMoreElements();) {
                NetworkInterface i = (NetworkInterface) n.nextElement();
                System.out.println("Interface: " + i.getName());
                Enumeration<?> a = i.getInetAddresses();
                for (; a.hasMoreElements();) {
                    InetAddress addr = (InetAddress) a.nextElement();
                    System.out.println("\t" + addr.getHostAddress());
                }
            }

            System.out.println("*******************************************");
            System.out.println("*******************************************\n");

            // attente de la connexion des clients
            while (true) {
                Socket s = null;

                try {
                    // un client vient de se connecter
                    s = ss.accept();
                    Server.addClient(s);
                    ClientHandler clh = new ClientHandler(s);
                    clh.start();
                } catch (Exception e) {
                    s.close();
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Le serveur n'a pas pu se lancer, il faut réessayer");
            e.printStackTrace();
        }
    }
}
