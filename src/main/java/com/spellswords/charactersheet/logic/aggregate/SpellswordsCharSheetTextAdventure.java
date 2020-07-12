package com.spellswords.charactersheet.logic.aggregate;

//import org.fusesource.jansi.AnsiConsole;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class SpellswordsCharSheetTextAdventure {

    private Scanner scanner;
    private Character character;

    public SpellswordsCharSheetTextAdventure() {
        scanner = new Scanner(System.in);
    }

    public void mainMenu() {
        int input;
//        AnsiConsole.systemInstall();
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
                    ArchetypeType pType = getType();
                    System.out.println("Select a secondary archetype:");
                    ArchetypeType sType = getType();
                    System.out.println("Enter Class Name:");
                    scanner.nextLine();
                    String className = scanner.nextLine();
                    System.out.println("Enter Hit Die:");
                    int HD = readUntilInt();
                    System.out.println("Enter No Skills/Level:");
                    int skillsPerLevel = readUntilInt();

                    CharClass firstClass = new CharClass(className, pType, sType, HD, 1, skillsPerLevel);
                    try {
                        character = new Character(charname, firstClass);
//                        character = new Character(charname, className, pType, sType);
                    } catch(IOException e) {
                        System.out.println("Error: Cannot find init file!");
                    }
                    characterMenu();
                    break;
                case 2:
                    System.out.println("Character name:");
                    String filename = scanner.next();
//                    character = Character.loadCharacterXML(filename);
//                    character = Character.loadCharacter(filename);
                    character = Character.loadCharacterJson(filename + ".json");
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
            System.out.println("2) Edit Classes");
            System.out.println("3) Edit Abilities");
            System.out.println("4) Edit Health");
            System.out.println("5) Edit Skills");
            System.out.println("6) Edit Pseudo Skills");
            System.out.println("7) Edit Feats");
            System.out.println("8) Edit Items");
            System.out.println("9) Save Character");
            System.out.println("10) Quit");
            input = readUntilInt();

            switch (input) {
                case 1:
                    System.out.println(character.toString());
                    break;
                case 2:
                    editClasses();
                    break;
                case 3:
                    editAbilities();
                    break;
                case 4:
                    editHealth();
                    break;
                case 5:
                    editSkills();
                    break;
                case 6:
                    editPseudoSkills();
                    break;
                case 7:
                    editFeats();
                    break;
                case 8:
                    editInventory();
                    break;
                case 9:
//                    Character.saveCharacterXML(character);
                    Character.saveCharacterJson(character);
//                    Character.saveCharacter(character);
                    break;
                default:
                    if(input != 10) System.out.println("Option not supported!");
                    break;
            }
            character.updateAll();
        } while(input != 10);
    }

    private void editClasses() {
        StringBuilder classOpts = new StringBuilder("Select Option:\n");
        Collection<CharClass> classes = character.getClasses();
        CharClass ccs[] = classes.toArray(new CharClass[classes.size()]);
        int i = 1;
        for(CharClass cc:ccs) {
            classOpts.append(i + ") Edit class " + cc.toString() + "\n");
            i++;
        }

        classOpts.append(i++ + ") Add new class\n");
        classOpts.append(i + ") Done Editing");

        int input = 0;
        do {
            System.out.println(classOpts.toString());
            input = readUntilInt();
            int choice;

            if(input <= 0 || input > i) continue;

            if(input == i) return;

            if(input == i - 1) {
                System.out.println("Option not supported yet!");
                continue;
            }

            // Grab the class
            CharClass charClass = ccs[input - 1];
            StringBuilder editClassOpts = new StringBuilder();
            editClassOpts.append("1) Edit Name\n");
            editClassOpts.append("2) Edit Level\n");
            editClassOpts.append("3) Edit HD\n");
            editClassOpts.append("4) Edit Skills Per Level\n");
            editClassOpts.append("5) Done\n");

            do {
                System.out.println(charClass.toString());
                System.out.println(editClassOpts.toString());
                choice = readUntilInt();

                switch (choice) {
                    case 1:
                        System.out.println("New Name:\n");
                        scanner.nextLine();
                        String newName = scanner.nextLine();
                        charClass.setName(newName);
                        break;
                    case 2:
                        System.out.println("New Level:\n");
                        int newLevel = readUntilInt();
                        charClass.setLevel(newLevel);
                        break;
                    case 3:
                        System.out.println("New HD:\n");
                        int newHD = readUntilInt();
                        charClass.setHD(newHD);
                        break;
                    case 4:
                        System.out.println("New Skills per Level:\n");
                        int newSkills = readUntilInt();
                        charClass.setSkill(newSkills);
                        break;
                }
                character.updateAll();
            } while(choice != 5);
        } while (input != i);
    }

    private void editAbilities() {
        System.out.println("Select Ability to edit:");
        StringBuilder abStr = new StringBuilder();
        Collection<AbilityScore> a = character.getAbilities();
        AbilityScore abs[] = a.toArray(new AbilityScore[a.size()]);
        Arrays.sort(abs);
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

            int choice = 0;
            if(input > 0 && input <= i) {
                if(input == i) return;

                if(input == i - 1) {
                    System.out.println("Option not supported yet!");
                    return;
                }
                // Grab the ability
                else {
                    AbilityScore toEdit = abs[input - 1];
                    do {
                        System.out.println("Current:\n" + toEdit.toFullString());
                        System.out.println("1) Enter new base score");
                        System.out.println("2) Edit proficiency");
                        System.out.println("3) Enter new item bonus");
                        System.out.println("4) Enter new enhancement bonus");
                        System.out.println("5) Enter new speciality bonus");
                        System.out.println("6) Enter new temporary bonus");
                        System.out.println("7) Edit another ability");
                        System.out.println("8) Done");

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
                                int profChoice;
                                int before = toEdit.getNumSkillPoints();

                                SkillType type = SkillType.FLEX;
                                switch (toEdit.getName()) {
                                    case "STR":
                                    case "DEX":
                                    case "CON":
                                        type = SkillType.BODY;
                                        break;
                                    case "INT":
                                    case "WIS":
                                    case "CHA":
                                        type = SkillType.MIND;
                                }

                                do {
                                    System.out.println("Select option:");
                                    System.out.println("1) Set level 1 proficiency");
                                    System.out.println("2) Set level 2 proficiency");
                                    System.out.println("3) Set level 3 proficiency");
                                    System.out.println("4) Set level 1 proficiency as free");
                                    System.out.println("5) Set level 2 proficiency as free");
                                    System.out.println("6) Set level 3 proficiency as free");
                                    System.out.println("7) Done");

                                    profChoice = readUntilInt();
                                    switch (profChoice) {
                                        case 1:
                                            toEdit.setProfLevel(1);
                                            break;
                                        case 2:
                                            toEdit.setProfLevel(2);
                                            break;
                                        case 3:
                                            toEdit.setProfLevel(3);
                                            break;
                                        case 4:
                                            toEdit.setProf1Free(true);
                                            break;
                                        case 5:
                                            toEdit.setProf2Free(true);
                                            break;
                                        case 6:
                                            toEdit.setProf3Free(true);
                                            break;
                                    }
                                } while(profChoice != 7);
                                character.spendSkillPoints(type, toEdit.getNumSkillPoints() - before);
                                break;
                            case 3:
                                System.out.println("Enter new " + toEdit.name + " item bonus:");
                                newScore = readUntilInt();
                                toEdit.setItemBonus(newScore);
                                character.updateAll();
                                break;
                            case 4:
                                System.out.println("Enter new " + toEdit.name + " enhancement bonus:");
                                newScore = readUntilInt();
                                toEdit.setEnhanceBonus(newScore);
                                character.updateAll();
                                break;
                            case 5:
                                System.out.println("Enter new " + toEdit.name + " speciality bonus:");
                                newScore = readUntilInt();
                                toEdit.setSpecBonus(newScore);
                                character.updateAll();
                                break;
                            case 6:
                                System.out.println("Enter new " + toEdit.name + " temporary bonus:");
                                newScore = readUntilInt();
                                toEdit.setTempBonus(newScore);
                                character.updateAll();
                                break;
                            // Debug to add index
//                            case 8:
//                                int newIndex = readUntilInt();
//                                toEdit.setIndex(newIndex);
//                                choice = 6;
                        }
                    } while (!(choice == 7 || choice == 8));
                }
            }
            if(choice == 8) break;
        } while (input != i);
    }

    private void editHealth() {
        int choice;
        Health health = character.getHealth();
        System.out.println(health.toString());
        do {
            System.out.println("Choose an option:");
            System.out.println("1) Set bonus stamina");
            System.out.println("2) Set bonus hit points");
            System.out.println("3) Done");

            choice = readUntilInt();

            switch(choice) {
                case 1:
                    System.out.println("Enter new bonus stamina:");
                    health.updateBonusStam(readUntilInt());
                    break;
                case 2:
                    System.out.println("Enter new bonus HP:");
                    health.updateBonusHP(readUntilInt());
                    break;
            }
        } while(choice != 3);
    }

    private void editSkills() {
        String skillname;
        StringBuilder skillOption = new StringBuilder();
        skillOption.append("Select an option:\n");
        skillOption.append("1) Edit Proficiency\n");
        skillOption.append("2) Edit Take\n");
        skillOption.append("3) Edit Min\n");
        skillOption.append("4) Edit Item Bonus\n");
        skillOption.append("5) Edit Enhancement Bonus\n");
        skillOption.append("6) Edit Specialization Bonus\n");
        skillOption.append("7) Edit Temporary\n");
        skillOption.append("8) Edit Focus\n");
        skillOption.append("9) Activate Subskill\n");
        skillOption.append("10) Set Free Proficiency\n");
        skillOption.append("11) Edit another skill\n");
        skillOption.append("12) Done");

        do {
            System.out.println("Enter a skill to edit:");
            scanner.nextLine();
            skillname = scanner.nextLine();

            Skill toEdit = character.getSkill(skillname);
            if(toEdit == null) {
                System.out.println("No such skill, try again");
                continue;
            }

            int choice;
            do {
                System.out.println(toEdit.toFullString());

                System.out.println(skillOption.toString());
                choice = readUntilInt();
                int before;

                switch (choice) {
                    // Proficiency
                    case 1:
                        int newProf;
                        do {
                            System.out.println("Enter new proficiency level between 0 and 3:");
                            newProf = readUntilInt();
                        } while (newProf < 0 || newProf > 3);
                        character.changeSkillProficiency(skillname, newProf);
                        break;
                    // Take
                    case 2:
                        int newTake;
                        do {
                            System.out.println("Enter new Take level between 0 and 10:");
                            newTake = readUntilInt();
                        } while (newTake < 0 || newTake > 10);
                        before = toEdit.getTotalSkillPoints();
                        toEdit.setTake(newTake);
                        character.spendSkillPoints(toEdit.getType(), toEdit.getTotalSkillPoints() - before);
                        break;
                    // Min
                    case 3:
                        int newMin;
                        do {
                            System.out.println("Enter new Minimum level between 0 and 8:");
                            newMin = readUntilInt();
                        } while (newMin < 0 || newMin > 8);
                        before = toEdit.getTotalSkillPoints();
                        toEdit.setMin(newMin);
                        character.spendSkillPoints(toEdit.getType(), toEdit.getTotalSkillPoints() - before);
                        break;
                    // Item
                    case 4:
                        System.out.println("Enter new item bonus:");
                        int newItemBonus = readUntilInt();
                        toEdit.setItemBonus(newItemBonus);
                    // Enhance
                    case 5:
                        System.out.println("Enter new enhancement bonus:");
                        int newEnhanceBonus = readUntilInt();
                        toEdit.setEnhanceBonus(newEnhanceBonus);
                    // Spec
                    case 6:
                        System.out.println("Enter new specialization bonus:");
                        int newSpecBonus = readUntilInt();
                        toEdit.setSpecBonus(newSpecBonus);
                    // Temp
                    case 7:
                        System.out.println("Enter new temporary bonus:");
                        int newTempBonus = readUntilInt();
                        toEdit.setTempBonus(newTempBonus);
                    // Focus
                    case 8:
                        int newFocus;
                        do {
                            System.out.println("Enter new Focus level between 0 and 4:");
                            newFocus = readUntilInt();
                        } while (newFocus < 0 || newFocus > 4);
                        before = toEdit.getTotalSkillPoints();
                        toEdit.setFocus(newFocus);
                        character.spendSkillPoints(toEdit.getType(), toEdit.getTotalSkillPoints() - before);
                        break;
                    // Subskill
                    case 9:
                        before = toEdit.getTotalSkillPoints();
                        editSubSkills(toEdit);
                        character.spendSkillPoints(toEdit.getType(), toEdit.getTotalSkillPoints() - before);
                        break;
                    // Free
                    case 10:
                        int freeChoice;
                        before = toEdit.getTotalSkillPoints();
                        do {
                            System.out.println("Select a proficiency level to be free:");
                            System.out.println("1) Level 1");
                            System.out.println("2) Level 2");
                            System.out.println("3) Level 3");
                            System.out.println("4) None, reset");
                            System.out.println("5) Done");

                            freeChoice = readUntilInt();

                            switch (freeChoice) {
                                case 1:
                                    toEdit.setProf1Free(true);
                                    break;
                                case 2:
                                    toEdit.setProf2Free(true);
                                    break;
                                case 3:
                                    toEdit.setProf3Free(true);
                                    break;
                                case 4:
                                    toEdit.setProf1Free(false);
                                    toEdit.setProf2Free(false);
                                    toEdit.setProf3Free(false);
                            }
                        } while(freeChoice != 5);

                        character.spendSkillPoints(toEdit.getType(), toEdit.getTotalSkillPoints() - before);
                        break;
                    // Another skill
                    case 11:
                        skillname = null;
                        break;
                    case 12:
                        return;
                }
            } while (choice != 11);
        } while (skillname == null);
    }

    public void editSubSkills(Skill toEdit) {
        String choice = null;

        do {
            System.out.println("Subskills avialable for " + toEdit.getName() + ":");
            System.out.print(toEdit.subSkillsToString(true));
            System.out.println("\nEnter subskill name to edit or 'Q' to quit:");
            scanner.nextLine();
            choice = scanner.nextLine();

            if(choice.toUpperCase().equals("Q")) return;

            SubSkill subSkill = toEdit.getSubSkills().get(choice);
            if(subSkill == null) {
                System.out.println("No subskill by that name, try again...");
                continue;
            }

            int selection;

            do {
                System.out.println("1) Activate Subskill '" + subSkill.name + "' for " + subSkill.getCost() + " Skill points");
                System.out.println("2) Activate Subskill '" + subSkill.name + "' for free");
                System.out.println("3) Deactivate Subskill '" + subSkill.name + "'");
                selection = readUntilInt();

                switch (selection) {
                    case 1:
                        subSkill.setActive(true);
                        subSkill.setFree(false);
                        break;
                    case 2:
                        subSkill.setActive(true);
                        subSkill.setFree(true);
                        break;
                    case 3:
                        subSkill.setActive(false);
                        subSkill.setFree(false);
                        break;
                }
            } while(selection < 1 || selection > 3);
        } while(true);
    }

    private void editPseudoSkills() {
        boolean hasPseudoSkills = character.hasPseudoSkills();

        if(!hasPseudoSkills) {
            System.out.println(character.charName + " has no pseudoskills currently.");
        }

        int selection;
        String key;
        PseudoSkill toEdit = null;
        boolean gotoEdit = false;

        do {
            System.out.println("Select an option:");
            System.out.println("1) Done");
            System.out.println("2) Add Pseudoskill");
            if (hasPseudoSkills) {
                System.out.println("3) Edit Pseudoskill");
            }

            selection = readUntilInt();

            switch (selection) {
                case 1:
                    return;
                case 2:
                    System.out.println("Enter Pseudoskill name:");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    System.out.println("Enter Pseudoskill type (i.e. Language, Weapon, etc):");
//                    scanner.nextLine();
                    String type = scanner.nextLine();

                    int skillTypeChoice;
                    SkillType skillType = SkillType.FLEX;
                    do {
                        System.out.println("Select a skill type for Pseudoskill:");
                        System.out.println("1) Mind");
                        System.out.println("2) Body");
                        System.out.println("3) Flex");

                        skillTypeChoice = readUntilInt();

                        switch (skillTypeChoice) {
                            case 1:
                                skillType = SkillType.MIND;
                                break;
                            case 2:
                                skillType = SkillType.BODY;
                                break;
                            case 3:
                                skillType = SkillType.FLEX;
                                break;
                        }
                    } while(skillTypeChoice < 1 || skillTypeChoice > 3);

                    toEdit = new PseudoSkill(name, type, skillType);
                    character.addPseudoSkill(toEdit);
                    hasPseudoSkills = true;
                    gotoEdit = true;
                case 3:
                    if(!hasPseudoSkills) continue;
                    if(!gotoEdit) {
                        System.out.println("Enter name of Pseudoskill to edit:");
                        scanner.nextLine();
                        key = scanner.nextLine();

                        toEdit = character.getPseudoSkill(key);

                        if (toEdit == null) {
                            System.out.println("No such Pseudoskill...");
                            continue;
                        }
                    } else {
                        if(toEdit == null) {
                            System.out.println("Well that's an error");
                            continue;
                        }
                        // Reset it
                        gotoEdit = false;
                    }

                    int choice;

                    do {
                        System.out.println("1) Remove '" + toEdit.getName() + "' from Pseudoskills");
                        System.out.println("2) Edit '" + toEdit.getName() + "' proficiency");
                        System.out.println("3) Make '" + toEdit.getName() + "' free");
                        System.out.println("4) Done");

                        choice = readUntilInt();
                        int before;

                        switch (choice) {
                            case 1:
                                character.removePseudoSkill(toEdit);
                                choice = 4;
                                hasPseudoSkills = character.hasPseudoSkills();
                                break;
                            case 2:
                                int newProf;
                                before = toEdit.getNumSkillPoints();
                                do {
                                    System.out.println("Enter new proficiency between 0 and 3:");
                                    newProf = readUntilInt();
                                } while(newProf < 0 || newProf > 3);
                                toEdit.setProfLevel(newProf);
                                character.spendSkillPoints(toEdit.getSkillType(), toEdit.getNumSkillPoints() - before);
                                break;
                            case 3:
                                int freeChoice;
//                                before = toEdit.getNumSkillPoints();
                                do {
                                    System.out.println("Select a proficiency level to be free:");
                                    System.out.println("1) Level 1");
                                    System.out.println("2) Level 2");
                                    System.out.println("3) Level 3");
                                    System.out.println("4) None, reset");
                                    System.out.println("5) Done");

                                    freeChoice = readUntilInt();

                                    switch (freeChoice) {
                                        case 1:
                                            toEdit.setProf1Free(true);
                                            break;
                                        case 2:
                                            toEdit.setProf2Free(true);
                                            break;
                                        case 3:
                                            toEdit.setProf3Free(true);
                                            break;
                                        case 4:
                                            toEdit.setProf1Free(false);
                                            toEdit.setProf2Free(false);
                                            toEdit.setProf3Free(false);
                                    }
                                } while(freeChoice != 5);

//                                character.spendSkillPoints(toEdit.getSkillType(), toEdit.getNumSkillPoints() - before);
                                break;
                        }
                    } while(choice != 4);

            }
        } while(selection != 1);

    }

    private void editFeats() {
        int choice;
        FeatRecord record = character.getFeatRecord();

        do {
            System.out.println("Select an option:");
            System.out.println("1) View/edit all feat trees");
            System.out.println("2) View feat resources");
            System.out.println("3) Done");

            choice = readUntilInt();

            switch (choice) {
                case 1:
                    editFeatTrees(record);
                    break;
                case 2:
                    System.out.println("Feat resources:");
                    System.out.println(record.featResourcesToString());
                    break;
            }
        } while (choice != 3);

    }

    private void editFeatTrees(FeatRecord record) {
        int choice;
        int treeIndex;
        int numTrees = record.getNumTrees();
        FeatTree treeToEdit;

        // Special - if getNumTrees is 0, create a new dummy tree
        if(numTrees == 0) {
            System.out.println("No feat trees, add your first feat!");

            int at;
            ArchetypeType arch = ArchetypeType.MARTIAL;
            boolean done;
            do {
                done = true;
                System.out.println("Enter archetype of first feat:");
                System.out.println("1) Martial");
                System.out.println("2) Specialist");
                System.out.println("3) Psionic");
                System.out.println("4) Cancel");

                at = readUntilInt();

                switch (at) {
                    case 1:
                        arch = ArchetypeType.MARTIAL;
                        break;
                    case 2:
                        arch = ArchetypeType.SPECIALIST;
                        break;
                    case 3:
                        arch = ArchetypeType.PSIONIC;
                        break;
                    case 4:
                        return;
                    default:
                        done = false;
                        System.out.println("Invalid choice, try again...");
                }

            } while(!done);

            System.out.println("Enter tier:");
            int tier = readUntilInt();

            FeatTree tree = new FeatTree();
            record.addNewTree(newFeatWizard(tree, arch, tier), tier);
            numTrees = record.getNumTrees();
            record.spendFeats();

        }

        do {
            System.out.println("FEAT TREE EDITOR");
            System.out.println("Select feat tree to edit, or 0 to quit:");
            System.out.print(record.featTreesToString());
            System.out.println((numTrees + 1) + ") Add new tree from primary feat");

            choice = readUntilInt();

            if(choice > 0 && choice <= numTrees) {
                treeIndex = choice - 1;
                treeToEdit = record.getTreeAt(treeIndex);

                int tier;
                int featChoice;
                do {
                    System.out.println(treeToEdit.toString());
                    System.out.println("1) Edit existing feat");
                    System.out.println("2) Add feat");
                    System.out.println("3) Remove tree");
                    System.out.println("4) Done");

                    featChoice = readUntilInt();

                    // 1 Search feat in tree, find its indices and open for editing
                    if(featChoice == 1) {
                        String search;
                        Feat found;
                        boolean first = true;
                        do {
                            System.out.print("Enter case-sensitive feat to edit, or 'q' to quit: ");
                            if(first) {
                                scanner.nextLine();
                                first = false;
                            }
                            search = scanner.nextLine();
                            if(search.equals("q")) {
                                break;
                            }

                            found = treeToEdit.findFeat(search);

                            if(found == null) {
                                System.out.println("No feat by that name in this tree! Try again.");
                                continue;
                            }

                            first = true;

                            if(null == editFeat(found)) {
                                // Remove feat from tree
                                treeToEdit.removeFeat(found);
                                System.out.println("Feat removed");
                            }
                            found = null;

                        } while (found == null);

                    }
                    else if(featChoice == 2) {
                        newFeatWizard(treeToEdit);
                    }
                    else if(featChoice == 3) {
                        record.removeTree(treeToEdit);
                        System.out.println("Feat tree removed");
                    }
                    else if(featChoice != 4){
                        System.out.println("Unrecognized choice");
                    }

                    numTrees = record.getNumTrees();

                } while(featChoice != 4);

            } else if (choice == numTrees + 1) {
                // New tree from primary feat
                int at;
                ArchetypeType arch = ArchetypeType.MARTIAL;
                boolean done;
                do {
                    done = true;
                    System.out.println("Enter archetype of first feat:");
                    System.out.println("1) Martial");
                    System.out.println("2) Specialist");
                    System.out.println("3) Psionic");
                    System.out.println("4) Cancel");

                    at = readUntilInt();

                    switch (at) {
                        case 1:
                            arch = ArchetypeType.MARTIAL;
                            break;
                        case 2:
                            arch = ArchetypeType.SPECIALIST;
                            break;
                        case 3:
                            arch = ArchetypeType.PSIONIC;
                            break;
                        case 4:
                            return;
                        default:
                            done = false;
                            System.out.println("Invalid choice, try again...");
                    }

                } while(!done);

                System.out.println("Enter tier:");
                int tier = readUntilInt();
                FeatTree tree = new FeatTree();
                record.addNewTree(newFeatWizard(tree, arch, tier), tier);

            }
            numTrees = record.getNumTrees();
            record.spendFeats();
        } while (choice != 0);
    }

    private Feat editFeat(Feat feat) {
        int choice;

        do {
            System.out.println("1) Edit Name");
            System.out.println("2) Edit Description");
            System.out.println("3) Edit Number of Universal Feats used");
            System.out.println("4) Edit Number of Free Feats used");
            System.out.println("5) Remove Feat from tree");
            System.out.println("6) Done");

            choice = readUntilInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter new name: ");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    feat.setName(name);
                    break;
                case 2:
                    System.out.print("Enter new description: ");
                    scanner.nextLine();
                    String description = scanner.nextLine();
                    feat.setDescription(description);
                    break;
                case 3:
                    System.out.println("Enter number of universal feats to spend:");
                    int numUfeats = readUntilInt();
                    feat.setUfeatsUsed(numUfeats);
                    break;
                case 4:
                    System.out.println("Enter number of free feats to spend:");
                    int numFreeFeats = readUntilInt();
                    feat.setFree(numFreeFeats);
                    break;
                case 5:
                    return null;
            }
        } while (choice != 6);
        return feat;
    }

    private Feat newFeatWizard(FeatTree featTree, ArchetypeType arch, int tier) {
        System.out.println("Enter feat name:");
        scanner.nextLine();
        String featName = scanner.nextLine();
        Feat addme = new Feat(featName, arch);

        System.out.println("Enter feat description:");
//        scanner.nextLine();
        String featDesc = scanner.nextLine();
        addme.setDescription(featDesc);

        System.out.println("Enter number of universal feats to use:");
        int numUfeats = readUntilInt();
        addme.setUfeatsUsed(numUfeats);

        System.out.println("Enter number of free feats to use:");
        int numFfeats = readUntilInt();
        addme.setFree(numFfeats);

        int ps;
        do {
            System.out.println("Enter feat type:");
            System.out.println("1) Primary");
            System.out.println("2) Secondary");

            ps = readUntilInt();
        } while(!(ps == 2 || ps == 1));

        if(ps == 1) {
            featTree.addPrimary(addme, tier);
        } else {
            featTree.addSecondary(addme, tier);
        }
        return addme;
    }

    private Feat newFeatWizard(FeatTree featTree) {
        System.out.println("Enter tier:");
        int tier = readUntilInt();
        return newFeatWizard(featTree, featTree.getArchetype(), tier);
    }

    private void editInventory() {
        // Add item
        // Weapon or armor?
        // Weapon and armor wizards
        // Make weapon class
        // Make armor class
        System.out.println("Inventory not supported yet");
    }

    /**** HELPER FUNCTIONS *****/

    private ArchetypeType getType() {
        int input;
        do {
            System.out.println("1) Martial");
            System.out.println("2) Specialist");
            System.out.println("3) Psionic");

            input = readUntilInt();

            switch(input) {
                case 1:
                    return ArchetypeType.MARTIAL;
                case 2:
                    return ArchetypeType.SPECIALIST;
                case 3:
                    return ArchetypeType.PSIONIC;
            }
        } while(input < 1 || input > 3);
        return ArchetypeType.SPECIALIST;
    }

    private int readUntilInt() {
        int tries = 3;
        while(scanner.hasNext()) {
            if(scanner.hasNextInt()) {
                return scanner.nextInt();
            }
            tries--;
            if(tries == 0) {
                System.out.println("Error reading integer input");
                return 0;
            }
            scanner.next();
        }
        return 0;
    }
}
