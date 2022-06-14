package Szczurki.Configuration;

import java.util.ArrayList;

public class ConfigurationData {
    private int repeatCount;
    private ArrayList<SimulationSettings> simulationSettingsList;
    private ArrayList<String> animalNames;

    public int getRepeatCount() {
        return repeatCount;
    }

    public ArrayList<SimulationSettings> getSimulationSettingsList()
    {
        return simulationSettingsList;
    }

    public ArrayList<String> getAnimalNames() {
        return animalNames;
    }
}
