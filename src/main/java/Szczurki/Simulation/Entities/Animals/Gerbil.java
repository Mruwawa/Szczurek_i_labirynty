package Szczurki.Simulation.Entities.Animals;

import Szczurki.Utilities.Vector;

/**
 * Klasa reprezentująca myszoskoczka
 * przechowująca statystyki oraz preferowany ruch
 * dla każdego przedstawiciela gatunku
 */
public class Gerbil extends Animal {

    /**
     * @param x - położenie poziome na mapie
     * @param y - położenie pionowe na mapie
     * @param name - indywidualne imię danego przedstawiciela gatunku
     */
    public Gerbil(int x, int y, String name) {
        super(x, y, name,3,1,4);
    }
    public Vector choosePreferredMove(){
        return lastMove;
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
