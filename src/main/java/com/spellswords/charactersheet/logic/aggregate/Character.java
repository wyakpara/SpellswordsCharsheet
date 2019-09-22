/*
 * This object describes a spellswords character
 * 
 * It should encapsulate all aspects of the character - from ability scores and 
 * beyond - and should be the gateway used by the gui.
 */
package com.spellswords.charactersheet.logic.aggregate;

import java.util.ArrayList;

/**
 *
 * @author Aric Hudson
 */
public class Character {
 
    /*
    * Constants
    */
    
    private final int NUMCLASSES = 6;
    
    public static final String[] baseSkills = 
    {"Acrobatics",
    "Appraise",
    "Art: Author",
    "Art: Draw/Paint",
    "Art: Musician",
    "Athletics",
    "Balance",
    "Bluff",
    "Concentrate",
    "Cook",
    "Cr: Alchemy",
    "Cr: Blacksmith",
    "Cr: Carpentry",
    "Cr: Device",
    "Cr: Masonry",
    "Cr: Tan/Weave",
    "Diplomacy",
    "Disguise",
    "Escape Artist",
    "Handle Animal",
    "Heal",
    "Intimidate",
    "Know: Anatomy",
    "Know: Arcana",
    "Know: Civic",
    "Know: Engineer",
    "Know: Geology",
    "Know: Hist/Geog",
    "Know: Logic",
    "Know: Nature",
    "Know: Phil/Rel",
    "Martial Lore",
    "Operator",
    "Perception",
    "Psicraft",
    "Search",
    "Sense Motive",
    "Spellcraft",
    "Stealth",
    "Survival",
    "Use Mystic Device",
    "Use Rope"};
    
    /*
    * Basic Vital information
    */
    String name, player, race, size, align, faith, bio, notes;
    
    /*
    * Ability Score arrays
    */
    AbilityScore[] abilities; // Ability scores
    
    /*
    * Leveling information
    */
    int level;
    ArrayList<CharClass> classes;
    
    /*
    * Armor information
    */
    ArmorClass ac;
    
    /*
    * Proficiency Information
    */
    int proficiency;
    
    /*
    * Armor Information
    */
    Armors armors;
    
    /*
    * Weapon information
    */
    Weapons weapons;
    
    /*
    * Inventory
    */
    Inventory inven;
    
    /*
    * HP and Stamina information
    */
    Health health;
    
    /*
    * Saves and Resistances information
    */
    Save fort, ref, will;
    
    String saveModifiers;
    
    int SR, PR, FR, CR, AR, ER, PosR, NegR;
    
    /*
    * Skills Information
    */
    Skills skills;
    
    /*
    * Actions information
    */
    Actions actions;
    
    /*
    * Speeds information
    */
    Speeds speeds;
    
    /*
    * Initiative information
    */
    Initiative initiative;
    
    /*
    * List of Feats
    */
    Feats feats;
    
    public Character() {
        // Blank constructor with everything initilized to zero/null
    }
    
    public void readJson() {
//        JsonWriter jw = new JsonWriter();
    }
    
//    public void wtf() {//throws UnsupportedEncodingException {
//        try{
//            File newFile = new File("./text.txt");
//            if(!newFile.exists())
//            {
//                newFile.createNewFile();
//            }
//                
//            FileOutputStream out = new FileOutputStream(newFile);
//            out.flush();
////            JsonWriter writer = new JsonWriter(new OutputStreamWriter(System.out, "UTF-8"));
//            JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
//            writer.beginArray();
//            // Block for abilities array
//            {
//                writer.beginObject();
//                writer.name("STR").value(""+abilities[0]);
//                writer.name("DEX").value(""+abilities[1]);
//                writer.name("CON").value(""+abilities[2]);
//                writer.name("INT").value(""+abilities[3]);
//                writer.name("WIS").value(""+abilities[4]);
//                writer.name("CHA").value(""+abilities[5]);
//                writer.endObject();
//            }
//            writer.endArray();
//            writer.close();
//            out.close();
////        } catch (UnsupportedEncodingException | IOException f) {}
//        } catch (IOException f) {System.err.println("IOException!!");}
//    }
    
    /**
     * Updates abilities
     * 
     * @param newAbils is an array of new abilities
     */
    public void updateAbils(int[] newAbils) {
        for(int i = 0; i < 6; i++) {
            abilities[i].setBaseScore(newAbils[i]);
            abilities[i].update();
        }
    }
    
    /**
     * Updates ability bonuses
     * 
     * @param newBonus is an array of new abilities
     */
    public void updateAbilsBonus(int[] newBonus) {
        for(int i = 0; i < 6; i++) {
            abilities[i].setBonus(newBonus[i]);
            abilities[i].update();
        }
    }
    
    /**
     * Updates ability temps
     * 
     * @param newTemp is an array of new abilities
     */
    public void updateAbilsTemp(int[] newTemp) {
        for(int i = 0; i < 6; i++) {
            abilities[i].setTemp(newTemp[i]);
            abilities[i].update();
        }
    }
    
    /**
     * Updates ability other
     * 
     * @param newOther is an array of new others
     */
    public void updateAbilsOther(int[] newOther) {
        for(int i = 0; i < 6; i++) {
            abilities[i].setOther(newOther[i]);
            abilities[i].update();
        }
    }
    
    public void updateAbil(String abil, String kind, int newVal) {
        int whichone;
        String a = abil.toUpperCase();
        if(a.equals("STR")) whichone = 0;
        else if(a.equals("DEX")) whichone = 1;
        else if(a.equals("CON")) whichone = 2;
        else if(a.equals("INT")) whichone = 3;
        else if(a.equals("WIS")) whichone = 4;
        else if(a.equals("CHA")) whichone = 5;
        else return;
        
        String k = kind.toUpperCase();
        if(k.equals("BASE")) abilities[whichone].setBaseScore(newVal);
        else if(k.equals("BONUS")) abilities[whichone].setBonus(newVal);
        else if(k.equals("TEMP")) abilities[whichone].setTemp(newVal);
        else if(k.equals("OTHER")) abilities[whichone].setOther(newVal);
    }
    
    public int getAbil(String abil, String kind) {
        int whichone;
        String a = abil.toUpperCase();
        if(a.equals("STR")) whichone = 0;
        else if(a.equals("DEX")) whichone = 1;
        else if(a.equals("CON")) whichone = 2;
        else if(a.equals("INT")) whichone = 3;
        else if(a.equals("WIS")) whichone = 4;
        else if(a.equals("CHA")) whichone = 5;
        else return 0;
        
        String k = kind.toUpperCase();
        if(k.equals("BASE")) return abilities[whichone].getBaseScore();
        else if(k.equals("MOD") || k.equals("MODIFIER")) return abilities[whichone].getMod();
        else if(k.equals("BASE")) return abilities[whichone].getBaseScore();
        else if(k.equals("BONUS")) return abilities[whichone].getBonus();
        else if(k.equals("TEMP")) return abilities[whichone].getTemp();
        else if(k.equals("OTHER")) return abilities[whichone].getOther();
        else return abilities[whichone].getBaseScore();
    }
    
    public int getStam() {
        return health.getStam();
    }
    
    public int getCurrentStam() {
        return health.getCurrentStam();
    }
    
    public Speeds getSpeeds() {
        return speeds;
    }
}
