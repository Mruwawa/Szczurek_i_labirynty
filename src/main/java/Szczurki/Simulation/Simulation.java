package Szczurki.Simulation;

import Szczurki.Configuration.SimulationSettings;
import Szczurki.Simulation.Visualization.Console.ConsoleRenderer;
import Szczurki.Simulation.Visualization.IRenderer;
import Szczurki.Simulation.Visualization.Window.WindowRenderer;

public class Simulation {

    private final Board board;

    private final SimulationSettings _settings;
    private final IRenderer _renderer;

    public Simulation(SimulationSettings settings){
        _settings = settings;
        board = new Board(settings);
        board.initializeEntities();

        _renderer = new WindowRenderer(board.map);
//        _renderer = new ConsoleRenderer();
    }

    public void run() {
        _renderer.render(board.map);

        for (int i = 0; i < _settings.turnCount; i++) {
            turn();
            if(board.updatableEntities.size() == 1) {
                break;
            }
        }
        System.out.println("Symulacja zakończona");
        _renderer.stop();
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
