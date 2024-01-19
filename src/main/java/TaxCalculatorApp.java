import InputData.Input;
import InputData.InputTerminalImpl;
import OutputData.Output;
import OutputData.OutputTerminalImpl;
import Umowa.Umowa;
import Umowa.UmowaFactory;

import java.util.LinkedHashMap;
import java.util.Map;

public class TaxCalculatorApp {

    public static void main(String[] args) {

        Input input = new InputTerminalImpl();
        Map<Integer, Double> dataFromClient = input.getDataFromClient();

        Umowa umowa = UmowaFactory.createUmowa(dataFromClient);
        umowa.oblicz();
        LinkedHashMap<String, Double> opisIwartosciWyliczone = umowa.getOpisIwartosciWyliczone();

        Output output = new OutputTerminalImpl();
        output.presentDataOutput(opisIwartosciWyliczone);
    }
}
