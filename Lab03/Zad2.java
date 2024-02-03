import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Zad2 {
    private static final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
    private static final Random random = new Random();

    public static void main(String[] args) {
        Thread producer = new Thread(() -> {
            while (true) {
                try {
                    int generatedNumber = random.nextInt(10); // Generuje liczby od 0 do 9
                    System.out.println("Wygenerowano cyfrę: " + generatedNumber);
                    queue.put(generatedNumber); // Umieszcza wygenerowaną cyfrę w kolejce
                    if (generatedNumber == 0) { // Jeśli wygenerowano 0, pozwala na chwilę odpocząć
                        Thread.sleep(1000); // Czekaj 1 sekundę przed wygenerowaniem nowej liczby
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Wątek generujący został przerwany");
                    break;
                }
            }
        });

        Thread consumer = new Thread(() -> {
            StringBuilder currentNumber = new StringBuilder();
            while (true) {
                try {
                    Integer digit = queue.take(); // Pobiera cyfrę z kolejki
                    if (digit == 0) {
                        if (currentNumber.length() > 0) {
                            System.out.println("Utworzono liczbę: " + currentNumber.append(digit));
                            currentNumber.setLength(0); // Resetuje StringBuilder do tworzenia nowej liczby
                        }
                    } else if (!(currentNumber.length() == 0 && digit == 0)) { // Ignoruje początkowe zera
                        currentNumber.append(digit);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Wątek konsumujący został przerwany");
                    break;
                }
            }
        });

        producer.start();
        consumer.start();
    }
}
