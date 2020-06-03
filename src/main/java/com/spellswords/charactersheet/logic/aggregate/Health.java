/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spellswords.charactersheet.logic.aggregate;

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
    
    public Health(Classes classes, AbilityScore con, int hpbonus, int stambonus) {
        HPLost = StamLost = 0;
        setItUp(classes, con, hpbonus, stambonus);
    }
    
    public void setItUp(Classes classes, AbilityScore con, int hpbonus, int stambonus) {
        // Stam is con bonus * level + total HD + bonus
        //StamLvl = con.getMod() * classes.getTotalLevel() + classes.getSumHDs();
        // Stamina is the sum of all hit die
        StamLvl = classes.getSumHDs();
        StamBonus = stambonus;
        Stam = StamLvl + StamBonus;
        
        // HP is con val + Stam/4 + HPbonus
        HPCon = con.getFinalScore();
        HPStam = Stam/4;
        HPBonus = hpbonus;
        HP = HPCon + HPStam + HPBonus;
    }
    
    public void updateBonusHP(int bonus) {
        HP -= HPBonus;
        HPBonus = bonus;
        HP += HPBonus;
    }
    
    public void updateBonusStam(int bonus) {
        Stam -= StamBonus;
        StamBonus = bonus;
        Stam += StamBonus;
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

}
