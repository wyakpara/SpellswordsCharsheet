/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spellswords.charactersheet.logic.aggregate;

import java.util.HashMap;

/**
 *
 * @author Didge
 */
public class Armors {
    HashMap<Integer, Armor> armors;
    
    public Armors() {
        armors = new HashMap<>();
    }
    
    public Armors(int[] num, String[] n, String k[], String t[], int chtac[], int bac[], int eac[], int tac[], int chtd[], int bdr[], int edr[], int tdr[], int chek[], int mdex[], int w[]) {
        for(int i = 0; i < num.length; i++) {
            addArmor(num[i], n[i], k[i], t[i], chtac[i], bac[i], eac[i], tac[i], chtd[i], bdr[i], edr[i], tdr[i], chek[i], mdex[i], w[i]);
        }
    }
    
    public void addArmor(int num, String n, String k, String t, int chtac, int bac, int eac, int tac, int chtd, int bdr, int edr, int tdr, int chek, int mdex, int w) {
        armors.put(num, new Armor(num, n, k, t, chtac, bac, eac, tac, chtd, bdr, edr, tdr, chek, mdex, w));
    }
    
    public Armor getArmor(int num) {
        return armors.get(num);
    }
}
