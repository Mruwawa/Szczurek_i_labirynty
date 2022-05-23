package Szczurki.Simulation.Entities.Interfaces;

import Szczurki.Simulation.Board;

import java.util.ArrayList;

public interface IUpdatable {
    //interfejs pozwala na aktualizowanie stanu elementów rozmieszczonych w labiryncie
    void update(Board board);
}
