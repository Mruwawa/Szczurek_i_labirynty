package Szczurki.Simulation.Entities.Animals;

import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Utilities.Vector;

import java.util.Random;

public class Mouse extends Animal{

    public Mouse(int x, int y, String name) {
        super(x, y, name,7,3,4,5);
    }
    public Vector choosePreferredMove(){
        Random chance = new Random();
        int chanceForTurningBack = chance.nextInt(101);
        if(chanceForTurningBack<26){
            return lastMove.reversed();
        }
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
    public int getStrength() {
        int strengthToReturn = this.strength;

        for (var neighbour : neighbours) {
            if (neighbour instanceof Mouse) {
                strengthToReturn += (neighbour.strength * cooperation) / 10;
            }
        }
        return strengthToReturn;
    }

    @Override
    public String toString()
    {
        return "Mysz";
    }
}
