package Szczurki.Simulation.Setup;

import Szczurki.Configuration.SimulationSettings;
import Szczurki.Simulation.Entities.Animals.*;
import Szczurki.Simulation.Entities.Guardian;
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
        _possibleNames = new ArrayList<>();
    }

    @Override
    public void placeEntities(IEntity[][] map, List<IUpdatable> updatableEntities) {
//umieszczenie strażnika na mapie


        _settings.animalCounts.forEach((animalName, count) ->
        {
            //rozmieszczenie zwierząt na mapie
            for (int i = 0; i < count; i++) {

                Vector place;
                do {
                    place = Vector.getRandomVector(map[0].length, map.length);
                } while (map[place.x][place.y] != null);

                Animal animal = switch (animalName) {
                    case "gerbils" -> new Gerbil(place.x, place.y, pickName());
                    case "hamsters" -> new Hamster(place.x, place.y, pickName());
                    case "mice" -> new Mouse(place.x, place.y, pickName());
                    case "mousedeer" -> new Mousedeer(place.x, place.y, pickName());
                    case "rats" -> new Rat(place.x, place.y, pickName());
                    default -> null;
                };

                map[place.x][place.y] = animal;
                updatableEntities.add(animal);
            }
        });

        Vector guardianPlace;

        do {
            guardianPlace = Vector.getRandomVector(_settings.mapWidth, _settings.mapHeight);
        } while (map[guardianPlace.x][guardianPlace.y] != null);

        Guardian guardian = new Guardian(guardianPlace.x, guardianPlace.y);

        map[guardianPlace.x][guardianPlace.y] = guardian;
        updatableEntities.add(guardian);
    }

    private String pickName() {
        if (_possibleNames.size() == 0) _possibleNames = new ArrayList<>(_settings.animalNames);

        var random = new Random();
        var index = random.nextInt(_possibleNames.size());
        var pickedName = _possibleNames.get(index);
        _possibleNames.remove(pickedName);

        return pickedName;
    }
}
