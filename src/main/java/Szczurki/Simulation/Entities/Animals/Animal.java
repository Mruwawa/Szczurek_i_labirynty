package Szczurki.Simulation.Entities.Animals;

import Szczurki.Simulation.Entities.IEntity;
import Szczurki.Simulation.Entities.IUpdatable;

public class Animal implements IEntity, IUpdatable {    //główna klasa po której dziedziczyć będą wszystkie zwierzęta

    Animal(String a, String b, int c, int d, int e, int f) {
        Species = a;
        Name = b;
        Speed = c;
        Intelligence = d;
        Strength = e;
        Cooperation = f;

    }

    public int Speed,Intelligence,Strength,Cooperation;
    public String Species, Name;

    @Override
    public void update() {

    }
}
