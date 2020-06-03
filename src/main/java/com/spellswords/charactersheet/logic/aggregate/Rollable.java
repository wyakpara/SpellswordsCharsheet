package com.spellswords.charactersheet.logic.aggregate;

/**
 * Rollable class is any ability that can be rolled
 */
public abstract class Rollable extends Proficiency {
    int itemBonus; // Bonus from an item
    int enhanceBonus; // Bonus for a semi-permanent enhancement
    int specBonus; // Bonus from some special feat
    int abilityBonus; // Bonuses from ability scores
    int tempBonus; // Temporary Bonuses

    public DiceCollection dice;

    public void initRollable() {
        initProficiency();
        itemBonus = enhanceBonus = specBonus = abilityBonus = tempBonus = 0;
    }

    public int roll() {
        int total = itemBonus+enhanceBonus+specBonus+abilityBonus+tempBonus+profBonus;
        return dice.rollDice(total);
    }

    public int getItemBonus() {
        return itemBonus;
    }

    public void setItemBonus(int itemBonus) {
        this.itemBonus = itemBonus;
        update();
    }

    public int getTempBonus() {
        return tempBonus;
    }

    public void setTempBonus(int tempBonus) {
        this.tempBonus = tempBonus;
        update();
    }

    public int getAbilityBonus() {
        return abilityBonus;
    }

    public void setAbilityBonus(int abilityBonus) {
        this.abilityBonus = abilityBonus;
        update();
    }

    public int getSpecBonus() {
        return specBonus;
    }

    public void setSpecBonus(int specBonus) {
        this.specBonus = specBonus;
        update();
    }

    public int getEnhanceBonus() {
        return enhanceBonus;
    }

    public void setEnhanceBonus(int enhanceBonus) {
        this.enhanceBonus = enhanceBonus;
        update();
    }

}
