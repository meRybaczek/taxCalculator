package Umowa;

import org.apache.commons.math3.util.Precision;

public class UmowaZlecenie extends Umowa {

    public static final double KOSZTY_UZYSKANIA_PRZYCHODU_PROC = 20;
    public static final double KWOTA_WOLNA_OD_PODATKU_PLN = 0;

    public UmowaZlecenie(double przychodBrutto) {
        super(przychodBrutto);
    }

    @Override
    void obliczKosztyUzyskaniaPrzychodu() {
        kosztyUzyskaniaPrzychodu = Precision.round((KOSZTY_UZYSKANIA_PRZYCHODU_PROC * podstawaWymiaruSkaldkiUbezZdrowotne / 100), 2);
    }

    @Override
    void obliczKwoteWolnaOdPodatku() {
        kwotaWolnaOdPodatku = KWOTA_WOLNA_OD_PODATKU_PLN;
    }


}
