import java.util.LinkedHashMap;

import org.apache.commons.math3.util.Precision;

public abstract class Umowa {
    public static final String PRZYCHOD_BRUTTO = "Podstawa wymiaru składek";
    public static final String SKLADKA_EMYRYTALNA = "Składka na ubezpieczenie emerytalne";
    public static final String SKLADKA_RENTOWA = "Składka na ubezpieczenie rentowe";
    public static final String SKLADKA_CHOROBOWA = "Składka na ubezpieczenie chorobowe";
    public static final String PODSTAWA_WYMIARU_SKLADKI_ZDROWOTNE = "Podstawa wymiaru składki na ubezpieczenie zdrowotne";
    public static final String SKLADKA_NA_UBEZPIECZENIE_ZDROW9 = "Składka na ubezpieczenie zdrowotne 9%";
    public static final String SKLADKA_NA_UBEZPIECZENIE_ZDROW7 = "Składka na ubezpieczenie zdrowotne 7,75%";
    public static final String KOSZTY_UZYSK_PRZYCH = "Koszty uzyskania przychodu w stałej kwocie";
    public static final String PODSTAWA_OPODATKOWANIA = "Podstawa opodatkowania";
    public static final String ZALICZKA_DOCH = "Zaliczka na podatek dochodowy 18%";
    public static final String KWOTA_WOLNA = "Kwota wolna od podatku";
    public static final String PODATEK_POTR = "Podatek potrącony";
    public static final String ZALICZKA_DO_US = "Zaliczka do urzędu skarbowego";
    public static final String KWOTA_NETTO = "Pracownik otrzyma wynagrodzenie netto w wysokości";

    private static final double EMERYTALNA_PROC = 9.76;
    public static final double RENTOWA_PROC = 1.5;
    public static final double CHOROBOWA_PROC = 2.45;
    public static final double ZDROWOTNA_9_0_PROC = 9;
    public static final double ZDROWOTNA_7_75_PROC = 7.75;
    public static final double ZALICZKA_PODATEK_DOCHODOWY_PROC = 18;


    protected double przychodBrutto;
    protected double skladkaEmerytalna;
    private double skladkaRentowa;
    protected double skladkaChorobowa;
    protected double podstawaWymiaruSkaldkiUbezZdrowotne;
    protected double skladkaZdrowotna9;
    protected double skladkaZdrowotna775;
    protected double kosztyUzyskaniaPrzychodu;
    protected double podstawaOpodatkownia;
    protected double zaliczkaNaPodatekDochodowy;
    protected double kwotaWolnaOdPodatku;
    protected double podatekPotracony;
    protected double zaliczkeDoUrzeduSkarb;
    protected double kwotaNettoPracownika;

    private final LinkedHashMap<String, Double> opisIwartosciWyliczone = new LinkedHashMap<>();

    public Umowa(double przychodBrutto) {
        this.przychodBrutto = przychodBrutto;
    }

    public LinkedHashMap<String, Double> getOpisIwartosciWyliczone() {
        return opisIwartosciWyliczone;
    }

    public void oblicz() {

        obliczSkladkeEmerytalna();
        obliczSkladkeRentowa();
        obliczSkladkeChorobowa();
        obliczPodstawaWymiaruSkaldkiUbezZdrowotne();
        obliczSkladkeZdrowotna9();
        obliczSkladkeZdrowotna775();
        obliczKosztyUzyskaniaPrzychodu();
        obliczPodstaweOpodatkownia();
        obliczZaliczkeNaPodatekDochodowy();
        obliczKwoteWolnaOdPodatku();
        obliczPodatekPotracony();
        obliczZaliczkeDoUrzeduSkarb();
        obliczKwoteNettoPracownika();

        opisIwartosciWyliczone.put(PRZYCHOD_BRUTTO, przychodBrutto);
        opisIwartosciWyliczone.put(SKLADKA_EMYRYTALNA, skladkaEmerytalna);
        opisIwartosciWyliczone.put(SKLADKA_RENTOWA, skladkaRentowa);
        opisIwartosciWyliczone.put(SKLADKA_CHOROBOWA, skladkaChorobowa);
        opisIwartosciWyliczone.put(PODSTAWA_WYMIARU_SKLADKI_ZDROWOTNE, podstawaWymiaruSkaldkiUbezZdrowotne);
        opisIwartosciWyliczone.put(SKLADKA_NA_UBEZPIECZENIE_ZDROW9, skladkaZdrowotna9);
        opisIwartosciWyliczone.put(SKLADKA_NA_UBEZPIECZENIE_ZDROW7, skladkaZdrowotna775);
        opisIwartosciWyliczone.put(KOSZTY_UZYSK_PRZYCH, kosztyUzyskaniaPrzychodu);
        opisIwartosciWyliczone.put(PODSTAWA_OPODATKOWANIA, podstawaOpodatkownia);
        opisIwartosciWyliczone.put(ZALICZKA_DOCH, zaliczkaNaPodatekDochodowy);
        opisIwartosciWyliczone.put(KWOTA_WOLNA, kwotaWolnaOdPodatku);
        opisIwartosciWyliczone.put(PODATEK_POTR, podatekPotracony);
        opisIwartosciWyliczone.put(ZALICZKA_DO_US, zaliczkeDoUrzeduSkarb);
        opisIwartosciWyliczone.put(KWOTA_NETTO, kwotaNettoPracownika);
    }

    protected void obliczSkladkeEmerytalna() {
        this.skladkaEmerytalna = Precision.round(((EMERYTALNA_PROC / 100) * przychodBrutto), 2);

    }

    protected void obliczSkladkeRentowa() {
        this.skladkaRentowa = Precision.round(((RENTOWA_PROC / 100) * przychodBrutto), 2);
    }

    protected void obliczSkladkeChorobowa() {
        this.skladkaChorobowa = Precision.round(((CHOROBOWA_PROC / 100) * przychodBrutto), 2);
    }

    protected void obliczPodstawaWymiaruSkaldkiUbezZdrowotne() {
        this.podstawaWymiaruSkaldkiUbezZdrowotne = przychodBrutto - (skladkaEmerytalna + skladkaRentowa + skladkaChorobowa);
    }

    protected void obliczSkladkeZdrowotna9() {
        this.skladkaZdrowotna9 = Precision.round(((ZDROWOTNA_9_0_PROC / 100) * podstawaWymiaruSkaldkiUbezZdrowotne), 2);
    }

    protected void obliczSkladkeZdrowotna775() {
        this.skladkaZdrowotna775 = Precision.round(((ZDROWOTNA_7_75_PROC / 100) * podstawaWymiaruSkaldkiUbezZdrowotne), 2);
    }

    abstract void obliczKosztyUzyskaniaPrzychodu();

    protected void obliczPodstaweOpodatkownia() {
        this.podstawaOpodatkownia = podstawaWymiaruSkaldkiUbezZdrowotne - kosztyUzyskaniaPrzychodu;
    }

    protected void obliczZaliczkeNaPodatekDochodowy() {
        this.zaliczkaNaPodatekDochodowy = Precision.round(((ZALICZKA_PODATEK_DOCHODOWY_PROC / 100) * podstawaOpodatkownia), 2);
    }

    abstract void obliczKwoteWolnaOdPodatku();

    protected void obliczPodatekPotracony() {
        this.podatekPotracony = zaliczkaNaPodatekDochodowy - kwotaWolnaOdPodatku;
    }

    protected void obliczZaliczkeDoUrzeduSkarb() {
        this.zaliczkeDoUrzeduSkarb = Precision.round((podatekPotracony - skladkaZdrowotna775), 0);
    }

    protected void obliczKwoteNettoPracownika() {
        this.kwotaNettoPracownika = przychodBrutto - (skladkaEmerytalna + skladkaRentowa + skladkaChorobowa + skladkaZdrowotna9 + zaliczkeDoUrzeduSkarb);
    }
}
