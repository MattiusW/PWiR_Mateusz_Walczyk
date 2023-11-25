import java.util.Scanner;
public class Zad_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj datę w formacie dzień, miesiąc, rok:");
        int dzien = scanner.nextInt();
        int miesiac = scanner.nextInt();
        int rok = scanner.nextInt();

        if (sprawdzDate(dzien, miesiac, rok)) {
            String nazwaMiesiaca = nazwaMiesiaca(miesiac);
            System.out.println("Data: " + dzien + " " + nazwaMiesiaca + " " + rok);
        } else {
            System.out.println("Podano nieprawidłową datę.");
        }
    }

    public static boolean sprawdzDate(int dzien, int miesiac, int rok) {
        if (miesiac < 1 || miesiac > 12) {
            return false;
        }

        int dniWMiesiacach[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (czyRokPrzestepny(rok)) {
            dniWMiesiacach[2] = 29;
        }

        return dzien >= 1 && dzien <= dniWMiesiacach[miesiac];
    }

    public static boolean czyRokPrzestepny(int rok) {
        return (rok % 400 == 0) || ((rok % 4 == 0) && (rok % 100 != 0));
    }

    public static String nazwaMiesiaca(int miesiac) {
        String[] nazwyMiesiecy = {"", "stycznia", "lutego", "marca", "kwietnia", "maja", "czerwca", "lipca", "sierpnia", "września", "października", "listopada", "grudnia"};
        if (miesiac >= 1 && miesiac <= 12) {
            return nazwyMiesiecy[miesiac];
        } else {
            return "Błąd";
        }
    }
}

