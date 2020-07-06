/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spellswords.charactersheet.logic.aggregate;

import static com.spellswords.charactersheet.logic.aggregate.FeatType.NONE;

/**
 *
 * @author Didge
 */
public class Feat implements Comparable<Feat> {
    ArchetypeType archetype;
    String name;
    String description;

    FeatType type;

    int ufeatsUsed; // Number of universal feats used for this
    int free; // Number of feats of this that were "free"

    public Feat() {}
    
    public Feat(ArchetypeType archetype) {
        this.archetype = archetype;
        name = "";
        description = "";
    }
    
    public Feat(String newName, ArchetypeType archetype) {
        name = newName;
        this.archetype = archetype;
        description = "";
    }

    public boolean isPlaceholder() {
        if(this.type == NONE) return true;
        return false;
    }

    public static Feat getPlaceholderFeat() {
        Feat placeholder = new Feat("", ArchetypeType.SPECIALIST);
        placeholder.setType(NONE);
        return placeholder;
    }

    public int compareTo(final Feat compare) {
        return this.name.compareTo(compare.name);
    }

    public void setName(String newName) {
        name = newName;
    }
    
    public String getName() {
        return name;
    }
    
    public void setDescription(String newDesc) {
        description = newDesc;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setArchetype(ArchetypeType newArch) {
        archetype = newArch;
    }
    
    public ArchetypeType getArchetype() {
        return archetype;
    }

    public int getUfeatsUsed() {
        return ufeatsUsed;
    }

    public void setUfeatsUsed(int ufeatsUsed) {
        this.ufeatsUsed = ufeatsUsed;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public FeatType getType() {
        return type;
    }

    public void setType(FeatType type) {
        this.type = type;
    }

}
