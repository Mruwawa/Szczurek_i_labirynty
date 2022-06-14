package Szczurki.Simulation.Entities;

import Szczurki.Simulation.Entities.Animals.Animal;
import Szczurki.Simulation.Entities.Interfaces.IEntity;

public class Obstacle implements IEntity {
    private int requiredIntelligence, requiredStrength;
    private boolean active;

    public Obstacle(int requiredIntelligence, int requiredStrength) {
        this.requiredIntelligence = requiredIntelligence;
        this.requiredStrength = requiredStrength;
        this.active = true;
    }


    public void interact(Animal animal)
    {
        if(this.requiredStrength <= animal.getStrength() || this.requiredIntelligence <= animal.getIntelligence())
        {
            this.active = false;
            return;
        }
        this.requiredStrength -= animal.getStrength();
        this.requiredIntelligence -=animal.getIntelligence();
    }

    public boolean isActive(){
        return this.active;
    }
}
