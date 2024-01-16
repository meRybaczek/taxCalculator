import java.util.Map;

public class OutputTerminalImpl implements Output{
    @Override
    public void presentDataOutput(Map<String, Double> outputData) {
        outputData.forEach((key, value) -> System.out.printf("%-54s%-9.2f%-9s%n",key,value,"[PLN]"));
    }
}
