package InputData;

import Umowa.TypUmowy;

import java.util.*;

public class InputTerminalImpl implements Input {
    @Override
    public Map<Integer, Double> getDataFromClient() {

        Map<Integer, Double> clientInputData = new HashMap<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj kod umowy (wybierz od 1 do " + TypUmowy.values().length + ")");
        for (TypUmowy value : TypUmowy.values()) {
            System.out.println(value);
        }

        int kodUmowy = scanner.nextInt();
        scanner.nextLine();

        if(!TypUmowy.isTypUmowyExists(kodUmowy))  //nie wiem czy to tu powinno być
            throw new InputMismatchException();

        System.out.println("Podaj kwotę brutto[PLN]: ");
        double kwotaBrutto = scanner.nextDouble();
        scanner.close();

        clientInputData.put(kodUmowy, kwotaBrutto);

        return clientInputData;
    }
}
