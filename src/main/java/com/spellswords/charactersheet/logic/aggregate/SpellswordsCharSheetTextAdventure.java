package com.spellswords.charactersheet.logic.aggregate;

import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;
//import org.fusesource.jansi.AnsiConsole;

public class SpellswordsCharSheetTextAdventure {

    private Scanner scanner;
    private Character character;

    public SpellswordsCharSheetTextAdventure() {
        scanner = new Scanner(System.in);
    }

    public void mainMenu() {
        int input;
        System.out.println("Welcome to Spellswords Charsheet editor: TextAdventure Edition");
        do {
            System.out.println("--------------------------------------------------------------");
            System.out.println("Select an option:");
            System.out.println("1) New Character");
            System.out.println("2) Load Character");
            System.out.println("3) Quit");

            input = readUntilInt();

            switch (input) {
                case 1:
                    System.out.println("\nEnter Character Name: ");
                    scanner.nextLine();
                    String charname = scanner.nextLine();
                    System.out.println("Select a primary archetype:");
                    CharacterType pType = getType();
                    System.out.println("Select a secondary archetype:");
                    CharacterType sType = getType();
                    System.out.println("Enter Class Name:");
                    scanner.nextLine();
                    String className = scanner.nextLine();
                    try {
                        character = new Character(charname, className, pType, sType);
                    } catch(IOException e) {
                        System.out.println("Error: Cannot find init file!");
                    }
                    characterMenu();
                    break;
                case 2:
                    System.out.println("Character file path:");
                    String filename = scanner.next();
//                    character = Character.loadCharacterXML(filename);
                    character = Character.loadCharacter(filename);

                    if(character == null) {
                        System.out.println("Unable to load character!");
                        continue;
                    }
                    characterMenu();
                    break;
            }
        } while (input != 3);
    }

    private void characterMenu() {
        System.out.println("Character Menu");
        int input;
        do {
            System.out.println("--------------------------------------------------------------");
            System.out.println("Select an option:");
            System.out.println("1) View Character");
            System.out.println("2) Edit Abilities");
            System.out.println("3) Edit Skills");
            System.out.println("4) Edit Feats");
            System.out.println("5) Edit Items");
            System.out.println("8) Save Character");
            System.out.println("9) Quit");
            input = readUntilInt();

            switch (input) {
                case 1:
                    System.out.println(character.toString());
                    break;
                case 2:
                    editAbilities();
                    break;
                case 3:
                    editSkills();
                    break;
                case 8:
//                    Character.saveCharacterXML(character);
                    Character.saveCharacter(character);
                    break;
                default:
                    if(input != 9) System.out.println("Option not supported!");
                    break;
            }
        } while(input != 9);
    }

    private void editAbilities() {
        System.out.println("Select Ability to edit:");
        StringBuilder abStr = new StringBuilder();
        Collection<AbilityScore> a = character.getAbilities();
        AbilityScore abs[] = a.toArray(new AbilityScore[a.size()]);
        int i = 1;
        for(AbilityScore ab:abs) {
            abStr.append(i + ") " + ab.getName() + "\n");
            i++;
        }
        abStr.append(i + ") Add New Ability\n");
        i++;
        abStr.append(i + ") Done\n");

        int input;
        do {
            System.out.println(abStr.toString());
            input = readUntilInt();

            if(input > 0 && input <= i) {
                if(input == i) return;

                if(input == i - 1) {
                    System.out.println("Option not supported yet!");
                    return;
                }
                // Grab the ability
                else {
                    AbilityScore toEdit = abs[input - 1];
                    int choice
                    do {
                        System.out.println("Current:\n" + toEdit.toFullString());
                        System.out.println("1) Enter new base score");
                        System.out.println("2) Enter new item bonus");
                        System.out.println("3) Enter new enhancement bonus");
                        System.out.println("4) Enter new speciality bonus");
                        System.out.println("5) Enter new temporary bonus");
                        System.out.println("6) Done");

                        choice = readUntilInt();

                        int newScore;
                        switch (choice) {
                            case 1:
                                System.out.println("Enter new " + toEdit.name + " score:");
                                newScore = readUntilInt();
                                toEdit.setBaseScore(newScore);
                                character.updateAll();
                                break;
                            case 2:
                                System.out.println("Enter new " + toEdit.name + " item bonus:");
                                newScore = readUntilInt();
                                toEdit.setItemBonus(newScore);
                                character.updateAll();
                                break;
                            case 3:
                                System.out.println("Enter new " + toEdit.name + " enhancement bonus:");
                                newScore = readUntilInt();
                                toEdit.setEnhanceBonus(newScore);
                                character.updateAll();
                                break;
                            case 4:
                                System.out.println("Enter new " + toEdit.name + " speciality bonus:");
                                newScore = readUntilInt();
                                toEdit.setSpecBonus(newScore);
                                character.updateAll();
                                break;
                            case 5:
                                System.out.println("Enter new " + toEdit.name + " temporary bonus:");
                                newScore = readUntilInt();
                                toEdit.setTempBonus(newScore);
                                character.updateAll();
                        }
                    } while (choice != 6);
                }
            }
        } while (input != i);
    }

    private void editSkills() {

    }

    /**** HELPER FUNCTIONS *****/

    private CharacterType getType() {
        int input;
        do {
            System.out.println("1) Martial");
            System.out.println("2) Specialist");
            System.out.println("3) Psionic");

            input = readUntilInt();

            switch(input) {
                case 1:
                    return CharacterType.MARTIAL;
                case 2:
                    return CharacterType.SPECIALIST;
                case 3:
                    return CharacterType.PSIONIC;
            }
        } while(input < 1 || input > 3);
        return CharacterType.SPECIALIST;
    }

    private int readUntilInt() {
        while(scanner.hasNext()) {
            if(scanner.hasNextInt()) {
                return scanner.nextInt();
            }
            scanner.next();
        }
        return 0;
    }
}
