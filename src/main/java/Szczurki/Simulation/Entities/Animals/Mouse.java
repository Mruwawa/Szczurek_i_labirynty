package Szczurki.Simulation.Entities.Animals;

import Szczurki.Utilities.Vector;

/**
 * Klasa reprezentująca mysz
 * przechowująca statystyki oraz preferowany ruch
 * dla każdego przedstawiciela gatunku
 */
public class Mouse extends Animal{

    /**
     * @param x - położenie poziome na mapie
     * @param y - położenie pionowe na mapie
     * @param name - indywidualne imię danego przedstawiciela gatunku
     */
    public Mouse(int x, int y, String name) {
        super(x, y, name,3,4,5);
    }
    public Vector choosePreferredMove(){
        return lastMove.turnLeft();
    }

    /**
     * @return Inteligencja powiększona na podstawie inteligencji sąsiadów według wzoru:
     * (siła sąsiada * kooperacja tego zwierzaka) / 10
     */
    @Override
    public int getIntelligence() {
        int intelligenceToReturn = this.intelligence;

        for (var neighbour : neighbours) {
            if (neighbour instanceof Mouse) {
                intelligenceToReturn += (neighbour.intelligence * cooperation) / 10;
            }
        }
        return intelligenceToReturn;
    }

    /**
     * @return Siła powiększona na podstawie sił sąsiadów według wzoru:
     * (siła sąsiada * kooperacja tego zwierzaka) / 10
     */
    @Override
    public int getStrength() {
        int strengthToReturn = this.strength;

        for (var neighbour : neighbours) {
            if (neighbour instanceof Mouse) {
                strengthToReturn += (neighbour.strength * cooperation) / 10;
            }
        }
        return strengthToReturn;
    }

    @Override
    public String toString()
    {
        return "Mysz";
    }
}
