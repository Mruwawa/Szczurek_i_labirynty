package Szczurki.Simulation.Entities.Animals;

import Szczurki.Simulation.Entities.IEntity;
import Szczurki.Simulation.Entities.IUpdatable;
import Szczurki.Simulation.Entities.Vector;
import Szczurki.Simulation.Entities.Wall;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract class Animal implements IEntity, IUpdatable {
    //główna klasa po której dziedziczyć będą wszystkie zwierzęta
    int speed, intelligence, strength, cooperation;
    String name;
    protected final Vector pos;
    protected final Vector lastPos;

    Animal(int y, int x, String name, int speed, int intelligence, int strength, int cooperation) {
        pos = new Vector(y, x);
        lastPos = pos;
        this.name = name;
        this.speed = speed;
        this.intelligence = intelligence;
        this.strength = strength;
        this.cooperation = cooperation;
    }

    @Override
    public void update(IEntity[][] entities, IEntity[][] nextTurnEntities) {

        var nextMove = chooseNextMove(entities);
        if (nextMove == null) {
            System.out.println("Szczur wyszedł");
            return;
        }

        nextTurnEntities[pos.y + nextMove.y][pos.x + nextMove.x] = this;

        lastPos.x = pos.x;
        lastPos.y = pos.y;

        pos.y += nextMove.y;
        pos.x += nextMove.x;

    }

    protected Vector chooseNextMove(IEntity[][] entities) {
        //deklarujemy zbiór możliwych ruchów
        var possibleMoves = new ArrayList<>(List.of(new Vector[]
                {
                        new Vector(0, 1),
                        new Vector(-1, 1),
                        new Vector(-1, 0),
                        new Vector(-1, -1),
                        new Vector(0, -1),
                        new Vector(1, -1),
                        new Vector(1, 0),
                        new Vector(1, 1)
                }));

        //jeśli znajdujemy się obok wyjścia to wykonujemy ten ruch i wychodzimy z labiryntu
        //zwracamy null, żeby odróżnić ten typ ruchu od zwykłego ruchu
        //oraz dlatego że nie możemy wykroczyć poza zakres planszy (arraya entities)
        for (var move : possibleMoves) {
            if (pos.y + move.y < 0 ||
                    pos.y + move.y >= entities.length ||
                    pos.x + move.x < 0 ||
                    pos.x + move.x >= entities[0].length) {
                return null;
            }
        }

        //wybieramy preferowany przez zwierzaka ruch (każda klasa ma inną implementację)
        //i jeżeli możemy go wykonać to to robimy
        var preferredMove = choosePreferredMove(entities);
        if (preferredMove != null && canMove(preferredMove, entities)) {
            return preferredMove;
        }

        //jeżeli nie jesteśmy koło wyjścia, oraz nie możemy wykonać preferowanego ruchu to losujemy nowy
        do {
            var random = new Random();
            var candidatePositionIndex = random.nextInt(possibleMoves.size());
            var candidatePosition = possibleMoves.get(candidatePositionIndex);

            //usuwamy wybrany ruch z możliwych ruchów, żeby ich nie powtarzać
            possibleMoves.remove(candidatePositionIndex);

            if (canMove(candidatePosition, entities)) {
                return candidatePosition;
            }

        } while (possibleMoves.size() != 0);

        //jeżeli skończą nam się możliwe ruchy to stoimy w miejscu
        return new Vector(0, 0);
    }

    protected boolean canMove(Vector candidatePosition, IEntity[][] entities) {

        //jeżeli na miejscu w które chcemy isc stoi sciana to nie możemy tam isc
        if (entities[pos.y + candidatePosition.y][pos.x + candidatePosition.x] instanceof Wall) {
            return false;
        }
        return true;
    }

    protected Vector choosePreferredMove(IEntity[][] entities) {
        return null;
    }

}
