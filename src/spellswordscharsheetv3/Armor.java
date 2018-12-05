/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spellswordscharactersheet_v3;
import java.util.ArrayList;

/**
 *
 * @author Didge
 */
public class Armor {
    private int number;
    private String name;
    private String kind;
    private String type;
    private int ac;
    int charToAc;
    private int baseAc;
    private int enhanceAc;
    private int tempAc;
    
    private int dr;
    private int charToDr;
    private int baseDr;
    private int enhanceDr;
    private int tempDr;
    
    private int check;
    private int critAc;
    private int maxDex;
    private int weight;
    
    private boolean worn;
    
    private ArrayList<String> qualities;
    public Armor() {
        qualities = new ArrayList<>();
        name = "";
        kind = "";
        type = "";
        
        ac = charToAc = baseAc = enhanceAc = tempAc = 0;
        
        dr = charToDr = baseDr = enhanceDr = tempDr = 0;
        
        check = critAc = maxDex = weight = 0;
        
        worn = false;
    }
    
    public Armor(int num, String n, String k, String t, int chtac, int bac, int eac, int tac, int chtd, int bdr, int edr, int tdr, int chek, int mdex, int w) {
        number = num;
        qualities = new ArrayList<>();
        name = n;
        kind = k; 
        type = t;
        
        charToAc = chtac;
        baseAc = bac;
        enhanceAc = eac;
        tempAc = tac;
        
        charToDr = chtd;
        baseDr = bdr;
        enhanceDr = edr;
        tempDr = tdr;
        
        check = chek;
        maxDex = mdex;
        weight = w;
        
        critAc = 0;
        worn = false;
        update();
    }
    
    public void update() {
        setAc(charToAc + getBaseAc() + getEnhanceAc() + getTempAc());
        setDr(getCharToDr() + getBaseDr() + getEnhanceDr() + getTempDr());
    }
    
    public void setCharToAc(int ctac) {
        charToAc = ctac;
        update();
    }
    
    public int getCharToAc() {
        return charToAc;
    }
    
    public void setNum(int num) {
        number = num;
    }
    
    public int getNum() {
        return number;
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
     * @return the kind
     */
    public String getKind() {
        return kind;
    }

    /**
     * @param kind the kind to set
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the ac
     */
    public int getAc() {
        return ac;
    }

    /**
     * @param ac the ac to set
     */
    public void setAc(int ac) {
        this.ac = ac;
        update();
    }

    /**
     * @return the baseAc
     */
    public int getBaseAc() {
        return baseAc;
    }

    /**
     * @param baseAc the baseAc to set
     */
    public void setBaseAc(int baseAc) {
        this.baseAc = baseAc;
        update();
    }

    /**
     * @return the enhanceAc
     */
    public int getEnhanceAc() {
        return enhanceAc;
    }

    /**
     * @param enhanceAc the enhanceAc to set
     */
    public void setEnhanceAc(int enhanceAc) {
        this.enhanceAc = enhanceAc;
        update();
    }

    /**
     * @return the tempAc
     */
    public int getTempAc() {
        return tempAc;
    }

    /**
     * @param tempAc the tempAc to set
     */
    public void setTempAc(int tempAc) {
        this.tempAc = tempAc;
        update();
    }

    /**
     * @return the dr
     */
    public int getDr() {
        return dr;
    }

    /**
     * @param dr the dr to set
     */
    public void setDr(int dr) {
        this.dr = dr;
        update();
    }

    /**
     * @return the charToDr
     */
    public int getCharToDr() {
        return charToDr;
    }

    /**
     * @param charToDr the charToDr to set
     */
    public void setCharToDr(int charToDr) {
        this.charToDr = charToDr;
        update();
    }

    /**
     * @return the baseDr
     */
    public int getBaseDr() {
        return baseDr;
    }

    /**
     * @param baseDr the baseDr to set
     */
    public void setBaseDr(int baseDr) {
        this.baseDr = baseDr;
        update();
    }

    /**
     * @return the enhanceDr
     */
    public int getEnhanceDr() {
        return enhanceDr;
    }

    /**
     * @param enhanceDr the enhanceDr to set
     */
    public void setEnhanceDr(int enhanceDr) {
        this.enhanceDr = enhanceDr;
        update();
    }

    /**
     * @return the tempDr
     */
    public int getTempDr() {
        return tempDr;
    }

    /**
     * @param tempDr the tempDr to set
     */
    public void setTempDr(int tempDr) {
        this.tempDr = tempDr;
        update();
    }

    /**
     * @return the check
     */
    public int getCheck() {
        return check;
    }

    /**
     * @param check the check to set
     */
    public void setCheck(int check) {
        this.check = check;
    }

    /**
     * @return the critAc
     */
    public int getCritAc() {
        return critAc;
    }

    /**
     * @param critAc the critAc to set
     */
    public void setCritAc(int critAc) {
        this.critAc = critAc;
    }

    /**
     * @return the maxDex
     */
    public int getMaxDex() {
        return maxDex;
    }

    /**
     * @param maxDex the maxDex to set
     */
    public void setMaxDex(int maxDex) {
        this.maxDex = maxDex;
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

    /**
     * @return the worn
     */
    public boolean isWorn() {
        return worn;
    }

    /**
     * @param worn the worn to set
     */
    public void setWorn(boolean worn) {
        this.worn = worn;
    }
    
    public void addQuality(String qual) {
        qualities.add(qual);
    }
    
    public ArrayList<String> getQualities() {
        return qualities;
    }
}
