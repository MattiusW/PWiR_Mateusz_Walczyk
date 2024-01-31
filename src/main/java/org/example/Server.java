package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable{

    private ArrayList<ConnectionHandler> connections;

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(9999);
            Socket client = server.accept();
            ConnectionHandler handler = new ConnectionHandler(client);
            connections.add(handler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void broadcast(String message) {
        for (ConnectionHandler ch : connections) {
            if (ch != null) {
                ch.sendMessage(message);
            }
        }
    }

    class ConnectionHandler implements Runnable {

        private Socket client;
        private BufferedReader in;
        private PrintWriter out;
        private String nickname;

        public ConnectionHandler(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(client.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out.println("Podaj swoj nick: ");
                nickname = in.readLine();
                System.out.println(nickname + " polaczono");
                broadcast(nickname + " doloaczyl do czatu");
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.startsWith(("/nick "))) {
                        String[] messageSplit = message.split("", 2);
                        if (messageSplit.length == 2) {
                            broadcast(nickname + " zmieniono nick na" + messageSplit[1]);
                            System.out.println(nickname + " zmieniono nick na " + messageSplit[1]);
                            nickname = messageSplit[1];
                            out.println("Udalo sie zmienic nick na to " + nickname);
                        }else {
                            out.println("");
                        }
                    }
                    else if (message.startsWith(("/quit"))){

                    }else {
                        broadcast(nickname + ": " + message);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void sendMessage(String message) {
            out.println(message);
        }
    }

}
