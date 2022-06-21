package Szczurki.Simulation.Entities.Animals;

import Szczurki.Utilities.Vector;

import java.util.Random;

/**
 * Klasa reprezentująca chomika
 * przechowująca statystyki oraz preferowany ruch
 * dla każdego przedstawiciela gatunku
 */
public class Hamster extends Animal {

    /**
     * @param x - położenie poziome na mapie
     * @param y - położenie pionowe na mapie
     * @param name - indywidualne imię danego przedstawiciela gatunku
     */
    public Hamster(int x, int y, String name) {
        super(x, y, name,6,2,3);
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
