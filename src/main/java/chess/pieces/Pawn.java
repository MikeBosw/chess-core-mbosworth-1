package chess.pieces;

import chess.BoardView;
import chess.Player;
import chess.Position;
import com.google.common.collect.ImmutableSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static chess.Position.*;

/**
 * The Pawn
 */
public class Pawn extends Piece {

    private static final int[] MOVE_OFFSET_WHITE = {0, 1}, MOVE_OFFSET_BLACK = {0, -1};

    private static final Set<int[]> CAPTURE_OFFSETS_WHITE = ImmutableSet.copyOf(new int[][]{
            {1, 1}, {-1, 1}
    });

    private static final Set<int[]> CAPTURE_OFFSETS_BLACK = ImmutableSet.copyOf(new int[][]{
            {1, -1}, {-1, -1}
    });

    public Pawn(Player owner) {
        super(owner, false);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'p';
    }

    @Override
    public Set<Position> getNextPositions(Position origin, BoardView boardView) {
        Set<Position> positions = new HashSet<Position>();
        findNextCapturePositions(origin, boardView, positions);
        findNextPeacefulPositions(origin, boardView, positions);
        return positions;
    }

    private void findNextPeacefulPositions(Position origin, BoardView boardView, Set<Position> positions) {
        final char column = origin.getColumn();
        final int row = origin.getRow();
        int maxDistance;
        if (row == (getOwner() == Player.White ? 2 : 7)) {
            maxDistance = 2;
        } else {
            maxDistance = 1;
        }
        int[] offset = getOwner() == Player.White ? MOVE_OFFSET_WHITE : MOVE_OFFSET_BLACK;
        int newColumn = column + offset[COLUMN_OFFSET], newRow = row + offset[ROW_OFFSET];
        for (int i = 0; i < maxDistance; i++) {
            if (newColumn < MIN_COLUMN || newRow < MIN_ROW || newColumn > MAX_COLUMN || newRow > MAX_ROW) {
                break;
            }
            Position position = new Position((char) (newColumn), newRow);
            Piece piece = boardView.getPieceAt(position);
            Player player = piece == null ? null : piece.getOwner();
            if (player != null) {
                break;
            }
            positions.add(position);
            newColumn += offset[0];
            newRow += offset[1];
        }
    }

    private void findNextCapturePositions(Position origin, BoardView boardView, Set<Position> positions) {
        Set<int[]> captureOffsets = getOwner() == Player.White ? CAPTURE_OFFSETS_WHITE : CAPTURE_OFFSETS_BLACK;
        final char column = origin.getColumn();
        final int row = origin.getRow();
        for (int[] offset : captureOffsets) {
            int newColumn = column + offset[0], newRow = row + offset[1];
            if (newColumn >= MIN_COLUMN && newRow >= MIN_ROW && newColumn <= MAX_COLUMN && newRow <= MAX_ROW) {
                Position position = new Position((char) (newColumn), newRow);
                Piece piece = boardView.getPieceAt(position);
                Player player = piece == null ? null : piece.getOwner();
                if (player != null && player != getOwner()) {
                    positions.add(position); //this is a capture
                }
            }
        }
    }

    @Override
    protected Collection<int[]> getSimpleMoveOffsets() {
        throw new UnsupportedOperationException("Not implemented for pawns.");
    }
}
