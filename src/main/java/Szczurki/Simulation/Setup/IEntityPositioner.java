package Szczurki.Simulation.Setup;

import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Simulation.Entities.Interfaces.IUpdatable;

import java.util.List;

/**
 * Interfejs pozwala na rozmieszczanie agentów na mapie
 */
public interface IEntityPositioner {
    /**
     * Metoda rozmieszcza na mapie agentów oraz tworzy ich listę
     * @param map Tablica reprezentująca mapę, na niej zostaną rozstawieni agenci
     * @return Lista agentów
     */
    List<IUpdatable> getPlacedEntities(IEntity[][] map);
}
