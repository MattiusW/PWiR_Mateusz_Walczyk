import java.util.Scanner;

public class Zad_04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj datę w formacie dzień.miesiąc.rok:");
        String data = scanner.nextLine();
        String[] parts = data.split("\\.");

        if (parts.length != 3) {
            System.out.println("Błędny format daty.");
            return;
        }

        int dzien = Integer.parseInt(parts[0]);
        int miesiac = Integer.parseInt(parts[1]);
        int rok = Integer.parseInt(parts[2]);

        if (sprawdzDate(dzien, miesiac, rok)) {
            int dzienRoku = obliczDzienRoku(dzien, miesiac, rok);
            System.out.println(data + " jest " + dzienRoku + " dniem roku " + rok + ".");
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

    public static int obliczDzienRoku(int dzien, int miesiac, int rok) {
        int dniWMiesiacach[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (czyRokPrzestepny(rok)) {
            dniWMiesiacach[2] = 29;
        }

        int dzienRoku = dzien;
        for (int i = 1; i < miesiac; i++) {
            dzienRoku += dniWMiesiacach[i];
        }

        return dzienRoku;
    }
}

