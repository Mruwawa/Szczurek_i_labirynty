package Szczurki.Configuration;

import Szczurki.Results.ResultsWriterType;
import Szczurki.Simulation.Visualization.RendererType;

import java.util.ArrayList;

/**
 * Klasa odpowiedzialna za trzymanie danych konfiguracyjnych całej aplikacji
 */
public class ConfigurationData {
    private int repeatCount;
    private ArrayList<SimulationSettings> simulationSettingsList;
    private ArrayList<String> animalNames;
    private RendererType rendererType;
    private int frameTime;
    private ResultsWriterType resultsWriterType;


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

    public ResultsWriterType getResultsWriterType() {
        return resultsWriterType;
    }
}