package Szczurki.Simulation;

import Szczurki.Configuration.SimulationSettings;
import Szczurki.Simulation.Entities.Animals.Animal;
import Szczurki.Simulation.Entities.Guardian;
import Szczurki.Simulation.Entities.Interfaces.IUpdatable;
import Szczurki.Simulation.Visualization.IRenderer;

import java.util.List;

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
        _renderer.stop();
    }

    public List<IUpdatable> getResults()
    {
        return _board.getUpdatableEntities();
    }

    private void turn(int iteration) {
        for (var entity : _board.getUpdatableEntities()) {
            if (entity instanceof Animal && ((Animal) entity).isActive()){
                int speed = ((Animal)entity).getSpeed();
                for(int i = 0; i < speed; i++) {
                    entity.update(_board, iteration);
                    _renderer.render(_board.getMap());
                }
            }
            if(entity instanceof Guardian) {
                entity.update(_board, iteration);
                _renderer.render(_board.getMap());
            }
        }
    }
}
