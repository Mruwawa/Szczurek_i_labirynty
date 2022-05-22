package Szczurki.Simulation.Entities.Animals;

import Szczurki.Simulation.Entities.IEntity;
import Szczurki.Simulation.Entities.IUpdatable;

public class Animal implements IEntity, IUpdatable {    //główna klasa po której dziedziczyć będą wszystkie zwierzęta

    int speed,intelligence,strength,cooperation;
    String name;

    Animal(String name, int speed, int intelligence, int strength, int cooperation) {
        this.name = name;
        this.speed = speed;
        this.intelligence = intelligence;
        this.strength = strength;
        this.cooperation = cooperation;

    }


    @Override
    public void update() {

    }
}
