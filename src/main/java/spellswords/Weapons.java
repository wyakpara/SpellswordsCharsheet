/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spellswords;
import java.util.HashMap;

/**
 *
 * @author Didge
 */
public class Weapons {
    HashMap<Integer, Weapon> weapons;
    
    public Weapons() {
        weapons = new HashMap<>();
    }
    
    public Weapons(int[] num, int[] prof, int[] pl, String[] n, String[] ac, int[] type, int[] cr, int[] cm, int[] range, int[] ammo, int[] wa, int[] wd, int[] die, int[] ndie, int[] wp, int[] weight) {
        for(int i = 0; i < prof.length; i++) {
            addWeapon(num[i], prof[i], pl[i], n[i], ac[i], type[i], cr[i], cm[i], range[i], ammo[i], wa[i], wd[i], die[i], ndie[i], wp[i], weight[i]);
        }
    }
    
    public void addWeapon(int num, int prof, int pl, String n, String ac, int type, int cr, int cm, int range, int ammo, int wa, int wd, int die, int ndie, int wp, int weight) {
        weapons.put(num, new Weapon(num, prof, pl, n, ac, type, cr, cm, range, ammo, wa, wd, die, ndie, wp, weight));
    }
    
    public Weapon getWeapon(int num) {
        return weapons.get(num);
    }
}
