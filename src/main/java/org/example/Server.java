package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable{

    private ArrayList<ConnectionHandler> connections;
    private ServerSocket server;
    private boolean done;
    private ExecutorService pool;

    public Server() {
        connections = new ArrayList<>();
        done = false;
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(7777);
            pool = Executors.newCachedThreadPool();
            while (!done) {
                Socket client = server.accept();
                ConnectionHandler handler = new ConnectionHandler(client);
                connections.add(handler);
                pool.execute(handler);
            }
        } catch (IOException e) {
            shutDown();
        }
    }

    public void broadcast(String message) {
        for (ConnectionHandler ch : connections) {
            if (ch != null) {
                ch.sendMessage(message);
            }
        }
    }

    public void shutDown() {
        done = true;
        pool.shutdown();
        if (!server.isClosed()) {
            try {
                server.close();
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (ConnectionHandler ch : connections) {
                ch.shutdown();
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
                System.out.println(nickname + " dołączył do serwera");
                broadcast(nickname + " dołączył do czatu");
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
                            out.println("Nie ma nicku");
                        }
                    }
                    else if (message.startsWith(("/quit"))){
                        broadcast(nickname + " uzytkownik opuscil chat");
                        shutdown();
                    }else {
                        broadcast(nickname + ": " + message);
                    }
                }
            } catch (IOException e) {
                shutdown();
            }
        }

        public void sendMessage(String message) {
            out.println(message);
        }

        public void shutdown() {
            try {
                in.close();
                out.close();

                if (!client.isClosed()) {
                    client.close();
                }
            } catch (IOException e) {

            }

        }
    }
}
