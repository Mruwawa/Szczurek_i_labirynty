package Szczurki.Simulation;

import Szczurki.Configuration.SimulationSettings;
import Szczurki.Simulation.Entities.IEntity;

import java.util.ArrayList;

public class Simulation {

    private final ArrayList<IEntity> entities;
    private final SimulationSettings _settings;

    public Simulation(SimulationSettings settings) {
        _settings = settings;
        entities = new ArrayList<>();
    }

    public void run() {
    }

    private void initializeEntities() {

    }

}
