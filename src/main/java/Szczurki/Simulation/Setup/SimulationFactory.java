package Szczurki.Simulation.Setup;

import Szczurki.Configuration.ConfigurationData;
import Szczurki.Configuration.SimulationSettings;
import Szczurki.Simulation.Board;
import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Simulation.Simulation;
import Szczurki.Simulation.Visualization.Console.ConsoleRenderer;
import Szczurki.Simulation.Visualization.IRenderer;
import Szczurki.Simulation.Visualization.Window.WindowRenderer;

/**
 * Klasa odpowiedzialna za tworzenie instancji symulacji według przekazanych ustawień
 */
public class SimulationFactory {
    /**
     * @param appConfig Konfiguracja aplikacji
     * @param simulationSettings Ustawienia poszczególnej symulacji
     * @return Odpowiednio skonfigurowana instancja symulacji
     */
    public static Simulation getSimulation(ConfigurationData appConfig, SimulationSettings simulationSettings) {

        //wczytujemy mapę z pliku
        var map = new MapReader().getMap(simulationSettings.getLabyrinthFileName());

        //rozmieszczamy agentów na mapie, oraz tworzymy ich listę
        var updatableEntities =
                new EntityPositioner(simulationSettings, appConfig.getAnimalNames())
                        .getPlacedEntities(map);

        //tworzymy planszę
        var board = new Board(updatableEntities, map);

        //wybieramy odpowiedni renderer i tworzymy jego instancję
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
            //Jeśli nie został podany w ustawieniach jego typ
            //to zwracamy pustą implementację
            //pozwala to na wyłączenie wyświetlania symulacji
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
