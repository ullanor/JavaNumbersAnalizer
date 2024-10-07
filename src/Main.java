import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int numbersSeparationCount = 100;
        if(args.length >= 1) if(args[0].equals("clear")) {
            StaticManager.hideErrorNumbers = true;
            System.out.println("Will hide error numbers!");
        }
        if(args.length >= 2){
            numbersSeparationCount = Integer.parseInt(args[1]);
            System.out.println("Will separate numbers each: "+numbersSeparationCount);
        }
        //--

        List<String> fileLines;
        boolean hasFile = false;
        Scanner scanner = new Scanner(System.in);
        String filePath = "";
        //--
        System.out.println("Welcome to numberChecker by Pablo :)");
        while (!hasFile) {
            System.out.print("Paste path to numbers file: ");
            filePath = scanner.nextLine();
            //filePath = "c:\\fileTestJAVA\\testNumbersFile.txt";
            if(filePath.equals("exit")) return;
            if(new File(filePath).exists()) hasFile = true;
            else System.out.println(filePath + " doesn't exists!");
        }
        //--
        fileLines = Files.readAllLines(Paths.get(filePath));
        System.out.println("File was loaded ("+fileLines.size()+" - lines)");
        System.out.println("-------------------------");

        int validCounter = 0;
        int lineCounter = 0;
        int lastSepartedLine = 0;
        List<Integer> errorLines = new ArrayList<>();
        StringBuilder outputForNewFile = new StringBuilder();
        for(String eachLine : fileLines){
            lineCounter++;
            if(eachLine.isEmpty()) continue; //prevent empties!
            NumberChecker numberChecker = new NumberChecker(eachLine);
            String formattedLine = numberChecker.FormatNumber();
            if(!formattedLine.isEmpty()) {
                System.out.println(formattedLine);
                validCounter++;
                outputForNewFile.append(formattedLine).append("\r\n");
                if(formattedLine.startsWith(StaticManager.defError)) errorLines.add(lineCounter);
            } else errorLines.add(lineCounter);
            if(validCounter % numbersSeparationCount == 0 && lastSepartedLine != validCounter) {
                lastSepartedLine = validCounter;
                System.out.println();
                outputForNewFile.append("\r\n");
            }
        }
        outputForNewFile.append("\r\n").append("\r\n")
                .append("Total: ").append(validCounter);
        System.out.println("-------------------------");
        if(!errorLines.isEmpty()) {
            System.out.println("Error lines: "+errorLines.size());
            outputForNewFile.append(" | error lines: ").append(errorLines.size()).append(" -> {");
            for(int lineNo : errorLines){
                outputForNewFile.append(lineNo).append(", ");
            }
            outputForNewFile.append("}");
        }

        //save to new file
        String parentDir = new File(filePath).getParent();
        if(parentDir == null) parentDir = ""; else parentDir += "\\";
        Files.writeString(Path.of(parentDir + "javedNumbersOut.txt"),outputForNewFile);
        //Files.writeString(Path.of(filePath),outputForNewFile);
    }
}