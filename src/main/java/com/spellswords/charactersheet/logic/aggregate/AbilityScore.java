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
public class AbilityScore extends Rollable implements Serializable {

    public String name;

    private int finalScore;

    private int baseScore;
    
    public AbilityScore(String name, int baseScore) {
        this.name = name;
        finalScore = this.baseScore = baseScore;
        initRollable();
        update();
    }

    @Override
    public void update() {
        finalScore = baseScore+itemBonus+enhanceBonus+specBonus+tempBonus;
        abilityBonus = (finalScore - 10)/2;
    }

    public int getMod() {
        return abilityBonus;
    }

    @Override
    public int roll() {
        int total = abilityBonus + profBonus;
        return dice.rollDice(total);
    }

    public int getBaseScore() {
        return baseScore;
    }

    public void setBaseScore(int baseScore) {
        this.baseScore = baseScore;
        update();
    }

    public int getFinalScore() {
        return finalScore;
    } public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toFullString() {
        StringBuilder abStr = new StringBuilder("Name\tFinal\tMod\tBase\tItem\tEnhance\tSpec\tTemp\t");
        abStr.append(name + "\t\t" + finalScore + "\t" + abilityBonus + "\t");
        abStr.append(" | " + itemBonus + "\t" + enhanceBonus + "\t" + specBonus + "\t" + tempBonus);
        return abStr.toString();
    }


}
