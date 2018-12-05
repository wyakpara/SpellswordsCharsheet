/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spellswordscharsheetv3;

/**
 *
 * @author Didge
 */
public class Skill {
    private static int checkPen;
    private String name;
    private String persuasion;
    private int score;
    private int progLevel;
    private int base;
    private int bonus;
    private int temp;
    private int take;
    private int min;
    private int upkeep;
    private boolean checkApplies;
    
    public Skill() {
        name = "";
        persuasion = "";
        score = base = bonus = temp = take = min = progLevel = 0;
        checkApplies = false;
    }
    
    public Skill(String newName, String newPers, int newProgLevel) {
        name = newName;
        persuasion = newPers;
        progLevel = newProgLevel;
    }
    
    public static void setCheckPen(int newCheckPen) {
        checkPen = newCheckPen;
    }
    
    public static int getCheckPen() {
        return checkPen;
    }
    
    public void update() {
        score = base + bonus + temp;
        if(checkApplies) score -= checkPen;
    }
    
    public void setBase(int proficiency) {
        base = proficiency/3;
        base *= progLevel;
        update();
    }
    
    public int getScore() {
        return score;
    }
    
    public int getBase() {
        return base;
    }
    
    public void setPersuasion(String newPers) {
        persuasion = newPers;
    }
    
    public String getPersuasion() {
        return persuasion;
    }
    
    public void setProgLevel(int newProg) {
        progLevel = newProg;
        update();
    }
    
    public int getProgLevel() {
        return progLevel;
    }
    
    public void setName(String newName) {
        name = newName;
    }
    
    public String getName() {
        return name;
    }
    
    public void setBonus(int newBonus) {
        bonus = newBonus;
        update();
    }
    
    public int getBonus() {
        return bonus;
    }
    
    public void setTemp(int newTemp) {
        temp = newTemp;
        update();
    }
    
    public int getTemp() {
        return temp;
    }
    
    public void setMin(int newMin) {
        min = newMin;
    }
    
    public int getMin() {
        return min;
    }
    
    public void setTake(int newTake) {
        take = newTake;
    }
    
    public int getTake() {
        return take;
    }
    
    public void setUpkeep(int newUpkeep) {
        upkeep = newUpkeep;
    }
    
    public int getUpkeep() {
        return upkeep;
    }
    
    public void setCheck(boolean check) {
        checkApplies = check;
    }
    
    public boolean penApplies() {
        return checkApplies;
    }
    
    public int getNumSkillPoints() {
        int temp;
        if(progLevel == 0) temp = 0;
        else if(progLevel == 1) temp = 1;
        else if(progLevel == 2) temp = 3;
        else if(progLevel == 3) temp = 6;
        else temp = 0;
        
        return temp + upkeep + take + min*2;
    }
}
