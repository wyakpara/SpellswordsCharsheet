package com.spellswords.charactersheet.logic.aggregate;

import java.io.Serializable;
import java.util.Collection;

public abstract class Proficiency {

    public static int level;

    int profBonus; // Bonus from proficiency
    int profLevel; // Level of proficiency

    boolean prof1Free;
    boolean prof2Free;
    boolean prof3Free;

    public void update() {
        return;
    }

    public static void setLevel(int newLevel) {
        level = newLevel;
    }

    public void initProficiency() {
        profBonus = 0;
        profLevel = 0;
    }

    public int getProfLevel() {
        return profLevel;
    }

    public void setProfLevel(int profLevel) {
        this.profLevel = profLevel;
        update();
        profBonus = calculateProfBonus(level, profLevel);
    }

    public int getProfBonus() {
        return profBonus;
    }

    public void setProfBonus(int profBonus) {
        this.profBonus = profBonus;
    }

    public boolean isProf1Free() {
        return prof1Free;
    }

    public void setProf1Free(boolean prof1Free) {
        this.prof1Free = prof1Free;
    }

    public boolean isProf2Free() {
        return prof2Free;
    }

    public void setProf2Free(boolean prof2Free) {
        this.prof2Free = prof2Free;
    }

    public boolean isProf3Free() {
        return prof3Free;
    }

    public void setProf3Free(boolean prof3Free) {
        this.prof3Free = prof3Free;
    }

    public void updateProficiency() {
        profBonus = calculateProfBonus(level, profLevel);
    }

    public void updateProficiency(int profLevel) {
        setProfLevel(profLevel);
    }

    public int calculateProfBonus(int level, int profLevel) {
        int fullprof = level/2 + 2;
        switch (profLevel) {
            case 1:
                return fullprof/3;
            case 2:
                return 2 * (fullprof / 3);
            case 3:
                return fullprof;
            case 0:
            default:
                return 0;
        }
    }

    public int getNumSkillPoints() {
        int temp = 0;
        if(profLevel == 0) temp = 0;
        else if(profLevel == 1) temp = 1;
        else if(profLevel == 2) temp = 3;
        else if(profLevel == 3) temp = 6;

        if(prof1Free && profLevel > 0) temp--;
        if(prof2Free && profLevel > 1) temp -= 2;
        if(prof3Free && profLevel > 2) temp -= 3;

        return temp;
    }
}
