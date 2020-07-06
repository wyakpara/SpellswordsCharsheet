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
        // Find first primary slot that isn't null
        for(Feat p:primaries) {
            if(!p.isPlaceholder()) return p;
        }

        // Or null if they all are.
        return null;
    }

    public int compareTo(final FeatTree compare) {
        // Compare based on strings if archetypes are the same
        if(this.getRoot().getArchetype() == compare.getRoot().getArchetype()) {
            return this.getRoot().compareTo(compare.getRoot());
        }

        // Otherwise compare enums
        return this.getRoot().getArchetype().ordinal() - compare.getRoot().getArchetype().ordinal();
    }

    private void resolveTier(ArrayList<Feat> list, int tier) {
        while(list.size() < tier) {
            list.add(Feat.getPlaceholderFeat());
        }
    }

    private void resolveTiers(ArrayList<ArrayList<Feat>> list, int tier) {
        while(list.size() < tier) {
            list.add(new ArrayList<>(1));
        }
    }

    public boolean addPrimary(Feat primary, int tier) {
        resolveTier(primaries, tier);

        // Check to see if there are any feats at all
        if(primaries.size() > 0) {
            // Check to see if there is a feat at that tier
            if (primaries.get(tier - 1).isPlaceholder()) {
                primaries.remove(tier - 1);
            } else if (primaries.get(tier - 1) != null) {
                // There's a feat there!
                return false;
            }
        }
        primary.setType(FeatType.PRIMARY);

        primaries.add(tier - 1, primary);
        return true;
    }

    public void replacePrimary(Feat primary, int tier) {
        if(primaries.size() >= tier && primaries.get(tier - 1) != null) {
            primaries.remove(tier - 1);
        }
        primary.setType(FeatType.PRIMARY);
        addPrimary(primary, tier - 1);
    }

    public boolean removePrimary(int tier) {
        if(tier == 1) return false; // Must replace root or destroy tree altogether
        if(primaries.size() < tier) return false;
        primaries.remove(tier);
        return true;
    }

    public boolean addSecondary(Feat secondary, int tier) {

        resolveTiers(secondaries, tier);
        // Check to see if there are any secondaries there at all
        if(secondaries.size() == 0) {
            secondaries.add(new ArrayList<>(1));
        }
        // Check to see if there's a primary at that tier
        else {
            if (primaries.get(tier - 1) == null) {
                return false;
            }
        }
        secondary.setType(FeatType.SECONDARY);
        secondaries.get(tier - 1).add(secondary);
        Collections.sort(secondaries.get(tier - 1));
        return true;
    }

    public void removeSecondary(int tier, int index) {
        if(secondaries.size() < tier) return;
        secondaries.get(tier).remove(index);
    }

    public int getCost() {
        int cost = 0;
        int i, j;
        for(i = 0; i < primaries.size(); i++) {
            if(primaries.get(i).isPlaceholder()) continue;
            cost += 2+i;
            cost -= primaries.get(i).getFree();
        }

        for(i = 0; i < secondaries.size(); i++) {
            ArrayList<Feat> s = secondaries.get(i);
            for(j = 0; j < s.size(); j++) {
                if(s.get(j).isPlaceholder()) continue;
                cost += 1+i;
                cost -= s.get(j).getFree();
            }
        }

        return cost;
    }

    public int getUCost() {

        int cost = 0;
        int i, j;
        for(Feat f:primaries) {
            if(f.isPlaceholder()) continue;
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

    public Feat findFeat(String search) {
        // Search Primary first
        for(Feat f:primaries) {
            if(f.getName().equals(search)) {
                return f;
            }
        }

        for(ArrayList<Feat> af:secondaries) {
            for(Feat f:af) {
                if(f.getName().equals(search)) {
                    return f;
                }
            }
        }

        // Didn't find it, return
        return null;
    }

    public void removeFeat(Feat feat) {
        // We don't really care where it is, just remove it from either list.
        primaries.remove(feat);
        for(ArrayList<Feat> fs:secondaries) {
            fs.remove(feat);
        }
    }

    /** Textedit Functions **/
    public String toString() {
        Columns featTreeStr = getHeader();
        featTreeStr = compoundString(featTreeStr);
        return featTreeStr.toString();
    }

    public static Columns getHeader() {
        return new Columns().addLine("", "Tier", "Type", "Name", "Description");
    }

    public Columns compoundString(Columns start) {
        for(int i = 0; i < primaries.size(); i++) {
            Feat prim = primaries.get(i);
            if(prim.isPlaceholder()) continue;
            start = start.addLine("", (i + 1) + "", "P", prim.getName(), prim.getDescription());
            if(secondaries.size() > i) {
                for (int j = 0; j < secondaries.get(i).size(); j++) {
                    Feat sec = secondaries.get(i).get(j);
                    if(sec.isPlaceholder()) continue;
                    start = start.addLine("", "", "S", sec.getName(), sec.getDescription());
                }
            }
        }
        return start;
    }

    public Columns compoundString(Columns start, int index) {
        for(int i = 0; i < primaries.size(); i++) {
            Feat prim = primaries.get(i);
            if(prim.isPlaceholder()) continue;
            start = start.addLine(index + ")", (i + 1) + "", "P", prim.getName(), prim.getDescription());
            if(secondaries.size() > i) {
                for (int j = 0; j < secondaries.get(i).size(); j++) {
                    Feat sec = secondaries.get(i).get(j);
                    if(sec.isPlaceholder()) continue;
                    start = start.addLine("", "", "S", sec.getName(), sec.getDescription());
                }
            }
        }
        return start;
    }

}
