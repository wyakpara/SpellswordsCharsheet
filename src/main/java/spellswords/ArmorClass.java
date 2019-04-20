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
public class ArmorClass {
    private int totalAC;
    private int dualAC;
    private int evadeAC;
    private int deflectAC;
    
    private int totalDR;
    
    public ArmorClass(int dual, int evade, int deflect, int dr) {
        dualAC = dual;
        evadeAC = evade;
        deflectAC = deflect;
        totalDR = dr;
        update();
    }
    
    public void update() {
        totalAC = dualAC + evadeAC + deflectAC;
    }
    
    public void setDualAC(int dual) {
        dualAC = dual;
        update();
    }
    
    public int getDualAC() {
        return dualAC;
    }
    
    public void setEvadeAC(int evade) {
        evadeAC = evade;
        update();
    }
    
    public int getEvadeAC() {
        return evadeAC;
    }
    
    public void setDeflectAC(int deflect) {
        deflectAC = deflect;
        update();
    }
    
    public int getDeflectAC() {
        return deflectAC;
    }
    
    public void setDR(int dr) {
        totalDR = dr;
    }
    
    public int getDR() {
        return totalDR;
    }
}
