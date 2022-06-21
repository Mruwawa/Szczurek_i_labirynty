package Szczurki.Simulation.Entities.Animals;

import Szczurki.Utilities.Vector;

public class Gerbil extends Animal {

    public Gerbil(int x, int y, String name) {
        super(x, y, name,9,3,1,4);
    }
    public Vector choosePreferredMove(){
        return lastMove.turnLeft();
    }

    /**
     * @return Siła powiększona na podstawie sił sąsiadów według wzoru:
     * (siła sąsiada * kooperacja tego zwierzaka) / 10
     */
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
        return "Myszoskoczek";
    }
}
