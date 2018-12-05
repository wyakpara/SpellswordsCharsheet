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
public class Actions {
    double actions, lvlActions, otherActions, tempActions;
    
    public Actions() {
        // Defaults to the base 1.5 actions
        lvlActions = 1.5;
        otherActions = tempActions = 0;
        updateActions();
    }
    
    public Actions(int level) {
        otherActions = tempActions = 0;
        actionsFromLevel(level);
        updateActions();
    }
    
    public void actionsFromLevel(int level) {
        // you get an extra .5 actions every 6 levels
        int extra = level/6;
        lvlActions = 1.5 + (double) extra/2;
        updateActions();
    }
    
    public void updateActions() {
        actions = lvlActions + otherActions + tempActions;
    }
    
    public void setOther(double newOther) {
        otherActions = newOther;
    }
    
    public double getOther() {
        return otherActions;
    }
    
    public void setTemp(double newTemp) {
        tempActions = newTemp;
    }
    
    public double getTemp() {
        return tempActions;
    }
    
}
