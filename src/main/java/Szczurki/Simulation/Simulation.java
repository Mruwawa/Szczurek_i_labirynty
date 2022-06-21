package Szczurki.Simulation;

import Szczurki.Configuration.SimulationSettings;
import Szczurki.Simulation.Entities.Animals.Animal;
import Szczurki.Simulation.Entities.Guardian;
import Szczurki.Simulation.Entities.Interfaces.IUpdatable;
import Szczurki.Simulation.Visualization.IRenderer;

import java.util.List;

/**
 * Główna klasa zajmująca się przeprowadzaniej symulacji
 */
public class Simulation {

    private final SimulationSettings _settings;
    private final Board _board;
    private final IRenderer _renderer;

    public Simulation(SimulationSettings settings, Board board, IRenderer renderer) {
        _settings = settings;
        _board = board;
        _renderer = renderer;
    }

    /**
     * Metoda przeprowadzająca symulację
     */
    public void run() {
        _renderer.render(_board.getMap());

        for (int i = 0; i < _settings.getTurnCount(); i++) {
            turn(i);
            //jeśli nie ma aktywnych zwierząt zatrzymujemy symulację
            if(!_board.areThereAnyActiveAnimalsLeft())
                break;
        }
        _renderer.stop();
    }

    /**
     * @param iteration Numer iteracji
     */
    private void turn(int iteration) {
        //dla każdego agenta
        for (var entity : _board.getUpdatableEntities()) {
            //jeśli agent jest nieaktywnym zwierzakiem to go pomijamy
            if (entity instanceof Animal && !((Animal) entity).isActive()) continue;

            entity.update(_board, iteration);
        }
        _renderer.render(_board.getMap());
    }

    public List<IUpdatable> getResults()
    {
        return _board.getUpdatableEntities();
    }

}
