package Szczurki.Simulation;

import Szczurki.Configuration.SimulationSettings;
import Szczurki.Simulation.Entities.Animals.*;
import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Simulation.Entities.Interfaces.IUpdatable;
import Szczurki.Utilities.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityPositioner implements IEntityPositioner {
    private final SimulationSettings _settings;
    private ArrayList<String> _possibleNames;

    public EntityPositioner(SimulationSettings settings) {
        _settings = settings;
        _possibleNames = getNameList();
    }


    @Override
    public void placeEntities(IEntity[][] map, List<IUpdatable> updatableEntities) {

        _settings.animalCounts.forEach((animalName, count) ->
        {
            for (int i = 0; i < count; i++) {
                Vector place;
                do {
                    place = getRandomVector(_settings.mapWidth, _settings.mapHeight);
                } while (map[place.x][place.y] != null);


                Animal animal = null;

                switch (animalName) {
                    case "gerbils":
                        animal = new Gerbil(place.x, place.y, pickName());
                        break;
                    case "hamsters":
                        animal = new Hamster(place.x, place.y, pickName());
                        break;
                    case "mice":
                        animal = new Mouse(place.x, place.y, pickName());
                        break;
                    case "mousedeer":
                        animal = new Mousedeer(place.x, place.y, pickName());
                        break;
                    case "rats":
                        animal = new Rat(place.x, place.y, pickName());
                        break;
                }

                map[place.x][place.y] = animal;
                updatableEntities.add(animal);
            }
        });

    }


    private Vector getRandomVector(int maxX, int maxY) {
        var random = new Random();

        var chosenX = random.nextInt(maxX);
        var chosenY = random.nextInt(maxY);

        return new Vector(chosenX, chosenY);
    }

    private String pickName() {
        if (_possibleNames.size() == 0) _possibleNames = getNameList();

        var random = new Random();
        var index = random.nextInt(_possibleNames.size());
        var pickedName = _possibleNames.get(index);
        _possibleNames.remove(pickedName);

        return pickedName;
    }


    private ArrayList<String> getNameList() {
        return new ArrayList<>(List.of(new String[]{
                "Kłapcio",
                "Dee dee",
                "Remi",
                "Reksio",
                "Derpy",
                "Azor",
                "Jest 23 a ja musze wymyslac nazwy zwierzat",
                "Mario",
                "Luigi",
                "Waluigi"}));
    }
}
