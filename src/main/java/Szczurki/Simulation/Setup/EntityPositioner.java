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

    private final ArrayList<String> _animalNames;
    private final SimulationSettings _settings;

    private ArrayList<String> _currentPossibleNames;

    public EntityPositioner(SimulationSettings settings, ArrayList<String> animalNames) {
        _settings = settings;
        _animalNames = animalNames;
        _currentPossibleNames = new ArrayList<>();
    }

    @Override
    public List<IUpdatable> getPlacedEntities(IEntity[][] map) {
        var updatableEntities = new ArrayList<IUpdatable>();
        var freeSpaces = getFreeSpaces(map);
        var rand = new Random();

        _settings.getAnimalCounts().forEach((animalName, count) ->
        {
            //rozmieszczenie zwierząt na mapie
            for (int i = 0; i < count; i++) {

                if (freeSpaces.size() == 0) {
                    System.out.println("Zabrakło miejsc na mapie dla wszystkich zwierząt!");
                    return;
                }
                var index = rand.nextInt(freeSpaces.size());
                var place = freeSpaces.get(index);
                freeSpaces.remove(place);

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
        //umieszczenie strażnika na mapie

        for (int i = 0; i < _settings.getGuardianCount(); i++) {

            if (freeSpaces.size() == 0) {
                System.out.println("Zabrakło miejsc na mapie dla wszystkich zwierząt!");
                return updatableEntities;
            }
            var index = rand.nextInt(freeSpaces.size());
            var guardianPlace = freeSpaces.get(index);
            freeSpaces.remove(guardianPlace);

            Guardian guardian = new Guardian(guardianPlace.x, guardianPlace.y);

            map[guardianPlace.x][guardianPlace.y] = guardian;
            updatableEntities.add(guardian);
        }
        return updatableEntities;
    }

    private String pickName() {
        if (_currentPossibleNames.size() == 0) _currentPossibleNames = new ArrayList<>(_animalNames);

        var random = new Random();
        var index = random.nextInt(_currentPossibleNames.size());
        var pickedName = _currentPossibleNames.get(index);
        _currentPossibleNames.remove(pickedName);

        return pickedName;
    }

    private List<Vector> getFreeSpaces(IEntity[][] map) {
        var freeSpaces = new ArrayList<Vector>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == null) freeSpaces.add(new Vector(i, j));
            }
        }
        return freeSpaces;
    }
}
