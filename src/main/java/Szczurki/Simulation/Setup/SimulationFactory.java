package Szczurki.Simulation.Setup;

import Szczurki.Configuration.ConfigurationData;
import Szczurki.Configuration.SimulationSettings;
import Szczurki.Simulation.Board;
import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Simulation.Simulation;
import Szczurki.Simulation.Visualization.Console.ConsoleRenderer;
import Szczurki.Simulation.Visualization.IRenderer;
import Szczurki.Simulation.Visualization.Window.WindowRenderer;

public class SimulationFactory {
    public static Simulation getSimulation(ConfigurationData appConfig, SimulationSettings simulationSettings) {

        var map = new MapReader().getMap(simulationSettings.getLabyrinthFileName());

        var updatableEntities =
                new EntityPositioner(simulationSettings, appConfig.getAnimalNames())
                        .getPlacedEntities(map);

        var board = new Board(updatableEntities, map);

        IRenderer renderer = null;
        if (appConfig.getRendererType() != null)
            switch (appConfig.getRendererType()) {
                case CONSOLE:
                    renderer = new ConsoleRenderer(appConfig.getFrameTime());
                    break;
                case WINDOW:
                    renderer = new WindowRenderer(board.getMap(), appConfig.getFrameTime());
                    break;
            }
        else {
            renderer = new IRenderer() {
                @Override
                public void render(IEntity[][] entities) {
                }

                @Override
                public void stop() {
                }
            };
        }

        return new Simulation(simulationSettings, board, renderer);
    }
}
