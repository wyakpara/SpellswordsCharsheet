package com.spellswords.charactersheet.logic.aggregate;

import com.spellswords.charactersheet.utilities.Columns;

import java.io.*;
import java.util.Arrays;
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
        Collection<AbilityScore> abs = abilities.values();
        AbilityScore abArray[] = abs.toArray(new AbilityScore[abs.size()]);
        Arrays.sort(abArray);
        Columns abilsToPrint = AbilityScore.getPartialAbilHeader();
        for(AbilityScore ab:abArray) {
            abilsToPrint = ab.addPartialToColumn(abilsToPrint);
        }

        return abilsToPrint.toString();
    }


}
