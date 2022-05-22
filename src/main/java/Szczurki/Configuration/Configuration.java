package Szczurki.Configuration;


import java.util.ArrayList;

public class Configuration implements IConfiguration{
    private final int repeatCount = 1; //Ilość powtórzeń każdej z symulacji
    private final ArrayList<SimulationSettings> simulationSettingsList; //ustawienia dla każdej przeprowadzonej symulacji

    public Configuration(String filename)
    {
        simulationSettingsList = new ArrayList<>();
        simulationSettingsList.add(new SimulationSettings());
    }

    @Override
    public int getRepeatCount() {
        return repeatCount;
    }

    public ArrayList<SimulationSettings> getSimulationSettingsList()
    {
        return simulationSettingsList;
    }
}
