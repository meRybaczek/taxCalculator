import java.util.Map;


public class UmowaFactory {

    public static Umowa createUmowa(Map<Integer, Double> clientInputMap) {

        Integer kodUmowy = 0;
        Double przychodBrutto = 0.0;

        for (Map.Entry<Integer, Double> entry : clientInputMap.entrySet()) {
            kodUmowy = entry.getKey();
            przychodBrutto = entry.getValue();
        }

        if (kodUmowy == 1) {
            return new UmowaOprace(przychodBrutto);
        }
        return new UmowaZlecenie(przychodBrutto);

    }
}
