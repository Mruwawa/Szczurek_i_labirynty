package Szczurki.Simulation.Visualization;

import Szczurki.Simulation.Entities.Interfaces.IEntity;

/**
 * Klasa odpowiedzialna za wizualizację symulacji
 */
public interface IRenderer {
    /**
     * @param entities Tablica reprezentująca mapę
     */
    void render(IEntity[][] entities);

    /**
     * Metoda zatrzymująca działanie renderera
     */
    void stop();
}
