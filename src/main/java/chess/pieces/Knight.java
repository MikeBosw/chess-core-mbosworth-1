package chess.pieces;

import chess.BoardView;
import chess.Player;
import chess.Position;

import java.util.Set;

/**
 * The Knight class
 */
public class Knight extends Piece {
    public Knight(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'n';
    }

    @Override
    public Set<Position> getNextPositions(Position origin, BoardView boardView) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
}
