/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spellswords.charactersheet.logic.aggregate;

import com.spellswords.charactersheet.utilities.Columns;

import java.io.Serializable;

/**
 *
 * @author Didge
 */
public class AbilityScore extends Rollable implements Comparable<AbilityScore> {

    public String name;

    private int finalScore;

    private int baseScore;

    public void setIndex(int index) {
        this.index = index;
    }

    private int index;

    AbilityScore () {}
    
    public AbilityScore(String name, int baseScore) {
        this.name = name;
        finalScore = this.baseScore = baseScore;
        initRollable();
        update();
    }

    @Override
    public int compareTo(AbilityScore abil) {
        return Integer.compare(index, abil.index);
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

    public int getSave() {
        return getMod() + getProfBonus();
    }

    public int getFinalScore() {
        return finalScore;
    } public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**** Textedit Functions ****/
    public String toPartialString() {
        Columns column = getPartialAbilHeader();
        return addPartialToColumn(column).toString();
    }

    public static Columns getPartialAbilHeader() {
        return new Columns().addLine("Name", "Score", "Mod", "Save");
    }

    public Columns addPartialToColumn(Columns column) {
        return column.addLine(name, "" + finalScore, "" + abilityBonus, "" + getSave());
    }

    public String toFullString() {
        Columns column = getFullAbilHeader();
        return addAllToColumn(column).toString();
    }

    public static Columns getFullAbilHeader() {
        return new Columns().addLine("Name", "Final", "Mod", "Save", "|", "Base", "Item", "Enhance", "Spec", "Temp");
    }

    public Columns addAllToColumn(Columns column) {
        return column.addLine(name, "" + finalScore, "" + abilityBonus, "" + getSave(), "|", "" + baseScore,
                "" + itemBonus, "" + enhanceBonus, "" + specBonus, "" + tempBonus);
    }


}
