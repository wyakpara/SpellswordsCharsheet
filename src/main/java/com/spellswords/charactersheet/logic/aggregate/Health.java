/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spellswords.charactersheet.logic.aggregate;

import com.spellswords.charactersheet.utilities.Columns;

/**
 *
 * @author Didge
 */
public class Health {
    int HP, Stam;
    int HPCon, StamCon;
    int HPBonus, StamBonus;
    int HPStam, StamLvl;
    
    int HPLost, StamLost;
    
    public Health() {
        
    }
    
    public Health(LevelRecord levelRecord, AbilityCollection abilities) {
        HPLost = StamLost = 0;
        calcluateFromLevels(levelRecord, abilities);
    }
    
    public void calcluateFromLevels(LevelRecord levelRecord, AbilityCollection abilities) {
        // Stam is con bonus * level + total HD + bonus
        AbilityScore con = abilities.getAbility("CON");
        StamCon = con.getMod() * levelRecord.getLevel();
        // Stamina is the sum of all hit die
        StamLvl = levelRecord.calculateHD();

        Stam = StamLvl + StamCon + StamBonus;

        // HP is con val + Stam/4 + HPbonus
        HPCon = con.getFinalScore();
        HPStam = Stam/4;
        HP = HPCon + HPStam + HPBonus;
    }
    
    public void updateBonusHP(int bonus) {
        HPBonus = bonus;
        HP = HPCon + HPStam + HPBonus;
    }
    
    public void updateBonusStam(int bonus) {
        StamBonus = bonus;
        Stam = StamLvl + StamCon + StamBonus;
    }
    
    public int getHP() {
        return HP;
    }
    
    public int getStam() {
        return Stam;
    }
    
    public int getCurrentHP() {
        return HP - HPLost;
    }
    
    public int getCurrentStam() {
        return Stam - StamLost;
    }

    /** Textedit functions **/

    public String toString() {
        Columns healthStr = new Columns().addLine("Stam Max", "HP Max", "Bonus Stam", "Bonus HP");
        healthStr = healthStr.addLine(Stam + "", HP + "", StamBonus + "", HPBonus + "");
        return healthStr.toString();
    }

}
