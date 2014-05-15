package chess.pieces;

import chess.BoardView;
import chess.Player;
import chess.Position;

import java.util.Collection;
import java.util.Set;

/**
 * The Pawn
 */
public class Pawn extends Piece {

    public Pawn(Player owner) {
        super(owner, false);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'p';
    }

    @Override
    public Set<Position> getNextPositions(Position origin, BoardView boardView) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    protected Collection<int[]> getSimpleMoveOffsets() {
        throw new UnsupportedOperationException("Not implemented for pawns.");
    }
}
