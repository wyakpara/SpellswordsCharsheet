/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spellswordscharactersheet_v3;
import java.util.HashMap;

/**
 *
 * @author Didge
 */
public class Feats {
    HashMap<Integer, Feat> feats;
    
    public Feats() {
        feats = new HashMap<>();
    }
    
    public Feats(String[] names, String[] archetype, String[] descrs, int[] tier, int[] mults, int[] nums) {
        feats = new HashMap<>();
        for(int i = 0; i < names.length; i++) {
            feats.put(nums[i], new Feat(names[i], archetype[i], descrs[i], tier[i], mults[i], nums[i]));
        }
    }
    
    public Feats(Feat[] far) {
        feats = new HashMap<>();
        for(Feat f: far) {
            feats.put(f.getNum(), f);
        }
    }
    
    public Feat getFeatByIndex(int index) {
        return feats.get(index);
    }
    
    public int getNumFeats() {
        return feats.size();
    }
}
