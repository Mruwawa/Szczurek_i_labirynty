package Szczurki.Simulation.Entities.Animals;

import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Utilities.Vector;

import java.util.Random;

public class Mousedeer extends Animal{

    public Mousedeer(int x, int y, String name) {
        super(x, y, name,3,2,6,3);
    }
    public Vector choosePreferredMove(){
        Random chance = new Random();
        int chanceForTurningBack = chance.nextInt(101);
        if(chanceForTurningBack<21){
            return lastMove.reversed();
        }
        return null;
    }

    @Override
    public int getStrength() {
        int strengthToReturn = this.strength;

        for (var neighbour : neighbours) {
            if (neighbour instanceof Gerbil) {
                strengthToReturn += (neighbour.strength * cooperation) / 10;
            }
        }
        return strengthToReturn;
    }

    @Override
    public String toString()
    {
        return "Myszojeleń";
    }
}
