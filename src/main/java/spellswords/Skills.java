/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spellswords;
import java.util.HashMap;

/**
 *
 * @author Didge
 */
public class Skills {
    private HashMap<String, Skill> skills;
    
    public Skills() {
        skills = new HashMap<>();
    }
    
    public void addSkills(String[] newName, String[] newPers, int[] newProgLevel) {
        if(newName.length != newPers.length || newName.length != newProgLevel.length) return;
        for(int i = 0; i < newName.length; i++) {
            addSkill(newName[i], newPers[i], newProgLevel[i]);
        }
    }
    
    public void addSkill(String newName, String newPers, int newProgLevel) {
        addSkill(new Skill(newName, newPers, newProgLevel));
    }
    
    public void addSkill(Skill skill) {
        skills.put(skill.getName(), skill);
    }
    
    public Skill getSkill(String skillName) {
        return skills.get(skillName);
    }
}
