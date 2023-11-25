import java.util.Scanner;
public class Zad_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wybierz opcję:");
        System.out.println("1. Oblicz liczbę dni w miesiącu w roku nieprzestępnym.");
        System.out.println("2. Oblicz liczbę dni w miesiącu i roku.");

        int wybor = scanner.nextInt();

        if (wybor == 1) {
            System.out.print("Podaj numer miesiąca (1-12): ");
            int miesiac = scanner.nextInt();

            int dni = liczbaDniWMiesiacu(miesiac, 0);
            System.out.println("Liczba dni w miesiącu " + miesiac + " w roku nieprzestępnym: " + dni);
        } else if (wybor == 2) {
            System.out.print("Podaj numer miesiąca (1-12): ");
            int miesiac = scanner.nextInt();
            System.out.print("Podaj rok: ");
            int rok = scanner.nextInt();

            int dni = liczbaDniWMiesiacu(miesiac, rok);
            System.out.println("Liczba dni w miesiącu " + miesiac + " w roku " + rok + ": " + dni);
        } else {
            System.out.println("Nieprawidłowy wybór.");
        }
    }

    public static int liczbaDniWMiesiacu(int miesiac, int rok) {
        int[] dniWMiesiacach = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (miesiac < 1 || miesiac > 12) {
            return -1; // Zwracamy -1 jako oznaczenie błędu
        }

        if (miesiac == 2 && (rok % 4 == 0 && (rok % 100 != 0 || rok % 400 == 0))) {
            return 29;
        } else {
            return dniWMiesiacach[miesiac];
        }
    }
}
