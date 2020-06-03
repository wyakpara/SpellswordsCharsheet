package com.spellswords.charactersheet.logic.aggregate;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;

import static com.spellswords.charactersheet.logic.aggregate.SkillType.*;

public class SkillRecord implements Serializable {
    private HashMap<String, Skill> skills;
    private HashMap<String, PseudoSkill> pSkills;
    private SkillPoints skillPoints;
    private AbilityCollection abilities;
    private LevelRecord record;
    private static final String defaultPath = "src/main/resources/configs/defaultSkills.csv";

    public SkillRecord(AbilityCollection abilities, LevelRecord level) throws FileNotFoundException, IOException {
        // Load default abilities
        record = level;
        this.abilities = abilities;
        skills = new HashMap<>();
        pSkills = new HashMap<>();
        readSkillsFromFile(defaultPath);
        skillPoints = new SkillPoints(record.calculateSkillPoints(), abilities);
    }

    private void readSkillsFromFile(String filepath) throws FileNotFoundException, IOException {
        BufferedReader csvReader = new BufferedReader((new FileReader(filepath)));
        String row;
        String name = "";
        String ability = "INT";
        int cost = 3;
        SkillType type = FLEX;
        boolean checkApplies = false;
        Skill lastSkill = null;
        while((row = csvReader.readLine()) != null) {
            if(row.contains("//")) continue;
            String[] data = row.split(",");
            boolean isSkill = false;
            String s;
            for(int i = 0; i < data.length; i++) {
                s = data[i];
                if(s.toUpperCase().equals("SKILL")) {
                    isSkill = true;
                } else if(s.toUpperCase().equals("SUBSKILL")) {
                    isSkill = false;
                } else {
                    switch(i) {
                        // Name in both cases
                        case 1:
                            name = s;
                            break;
                        // Cost for pskill, Ability for skill
                        case 2:
                            if(isSkill) {
                                ability = s;
                            } else {
                                cost = Integer.parseInt(s);
                            }
                            break;
                        // Type for skill
                        case 3:
                            if(s.toUpperCase().equals("BODY")) {
                                type = BODY;
                            } else if(s.equals("MIND")) {
                                type = MIND;
                            }
                            break;
                        case 4:
                            if(s.toUpperCase().equals("TRUE")) {
                                checkApplies = true;
                            }
                            break;
                    }
                }
            }
            if(isSkill) {
                lastSkill = new Skill(name, ability, type, abilities);
                addSkill(lastSkill);
            } else {
                SubSkill sub = new SubSkill(lastSkill, name, cost);
                lastSkill.addSubSkill(sub);
            }
        }
    }

    public void setSkillProf(String skillName, int profLevel) {
        // Find skill
        Skill skill = getSkill(skillName);
        skillPoints.setSkillProf(skill, profLevel);
    }

    public void updateSkills() {
        Collection<Skill> sc = skills.values();
        for(Skill s:sc) {
            s.updateAbility(abilities);
        }
    }

    public void addSkill(Skill skill) {
        skills.put(skill.getName(), skill);
    }

    public Skill getSkill(String skillName) {
        return skills.get(skillName);
    }

    /************* TextAdventure Functions **********/

    public String toString() {
        StringBuilder srStr = new StringBuilder("Name\t\tRaw\t+Abil\tAbil\n");
        Collection<Skill> skillCollection = skills.values();
        for(Skill s:skillCollection) {
            srStr.append(s.getName() + "\t");
            srStr.append((s.getFinalBonus() - s.getAbilityBonus()) + "\t");
            srStr.append(s.getFinalBonus() + "\t");
            srStr.append(s.getAbilityName() + "\n");
        }
        return srStr.toString();
    }

}
