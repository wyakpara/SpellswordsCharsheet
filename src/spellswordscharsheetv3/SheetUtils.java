/*
 * This is a helper class for the spellswords character sheet
 */
package spellswordscharactersheet_v3;

/**
 *
 * @author Aric Hudson
 */
public class SheetUtils {
    
    protected static String getMod(int base) {
        int mod = base;
        mod -= 10;
        mod /= 2;
        return ""+mod;
    }
    
    protected static int getSave(String progression, int level) {
        double save = 0;
        if(progression.equals("l"))
        {
            save = level/3;
        }
        else if(progression.equals("m"))
        {
            save = level*0.4 + 1.5;
        }
        else if(progression.equals("h"))
        {
            save = level/2 + 2;
        }
        return (int) save;
    }
    
    /*
        Utility Functions
    */
    public static int parseInt(String num) {
        try{
            return Integer.parseInt(num);
        } catch(NumberFormatException n) {
            return 0;
        }
    }
    
    public static double parseDouble(String num) {
        try{
            return Double.parseDouble(num);
        } catch(NumberFormatException n) {
            return 0;
        }
    }
    
    public static int calcProf(int prof, int tier) {
        double temp = prof/3;
        if(tier == 1) return (int) temp;
        if(tier == 2) return (int) (2*temp);
        if(tier == 3) return prof;
        else return 0;
    }
}
