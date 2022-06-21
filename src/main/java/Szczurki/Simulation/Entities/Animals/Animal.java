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

/**
 * Główna klasa reprezentująca zwierzęta
 * implementuje interfejsy:
 * IEntity - który pozwala na umieszczenie jej instancji na mapie
 * IUpdateable - który pozwala na aktualizowanie stanu w trakcie trwania symulacji
 */
public abstract class Animal implements IEntity, IUpdatable {

    protected final int intelligence, strength, cooperation;
    final String name;
    public final Vector pos;
    protected final Vector lastMove;
    protected ArrayList<Animal> neighbours;

    private boolean active;
    private int timeOfFinish;
    private CauseOfFinish causeOfFinish;

    Animal(int x, int y, String name, int intelligence, int strength, int cooperation) {
        pos = new Vector(x, y);
        lastMove = Vector.ZERO();

        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.cooperation = cooperation;
        active = true;

    }


    /**
     * Metoda służy do aktualizowania stanu zwierzęcia
     * Szuka ruchu, przeprowadza interakcje z przeszkodami
     * oraz wykonuje ruchy
     * @param board     plansza, na której odbywa się symulacja
     * @param iteration numer tury
     */
    @Override
    public void update(Board board, int iteration) {

        var nextMove = chooseNextMove(board);

        //jeżeli następny ruch jest nullem oznacza to, że istnieje możliwość wyjścia z labiryntu
        //jeśli jest taka możlowiość to zwierzątko to zrobi
        if (nextMove == null) {
            board.remove(this, pos);
            timeOfFinish = iteration;
            causeOfFinish = CauseOfFinish.ESCAPE;
            return;
        }
        //sprawdzamy, czy w miejscu, na które chcemy wejść znajduje się przeszkoda
        if (board.getEntityAt(Vector.add(pos, nextMove)) instanceof Obstacle) {
            var obstacle = (Obstacle) board.getEntityAt(Vector.add(pos, nextMove));

            //jeżeli jest aktywna to przeprowadzamy interakcję i czekamy
            if (obstacle.isActive()) {
                this.neighbours = getNeighbours(board);
                obstacle.interact(this);
                //wychodzimy z metody i nie wykonujemy ruchu,
                // ponieważ zużyliśmy tą turę na interakcję z przeszkodą
                return;
            }
        }

        //ruszamy się i zapamiętujemy ostatni ruch
        board.move(pos, nextMove);
        lastMove.set(nextMove);
    }

    /**
     * @param board Plansza, na której odbywa się symulacja
     * @return wektor reprezentujący wybrany ruch
     */
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
        //i jeżeli możemy go wykonać to robimy
        var preferredMove = choosePreferredMove();
        if (preferredMove != null && canMove(preferredMove, board)) {
            return preferredMove;
        }

        //jeżeli nie jesteśmy koło wyjścia oraz nie możemy wykonać preferowanego ruchu to losujemy nowy
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

    /**
     * @param moveBy Wektor o który chcemy się przesunąć
     * @param board Plansza
     * @return Czy ruch jest możliwy
     */
    protected boolean canMove(Vector moveBy, Board board) {
        var chosenTile = board.getEntityAt(Vector.add(pos, moveBy));

        //jeżeli natrafimy na ścianę, inne zwierze lub strażnika to nie możemy tam pójść
        if (chosenTile instanceof Wall || chosenTile instanceof Animal || chosenTile instanceof Guardian) {
            return false;
        }

        return true;
    }

    /**
     * @return Ruch preferowany przez zwierzaka
     */
    protected Vector choosePreferredMove() {
        return null;
    }

    /**
     * @param board Plansza
     * @return Lista zwierząt, które znajdują się obok tego zwierzaka
     */
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
