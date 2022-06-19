package Szczurki.Configuration;

import java.util.ArrayList;

public class ConfigurationData {
    private int repeatCount;
    private ArrayList<SimulationSettings> simulationSettingsList;
    private ArrayList<String> animalNames;
    private RendererType rendererType;
    private int frameTime;


    public int getRepeatCount() {
        return repeatCount;
    }

    public ArrayList<SimulationSettings> getSimulationSettingsList() {
        return simulationSettingsList;
    }

    public ArrayList<String> getAnimalNames() {
        return animalNames;
    }

    public RendererType getRendererType() {
        return rendererType;
    }

    public int getFrameTime() {
        return frameTime;
    }
}