package Szczurki.Simulation.Entities.Animals;

import Szczurki.Simulation.Board;
import Szczurki.Simulation.CauseOfFinish;
import Szczurki.Simulation.Entities.Guardian;
import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Simulation.Entities.Interfaces.IUpdatable;
import Szczurki.Simulation.Entities.Obstacle;
import Szczurki.Utilities.Vector;
import Szczurki.Simulation.Entities.Wall;

import java.util.ArrayList;
import java.util.Random;

public abstract class Animal implements IEntity, IUpdatable {

    //główna klasa po której dziedziczyć będą wszystkie zwierzęta
    protected final int speed, intelligence, strength, cooperation;
    final String name;
    protected final Vector pos;
    protected final Vector lastMove;
    protected ArrayList<Animal> neighbours;

    private boolean active;
    private int timeOfFinish;
    private CauseOfFinish causeOfFinish;

    Animal(int x, int y, String name, int speed, int intelligence, int strength, int cooperation) {
        pos = new Vector(x, y);
        lastMove = Vector.ZERO();

        this.name = name;
        this.speed = speed;
        this.intelligence = intelligence;
        this.strength = strength;
        this.cooperation = cooperation;
        active = true;

    }

    @Override
    public void update(Board board, int iteration) {

        var nextMove = chooseNextMove(board);

        if (nextMove == null) {
            board.remove(this, pos);
            timeOfFinish = iteration;
            causeOfFinish = CauseOfFinish.ESCAPE;
            return;
        }

        if (board.getEntityAt(Vector.add(pos, nextMove)) instanceof Obstacle) {
            var obstacle = (Obstacle) board.getEntityAt(Vector.add(pos, nextMove));
            if (obstacle.isActive()) {
                this.neighbours = getNeighbours(board);

                obstacle.interact(this);
                return;
            }
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
        var preferredMove = choosePreferredMove();
        if (preferredMove != null && canMove(preferredMove, board)) {
            return preferredMove;
        }

        //jeżeli nie jesteśmy koło wyjścia, oraz nie możemy wykonać preferowanego ruchu to losujemy nowy
        do {
            var random = new Random();
            var candidateMoveIndex = random.nextInt(possibleMoves.size());
            var candidateMove = possibleMoves.get(candidateMoveIndex);

            //usuwamy wybrany ruch z możliwych ruchów, żeby ich nie powtarzać
            possibleMoves.remove(candidateMoveIndex);

            if (canMove(candidateMove, board)) {
                return candidateMove;
            }

        } while (possibleMoves.size() != 0);

        //Jeżeli nie możemy się ruszyć to próbujemy się cofnąć
        if (canMove(lastMove.reversed(), board))
            return lastMove.reversed();

        //jeżeli skończą nam się możliwe ruchy to stoimy w miejscu
        return new Vector(0, 0);
    }

    protected boolean canMove(Vector moveBy, Board board) {
        var chosenTile = board.getEntityAt(Vector.add(pos, moveBy));

        //jeżeli natrafimy na inne zwierze lub na ścianę to nie możemy tam pójść
        if (chosenTile instanceof Wall || chosenTile instanceof Animal || chosenTile instanceof Guardian) {
            return false;
        }

        return true;
    }

    protected Vector choosePreferredMove() {
        return null;
    }

    private ArrayList<Animal> getNeighbours(Board board) {
        var currentNeighbours = new ArrayList<Animal>();

        var possibleDirections = Vector.getAllDirections();
        possibleDirections.forEach(dir ->
        {
            if (board.getEntityAt(Vector.add(pos, dir)) instanceof Animal) {
                currentNeighbours.add((Animal) board.getEntityAt(Vector.add(pos, dir)));
            }
        });

        return currentNeighbours;
    }

    public int getIntelligence() {
        return this.intelligence;
    }

    public int getStrength() {
        return this.strength;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean state) {
        active = state;
    }

    public int getTimeOfFinish() {
        return timeOfFinish;
    }

    public void setTimeOfFinish(int iteration) {
        timeOfFinish = iteration;
    }

    public CauseOfFinish getCauseOfFinish() {
        return causeOfFinish;
    }

    public void setCauseOfFinish(CauseOfFinish causeOfFinish) {
        this.causeOfFinish = causeOfFinish;
    }
}
