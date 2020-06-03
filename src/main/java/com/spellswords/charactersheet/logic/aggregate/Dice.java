package com.spellswords.charactersheet.logic.aggregate;

import java.util.Random;

public class Dice {
    int numDice;
    int dieType;

    public Dice(int numDice, int dieType) {
        this.numDice = numDice;
        this.dieType = dieType;
    }

    public int getNumDice() {
        return numDice;
    }

    public void setNumDice(int numDice) {
        this.numDice = numDice;
    }

    public int getDieType() {
        return dieType;
    }

    public void setDieType(int dieType) {
        this.dieType = dieType;
    }

    int rollDice() {
        Random rand = new Random();
        int total = 0;
        for(int i = 0; i < numDice; i++) {
            total += rand.nextInt(dieType -1) + 1;
        }

        return total;
    }

    int rollDice(int bonus) {
        Random rand = new Random();
        int total = bonus;
        for(int i = 0; i < numDice; i++) {
            total += rand.nextInt(dieType -1) + 1;
        }

        return total;
    }
}
