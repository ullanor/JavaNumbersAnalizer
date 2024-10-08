import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LandLineNumbersManager {
    private final ArrayList<Integer> landLineNumbers;

    public ArrayList<Integer> getLandLineNumbers() {
        return landLineNumbers;
    }

    public LandLineNumbersManager() throws IOException {
        landLineNumbers = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream("Resources/NumeryKierunkowe.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));
        List<String> fileLines = new ArrayList<>();
        while(reader.ready()) {
            String line = reader.readLine();
            fileLines.add(line);
        }
        for(String eachLine : fileLines){
            if(eachLine.isEmpty()) continue;
            Integer landNo = Integer.parseInt(eachLine.replaceAll("[^0-9]",""));
            landLineNumbers.add(landNo);
        }
    }
}
