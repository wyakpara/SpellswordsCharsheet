/*
 * This object describes a spellswords character
 * 
 * It should encapsulate all aspects of the character - from ability scores and 
 * beyond - and should be the gateway used by the gui.
 */
package com.spellswords.charactersheet.logic.aggregate;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
//import

/**
 *
 * @author Aric Hudson
 */
public class Character implements Serializable {
    public String charName;
    private LevelRecord levels;
    private SkillRecord skillRecord;
    private AbilityCollection abilityCollection;

    public Character(String charname, CharacterType primeType, CharacterType secondType) throws FileNotFoundException, IOException {
        this.charName = charname;
        levels = new LevelRecord(new CharClass("", primeType, secondType, 6, 1, 4));
        abilityCollection = new AbilityCollection();
        generateStandardAbilities();
        skillRecord = new SkillRecord(abilityCollection, levels);
    }

    public Character(String charname, String className, CharacterType primeType, CharacterType secondType) throws FileNotFoundException, IOException {
        this.charName = charname;
        levels = new LevelRecord(new CharClass(className, primeType, secondType, 6, 1, 4));
        abilityCollection = new AbilityCollection();
        generateStandardAbilities();
        skillRecord = new SkillRecord(abilityCollection, levels);
    }

    public void generateStandardAbilities() {
        AbilityScore str = new AbilityScore("STR", 10);
        abilityCollection.add(str);
        AbilityScore dex = new AbilityScore("DEX", 10);
        abilityCollection.add(dex);
        AbilityScore con = new AbilityScore("CON", 10);
        abilityCollection.add(con);
        AbilityScore ing = new AbilityScore("INT", 10);
        abilityCollection.add(ing);
        AbilityScore wis = new AbilityScore("WIS", 10);
        abilityCollection.add(wis);
        AbilityScore cha = new AbilityScore("CHA", 10);
        abilityCollection.add(cha);
    }

    public static void saveCharacterXML(Character character) {
        try {
            File file = new File(character.charName + ".xml");
            JAXBContext jaxbc= JAXBContext.newInstance(Character.class);
            Marshaller jaxbcM = jaxbc.createMarshaller();

            jaxbcM.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbcM.marshal(character, file);
        } catch (JAXBException e) {
            System.out.println("Error saving character!");
        }
    }

//    public static void saveCharacterJson(Character character) {
//        Gson();
//    }

    public static Character loadCharacterXML(String filename) {
        try {
            File file = new File("filename");
            JAXBContext jaxbc = JAXBContext.newInstance(Character.class);

            Unmarshaller jaxbcUm = jaxbc.createUnmarshaller();
            Character character = (Character) jaxbcUm.unmarshal(file);

            return character;
        } catch (JAXBException e) {
            System.out.println("Error loading character!");
            return null;
        }
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

    public void updateAll() {
        abilityCollection.updateAbilities();
        skillRecord.updateSkills();
    }

    public void addAbility(AbilityScore ability) {
        abilityCollection.add(ability);
    }

    public AbilityScore getAbility(String key) {
        return abilityCollection.getAbility(key);
    }

    public Collection<AbilityScore> getAbilities() {
        return abilityCollection.abilities.values();
    }

    public void addSkill(Skill skill) {
        skillRecord.addSkill(skill);
    }

    public Skill getSkill(String key) {
        return skillRecord.getSkill(key);
    }

    /************* TextAdventure Functions **********/
    public String toString() {
        String breakline = "-----------------------------------------\n";
        StringBuilder charToString = new StringBuilder(breakline);
        charToString.append("Name: " + charName + "\n");
        charToString.append(levels.toString() + "\n");
        charToString.append(breakline);
        charToString.append("ABILITIES\n");
        charToString.append(abilityCollection.toString() + "\n");
        charToString.append(breakline);
        charToString.append("SKILLS\n");
        charToString.append(skillRecord.toString() + "\n");
        return charToString.toString();
    }
}
