package com.spellswords.charactersheet.logic.aggregate;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

public class LevelRecord implements Serializable {
    protected HashMap<String, CharClass> charClasses;

    public LevelRecord() {}

    public LevelRecord(CharClass firstClass) {
        charClasses = new HashMap<>();
        charClasses.put(firstClass.getName(), firstClass);
    }

    public int getLevel() {
        int i = 0;
        Collection<CharClass> c = charClasses.values();
        for(CharClass cc:c) {
            i += cc.getLevel();
        }
        return i;
    }

    public void updateClassName(String newName, String oldName) {
        CharClass c = charClasses.get(oldName);
        if(c == null) return;
        charClasses.remove(c);
        charClasses.put(newName, c);
    }

    public void addClass(CharClass newClass) {
        charClasses.put(newClass.getName(), newClass);
    }

    public int calculateSkillPoints() {
        int skillpoints = 0;
        Collection<CharClass> cs = charClasses.values();
        for(CharClass c:cs) {
            skillpoints += c.calculateSkillPoints();
        }
        return skillpoints;
    }

    public int calculateHD() {
        int hd = 0;
        Collection<CharClass> cs = charClasses.values();
        for(CharClass c:cs) {
            hd += c.getHD() * c.getLevel();
        }
        return hd;
    }

    public Collection<CharClass> getCollection() {
        return charClasses.values();
    }

    /************* TextAdventure Functions **********/
    public String toString() {
        StringBuilder levelStr = new StringBuilder("Level: " + getLevel() + "\n");
        Collection<CharClass> ccs = charClasses.values();

        int i = 1;
        for(CharClass cc:ccs) {
            levelStr.append("Class " + i + ": ");
            levelStr.append(cc.getName() + " (" + cc.archetypeToString() + ") " + cc.getLevel() + "\n");
            levelStr.append("Hit Die: " + cc.getHD() + "\n");
            levelStr.append("Skills Per Level: " + cc.getSkill() + "\n");
        }
        return levelStr.toString();
    }
}
