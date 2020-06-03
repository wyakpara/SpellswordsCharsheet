/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spellswords.charactersheet.logic.aggregate;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author Didge
 */
public class Skill extends Rollable implements Serializable {

    private String name;
    private SkillType type;
    private String abilityName;
    private int finalBonus;
    private int take;
    private int min;
    private int focus;
    private boolean checkApplies;
    private HashMap<String, SubSkill> subSkills;

    private static int checkPen;
    
    public Skill(String name, String ability, SkillType type, AbilityCollection abilities) {
        initRollable();
        this.name = name;
        this.type = type;
        subSkills = new HashMap<>();
        setAbility(ability, abilities);
    }

    public void updateAbility(AbilityCollection abilities) {
        abilityBonus = abilities.getAbility(abilityName).getAbilityBonus();
        update();
    }

    public void setAbility(String ability, AbilityCollection abilities) {
        abilityName = ability;
        abilityBonus = abilities.getAbility(abilityName).getAbilityBonus();
        update();
    }

    public void addSubSkill(SubSkill subSkill) {
        subSkills.put(subSkill.name, subSkill);
    }

    @Override
    public void update() {
        finalBonus = profBonus + focus + itemBonus + enhanceBonus + specBonus + tempBonus + abilityBonus;
        if(checkApplies) finalBonus -= checkPen;
    }

    public int getTotalSkillPoints() {
        int temp = getNumSkillPoints();
        Collection<SubSkill> subs = subSkills.values();
        for(SubSkill s:subs) {
            if(s.isActive()) {
                temp += s.getCost();
            }
        }

        return temp + focus*2 + take + min*2;
    }

    public String getName() {
        return name;
    }

    public SkillType getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(SkillType type) {
        this.type = type;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public int getFinalBonus() {
        return finalBonus;
    }

    public void setFinalBonus(int finalBonus) {
        this.finalBonus = finalBonus;
    }

    public int getTake() {
        return take;
    }

    public void setTake(int take) {
        this.take = take;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getFocus() {
        return focus;
    }

    public void setFocus(int focus) {
        this.focus = focus;
    }

    public boolean isCheckApplies() {
        return checkApplies;
    }

    public void setCheckApplies(boolean checkApplies) {
        this.checkApplies = checkApplies;
    }

    public HashMap<String, SubSkill> getSubSkills() {
        return subSkills;
    }

    public void setSubSkills(HashMap<String, SubSkill> subSkills) {
        this.subSkills = subSkills;
    }

    public static int getCheckPen() {
        return checkPen;
    }

    public static void setCheckPen(int checkPen) {
        Skill.checkPen = checkPen;
    }
}
