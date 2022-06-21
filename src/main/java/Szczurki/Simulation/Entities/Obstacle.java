package Szczurki.Simulation.Entities;

import Szczurki.Simulation.Entities.Animals.Animal;
import Szczurki.Simulation.Entities.Interfaces.IEntity;

/**
 * Klasa reprezentująca przeszkodę
 * Przeszkoda ma swoje statystyki
 * oraz może być aktywna lub nieaktywna
 *
 * Implementuje interfejs
 * IEntity - który pozwala na umieszczenie jej instancji na mapie
 */
public class Obstacle implements IEntity {
    private int requiredIntelligence, requiredStrength;
    private boolean active;

    public Obstacle(int requiredIntelligence, int requiredStrength) {
        this.requiredIntelligence = requiredIntelligence;
        this.requiredStrength = requiredStrength;
        active = true;
    }


    /**
     * Metoda przeprowadza interakcję pomiędzy zwierzęciem a tą przeszkodą
     * Jeśli statystyki pułapki są mniejsze niż zwierzaka, zostaje ona wyłączona
     * Jeśli nie, to zostają one zmniejszone o statystyki zwierzaka
     * @param animal Zwierzę, które przeprowadza interakcję z przeszkodą
     */
    public void interact(Animal animal)
    {
        if(this.requiredStrength <= animal.getStrength() || this.requiredIntelligence <= animal.getIntelligence())
        {
            active = false;
            return;
        }
        this.requiredStrength -= animal.getStrength();
        this.requiredIntelligence -=animal.getIntelligence();
    }

    public boolean isActive(){
        return active;
    }
}
