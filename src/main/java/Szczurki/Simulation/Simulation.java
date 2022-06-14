package Szczurki.Simulation;

import Szczurki.Configuration.SimulationSettings;
import Szczurki.Simulation.Visualization.ConsoleRenderer;
import Szczurki.Simulation.Visualization.IRenderer;

import java.util.List;

public class Simulation {

    private final Board board;

    private final SimulationSettings _settings;
    private final IRenderer _renderer;

    public Simulation(SimulationSettings settings){
        _settings = settings;
        board = new Board(settings);
        _renderer = new ConsoleRenderer();

        board.initializeEntities();
    }

    public void run() {
        _renderer.render(board.map);

        for (int i = 0; i < _settings.turnCount; i++) {
            turn();
        }
    }

    private void turn() {
        for (var entity :
                board.updatableEntities) {
            entity.update(board);

        }
        board.removeInactiveEntities();
        _renderer.render(board.map);
    }
}
