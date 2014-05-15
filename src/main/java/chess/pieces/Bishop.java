package chess.pieces;

import chess.BoardView;
import chess.Player;
import chess.Position;

import java.util.Set;

/**
 * The 'Bishop' class
 */
public class Bishop extends Piece {
    public Bishop(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'b';
    }

    @Override
    public Set<Position> getNextPositions(Position origin, BoardView boardView) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
}
