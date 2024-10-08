import java.util.ArrayList;

public class NumberChecker {
    private final String initNumber;

    public NumberChecker(String number){
        this.initNumber = number;
    }

    public String FormatNumber(){
        String finNumber = initNumber.replaceAll("[^0-9]","");
        int noPrefix = Integer.parseInt(finNumber.substring(0,2));
        if(finNumber.length() == 9) return checkForLandLineNo(finNumber,noPrefix);
        else if(finNumber.length() == 11) return checkNumberPrefix(finNumber,noPrefix);
        else return StaticManager.buildAsWrongNumber(initNumber,finNumber, StaticManager.WrongNumberType.Length);
    }

    //number checking and formatting
    private String checkForLandLineNo(String number, int noPrefix){
        ArrayList<Integer> landLineNumbers = StaticManager.landLineNumbersManager.getLandLineNumbers();
        if(landLineNumbers.contains(noPrefix)) return StaticManager.buildAsWrongNumber(initNumber, number, StaticManager.WrongNumberType.Landline);
        else return number;
    }

    private String checkNumberPrefix(String number, int noPrefix){
        String shortenedNo = number.substring(2);
        if(noPrefix == 48) return shortenedNo; //poland
        else return StaticManager.buildAsWrongNumber(initNumber, shortenedNo, StaticManager.WrongNumberType.Country);
    }
}
