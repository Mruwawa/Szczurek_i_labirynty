package Szczurki.Simulation.Entities;

import Szczurki.Simulation.Board;
import Szczurki.Simulation.CauseOfFinish;
import Szczurki.Simulation.Entities.Animals.Animal;
import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Simulation.Entities.Interfaces.IUpdatable;
import Szczurki.Utilities.Vector;

import java.util.Random;

public class Guardian implements IEntity, IUpdatable {

    final int speed;
    final String name;
    protected final Vector pos;
    protected final Vector lastMove;

    public Guardian(int x, int y) {
        pos = new Vector(x, y);
        lastMove = Vector.ZERO();

        name = "Baca";
        speed = 2;
    }

    /**
     * @param board     plansza, na której odbywa się symulacja
     * @param iteration numer tury
     */
    @Override
    public void update(Board board, int iteration) {

        var nextMove = chooseNextMove(board, iteration);

        board.move(pos, nextMove);
        lastMove.set(nextMove);
    }

    protected Vector chooseNextMove(Board board, int iteration) {

        //deklarujemy zbiór możliwych ruchów oraz preferowanych ruchów (czyli takich, które zakończą się złapaniem zwierzęcia)

        var possibleMoves = Vector.getAllDirections();
        var preferredMoves = Vector.getAllDirections();

        //usunięcie możliwości cofania się ze zbioru możliwych ruchów (nie usuwamy jej ze zbioru preferowanych ruchów na wypadek, gdyby za strażnikiem pojawiło się zwierzę)

        possibleMoves.remove(lastMove.reversed());

        //jeśli strażnik jest obok wyjścia, to musi się cofnąć (bo nie może opuścić labiryntu)
        for (var move : possibleMoves) {
            if (board.isOutside(Vector.add(pos, move))) {
                return lastMove.reversed();
            }
        }

        //sprawdzenie, czy strażnik ma w zasięgu jakieś zwierzątko (jeśli jest ich więcej, złapane zostaje to, które było dostrzeżone jako pierwsze)
        do {
            var random = new Random();
            var candidateMoveIndex = random.nextInt(preferredMoves.size());
            var candidateMove = preferredMoves.get(candidateMoveIndex);

            //usuwamy wybrany ruch z możliwych ruchów, żeby ich nie powtarzać
            preferredMoves.remove(candidateMoveIndex);

            if (isAnimalThere(candidateMove, board.getMap())){

                    var animal = (Animal)board.getEntityAt(Vector.add(pos, candidateMove));

                    board.remove(animal,Vector.add(pos,candidateMove));
                    animal.setCauseOfFinish(CauseOfFinish.GUARDIAN);
                    animal.setTimeOfFinish(iteration);

                return candidateMove;
            }

        } while (preferredMoves.size() != 0);

        //jeśli strażnik nie ma możliwości załapania zwierzęcia wybiera losowy możliwy ruch

        do {
            var random = new Random();
            var candidateMoveIndex = random.nextInt(possibleMoves.size());
            var candidateMove = possibleMoves.get(candidateMoveIndex);

            //usuwamy wybrany ruch z możliwych ruchów, żeby ich nie powtarzać
            possibleMoves.remove(candidateMoveIndex);

            if (canMove(candidateMove, board.getMap())) {
                return candidateMove;
            }

        } while (possibleMoves.size() != 0);

        //jeśli nie ma innych możliwych ruchów, strażnik cofa się
        return lastMove.reversed();
    }

    /**
     * tutaj sobie piszesz komentarz
     * @param moveBy parametr 1 to wektor który cośtam cośtam
     * @param entities
     * @return
     */
    protected boolean canMove(Vector moveBy, IEntity[][] entities) {
        var chosenTile = entities[pos.x + moveBy.x][pos.y + moveBy.y];

        //strażnik nie może przemieścić się w miejsce gdzie jest ściana
        return !(chosenTile instanceof Wall || chosenTile instanceof Obstacle);
    }

    protected boolean isAnimalThere(Vector moveBy, IEntity[][] entities){
        var chosenTile = entities[pos.x + moveBy.x][pos.y + moveBy.y];

        return chosenTile instanceof Animal;
    }

}
