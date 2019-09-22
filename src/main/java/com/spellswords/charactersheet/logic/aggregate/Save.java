/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spellswords.charactersheet.logic.aggregate;

/**
 *
 * @author Didge
 */
public class Save {
    String type;
    int progLevel; // 0 - none, 1 - 1/3, 2 - 2/3, 3 - full
    int score, base, bonus, temp, other;
    
    public Save(String newType) {
        type = newType;
        score = base = bonus = temp = other = 0;
    }
    
    public Save(String newType, int newProgLevel, int prof, int newBonus, int newTemp, int newOther) {
        type = newType;
        progLevel = newProgLevel;
        bonus = newBonus;
        temp = newTemp;
        other = newOther;
        setBase(prof);
        updateScore();
    }
    
    public void setBase(int prof) {
        base = prof/3;
        base *= progLevel;
    }
    
    public void updateScore() {
        score = base + bonus + temp + other;
    }
    
    public int getScore() {
        return score;
    }
    
    
    public int getProgLevel() {
        return progLevel;
    }
    
    public void setProgLevel(int newProgLevel) {
        if(newProgLevel == 0 || newProgLevel == 1 || newProgLevel == 2 || newProgLevel == 3)
            progLevel = newProgLevel;
    }
    
    public int getBonus() {
        return bonus;
    }
    
    public void setBonus(int newBonus) {
        bonus = newBonus;
    }
    
    public int getTemp() {
        return temp;
    }
    
    public void setTemp(int newTemp) {
        temp = newTemp;
    }
    
    public int getOther() {
        return other;
    }
    
    public void setOther(int newOther) {
        other = newOther;
    }
    
    public int getBase() {
        return base;
    }
}
