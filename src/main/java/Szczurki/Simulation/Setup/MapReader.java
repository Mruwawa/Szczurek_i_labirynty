package Szczurki.Simulation.Setup;

import Szczurki.Configuration.SimulationSettings;
import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Simulation.Entities.Obstacle;
import Szczurki.Simulation.Entities.Wall;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MapReader implements IMapReader {

    public IEntity[][] getMap(SimulationSettings settings) {

        String fileName = settings.fileName;
        IEntity[][] map = new IEntity[settings.mapWidth][settings.mapHeight];
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String[] labirynth = new String[settings.mapHeight];


            map = new IEntity[settings.mapWidth][settings.mapHeight];

            for (int i = 0; i < settings.mapHeight; ++i) {

                labirynth[i] = reader.readLine();

            }

            for (int i = 0; i < settings.mapHeight; ++i) {
                for (int j = 0; j < settings.mapWidth; ++j) {
                    if (labirynth[i].charAt(j) == '#') {
                        map[j][i] = new Wall();
                    }

                    if (labirynth[i].charAt(j) == '@') {
                        map[j][i] = new Obstacle(10, 10);
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;

    }

}
