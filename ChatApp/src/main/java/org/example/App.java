package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Server server = new Server();
        Thread serverThread = new Thread(server);
        serverThread.start();
    }
}
