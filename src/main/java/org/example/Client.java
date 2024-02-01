package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean done;

    @Override
    public void run() {
        try {
            client = new Socket("127.0.0.1", 7777);
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            InputHandler inputHandler = new InputHandler();
            Thread t = new Thread(inputHandler);
            t.start();

            String inMessage;
            while ((inMessage = in.readLine()) != null) {
                System.out.println(inMessage);
            }

        } catch (IOException e){
            shutdown();
        }
    }

    class InputHandler implements Runnable {

        @Override
        public void run() {
            try {
                BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
                while (!done) {
                    String messega = inReader.readLine();
                    if (messega.equals("/quit")) {
                        inReader.close();
                        shutdown();
                    } else {
                        out.println(messega);
                    }
                }
            } catch (IOException e){
                shutdown();
            }
        }

    }

    private void shutdown() {
        done = true;
        try {
            in.close();
            out.close();
            if (!client.isClosed()) {
                client.close();
            }
        }catch (IOException e) {

        }
    }

    public static void main(String[] args) {
        Client clinet = new Client();
        clinet.run();
    }
}
