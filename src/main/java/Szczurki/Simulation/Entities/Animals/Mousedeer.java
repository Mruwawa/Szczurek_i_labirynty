package Szczurki.Simulation.Entities.Animals;

import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Utilities.Vector;

public class Mousedeer extends Animal{

    public Mousedeer(int x, int y, String name) {
        super(x, y, name,8,2,6,3);
    }
    public Vector choosePreferredMove(IEntity[][] entities){
        return lastMove;
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
