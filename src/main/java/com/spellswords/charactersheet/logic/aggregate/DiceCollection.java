package com.spellswords.charactersheet.logic.aggregate;

import java.util.ArrayList;

public class DiceCollection {
    ArrayList<Dice> diceCollection;

    public void setDiceCollection(int numDice, int dieType) {
        Dice die = new Dice(numDice, dieType);
        diceCollection = new ArrayList<>();
        addDice(die);
    }

    public void setDiceCollection(ArrayList<Dice> diceCollection) {
        this.diceCollection = diceCollection;
    }

    void addDice(Dice dice) {
        diceCollection.add(dice);
    }

    int rollDice(int bonus) {
        int total = bonus;
        for(Dice d: diceCollection) {
            total += d.rollDice();
        }
        return total;
    }
}
