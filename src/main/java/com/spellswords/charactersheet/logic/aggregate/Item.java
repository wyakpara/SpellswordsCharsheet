/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spellswords.charactersheet.logic.aggregate;

/**
 *
 * @author Didge
 */
public class Item {
        private int itemPlace;
        private String itemName;
        private String itemLocation;
        private int itemWeight;
        private boolean carried;
        
        public Item() {
            itemName = "";
            itemLocation = "";
            itemWeight = 0;
            carried = false;
        }
        
        public Item(int place, String name, String location, int weight) {
            itemPlace = place;
            itemName = name;
            itemLocation = location;
            itemWeight = weight;
            carried = false;
        }
        
        public Item(int place, String name, String location, String weight) {
            itemPlace = place;
            itemName = name;
            itemLocation = location;
            itemWeight = Integer.parseInt(weight);
            carried = false;
        }
        
        public void setCarried(boolean toCarry) {
            carried = toCarry;
        }
        
        public boolean carried() {
            return carried;
        }
        
        public int place() {
            return itemPlace;
        }
        
        public int weight() {
            return itemWeight;
        }
        
        public String weightStr() {
            return "" + itemWeight;
        }
        
        public void setWeight(int weight) {
            itemWeight = weight;
        }
        
        public void setWeight(String weight) {
            setWeight(Integer.parseInt(weight));
        }
        
        public String name() {
            return itemName;
        }
        
        public void setName(String name) {
            itemName = name;
        }
        
        public String location() {
            return itemLocation;
        }
        
        public void setLocation(String location) {
            itemLocation = location;
        }
        
    }
