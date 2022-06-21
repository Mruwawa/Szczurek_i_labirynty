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

/**
 * Klasa odpowiedzialna za rozmieszczanie agentów na mapie
 */
public class EntityPositioner implements IEntityPositioner {

    private final ArrayList<String> _animalNames;
    private final SimulationSettings _settings;

    private ArrayList<String> _currentPossibleNames;

    public EntityPositioner(SimulationSettings settings, ArrayList<String> animalNames) {
        _settings = settings;
        _animalNames = animalNames;
        _currentPossibleNames = new ArrayList<>();
    }

    /**
     * Metoda rozmieszcza na mapie agentów według ustawień symulacji
     * @param map Tablica reprezentująca mapę, na niej zostaną rozstawieni agenci
     * @return Lista agentów
     */
    @Override
    public List<IUpdatable> getPlacedEntities(IEntity[][] map) {
        var updatableEntities = new ArrayList<IUpdatable>();
        //przygotowujemy listę dostępnych miejsc
        var freeSpaces = getFreeSpaces(map);
        var rand = new Random();

        //rozmieszczenie zwierząt na mapie
        //dla każdego typu zwierzęcia rozmieszczamy je
        //określoną w ustawieniach ilość razy
        _settings.getAnimalCounts().forEach((animalName, count) ->
        {
            for (int i = 0; i < count; i++) {
                //Jeśli nie da się zmieścić więcej zwierząt na mapie
                //Wyświetlamy komunikat i wychodzimy z funkcji (z wyrażenia lambda)
                if (freeSpaces.size() == 0) {
                    System.out.println("Zabrakło miejsc na mapie dla wszystkich zwierząt!");
                    return;
                }
                //wybieramy losowe miejsce z dostępnych
                var index = rand.nextInt(freeSpaces.size());
                var place = freeSpaces.get(index);
                //usuwamy to miejsce z dostępnych
                freeSpaces.remove(place);

                Animal animal = null;
                //tworzymy odpowiednią instancję zwierzaka
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

                //wstawiamy zwierzaka na mapę
                map[place.x][place.y] = animal;
                //dodajemy zwierzaka do listy agentów
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

    /**
     * @return Wybrane losowo imię zwierzaka
     */
    private String pickName() {
        //Jeśli skończyły nam się imiona, wypełniamy listę od nowa
        if (_currentPossibleNames.size() == 0) _currentPossibleNames = new ArrayList<>(_animalNames);

        //Wybieramy losowe imię z listy dostępnych
        var random = new Random();
        var index = random.nextInt(_currentPossibleNames.size());
        var pickedName = _currentPossibleNames.get(index);
        //Usuwamy to imię z listy dostępnych
        _currentPossibleNames.remove(pickedName);

        return pickedName;
    }

    /**
     * @param map Tablica reprezentująca mapę
     * @return Lista pustych miejsc
     */
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
