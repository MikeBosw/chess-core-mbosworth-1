package chess.pieces;

import chess.Player;
import com.google.common.collect.ImmutableSet;

import java.util.Collection;
import java.util.Set;

/**
 * The 'Rook' class
 */
public class Rook extends Piece {

    private static final Set<int[]> MOVE_OFFSETS = ImmutableSet.copyOf(new int[][]{
            {1, 0}, {0, 1}, {-1, 0}, {0, -1},
    });

    public Rook(Player owner) {
        super(owner, true);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'r';
    }

    @Override
    protected Collection<int[]> getSimpleMoveOffsets() {
        return MOVE_OFFSETS;
    }
}
