/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spellswords.charactersheet.logic.aggregate;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Didge
 */
public class Classes implements Serializable {
    HashMap<String, CharClass> classes;
    
    public Classes() {
        classes = new HashMap<>();
    }
    
    public Classes(String[] names, ArchetypeType[] primaryTypes, ArchetypeType[] secondaryTypes, int[] HDs, int[] levels, int[] skills) {
        if(names.length != primaryTypes.length
            || names.length != secondaryTypes.length
            || names.length != HDs.length
            || names.length != levels.length
            || names.length != skills.length) {
            System.err.println("Incorrect class params lens!!");
        }
        for(int i = 0; i < names.length; i++) {
            addClass(names[i], primaryTypes[i], secondaryTypes[i], HDs[i], levels[i], skills[i]);
        }
    }
    
    public void addClass(String name, ArchetypeType primaryType, ArchetypeType secondaryType, int HD, int level, int skill) {
        classes.put(name, new CharClass(name, primaryType, secondaryType, HD, level, skill));
    }
    
    public void addClass(CharClass newClass) {
        classes.put(newClass.getName(), newClass);
    }
    
    public int getSumHDs() {
        int sum = 0;
        for(CharClass cls: classes.values()) {
            sum += cls.getHD() * cls.getLevel();
        }
        return sum;
    }
    
    public int getTotalLevel() {        
        int sum = 0;
        for(CharClass cls: classes.values()) {
            sum += cls.getLevel();
        }
        return sum;
    }
    
    public void updateClassName(String name, String newName) {
        classes.get(name).setName(newName);
    }
    
    public void updateClassType(String name, ArchetypeType newType) {
        classes.get(name).setType(newType);
    }
    
    public void updateClassSecondary(String name, ArchetypeType newType) {
        classes.get(name).setType(newType);
    }
    
    public void updateClassHD(String name, int newHD) {
        classes.get(name).setHD(newHD);
    }
    
    public void updateClassLevel(String name, int newLevel) {
        classes.get(name).setLevel(newLevel);
    }
    
    public void updateClassSkill(String name, int newSkill) {
        classes.get(name).setSkill(newSkill);
    }
}
