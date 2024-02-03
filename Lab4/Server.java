import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7777);
        System.out.println("Serwer uruchomiony. Oczekiwanie na połączenia...");

        try (Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            System.out.println("Klient połączony.");

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Odebrano: " + inputLine);
                String[] tokens = inputLine.split(" ");
                try {
                    double num1 = Double.parseDouble(tokens[0]);
                    double num2 = Double.parseDouble(tokens[1]);
                    String operation = tokens[2];
                    double result = 0;

                    switch (operation) {
                        case "dodaj":
                            result = num1 + num2;
                            break;
                        case "odejmij":
                            result = num1 - num2;
                            break;
                        case "mnoz":
                            result = num1 * num2;
                            break;
                        case "dziel":
                            if (num2 != 0) {
                                result = num1 / num2;
                            } else {
                                out.println("Błąd: Dzielenie przez zero!");
                                continue;
                            }
                            break;
                        default:
                            out.println("Nieznana operacja");
                            continue;
                    }

                    if (tokens[3].equals("calkowite") && result == (int) result) {
                        out.println((int) result);
                    } else {
                        out.println(result);
                    }
                } catch (NumberFormatException e) {
                    out.println("Blad: Niepoprawne liczby");
                } catch (ArrayIndexOutOfBoundsException e) {
                    out.println("Blad: Niekompletne dane");
                }
            }
        } catch (IOException e) {
            System.out.println("Wyjatek serwera: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
