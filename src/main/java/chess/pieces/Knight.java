package chess.pieces;

import chess.Player;
import com.google.common.collect.ImmutableSet;

import java.util.Collection;
import java.util.Set;

/**
 * The Knight class
 */
public class Knight extends Piece {

    private static final Set<int[]> MOVE_OFFSETS = ImmutableSet.copyOf(new int[][]{
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2},
    });

    public Knight(Player owner) {
        super(owner, false);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'n';
    }

    @Override
    protected Collection<int[]> getSimpleMoveOffsets() {
        return MOVE_OFFSETS;
    }
}
