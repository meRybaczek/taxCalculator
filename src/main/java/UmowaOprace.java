public class UmowaOprace extends Umowa {

    public static final double KOSZTY_UZYSKANIA_PRZYCHODU_PLN = 111.25;
    public static final double KWOTA_WOLNA_OD_PODATKU_PLN = 46.33;

    public UmowaOprace(double przychodBrutto) {
        super(przychodBrutto);
    }

    @Override
    void obliczKosztyUzyskaniaPrzychodu() {
        kosztyUzyskaniaPrzychodu = KOSZTY_UZYSKANIA_PRZYCHODU_PLN;
    }

    @Override
    void obliczKwoteWolnaOdPodatku() {
        kwotaWolnaOdPodatku = KWOTA_WOLNA_OD_PODATKU_PLN;
    }
}
