package Szczurki.Simulation.Entities.Animals;

import Szczurki.Utilities.Vector;

import java.util.Random;

/**
 * Klasa reprezentująca myszojelenia
 * przechowująca statystyki oraz preferowany ruch
 * dla każdego przedstawiciela gatunku
 */
public class Mousedeer extends Animal{

    /**
     * @param x położenie poziome na mapie
     * @param y - położenie pionowe na mapie
     * @param name - indywidualne imię danego przedstawiciela gatunku
     */
    public Mousedeer(int x, int y, String name) {
        super(x, y, name,2,6,3);
    }
    public Vector choosePreferredMove(){
        Random chance = new Random();
        int chanceForTurningBack = chance.nextInt(101);
        if(chanceForTurningBack<21){
            return lastMove.reversed();
        }
        return null;
    }

    /**
     * @return Siła powiększona na podstawie sił sąsiadów według wzoru:
     * (siła sąsiada * kooperacja tego zwierzaka) / 10
     */
    @Override
    public int getStrength() {
        int strengthToReturn = this.strength;

        for (var neighbour : neighbours) {
            if (neighbour instanceof Mousedeer) {
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
