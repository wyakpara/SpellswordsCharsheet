package com.spellswords.charactersheet.logic.aggregate;

public class PseudoSkill extends Proficiency {

    String name;
    private SkillType type;
    public PseudoSkill(String name, SkillType type) {
        this.type = type;
        this.name = name;
    }

}
