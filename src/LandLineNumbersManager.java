import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LandLineNumbersManager {
    private final ArrayList<Integer> landLineNumbers;

    public ArrayList<Integer> getLandLineNumbers() {
        return landLineNumbers;
    }

    public LandLineNumbersManager() throws IOException {
        landLineNumbers = new ArrayList<>();
        String numbersSourceFile = "src/Resources/NumeryKierunkowe.txt";
        List<String> fileLines = Files.readAllLines(Paths.get(numbersSourceFile));
        for(String eachLine : fileLines){
            if(eachLine.isEmpty()) continue;
            Integer landNo = Integer.parseInt(eachLine.replaceAll("[^0-9]",""));
            landLineNumbers.add(landNo);
        }
    }
}
