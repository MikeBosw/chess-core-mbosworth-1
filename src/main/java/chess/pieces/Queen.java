package chess.pieces;

import chess.BoardView;
import chess.Player;
import chess.Position;

import java.util.Set;

/**
 * The Queen
 */
public class Queen extends Piece{
    public Queen(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'q';
    }

    @Override
    public Set<Position> getNextPositions(Position origin, BoardView boardView) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
}
