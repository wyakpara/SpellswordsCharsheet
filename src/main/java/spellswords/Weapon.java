/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spellswords;
import java.util.ArrayList;

/**
 *
 * @author Didge
 */
public class Weapon {
    int number;
    private int profLevel;
    
    private String name;
    private String armClass;
    
    private boolean slashing;
    private boolean bludgeoning;
    private boolean piercing;
    
    private ArrayList<String> qualities;
    
    private int critRange;
    private int critMult;
    
    private boolean ranged;
    private int range;
    private int ammo;
    
    
    private int attack;
    private int baseAtck;
    private int specAtck;
    private int weapAtck;
    private int tempAtck;
    private int damage;
    private int baseDmg;
    private int specDmg;
    private int weapDmg;
    private int tempDmg;
    private int numDie;
    private int dieNum;
    private int pen;
    private int basePen;
    private int specPen;
    private int weapPen;
    private int tempPen;
    private int weight;
    
    public Weapon(int num, int prof, int pl, String n, String ac, int type, int cr, int cm, int range, int ammo, int wa, int wd, int die, int ndie, int wp, int weight) {
        number = num;
        profLevel = pl;
        name = n;
        armClass = ac;
        getType(type);
        critRange = cr;
        critMult = cm;
        this.range = range;
        if(range == 0) ranged = false;
        ammo = ammo;
        weapAtck = wa;
        weapDmg = wd;
        dieNum = die;
        numDie = ndie;
        weapPen = wp;
        this.weight = weight;
        updateProf(prof);
    }
    
    public void getType(int type) {
    // 0 is slash
    // 1 is blud
    // 2 is piercing
    // 3 is slash + blud
    // 4 is slash + pie
    // 5 is pie + blud
    // 6 is all three
    switch(type) {
        case 0:
            setSlashing(true);
            setBludgeoning(false);
            setPiercing(false);
            break;
        case 1:
            setSlashing(false);
            setBludgeoning(true);
            setPiercing(false);
            break;
        case 2:
            setSlashing(false);
            setBludgeoning(false);
            setPiercing(true);
            break;
        case 3:
            setSlashing(true);
            setBludgeoning(true);
            setPiercing(false);
            break;
        case 4:
            setSlashing(true);
            setBludgeoning(false);
            setPiercing(true);
            break;
        case 5:
            setSlashing(false);
            setBludgeoning(true);
            setPiercing(true);
            break;
        case 6:
            setSlashing(true);
            setBludgeoning(true);
            setPiercing(true);
            break;
        default:
            setSlashing(true);
            setBludgeoning(false);
            setPiercing(false);
        }
    }
    
    public void updateProf(int prof) {
        double temp;
        switch (getProfLevel()) {
            case 0:
                baseAtck = 0;
                break;
            case 1:
                baseAtck = prof/3;
                break;
            case 2:
                temp = (double)prof/3;
                temp *= 2;
                baseAtck = (int) temp;
                break;
            case 3:
                baseAtck = prof;
                break;
        }
        update();
    }
    
    public void update() {
        attack = getBaseAtck() + getWeapAtck() + getTempAtck() + getSpecAtck();
        damage = getBaseDmg() + getWeapDmg() + getTempDmg() + getSpecDmg();
        pen = getBasePen() + getWeapPen() + getTempPen() + getSpecPen();
    }
    
    public int getNumber() {
        return number;
    }
    
    public void setNumber(int newNumber) {
        number = newNumber;
    }

    /**
     * @return the profLevel
     */
    public int getProfLevel() {
        return profLevel;
    }

    /**
     * @param profLevel the profLevel to set
     */
    public void setProfLevel(int profLevel) {
        this.profLevel = profLevel;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the armClass
     */
    public String getArmClass() {
        return armClass;
    }

    /**
     * @param armClass the armClass to set
     */
    public void setArmClass(String armClass) {
        this.armClass = armClass;
    }

    /**
     * @return the slashing
     */
    public boolean isSlashing() {
        return slashing;
    }

    /**
     * @param slashing the slashing to set
     */
    public void setSlashing(boolean slashing) {
        this.slashing = slashing;
    }

    /**
     * @return the bludgeoning
     */
    public boolean isBludgeoning() {
        return bludgeoning;
    }

    /**
     * @param bludgeoning the bludgeoning to set
     */
    public void setBludgeoning(boolean bludgeoning) {
        this.bludgeoning = bludgeoning;
    }

    /**
     * @return the piercing
     */
    public boolean isPiercing() {
        return piercing;
    }

    /**
     * @param piercing the piercing to set
     */
    public void setPiercing(boolean piercing) {
        this.piercing = piercing;
    }

    /**
     * @return the qualities
     */
    public ArrayList<String> getQualities() {
        return qualities;
    }

    /**
     * @param qualities the qualities to set
     */
    public void setQualities(ArrayList<String> qualities) {
        this.qualities = qualities;
    }

    /**
     * @return the critRange
     */
    public int getCritRange() {
        return critRange;
    }

    /**
     * @param critRange the critRange to set
     */
    public void setCritRange(int critRange) {
        this.critRange = critRange;
    }

    /**
     * @return the critMult
     */
    public int getCritMult() {
        return critMult;
    }

    /**
     * @param critMult the critMult to set
     */
    public void setCritMult(int critMult) {
        this.critMult = critMult;
    }

    /**
     * @return the ranged
     */
    public boolean isRanged() {
        return ranged;
    }

    /**
     * @param ranged the ranged to set
     */
    public void setRanged(boolean ranged) {
        this.ranged = ranged;
    }

    /**
     * @return the range
     */
    public int getRange() {
        return range;
    }

    /**
     * @param range the range to set
     */
    public void setRange(int range) {
        this.range = range;
    }

    /**
     * @return the ammo
     */
    public int getAmmo() {
        return ammo;
    }

    /**
     * @param ammo the ammo to set
     */
    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    /**
     * @return the attack
     */
    public int getAttack() {
        return attack;
    }

    /**
     * @return the baseAtck
     */
    public int getBaseAtck() {
        return baseAtck;
    }

    /**
     * @return the specAtck
     */
    public int getSpecAtck() {
        return specAtck;
    }

    /**
     * @param specAtck the specAtck to set
     */
    public void setSpecAtck(int specAtck) {
        this.specAtck = specAtck;
        update();
    }

    /**
     * @return the weapAtck
     */
    public int getWeapAtck() {
        return weapAtck;
    }

    /**
     * @param weapAtck the weapAtck to set
     */
    public void setWeapAtck(int weapAtck) {
        this.weapAtck = weapAtck;
        update();
    }

    /**
     * @return the tempAtck
     */
    public int getTempAtck() {
        return tempAtck;
    }

    /**
     * @param tempAtck the tempAtck to set
     */
    public void setTempAtck(int tempAtck) {
        this.tempAtck = tempAtck;
        update();
    }

    /**
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @return the baseDmg
     */
    public int getBaseDmg() {
        return baseDmg;
    }

    /**
     * @param baseDmg the baseDmg to set
     */
    public void setBaseDmg(int baseDmg) {
        this.baseDmg = baseDmg;
        update();
    }

    /**
     * @return the specDmg
     */
    public int getSpecDmg() {
        return specDmg;
    }

    /**
     * @param specDmg the specDmg to set
     */
    public void setSpecDmg(int specDmg) {
        this.specDmg = specDmg;
        update();
    }

    /**
     * @return the weapDmg
     */
    public int getWeapDmg() {
        return weapDmg;
    }

    /**
     * @param weapDmg the weapDmg to set
     */
    public void setWeapDmg(int weapDmg) {
        this.weapDmg = weapDmg;
        update();
    }

    /**
     * @return the tempDmg
     */
    public int getTempDmg() {
        return tempDmg;
    }

    /**
     * @param tempDmg the tempDmg to set
     */
    public void setTempDmg(int tempDmg) {
        this.tempDmg = tempDmg;
        update();
    }

    /**
     * @return the numDie
     */
    public int getNumDie() {
        return numDie;
    }

    /**
     * @param numDie the numDie to set
     */
    public void setNumDie(int numDie) {
        this.numDie = numDie;
    }

    /**
     * @return the dieNum
     */
    public int getDieNum() {
        return dieNum;
    }

    /**
     * @param dieNum the dieNum to set
     */
    public void setDieNum(int dieNum) {
        this.dieNum = dieNum;
    }

    /**
     * @return the pen
     */
    public int getPen() {
        return pen;
    }

    /**
     * @return the basePen
     */
    public int getBasePen() {
        return basePen;
    }

    /**
     * @param basePen the basePen to set
     */
    public void setBasePen(int basePen) {
        this.basePen = basePen;
        update();
    }

    /**
     * @return the specPen
     */
    public int getSpecPen() {
        return specPen;
    }

    /**
     * @param specPen the specPen to set
     */
    public void setSpecPen(int specPen) {
        this.specPen = specPen;
        update();
    }

    /**
     * @return the weapPen
     */
    public int getWeapPen() {
        return weapPen;
    }

    /**
     * @param weapPen the weapPen to set
     */
    public void setWeapPen(int weapPen) {
        this.weapPen = weapPen;
        update();
    }

    /**
     * @return the tempPen
     */
    public int getTempPen() {
        return tempPen;
    }

    /**
     * @param tempPen the tempPen to set
     */
    public void setTempPen(int tempPen) {
        this.tempPen = tempPen;
        update();
    }

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }
}
