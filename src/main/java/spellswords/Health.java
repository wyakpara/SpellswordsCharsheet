/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spellswords;

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
    
    int HPThresh, StamThresh; // Thresholds are at 3/4, 1/2, 1/4, and 0, respectively, with the "remainder" thresh being the highest.
    
    public Health() {
        
    }
    
    public Health(Classes classes, AbilityScore con, int hpbonus, int stambonus) {
        HPLost = StamLost = 0;
        setItUp(classes, con, hpbonus, stambonus);
    }
    
    public void setItUp(Classes classes, AbilityScore con, int hpbonus, int stambonus) {
        // Stam is con bonus * level + total HD + bonus
        StamLvl = con.getMod() * classes.getTotalLevel() + classes.getSumHDs();
        StamBonus = stambonus;
        Stam = StamLvl + StamBonus;
        getStamThresh();
        
        // HP is con val + Stam/4 + HPbonus
        HPCon = con.getFinalScore();
        HPStam = Stam/4;
        HPBonus = hpbonus;
        HP = HPCon + HPStam + HPBonus;
        getHPThresh();
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
     
    public int getStamThresh() {
        
        int thresh = Stam/4;
        int current = Stam - StamLost;
//        System.err.println("Current Stam: " + current);
        
        if(current >= thresh * 3) // Top Thresh
        {
            StamThresh = thresh * 3;
            return 4;
        }
        else if((current < thresh * 3) & (current >= thresh * 2)) // Down one thresh
        {
            StamThresh = thresh * 2;
            return 3;
        }
        else if((current < thresh * 2) & (current >= thresh)) // Down two threshes
        {
            StamThresh = thresh;
            return 2;
        }
        else if((current < thresh) & (current >= 0)) // Last thresh
        {
            StamThresh = 0;
            return 1;
        }
        else if(current < 0) // Negative thresh
        {
            StamThresh = -thresh;
            return 0;
        }
        return -1;
    }
    
    public int getHPThresh() {
        int thresh = HP/4;
        int current = HP - HPLost;
        
        if(current >= thresh * 3) // Top Thresh
        {
            HPThresh = thresh * 3;
            return 4;
        }
        else if((current < thresh * 3) & (current >= thresh * 2)) // Down one thresh
        {
            HPThresh = thresh * 2;
            return 3;
        }
        else if((current < thresh * 2) & (current >= thresh)) // Down two threshes
        {
            HPThresh = thresh;
            return 2;
        }
        else if((current < thresh) & (current >= 0)) // Last thresh
        {
            HPThresh = 0;
            return 1;
        }
        else if(current < 0) // Negative thresh
        {
            HPThresh = -thresh;
            return 0;
        }
        return 0;
        
    }
}
