import java.util.Scanner;
public class Zad_05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date data1 = null;
        Date data2 = null;

        try {
            System.out.print("Podaj pierwszą datę w formacie dd.MM.yyyy: ");
            String input1 = scanner.nextLine();
            data1 = sdf.parse(input1);

            System.out.print("Podaj drugą datę w formacie dd.MM.yyyy: ");
            String input2 = scanner.nextLine();
            data2 = sdf.parse(input2);
        } catch (Exception e) {
            System.out.println("Błędny format daty. Poprawny format to dd.MM.yyyy.");
            return;
        }

        long roznicaDni = obliczRozniceDni(data1, data2);
        System.out.println("Różnica dni między datami: " + roznicaDni + " dni.");
    }

    public static long obliczRozniceDni(Date data1, Date data2) {
        long roznicaCzasu = data2.getTime() - data1.getTime();
        return Math.abs(roznicaCzasu / (1000 * 60 * 60 * 24));
    }
}

