package chess.pieces;

import chess.BoardView;
import chess.Player;
import chess.Position;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static chess.Position.*;

/**
 * A base class for chess pieces
 */
public abstract class Piece {
    private final Player owner;
    private final boolean isContinuousMover;

    protected static final int COLUMN_OFFSET = 0;
    protected static final int ROW_OFFSET = 1;

    protected Piece(Player owner, boolean isContinuousMover) {
        this.owner = owner;
        this.isContinuousMover = isContinuousMover;
    }

    public char getIdentifier() {
        char id = getIdentifyingCharacter();
        if (owner.equals(Player.White)) {
            return Character.toLowerCase(id);
        } else {
            return Character.toUpperCase(id);
        }
    }

    public Player getOwner() {
        return owner;
    }

    public Set<Position> getNextPositions(Position origin, BoardView boardView) {
        Collection<int[]> moveOffsets = getSimpleMoveOffsets();
        Set<Position> totalPositions = new HashSet<Position>();
        final char column = origin.getColumn();
        final int row = origin.getRow();
        for (int[] offset : moveOffsets) {
            int newColumn = column + offset[COLUMN_OFFSET], newRow = row + offset[ROW_OFFSET];
            while (newColumn >= MIN_COLUMN && newRow >= MIN_ROW && newColumn <= MAX_COLUMN && newRow <= MAX_ROW) {
                Position position = new Position((char) (newColumn), newRow);
                Piece piece = boardView.getPieceAt(position);
                Player player = piece == null ? null : piece.getOwner();
                if (player == getOwner()) {
                    break; //blocking ourselves
                }
                totalPositions.add(position);
                if (player != null) {
                    break; //this is a capture
                }
                if (!isContinuousMover) {
                    break;
                }
                newColumn += offset[0];
                newRow += offset[1];
            }
        }
        Set<Position> specialPositions = getSpecialNextPositions(origin, boardView);
        totalPositions.addAll(specialPositions);
        return totalPositions;
    }

    protected Set<Position> getSpecialNextPositions(Position origin, BoardView boardView) {
        return Collections.emptySet();
    }

    protected abstract char getIdentifyingCharacter();

    /**
     * @return The set of moves this piece could make on an empty board. Moves should be specified in the form of
     * position offsets, where each offset is an int array of length 2, containing the column offset at index
     * {@link #COLUMN_OFFSET}, and the row offset at index {@link #ROW_OFFSET}.
     */
    protected abstract Collection<int[]> getSimpleMoveOffsets();
}
