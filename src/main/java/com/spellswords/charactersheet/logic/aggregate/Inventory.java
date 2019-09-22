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
public class Inventory {
    HashMap<Integer, Item> inven;
    
    public Inventory() {
        inven = new HashMap<>();
    }
    
    public Inventory(Item item) {
        inven = new HashMap<>();
        addItem(item);
    }
    
    public Inventory(Item[] items) {
        inven = new HashMap<>();
        for(Item i: items) {
            addItem(i);
        }
    }
    
    public Inventory(int[] places, String[] names, String[] locations, String[] weight) {
        inven = new HashMap<>();
        addItems(places, names, locations, weight);
    }
    
    public Inventory(int[] places, String[] names, String[] locations, int[] weight) {
        inven = new HashMap<>();
        addItems(places, names, locations, weight);
    }
    
    public void addItems(int[] places, String[] names, String[] locations, String[] weight) {
        if(names.length != locations.length || names.length != weight.length) return;
        
        for(int i = 0; i < names.length; i++) {
            inven.put(places[i], new Item(places[i], names[i], locations[i], weight[i]));
        }
    }
    
    public void addItems(int[] places, String[] names, String[] locations, int[] weight) {
        if(names.length != locations.length || names.length != weight.length) return;
        
        for(int i = 0; i < names.length; i++) {
            inven.put(places[i], new Item(places[i], names[i], locations[i], weight[i]));
        }
    }
    
    public void addItem(Item item) {
        if(inven.containsKey(item.place())) {
            System.err.println("Item with that name exists, cannot add");
            return;
        }
        inven.put(item.place(), item);
    }
    
    public void addItem(int place, String name, String location, String weight) {
        if(inven.containsKey(name)) {
            System.err.println("Item with that name exists, cannot add");
            return;
        }
        inven.put(place, new Item(place, name, location, weight));
    }
    
    public void addItem(int place, String name, String location, int weight) {
        if(inven.containsKey(place)) {
            System.err.println("Item with that name exists, cannot add");
            return;
        }
        inven.put(place, new Item(place, name, location, weight));
    }
    
    public void updateItem(int place, String newName, String location, String weight) {
        Item temp = inven.get(place);
        temp.setLocation(location);
        temp.setWeight(weight);
        temp.setName(newName);
        inven.put(place, temp);
    }
    
    public void updateItem(int place, String newName, String location, int weight) {
        Item temp = inven.get(place);
        temp.setLocation(location);
        temp.setWeight(weight);
        temp.setName(newName);
        inven.put(place, temp);

    }
    
    public void removeItem(String name) {
        if(inven.containsKey(name))
        inven.remove(name);
    }
    
    public void setCarried(int place, boolean isCarried) {
        inven.get(place).setCarried(isCarried);
    }
    
}
