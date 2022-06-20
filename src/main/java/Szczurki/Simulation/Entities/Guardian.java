package Szczurki.Simulation.Entities;

import Szczurki.Simulation.Board;
import Szczurki.Simulation.CauseOfFinish;
import Szczurki.Simulation.Entities.Animals.Animal;
import Szczurki.Simulation.Entities.Interfaces.IEntity;
import Szczurki.Simulation.Entities.Interfaces.IUpdatable;
import Szczurki.Utilities.Vector;

import java.util.Random;

//

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

        //sprawdzenie, czy strażnik ma w zasięgu jakieś zwierzątko (jeśli jest ich więcej, złapane zostaje to, które było dostrzeżone jako pierwsze)
        do {
            var random = new Random();
            var candidateMoveIndex = random.nextInt(preferredMoves.size());
            var candidateMove = preferredMoves.get(candidateMoveIndex);

            //usuwamy wybrany ruch z możliwych ruchów, żeby ich nie powtarzać
            preferredMoves.remove(candidateMoveIndex);

            if (isAnimalThere(candidateMove, board)){

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

            if (canMove(candidateMove, board)) {
                return candidateMove;
            }

        } while (possibleMoves.size() != 0);

        //jeśli nie ma innych możliwych ruchów, strażnik cofa się
        return lastMove.reversed();
    }

    protected boolean canMove(Vector moveBy, Board board) {
        if(!board.isOutside(Vector.add(pos,moveBy))) {
            var chosenTile = board.getEntityAt(Vector.add(pos, moveBy));

            //strażnik nie może przemieścić się w miejsce gdzie jest ściana
            return !(chosenTile instanceof Wall || chosenTile instanceof Obstacle);
        }
        return false;
    }

    protected boolean isAnimalThere(Vector moveBy, Board board){
        if(!board.isOutside(Vector.add(pos,moveBy))){
            var chosenTile = board.getEntityAt(Vector.add(pos, moveBy));
            return chosenTile instanceof Animal;
        }
        return false;
    }

}
