/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spellswordscharsheetv3;

import java.io.IOException;

/**
 *
 * @author Didge
 */
public class CharClass {
    private String name;
    private String type;
    private String secondary;
    private int HD;
    private int level;
    private int skill;
    
    public CharClass() {
        name = "";
        type = "";
        secondary = "";
        HD = 0;
        level = 0;
        skill = 0;
        
    }
    
    public CharClass(String newName) {
        name = newName;
        type = "";
        secondary = "";
        HD = 0;
        level = 0;
        skill = 0;
    }
    
    public CharClass(String name, String primaryType, String secondaryType, int HD, int level, int skill) {
        this.name = name;
        this.HD = HD;
        this.level = level;
        this.skill = skill;
        this.type = primaryType;
        this.secondary = secondaryType;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String newName) {
        name = newName;
    }
    
    public int getLevel() {
        return level;
    }
    
    public void setLevel(int newLevel) {
        level = newLevel;
    }
    
    public int getHD() {
        return HD;
    }
    
    public void setHD(int newHD) {
        HD = newHD;
    }
    
    public void setHD(String newName) {
        name = newName;
    }
    
    public int getSkill() {
        return skill;
    }
    
    public void setSkill(int newSkill) {
        skill = newSkill;
    }
    
    public String getType(int which) {
        if(which == 1)
        {
            return type;
        }
        else
        {
            return secondary;
        }
    }
    
    public void setType(int which, String newType) {
        if(which == 1)
        {
            type = newType;
        }
        else
        {
            secondary = newType;
        }
    }
    
//    public void writeToJson(JsonWriter writer) throws IOException {
//        writer.beginObject();
//        writer.name("name").value(name);
//        writer.name("type").value(type);
//        writer.name("secondary").value(secondary);
//        writer.name("level").value(level);
//        writer.name("skill").value(skill);
//        writer.name("HD").value(HD);
//        writer.endObject();
//    }
}
