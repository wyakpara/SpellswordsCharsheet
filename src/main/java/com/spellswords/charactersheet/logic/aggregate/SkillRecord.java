package com.spellswords.charactersheet.logic.aggregate;

import com.spellswords.charactersheet.utilities.Columns;

import java.io.*;
import java.sql.PseudoColumnUsage;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import static com.spellswords.charactersheet.logic.aggregate.SkillType.*;

public class SkillRecord implements Serializable {
    private HashMap<String, Skill> skills;
    private HashMap<String, PseudoSkill> pSkills;
    private SkillPoints skillPoints;
//    private static AbilityCollection abilities;
//    private static LevelRecord record;
    private transient static final String defaultPath = "src/main/resources/configs/defaultSkills.csv";

    SkillRecord() {}

    public SkillRecord(AbilityCollection abilities, LevelRecord level) throws FileNotFoundException, IOException {
        // Load default abilities
//        record = level;
//        SkillRecord.abilities = abilities;
        skills = new HashMap<>();
        pSkills = new HashMap<>();
        readSkillsFromFile(defaultPath, abilities);
//        skillPoints = new SkillPoints(record.calculateSkillPoints(), abilities);
        skillPoints = new SkillPoints(0, abilities, level);
    }

    private void readSkillsFromFile(String filepath, AbilityCollection abilities) throws FileNotFoundException, IOException {
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
                SubSkill sub = new SubSkill(name, cost);
                lastSkill.addSubSkill(sub);
            }
        }
    }

    public boolean skillExists(String skillName) {
        return skills.containsKey(skillName);
    }

    public void setSkillProf(String skillName, int profLevel) {
        // Find skill
        Skill skill = getSkill(skillName);
        skillPoints.setSkillProf(skill, profLevel);
    }

    public void updateSkills(AbilityCollection abilities, LevelRecord level) {
        skillPoints.updatePoints(abilities, level);
        skillPoints.reset();
        Collection<Skill> sc = skills.values();
        for(Skill s:sc) {
            s.updateAbility(abilities);
            skillPoints.spendSkillPoints(s.getType(), s.getNumSkillPoints());
        }

        Collection<PseudoSkill> psc = pSkills.values();
        for(PseudoSkill ps:psc) {
            skillPoints.spendSkillPoints(ps.getSkillType(), ps.getNumSkillPoints());
        }
    }

    public void spendSkillPoints(SkillType type, int points) {
        skillPoints.spendSkillPoints(type, points);
    }

    public void addSkill(Skill skill) {
        skills.put(skill.getName(), skill);
    }

    public Skill getSkill(String skillName) {
        return skills.get(skillName);
    }

    public void addPseudoSkill(PseudoSkill pskill) {
        pSkills.put(pskill.getName(), pskill);
        skillPoints.spendSkillPoints(pskill.getSkillType(), pskill.getNumSkillPoints());
    }

    public void removePseudoSkill(PseudoSkill pskill) {
        skillPoints.spendSkillPoints(pskill.getSkillType(), -1 * pskill.getNumSkillPoints());
        pSkills.remove(pskill.getName());
    }

    public PseudoSkill getPseudoSkill(String key) {
        return pSkills.get(key);
    }

    public boolean hasPseudoSkills() {
        return !pSkills.isEmpty();
    }

    /************* TextAdventure Functions **********/

    public String skillPointsToString() {
        return skillPoints.toString();
    }

    public String skillsToString() {
        Columns srStr = Skill.getPartialSkillHeader();
        Collection<Skill> skillCollection = skills.values();
        Skill sarray[] = skillCollection.toArray(new Skill[skillCollection.size()]);
        Arrays.sort(sarray);
        for(Skill s:sarray) {
            srStr = s.addPartialToColumn(srStr);
        }
        return srStr.toString();
    }

    public String pseudoSkillsToString() {
        Collection<PseudoSkill> pskillCollection = pSkills.values();
        Columns srStr = PseudoSkill.getPartialPseudoSkillHeader();
        PseudoSkill psarray[] = pskillCollection.toArray(new PseudoSkill[pskillCollection.size()]);
        Arrays.sort(psarray);
        for(PseudoSkill p:psarray) {
            srStr = p.addPartialToColumn(srStr);
        }
        return srStr.toString();
    }

}
