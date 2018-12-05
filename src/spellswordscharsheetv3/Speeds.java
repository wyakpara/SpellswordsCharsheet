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
public class Speeds {
    int walk, swim, fly, climb, dig, runMult;
    
    public Speeds() {
        walk = swim = fly = climb = dig = runMult = 0;
    }
    
    public Speeds(int newWalk) {
        walk = newWalk;
        swim = fly = climb = dig = runMult = 0;
        runMult = 4;
    }
    
    public void setWalk(int newWalk) {
        walk = newWalk;
    }
    
    public int getWalk() {
        return walk;
    }
    
    public void setSwm(int newSwim) {
        swim = newSwim;
    }
    
    public int getSwim() {
        return swim;
    }
    
    public void setFly(int newFly) {
        fly = newFly;
    }
    
    public int getFly() {
        return fly;
    }
    
    public void setClimb(int newClimb) {
        climb = newClimb;
    }
    
    public int getClimb() {
        return climb;
    }
    
    public void setDig(int newDig) {
        dig = newDig;
    }
    
    public int getDig() {
        return dig;
    }
    
    public void setMult(int newMult) {
        runMult = newMult;
    }
    
    public int getMult() {
        return runMult;
    }
}
