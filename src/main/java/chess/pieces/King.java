package chess.pieces;

import chess.Player;
import com.google.common.collect.ImmutableSet;

import java.util.Collection;
import java.util.Set;

/**
 * The King class
 */
public class King extends Piece {

    private static final Set<int[]> MOVE_OFFSETS = ImmutableSet.copyOf(new int[][]{
            {1, 1}, {-1, -1}, {1, -1}, {-1, 1},
            {1, 0}, {0, 1}, {-1, 0}, {0, -1},
    });

    public King(Player owner) {
        super(owner, false);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'k';
    }

    @Override
    protected Collection<int[]> getSimpleMoveOffsets() {
        return MOVE_OFFSETS;
    }
}
