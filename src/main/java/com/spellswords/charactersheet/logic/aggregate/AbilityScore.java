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
public class AbilityScore {
    private int finalScore;
    private int modifier;
    private int baseScore;
    private int bonus;
    private int temp;
    private int other;
    
    public AbilityScore(int[] numbers) {
        if(numbers.length != 4)
        {
            System.err.println("Ability Error");
        }
        else
        {
            baseScore = numbers[0];
            bonus = numbers[1];
            temp = numbers[2];
            other = numbers[3];
            update();
        }
    }
    
    public AbilityScore(int base) {
        baseScore = base;
        bonus = 0;
        temp = 0;
        other = 0;
        update();
    }
    
    public void update() {
        finalScore = baseScore + bonus + temp + other;
        modifier = (finalScore - 10)/2;
    }
    
    public int getFinalScore() {
        return finalScore;
    }
    
    public int getBaseScore() {
        return baseScore;
    }
    
    public void setBaseScore(int baseScore) {
        this.baseScore = baseScore;
        update();
    }
    
    public int getBonus() {
        return bonus;
    }
    
    public void setBonus(int baseScore) {
        this.bonus = baseScore;
        update();
    }
    
    public int getTemp() {
        return temp;
    }
    
    public void setTemp(int baseScore) {
        this.temp = baseScore;
        update();
    }
    
    public int getOther() {
        return other;
    }
    
    public void setOther(int baseScore) {
        this.other = baseScore;
        update();
    }
    
    public int getMod() {
        return modifier;
    }
}
