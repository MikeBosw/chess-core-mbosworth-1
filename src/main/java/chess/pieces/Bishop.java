package chess.pieces;

import chess.BoardView;
import chess.Player;
import chess.Position;

import java.util.HashSet;
import java.util.Set;

import static chess.Position.*;

/**
 * The 'Bishop' class
 */
public class Bishop extends Piece {
    private static final int[][] DIRECTION_OFFSETS = {{-1, -1}, {1, 1}, {1, -1}, {-1, 1}};

    public Bishop(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'b';
    }

    @Override
    public Set<Position> getNextPositions(Position origin, BoardView boardView) {
        Set<Position> positions = new HashSet<>();
        final char column = origin.getColumn();
        final int row = origin.getRow();
        for (int[] offset : DIRECTION_OFFSETS) {
            int newColumn = column + offset[0], newRow = row + offset[1];
            while (newColumn >= MIN_COLUMN && newRow >= MIN_ROW && newColumn <= MAX_COLUMN && newRow <= MAX_ROW) {
                Position position = new Position((char) (newColumn), newRow);
                Piece piece = boardView.getPieceAt(position);
                Player player = piece == null ? null : piece.getOwner();
                if (player == getOwner()) {
                    break; //blocking ourselves
                }
                positions.add(position);
                if (player != null) {
                    break; //this is a capture
                }
                newColumn += offset[0];
                newRow += offset[1];
            }
        }
        return positions;
    }
}
