package chess.pieces;

import chess.Player;
import com.google.common.collect.ImmutableSet;

import java.util.Collection;
import java.util.Set;

/**
 * The 'Bishop' class
 */
public class Bishop extends Piece {

    private static final Set<int[]> MOVE_OFFSETS = ImmutableSet.copyOf(new int[][]{
            {-1, -1}, {1, 1}, {1, -1}, {-1, 1}
    });

    public Bishop(Player owner) {
        super(owner, true);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'b';
    }

    @Override
    protected Collection<int[]> getSimpleMoveOffsets() {
        return MOVE_OFFSETS;
    }
}
