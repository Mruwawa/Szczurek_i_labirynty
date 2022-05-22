package Szczurki.Simulation;

import Szczurki.Configuration.SimulationSettings;
import Szczurki.Simulation.Entities.Animals.Rat;
import Szczurki.Simulation.Entities.IEntity;
import Szczurki.Simulation.Entities.IUpdatable;
import Szczurki.Simulation.Entities.Wall;
import Szczurki.Simulation.Visualization.ConsoleRenderer;
import Szczurki.Simulation.Visualization.IRenderer;

public class Simulation {

    private IEntity[][] entities;
    private final SimulationSettings _settings;
    private final IRenderer _renderer;

    public Simulation(SimulationSettings settings) {
        _settings = settings;
        entities = new IEntity[_settings.mapHeight][_settings.mapWidth];
        //docelowo renderer będzie wybierany w ustawieniach i przekazywany przez konstruktor
        _renderer = new ConsoleRenderer();

        initializeEntities();
    }

    public void run() {
        for (int i = 0; i < _settings.turnCount; i++) {
            turn();
        }
    }

    private void initializeEntities() {

        //docelowo obiekty będą rozmieszczane losowo
        entities[1][4] = new Rat(1, 4, "Kłapcio");

        //docelowo te ściany będą wczywywane z pliku
        entities[0][5] = new Wall();
        entities[1][5] = new Wall();
        entities[2][5] = new Wall();
        entities[3][5] = new Wall();
        entities[4][5] = new Wall();
        entities[5][5] = new Wall();
        entities[6][5] = new Wall();

        entities[0][0] = new Wall();
        entities[0][1] = new Wall();
        entities[0][2] = new Wall();
        entities[0][3] = new Wall();
        entities[0][4] = new Wall();
        entities[0][5] = new Wall();


        entities[1][3] = new Wall();
        entities[2][3] = new Wall();
        entities[3][3] = new Wall();
        entities[4][3] = new Wall();
        entities[5][3] = new Wall();

        //pokazujemy stan początkowy
        _renderer.render(entities);
    }

    private void turn() {
        //tworzymy nowy array reprezentujący planszę w następnej turze i na nim ustawiamy obiekty
        var nextTurnEntities = new IEntity[_settings.mapHeight][_settings.mapWidth];

        for (int y = 0; y < _settings.mapHeight; y++) {
            for (int x = 0; x < _settings.mapWidth; x++) {

                var entity = entities[y][x];
                if (entity instanceof IUpdatable) {
                    ((IUpdatable) entity).update(entities, nextTurnEntities);
                }

                if (entity instanceof Wall) {
                    nextTurnEntities[y][x] = entity;
                }
            }
        }
        //po ustawieniu obiektow na nowej mapie zamieniamy ją ze starą
        entities = nextTurnEntities;

        _renderer.render(entities);
    }
}
