package com.spellswords.charactersheet.logic.aggregate;

import com.spellswords.charactersheet.utilities.Columns;

import java.util.Comparator;

public class PseudoSkill extends Proficiency implements Comparable<PseudoSkill> {

    private String name;
    private String type; // i.e. language, weapon, armor, always in all caps
    private SkillType skillType;

    PseudoSkill() {}

    public PseudoSkill(String name, String type, SkillType skillType) {
        this.type = type.toUpperCase();
        this.skillType = skillType;
        this.name = name;
    }

    @Override
    public int compareTo(PseudoSkill ps) {
        return Comparator.comparing(PseudoSkill::getType)
                .thenComparing(PseudoSkill::getName)
                .compare(this, ps);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public SkillType getSkillType() {
        return skillType;
    }

    /**** Textedit Functions ****/
    public static Columns getPartialPseudoSkillHeader() {
        return new Columns().addLine("Name", "Tier", "Type");
    }

    public Columns addPartialToColumn(Columns column) {
        return column.addLine(name, "" + getProfLevel(), type);
    }

}
