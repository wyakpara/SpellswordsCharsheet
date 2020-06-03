package com.spellswords.charactersheet.logic.aggregate;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;

public class AbilityCollection implements Serializable {
    protected HashMap<String, AbilityScore> abilities;
    private static final String defaultPath = "defaultAbilities.csv";

    public AbilityCollection() {
        abilities = new HashMap<>();
    }

    public void readAbilitiesFromFile(String filepath) throws FileNotFoundException, IOException {
        BufferedReader csvReader = new BufferedReader((new FileReader(filepath)));
        String row;
        while((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            for(String s:data) {
                add(new AbilityScore(s, 0));
            }
        }
    }

    public void updateAbilities() {
        Collection<AbilityScore> abs = abilities.values();
        for(AbilityScore ab:abs) {
            ab.update();
        }
    }

    public void add(AbilityScore ability) {
        abilities.put(ability.name, ability);
    }

    public AbilityScore getAbility(String name) {
        return abilities.get(name);
    }

    /************* TextAdventure Functions **********/

    public String toString() {
        StringBuilder abStr = new StringBuilder("Name\tScore\tMod\n");
        Collection<AbilityScore> abs = abilities.values();
        for(AbilityScore ab:abs) {
            abStr.append(ab.getName() + "\t\t");
            abStr.append(ab.getFinalScore() + "\t\t");
            abStr.append(ab.getAbilityBonus() + "\n");
        }

        return abStr.toString();
    }


}
