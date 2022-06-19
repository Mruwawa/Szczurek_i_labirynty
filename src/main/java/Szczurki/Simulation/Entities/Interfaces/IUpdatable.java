package Szczurki.Simulation.Entities.Interfaces;

import Szczurki.Simulation.Board;

public interface IUpdatable {
    //interfejs pozwala na aktualizowanie stanu elementów rozmieszczonych w labiryncie
    void update(Board board, int iteration);
}
