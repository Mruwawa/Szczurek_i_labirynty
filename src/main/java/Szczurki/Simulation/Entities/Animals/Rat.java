package Szczurki.Simulation.Entities.Animals;

import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Utilities.Vector;

public class Rat extends Animal {
    public Rat(int x, int y, String name) {
        super(x, y, name, 3, 4, 7, 6);
    }
    public Vector choosePreferredMove(IEntity[][] entities){
        return lastMove.turnRight();
    }
}
