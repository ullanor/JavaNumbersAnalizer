public class NumberChecker {
    private final String initNumber;

    public NumberChecker(String number){
        this.initNumber = number;
    }

    public String FormatNumber(){
        String finNumber = initNumber.replaceAll("[^0-9]","");
        if(finNumber.length() == 9) return finNumber;
        else if(finNumber.length() == 11) return checkNumberPrefix(finNumber);
        else return StaticManager.buildAsWrongNumber(initNumber,finNumber, StaticManager.WrongNumberType.Length);
    }

    //number checking and formatting
    private String checkNumberPrefix(String number){
        int noPrefix = Integer.parseInt(number.substring(0,2));
        String shortenedNo = number.substring(2);
        if(noPrefix == 48) return shortenedNo; //poland
        else return StaticManager.buildAsWrongNumber(initNumber, shortenedNo, StaticManager.WrongNumberType.Country);
    }
}
