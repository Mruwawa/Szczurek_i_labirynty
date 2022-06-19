package Szczurki.Simulation;

import Szczurki.Configuration.SimulationSettings;
import Szczurki.Simulation.Entities.Animals.Animal;
import Szczurki.Simulation.Visualization.IRenderer;

public class Simulation {

    private final SimulationSettings _settings;
    private final Board _board;
    private final IRenderer _renderer;

    public Simulation(SimulationSettings settings, Board board, IRenderer renderer) {
        _settings = settings;
        _board = board;
        _renderer = renderer;
    }

    public void run() {
        _renderer.render(_board.getMap());

        for (int i = 0; i < _settings.getTurnCount(); i++) {
            turn(i);
            if(!_board.areThereAnyActiveAnimalsLeft())
                break;
        }
        System.out.println("Symulacja zakończona");
        _renderer.stop();
    }

    private void turn(int iteration) {

        for (var entity : _board.getUpdatableEntities()) {

            if (entity instanceof Animal && !((Animal) entity).isActive()) continue;

            entity.update(_board, iteration);
        }
        _renderer.render(_board.getMap());
    }
}
