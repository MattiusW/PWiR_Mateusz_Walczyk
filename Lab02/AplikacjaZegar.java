public class AplikacjaZegar {
    public static void main(String[] args) {
        Zegar zegar = new Zegar();
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        while (true) {
            System.out.println("1. Nastaw zegar");
            System.out.println("2. Wypisz czas");
            System.out.println("3. Zmień format (12/24)");
            System.out.println("4. Tyknięcie");
            System.out.println("5. Wyjście");

            System.out.print("Wybierz opcję: ");
            int wybor = scanner.nextInt();

            switch (wybor) {
                case 1:
                    System.out.print("Podaj godzinę: ");
                    int godzina = scanner.nextInt();
                    System.out.print("Podaj minutę: ");
                    int minuta = scanner.nextInt();
                    System.out.print("Podaj sekundę: ");
                    int sekunda = scanner.nextInt();
                    zegar.nastaw(godzina, minuta, sekunda);
                    break;
                case 2:
                    zegar.wypisz();
                    break;
                case 3:
                    System.out.print("Wybierz format (1 - 24h, 2 - 12h): ");
                    int format = scanner.nextInt();
                    zegar.format(format == 1);
                    break;
                case 4:
                    zegar.tykniecie();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Niepoprawna opcja.");
                    break;
            }
        }
    }
}