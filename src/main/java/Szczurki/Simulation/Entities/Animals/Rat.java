package Szczurki.Simulation.Entities.Animals;

import Szczurki.Utilities.Vector;

public class Rat extends Animal {
    public Rat(int x, int y, String name) {
        super(x, y, name, 2, 4, 7, 6);
    }
    public Vector choosePreferredMove(){
        return null;
    }

    @Override
    public int getIntelligence() {
        int intelligenceToReturn = this.intelligence;

        for (var neighbour : neighbours) {
            if (neighbour instanceof Rat) {
                intelligenceToReturn += (neighbour.intelligence * cooperation) / 10;
            }
        }
        return intelligenceToReturn;
    }

    @Override
    public String toString()
    {
        return "Szczur";
    }
}
