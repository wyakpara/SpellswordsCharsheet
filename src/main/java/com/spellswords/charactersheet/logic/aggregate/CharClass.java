/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spellswords.charactersheet.logic.aggregate;

import java.io.Serializable;

/**
 *
 * @author Didge
 */
public class CharClass implements Serializable {
    private String name;
    private CharacterType type;
    private CharacterType secondary;
    private int HD;
    private int level;
    private int skill;
    
    public CharClass() {
        name = "";
        HD = 0;
        level = 0;
        skill = 0;
        
    }
    
    public CharClass(String newName) {
        name = newName;
        HD = 0;
        level = 0;
        skill = 0;
    }
    
    public CharClass(String name, CharacterType primaryType, CharacterType secondaryType, int HD, int level, int skill) {
        this.name = name;
        this.HD = HD;
        this.level = level;
        this.skill = skill;
        this.type = primaryType;
        this.secondary = secondaryType;
    }

    public String archetypeToString() {
        StringBuilder arch = new StringBuilder();
        switch (type) {
            case MARTIAL:
                arch.append("M");
                break;
            case SPECIALIST:
                arch.append("S");
                break;
            case PSIONIC:
                arch.append("P");
                break;
        }

        switch (secondary) {
            case MARTIAL:
                arch.append("m");
                break;
            case SPECIALIST:
                arch.append("s");
                break;
            case PSIONIC:
                arch.append("p");
                break;
        }

        return arch.toString();
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String newName) {
        name = newName;
    }
    
    public int getLevel() {
        return level;
    }
    
    public void setLevel(int newLevel) {
        level = newLevel;
    }
    
    public int getHD() {
        return HD;
    }
    
    public void setHD(int newHD) {
        HD = newHD;
    }
    
    public void setHD(String newName) {
        name = newName;
    }
    
    public int getSkill() {
        return skill;
    }
    
    public void setSkill(int newSkill) {
        skill = newSkill;
    }
    
    public CharacterType getType() {
        return type;
    }

    public CharacterType getSecondary() {
        return secondary;
    }
    
    public void setType(CharacterType newType) {
        type = newType;
    }

    public void setSecondary(CharacterType newType) {
        secondary = newType;
    }

    public int calculateSkillPoints() {
        return skill * level;
    }
}
