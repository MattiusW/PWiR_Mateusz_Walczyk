import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        if (args.length < 4) {
            System.out.println("Użycie: java ArithmeticClient <numer1> <numer2> <operacja> <typ>");
            return;
        }

        String hostName = "localhost";
        int portNumber = 7777;

        try (Socket socket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String message = String.join(" ", args);
            out.println(message);

            System.out.println("Oczekiwanie na odpowiedz...");
            String response = in.readLine();
            System.out.println("Odpowiedz serwera: " + response);
        } catch (UnknownHostException e) {
            System.err.println("Nie znaleziono hosta: " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Nie mozna uzyskać I/O dla połączenia z " + hostName);
            System.exit(1);
        }
    }
}
