public class StaticManager {
    public enum WrongNumberType{
        Length,
        Country,
        Landline
    }
    public static LandLineNumbersManager landLineNumbersManager;
    public static boolean hideErrorNumbers = false;
    public static final String defError = "!ERROR -> ";

    private static final String wrongNoLen = "Error in length!";
    private static final String wrongNoCountry = "Wrong Country!";
    private static final String wrongNoLandLine = "LandLine number!";

    public static String buildAsWrongNumber(String initNo, String finNo, WrongNumberType errType){
        if(hideErrorNumbers) return "";
        String output = defError;
        switch (errType){
            case Length -> output += wrongNoLen;
            case Country -> output += wrongNoCountry;
            case Landline -> output += wrongNoLandLine;
        }
        output += " ("+initNo+" -> " + finNo+")";
        return output;
    }
}
