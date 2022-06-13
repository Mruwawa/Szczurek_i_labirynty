package Szczurki.Configuration;

import java.util.ArrayList;

public class ConfigurationData {
    public int repeatCount;
    public ArrayList<SimulationSettings> simulationSettingsList;

    public int getRepeatCount() {
        return repeatCount;
    }

    public ArrayList<SimulationSettings> getSimulationSettingsList()
    {
        return simulationSettingsList;
    }
}
