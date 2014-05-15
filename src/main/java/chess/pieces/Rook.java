package chess.pieces;

import chess.BoardView;
import chess.Player;
import chess.Position;

import java.util.Set;

/**
 * The 'Rook' class
 */
public class Rook extends Piece {

    public Rook(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'r';
    }

    @Override
    public Set<Position> getNextPositions(Position origin, BoardView boardView) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
}
