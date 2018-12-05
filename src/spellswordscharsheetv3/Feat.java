/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spellswordscharactersheet_v3;

/**
 *
 * @author Didge
 */
public class Feat {
    String archetype;
    String name;
    String description;
    int tier;
    int multiplier;
    int number;
    
    public Feat() {
        archetype = "";
        name = "";
        description = "";
        tier = 0;
        multiplier = 0;
    }
    
    public Feat(String newName) {
        name = newName;
        archetype = "";
        description = "";
        tier = 0;
        multiplier = 0;
    }
    
    public Feat(String newName, String newArch, String newDesc, int newTier, int newMult, int num) {
        name = newName;
        archetype = newArch;
        description = newDesc;
        tier = newTier;
        multiplier = newMult;
        number = num;
    }
    
    public void setName(String newName) {
        name = newName;
    }
    
    public String getName() {
        return name;
    }
    
    public void setDescription(String newDesc) {
        description = newDesc;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setArchetype(String newArch) {
        archetype = newArch;
    }
    
    public String getArchetype() {
        return archetype;
    }
    
    public void setTier(int newTier) {
        tier = newTier;
    }
    
    public int getTier() {
        return tier;
    }
    
    public void setMult(int mult) {
        multiplier = mult;
    }
    
    public int getMult() {
        return multiplier;
    }
    
    public void setNum(int num) {
        number = num;
    }
    
    public int getNum() {
        return number;
    }
}
