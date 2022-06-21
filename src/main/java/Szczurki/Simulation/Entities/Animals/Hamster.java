package Szczurki.Simulation.Entities.Animals;

import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Utilities.Vector;

import java.util.Random;

public class Hamster extends Animal {

    public Hamster(int x, int y, String name) {
        super(x, y, name,1,6,2,3);
    }
    public Vector choosePreferredMove(){
        Random chance = new Random();
        int chanceForWaiting = chance.nextInt(101);
        if(chanceForWaiting<26){
            return new Vector(0,0);
        }
        return null;
    }

    @Override
    public String toString()
    {
        return "Chomik";
    }

}
