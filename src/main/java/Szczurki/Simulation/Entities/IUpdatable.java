package Szczurki.Simulation.Entities;

import java.util.ArrayList;

public interface IUpdatable {
    //interfejs pozwala na aktualizowanie stanu elementów rozmieszczonych w labiryncie
    void update(IEntity[][] otherEntities, IEntity[][] nextTurnEntities);
}
