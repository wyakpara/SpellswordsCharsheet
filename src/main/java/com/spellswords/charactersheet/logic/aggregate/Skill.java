/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spellswords.charactersheet.logic.aggregate;

import com.spellswords.charactersheet.utilities.Columns;

import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author Didge
 */
public class Skill extends Rollable implements Comparable<Skill> {

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

    Skill() {}
    
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
        profBonus = calculateProfBonus(Proficiency.level, profLevel);
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

    public int compareTo(final Skill compare) {
        return this.name.compareTo(compare.name);
    }

    public int getRawBonus() {
        return finalBonus - abilityBonus;
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

    /**** Textedit Functions ****/

    public static String getSkillTypeString(SkillType type) {
        switch (type) {
            case BODY:
                return "BODY";
            case FLEX:
                return "FLEX";
            case MIND:
                return "MIND";
        }
        return null;
    }

    public String toPartialString() {
        Columns column = getPartialSkillHeader();
        return addPartialToColumn(column).toString();
    }

    public static Columns getPartialSkillHeader() {
        return new Columns().addLine("Name", "Raw", "+Abil", "Abil", "");
    }

    public Columns addPartialToColumn(Columns column) {
        return column.addLine(name, "" + getRawBonus(), "" + getFinalBonus(), abilityName, subSkillsToString());
    }

    public String toFullString() {
        Columns column = getFullSkillHeader();
        return addAllToColumn(column).toString();
    }

    public static Columns getFullSkillHeader() {
        return new Columns().addLine("Name", "Raw", "+Abil", "Abil", "Proficiency", "Take", "Min", "|", "Item",
                "Enhance", "Spec", "Temp", "Focus", "");
    }

    public Columns addAllToColumn(Columns column) {
        return column.addLine(name, "" + getRawBonus(), "" + getFinalBonus(), "" + abilityName, "" + getProfLevel(),
                "" + take, "" + min, "|", "" + itemBonus, "" + enhanceBonus, "" + specBonus, "" + tempBonus, "" + focus,
                subSkillsToString());
    }

    public String subSkillsToString() {
        StringBuilder subStr = new StringBuilder("");
        Collection<SubSkill> subs = subSkills.values();
        for(SubSkill s:subs) {
            if(s.active) {
                subStr.append("\n\tActive Subskill: " + s.name);
            }
        }
        return subStr.toString();
    }

    public String subSkillsToString(boolean showInactive) {
        if(!showInactive) {
            return subSkillsToString();
        }

        StringBuilder subStr = new StringBuilder("");
        Collection<SubSkill> subs = subSkills.values();
        for(SubSkill s:subs) {
            if(s.active) {
                subStr.append("\n\tActive Subskill: " + s.name);

            } else {
                subStr.append("\n\tInactive Subskill: " + s.name);
            }
        }
        return subStr.toString();
    }

}
