package Szczurki.Configuration;

import java.util.ArrayList;

public interface IConfiguration {
    ArrayList<SimulationSettings> getSimulationSettingsList();
    int getRepeatCount();
}