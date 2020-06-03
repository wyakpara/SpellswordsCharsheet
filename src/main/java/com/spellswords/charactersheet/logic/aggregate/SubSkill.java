package com.spellswords.charactersheet.logic.aggregate;

import java.io.Serializable;

public class SubSkill implements Serializable {
    Skill relatesTo;
    String name;
    boolean active;

    int cost;

    public SubSkill(Skill skill, String name, int cost) {
        relatesTo = skill;
        this.name = name;
        this.cost = cost;
        active = false;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
