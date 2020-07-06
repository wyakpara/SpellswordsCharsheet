/*
 * This object describes a spellswords character
 * 
 * It should encapsulate all aspects of the character - from ability scores and 
 * beyond - and should be the gateway used by the gui.
 */
package com.spellswords.charactersheet.logic.aggregate;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

/**
 *
 * @author Aric Hudson
 */
public class Character implements Serializable {
    public String charName;
    private LevelRecord levels;
    private Health health;
    private SkillRecord skillRecord;
    private AbilityCollection abilityCollection;
    private FeatRecord featRecord;

    public Character () {}

    public Character(String charname, ArchetypeType primeType, ArchetypeType secondType) throws FileNotFoundException, IOException {
        this.charName = charname;
        levels = new LevelRecord(new CharClass("", primeType, secondType, 6, 1, 4));
        abilityCollection = new AbilityCollection();
        generateStandardAbilities();
        skillRecord = new SkillRecord(abilityCollection, levels);
    }

    public Character(String charname, String className, ArchetypeType primeType, ArchetypeType secondType) throws FileNotFoundException, IOException {
        this.charName = charname;
        levels = new LevelRecord(new CharClass(className, primeType, secondType, 6, 1, 4));
        abilityCollection = new AbilityCollection();
        generateStandardAbilities();
        skillRecord = new SkillRecord(abilityCollection, levels);
        health = new Health(levels, abilityCollection);
    }

    public Character (String charName, CharClass firstClass) throws IOException {
        this.charName = charName;
        levels = new LevelRecord(firstClass);
        abilityCollection = new AbilityCollection();
        generateStandardAbilities();
        skillRecord = new SkillRecord(abilityCollection, levels);
        featRecord = new FeatRecord();
        health = new Health(levels, abilityCollection);
    }

    public void generateStandardAbilities() {
        AbilityScore str = new AbilityScore("STR", 10);
        str.setIndex(0);
        abilityCollection.add(str);
        AbilityScore dex = new AbilityScore("DEX", 10);
        dex.setIndex(1);
        abilityCollection.add(dex);
        AbilityScore con = new AbilityScore("CON", 10);
        con.setIndex(2);
        abilityCollection.add(con);
        AbilityScore ing = new AbilityScore("INT", 10);
        ing.setIndex(3);
        abilityCollection.add(ing);
        AbilityScore wis = new AbilityScore("WIS", 10);
        wis.setIndex(4);
        abilityCollection.add(wis);
        AbilityScore cha = new AbilityScore("CHA", 10);
        cha.setIndex(5);
        abilityCollection.add(cha);
    }

    public static void saveCharacterJson(Character character) {
        Gson gson = new Gson();
        String json = gson.toJson(character);
//        System.out.println(json);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(character.charName + ".json"));
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving character");
        }
    }

    public static Character loadCharacterJson(String filename) {
        Gson gson = new Gson();
        String json = "";
        try {
            json = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            System.out.println("Unable to read file");
            return null;
        }

//        System.out.println("Character:\n" + json);
        Character character = gson.fromJson(json, Character.class);
        character.updateAll();
        return character;
    }

    public static void saveCharacter(Character character) {
        String filename = character.charName + ".ss";
        try {
            FileOutputStream f = new FileOutputStream(new File(filename));
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write to file
            o.writeObject(character);
            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }

    public static Character loadCharacter(String filename) {
        try {
            FileInputStream f = new FileInputStream(new File(filename));
            ObjectInputStream i = new ObjectInputStream(f);

            // Read from file
            Character character = (Character) i.readObject();
            i.close();
            f.close();

            character.updateAll();
            return character;

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void changeSkillProficiency(String skillname, int newProf) {
        skillRecord.setSkillProf(skillname, newProf);
        updateAll();
    }

    public void updateAll() {
        Proficiency.setLevel(levels.getLevel());
        abilityCollection.updateAbilities();
        skillRecord.updateSkills(abilityCollection, levels);
        featRecord.calculateNumFeats(levels);
        featRecord.spendFeats();
        if(health == null) {
            health = new Health(levels, abilityCollection);
        }
        health.calcluateFromLevels(levels, abilityCollection);
    }

    public void addAbility(AbilityScore ability) {
        abilityCollection.add(ability);
    }

    public AbilityScore getAbility(String key) {
        return abilityCollection.getAbility(key);
    }

    public void setBonusHP(int bonus) {
        health.updateBonusHP(bonus);
    }

    public void setBonusStam(int bonus) {
        health.updateBonusStam(bonus);
    }

    public void spendSkillPoints(SkillType type, int pointsSpent) {
        skillRecord.spendSkillPoints(type, pointsSpent);
    }

    public boolean hasPseudoSkills() {
        return skillRecord.hasPseudoSkills();
    }

    public Collection<AbilityScore> getAbilities() {
        return abilityCollection.abilities.values();
    }

    public Collection<CharClass> getClasses() {
        return levels.charClasses.values();
    }

    public Health getHealth() {
        return health;
    }

    public void addSkill(Skill skill) {
        skillRecord.addSkill(skill);
    }

    public Skill getSkill(String key) {
        return skillRecord.getSkill(key);
    }

    public void addPseudoSkill(PseudoSkill ps) {
        skillRecord.addPseudoSkill(ps);
    }

    public PseudoSkill getPseudoSkill(String key) {
        return skillRecord.getPseudoSkill(key);
    }

    public void removePseudoSkill(PseudoSkill ps) {
        skillRecord.removePseudoSkill(ps);
    }

    FeatRecord getFeatRecord() {
        return featRecord;
    }

    /************* TextAdventure Functions **********/
    public String toString() {
        String breakline = "-----------------------------------------\n";
        StringBuilder charToString = new StringBuilder(breakline);
        charToString.append("Name: " + charName + "\n");
        charToString.append(levels.toString());
        charToString.append(breakline);
        charToString.append("ABILITIES\n");
        charToString.append(abilityCollection.toString());
        charToString.append(breakline);
        charToString.append("HEALTH\n");
        charToString.append(health.toString());
        charToString.append(breakline);
        charToString.append("SKILL POINTS\n");
        charToString.append(skillRecord.skillPointsToString());
        charToString.append(breakline);
        charToString.append("SKILLS\n");
        charToString.append(skillRecord.skillsToString());
        charToString.append(breakline);
        charToString.append("PSEUDO SKILLS\n");
        charToString.append(skillRecord.pseudoSkillsToString());
        charToString.append(breakline);
        charToString.append("FEAT RESOURCES\n");
        charToString.append(featRecord.featResourcesToString());
        charToString.append(breakline);
        charToString.append("FEATS\n");
        charToString.append(featRecord.featTreesToString());
        return charToString.toString();
    }
}
