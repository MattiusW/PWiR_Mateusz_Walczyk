public class Zegar {
    private int godzina;
    private int minuta;
    private int sekunda;
    private boolean format24;

    public Zegar() {
        this.format24 = true; // Domyślnie ustawiamy format 24-godzinny
    }

    public void nastaw(int godzina, int minuta, int sekunda) {
        if (godzina >= 0 && godzina < 24 && minuta >= 0 && minuta < 60 && sekunda >= 0 && sekunda < 60) {
            this.godzina = godzina;
            this.minuta = minuta;
            this.sekunda = sekunda;
        } else {
            System.out.println("Niepoprawna wartość godziny, minuty lub sekundy.");
        }
    }

    public void wypisz() {
        String formatGodziny = format24 ? "HH:mm:ss" : "hh:mm:ss a";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(formatGodziny);
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(0, 0, 0, this.godzina, this.minuta, this.sekunda);
        System.out.println(sdf.format(calendar.getTime()));
    }

    public void format(boolean format24) {
        this.format24 = format24;
    }

    public void tykniecie() {
        sekunda++;
        if (sekunda >= 60) {
            sekunda = 0;
            minuta++;
            if (minuta >= 60) {
                minuta = 0;
                godzina++;
                if (godzina >= 24) {
                    godzina = 0;
                }
            }
        }
    }
}

