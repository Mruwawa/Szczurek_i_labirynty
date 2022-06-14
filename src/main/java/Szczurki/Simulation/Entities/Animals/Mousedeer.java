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

}
