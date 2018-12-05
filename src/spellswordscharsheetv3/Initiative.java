/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spellswordscharactersheet_v3;

/**
 *
 * @author Didge
 */
public class Initiative {
    int init, dexInit, bonusInit, tempInit;
    
    public Initiative() {
        dexInit = bonusInit = tempInit = 0;
    }
    
    public Initiative(AbilityScore dex) {
        bonusInit = tempInit = 0;
        dexInit = dex.getMod();
        update();
    }
    
    public void setDex(AbilityScore dex) {
        dexInit = dex.getMod();
        update();
    }
    
    public void setBonus(int newBonus) {
        bonusInit = newBonus;
        update();
    }
    
    public int getBonus() {
        return bonusInit;
    }
    
    public void setTemp(int newTemp) {
        tempInit = newTemp;
        update();
    }
    
    public int getTemp() {
        return tempInit;
    }
    
    public void update() {
        init = dexInit + bonusInit + tempInit;
    }
}
