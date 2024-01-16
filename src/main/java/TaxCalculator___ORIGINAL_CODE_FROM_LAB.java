import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class TaxCalculator___ORIGINAL_CODE_FROM_LAB {

    public static double podstawaWynagrodzenia;
    public static char umowa = ' ';

    public static final double EMERYTALNA_PROC = 9.76;
    public static double stawkaEmerytalna;


    public static final double RENTOWA_PROC = 1.5;
    public static double stawkaRentowa;

    public static final double CHOROBOWA_PROC = 2.45;
    public static double stawkaChorobowa;


    // składki na ubezpieczenia zdrowotne
    public static double kosztyUzyskania = 111.25;

    public static final double ZDROWOTNA_9_PROC = 9;
    public static double stawkaZdrowotna9;


    public static final double ZDROWOTNA_7_PROC = 7.75;
    public static double stawkaZdrowotna7;

    public static final double PODATEK_DOCHDOWY_PROC = 18;
    public static double wyliczonyPodatekDochodowy;

    public static final double KWOTA_ZMNIEJSZJACA_UMOWA_O_PRACE = 46.33;
    public static final double KWOTA_ZMNIEJSZJACA_UMOWA_ZLECENIE = 0;

    public static double zaliczkaUsDwieLiczbyPoPrzecinku;
    public static double zaliczkaUsLiczbaCalkowita;

    public static void main(String[] args) {
        try (
                InputStreamReader inputStreamReader = new InputStreamReader(System.in);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                ){

            System.out.print("Podaj kwotę dochodu: ");
            podstawaWynagrodzenia = Double.parseDouble(bufferedReader.readLine());

            System.out.print("Typ umowy: (P)raca, (Z)lecenie: ");
            umowa = bufferedReader.readLine().charAt(0);

        } catch (Exception ex) {
            System.out.println("Błędna kwota");
            System.err.println(ex);
            return;
        }

        DecimalFormat formatDwieLiczbyPoPrzecinku = new DecimalFormat("#.00");
        DecimalFormat formatLiczbaCalkowita = new DecimalFormat("#");

        if (umowa == 'P') {
            System.out.println("UMOWA O PRACĘ");
            System.out.println("Podstawa wymiaru składek " + podstawaWynagrodzenia);

            double oPodstawa = obliczonaPodstawa(podstawaWynagrodzenia);

            System.out.println("Składka na ubezpieczenie emerytalne "
                    + formatDwieLiczbyPoPrzecinku.format(stawkaEmerytalna));
            System.out.println("Składka na ubezpieczenie rentowe    "
                    + formatDwieLiczbyPoPrzecinku.format(stawkaRentowa));
            System.out.println("Składka na ubezpieczenie chorobowe  "
                    + formatDwieLiczbyPoPrzecinku.format(stawkaChorobowa));
            System.out.println("Podstawa wymiaru składki na ubezpieczenie zdrowotne: "
                    + oPodstawa);
            obliczUbezpieczenia(oPodstawa);
            System.out.println("Składka na ubezpieczenie zdrowotne: 9% = "
                    + formatDwieLiczbyPoPrzecinku.format(stawkaZdrowotna9) + " 7,75% = " + formatDwieLiczbyPoPrzecinku.format(stawkaZdrowotna7));
            System.out.println("Koszty uzyskania przychodu w stałej wysokości "
                    + kosztyUzyskania);

            double podstawaOpodat = oPodstawa - kosztyUzyskania;
            double podstawaOpodat0 = Double
                    .parseDouble(formatLiczbaCalkowita.format(podstawaOpodat));

            System.out.println("Podstawa opodatkowania " + podstawaOpodat
                    + " zaokrąglona " + formatLiczbaCalkowita.format(podstawaOpodat0));
            obliczPodatek(podstawaOpodat0);
            System.out.println("Zaliczka na podatek dochodowy 18 % = "
                    + wyliczonyPodatekDochodowy);
            System.out.println("Kwota wolna od podatku = " + KWOTA_ZMNIEJSZJACA_UMOWA_O_PRACE);

            double podatekPotracony = wyliczonyPodatekDochodowy - KWOTA_ZMNIEJSZJACA_UMOWA_O_PRACE;

            System.out.println("Podatek potrącony = "
                    + formatDwieLiczbyPoPrzecinku.format(podatekPotracony));
            obliczZaliczkeDlaUmowyOPrace();
            zaliczkaUsLiczbaCalkowita = Double.parseDouble(formatLiczbaCalkowita.format(zaliczkaUsDwieLiczbyPoPrzecinku));
            System.out.println("Zaliczka do urzędu skarbowego = "
                    + formatDwieLiczbyPoPrzecinku.format(zaliczkaUsDwieLiczbyPoPrzecinku) + " po zaokrągleniu = "
                    + formatLiczbaCalkowita.format(zaliczkaUsLiczbaCalkowita));

            double wynagrodzenie = podstawaWynagrodzenia
                    - ((stawkaEmerytalna + stawkaRentowa + stawkaChorobowa) + stawkaZdrowotna9 + zaliczkaUsLiczbaCalkowita);

            System.out.println();
            System.out
                    .println("Pracownik otrzyma wynagrodzenie netto w wysokości = "
                            + formatDwieLiczbyPoPrzecinku.format(wynagrodzenie));
        } else if (umowa == 'Z') {
            System.out.println("UMOWA-ZLECENIE");
            System.out.println("Podstawa wymiaru składek " + podstawaWynagrodzenia);
            double oPodstawa = obliczonaPodstawa(podstawaWynagrodzenia);
            System.out.println("Składka na ubezpieczenie emerytalne "
                    + formatDwieLiczbyPoPrzecinku.format(stawkaEmerytalna));
            System.out.println("Składka na ubezpieczenie rentowe    "
                    + formatDwieLiczbyPoPrzecinku.format(stawkaRentowa));
            System.out.println("Składka na ubezpieczenie chorobowe  "
                    + formatDwieLiczbyPoPrzecinku.format(stawkaChorobowa));
            System.out
                    .println("Podstawa wymiaru składki na ubezpieczenie zdrowotne: "
                            + oPodstawa);
            obliczUbezpieczenia(oPodstawa);
            System.out.println("Składka na ubezpieczenie zdrowotne: 9% = "
                    + formatDwieLiczbyPoPrzecinku.format(stawkaZdrowotna9) + " 7,75% = " + formatDwieLiczbyPoPrzecinku.format(stawkaZdrowotna7));

            kosztyUzyskania = (oPodstawa * 20) / 100;
            System.out.println("Koszty uzyskania przychodu (stałe) "
                    + kosztyUzyskania);
            double podstawaOpodat = oPodstawa - kosztyUzyskania;
            double podstawaOpodat0 = Double.parseDouble(formatLiczbaCalkowita.format(podstawaOpodat));
            System.out.println("Podstawa opodatkowania " + podstawaOpodat
                    + " zaokrąglona " + formatLiczbaCalkowita.format(podstawaOpodat0));
            obliczPodatek(podstawaOpodat0);
            System.out.println("Zaliczka na podatek dochodowy 18 % = "
                    + wyliczonyPodatekDochodowy);
            double podatekPotracony = wyliczonyPodatekDochodowy;
            System.out.println("Podatek potrącony = "
                    + formatDwieLiczbyPoPrzecinku.format(podatekPotracony));
            obliczZaliczkeDlaUmowyZlecenie();
            zaliczkaUsLiczbaCalkowita = Double.parseDouble(formatLiczbaCalkowita.format(zaliczkaUsDwieLiczbyPoPrzecinku));
            System.out.println("Zaliczka do urzędu skarbowego = "
                    + formatDwieLiczbyPoPrzecinku.format(zaliczkaUsDwieLiczbyPoPrzecinku) + " po zaokrągleniu = "
                    + formatLiczbaCalkowita.format(zaliczkaUsLiczbaCalkowita));
            double wynagrodzenie = podstawaWynagrodzenia
                    - ((stawkaEmerytalna + stawkaRentowa + stawkaChorobowa) + ZDROWOTNA_9_PROC + zaliczkaUsLiczbaCalkowita);
            System.out.println();
            System.out
                    .println("Pracownik otrzyma wynagrodzenie netto w wysokości = "
                            + formatDwieLiczbyPoPrzecinku.format(wynagrodzenie));
        } else {
            System.out.println("Nieznany typ umowy!");
        }
    }

    public static void obliczZaliczkeDlaUmowyOPrace() {
        zaliczkaUsDwieLiczbyPoPrzecinku = wyliczonyPodatekDochodowy - stawkaZdrowotna7 - KWOTA_ZMNIEJSZJACA_UMOWA_O_PRACE;
    }

    public static void obliczZaliczkeDlaUmowyZlecenie() {
        zaliczkaUsDwieLiczbyPoPrzecinku = wyliczonyPodatekDochodowy - stawkaZdrowotna7 - KWOTA_ZMNIEJSZJACA_UMOWA_ZLECENIE;
    }

    public static void obliczPodatek(double podstawa) {
        wyliczonyPodatekDochodowy = (podstawa * PODATEK_DOCHDOWY_PROC) / 100;
    }

    public static double obliczonaPodstawa(double podstawa) {
        stawkaEmerytalna = (podstawa * EMERYTALNA_PROC) / 100;
        stawkaRentowa = (podstawa * RENTOWA_PROC) / 100;
        stawkaChorobowa = (podstawa * CHOROBOWA_PROC) / 100;
        return (podstawa - stawkaEmerytalna - stawkaRentowa - stawkaChorobowa);
    }

    public static void obliczUbezpieczenia(double podstawa) {
        stawkaZdrowotna9 = (podstawa * ZDROWOTNA_9_PROC) / 100;
        stawkaZdrowotna7 = (podstawa * ZDROWOTNA_7_PROC) / 100;
    }
}