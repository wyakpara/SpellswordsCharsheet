package com.spellswords.charactersheet.logic.aggregate;

import java.io.Serializable;

public class SubSkill implements Serializable {
    //Skill relatesTo;
    String name;
    boolean active;
    boolean free;

    int cost;

    SubSkill() {}

    public SubSkill(String name, int cost) {
      //  relatesTo = skill;
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

    public void setFree(boolean free) {
        this.free = free;
    }

    public int getCost() {
        if(free) return 0;
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
