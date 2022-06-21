package Szczurki.Simulation.Entities.Interfaces;

import Szczurki.Simulation.Board;

/**
 * Interfejs pozwala na aktualizowanie stanu elementów rozmieszczonych w labiryncie
 */
public interface IUpdatable {

    /**
     * Metoda służy do aktualizowania stanu pojedynczego agenta
     * @param board plansza, na której odbywa się symulacja
     * @param iteration numer tury
     */
    void update(Board board, int iteration);
}
