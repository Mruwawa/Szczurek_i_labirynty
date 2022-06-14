package Szczurki.Simulation.Entities.Animals;

import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Utilities.Vector;

import java.util.Random;

public class Mouse extends Animal{

    public Mouse(int x, int y, String name) {
        super(x, y, name,7,3,4,5);
    }
    public Vector choosePreferredMove(IEntity[][] entities){
        Random chance = new Random();
        int chanceForTurningBack = chance.nextInt(101);
        if(chanceForTurningBack<26){
            return lastMove.reversed();
        }
        return null;
    }

}
