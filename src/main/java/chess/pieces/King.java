package chess.pieces;

import chess.BoardView;
import chess.Player;
import chess.Position;

import java.util.Set;

/**
 * The King class
 */
public class King extends Piece {
    public King(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'k';
    }

    @Override
    public Set<Position> getNextPositions(Position origin, BoardView boardView) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
}
