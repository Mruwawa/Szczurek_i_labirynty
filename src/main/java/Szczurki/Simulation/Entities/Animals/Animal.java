package Szczurki.Simulation.Entities.Animals;

import Szczurki.Simulation.Board;
import Szczurki.Simulation.Entities.Guardian;
import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Simulation.Entities.Interfaces.IUpdatable;
import Szczurki.Utilities.Vector;
import Szczurki.Simulation.Entities.Wall;

import java.util.Objects;
import java.util.Random;

public abstract class Animal implements IEntity, IUpdatable {

    //główna klasa po której dziedziczyć będą wszystkie zwierzęta
    final int speed, intelligence, strength, cooperation;
    final String name;
    protected final Vector pos;
    protected final Vector lastMove;

    Animal(int x, int y, String name, int speed, int intelligence, int strength, int cooperation) {
        pos = new Vector(x, y);
        lastMove = Vector.ZERO();

        this.name = name;
        this.speed = speed;
        this.intelligence = intelligence;
        this.strength = strength;
        this.cooperation = cooperation;
    }

    @Override
    public void update(Board board) {

        var nextMove = chooseNextMove(board);

        if (nextMove == null) {
            System.out.println("Zwierzątko o imieniu" + this.name + " wyszło z labiryntu!");
            board.remove(this, pos);
            return;
        }

        board.move(pos, nextMove);
        lastMove.set(nextMove);
    }

    protected Vector chooseNextMove(Board board) {
        //deklarujemy zbiór możliwych ruchów
        var possibleMoves = Vector.getAllDirections();
        //usuwamy z nich odwrotność poprzedniego ruchu, żeby się nie cofać
        possibleMoves.remove(lastMove.reversed());

        //jeśli znajdujemy się obok wyjścia to wykonujemy ten ruch i wychodzimy z labiryntu
        //zwracamy null, żeby odróżnić ten typ ruchu od zwykłego ruchu
        //oraz dlatego że nie możemy wykroczyć poza zakres planszy (arraya entities)
        for (var move : possibleMoves) {
            if (board.isOutside(Vector.add(pos, move))) {
                return null;
            }
        }

        //wybieramy preferowany przez zwierzaka ruch (każda klasa ma inną implementację)
        //i jeżeli możemy go wykonać to to robimy
        var preferredMove = choosePreferredMove(board.map);
        if (preferredMove != null && canMove(preferredMove, board.map)) {
            return preferredMove;
        }

        //jeżeli nie jesteśmy koło wyjścia, oraz nie możemy wykonać preferowanego ruchu to losujemy nowy
        do {
            var random = new Random();
            var candidateMoveIndex = random.nextInt(possibleMoves.size());
            var candidateMove = possibleMoves.get(candidateMoveIndex);

            //usuwamy wybrany ruch z możliwych ruchów, żeby ich nie powtarzać
            possibleMoves.remove(candidateMoveIndex);

            if (canMove(candidateMove, board.map)) {
                return candidateMove;
            }

        } while (possibleMoves.size() != 0);

        //Jeżeli nie możemy się ruszyć to próbujemy się cofnąć
        if (canMove(lastMove.reversed(), board.map))
            return lastMove.reversed();

        //jeżeli skończą nam się możliwe ruchy to stoimy w miejscu
        return new Vector(0, 0);
    }

    protected boolean canMove(Vector moveBy, IEntity[][] entities) {
        var chosenTile = entities[pos.x + moveBy.x][pos.y + moveBy.y];

        //jeżeli natrafimy na inne zwierze lub na ścianę to nie możemy tam pójść
        if (chosenTile instanceof Wall || chosenTile instanceof Animal || chosenTile instanceof Guardian) {
            return false;
        }

        return true;
    }

    protected Vector choosePreferredMove(IEntity[][] entities) {
        return null;
    }

}