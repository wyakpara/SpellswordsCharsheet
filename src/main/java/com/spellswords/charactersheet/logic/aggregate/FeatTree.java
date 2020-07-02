package com.spellswords.charactersheet.logic.aggregate;

import com.spellswords.charactersheet.utilities.Columns;

import java.util.ArrayList;
import java.util.Collections;

public class FeatTree implements Comparable<FeatTree> {

    ArrayList<Feat> primaries; // Primary feats
    ArrayList<ArrayList<Feat>> secondaries; // Secondary feats

    public FeatTree() {
        primaries = new ArrayList<>();
        secondaries = new ArrayList<>();
    }

    public Feat getRoot() {
        if(primaries.size() == 0) return null;
        return primaries.get(0);
    }

    public int compareTo(final FeatTree compare) {
        // Compare based on strings if archetypes are the same
        if(this.getRoot().getArchetype() == compare.getRoot().getArchetype()) {
            return this.getRoot().compareTo(compare.getRoot());
        }

        // Otherwise compare enums
        return this.getRoot().getArchetype().ordinal() - compare.getRoot().getArchetype().ordinal();
    }

    public boolean addPrimary(Feat primary, int tier) {
        // Check to see if there is a feat at that tier
        if(primaries.get(tier - 1) != null) {
            // There's a feat there!
            return false;
        }

        primaries.add(tier - 1, primary);
        return true;
    }

    public void replacePrimary(Feat primary, int tier) {
        if(primaries.get(tier - 1) != null) {
            primaries.remove(tier - 1);
        }
        addPrimary(primary, tier - 1);
    }

    public boolean removePrimary(int tier) {
        if(tier == 1) return false; // Must replace root or destroy tree altogether
        primaries.remove(tier);
        return true;
    }

    public boolean addSecondary(Feat secondary, int tier) {
        // Check to see if there's a primary at that tier
        if(primaries.get(tier - 1) == null) {
            return false;
        }

        secondaries.get(tier - 1).add(secondary);
        Collections.sort(secondaries.get(tier - 1));
        return true;
    }

    public void removeSecondary(int tier, int index) {
        secondaries.get(tier).remove(index);
    }

    public int getCost() {
        int cost = 0;
        int i, j;
        for(i = 0; i < primaries.size(); i++) {
            cost += 2+i;
        }

        for(i = 0; i < secondaries.size(); i++) {
            ArrayList<Feat> s = secondaries.get(i);
            for(j = 0; j < s.size(); j++) {
                cost += 1+i;
            }
        }
        return cost;
    }

    public int getUCost() {

        int cost = 0;
        int i, j;
        for(Feat f:primaries) {
            cost += f.getUfeatsUsed();
        }

        for(i = 0; i < secondaries.size(); i++) {
            ArrayList<Feat> s = secondaries.get(i);
            for(j = 0; j < s.size(); j++) {
                cost += s.get(j).getUfeatsUsed();
            }
        }
        return cost;
    }

    public ArchetypeType getArchetype() {
        return getRoot().getArchetype();
    }

    public int getNumTiers() {
        return primaries.size();
    }

    /** Textedit Functions **/
    public String toString() {
        Columns featTreeStr = new Columns().addLine("Tier", "Type", "Name", "Description");
        for(int i = 0; i < primaries.size(); i++) {
            Feat prim = primaries.get(i);
            featTreeStr = featTreeStr.addLine(i + "", "P", prim.getName(), prim.getDescription());
            for(int j = 0; j < secondaries.get(i).size(); j++) {
                Feat sec = secondaries.get(i).get(j);
                featTreeStr = featTreeStr.addLine("", "S", sec.getName(), sec.getDescription());
            }
        }
        return featTreeStr.toString();
    }

}
