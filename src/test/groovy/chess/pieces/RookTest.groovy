package chess.pieces

import chess.BoardView
import chess.Player
import chess.Position
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.class)
class RookTest {

    private static final def HORIZONTAL_FROM_TOP_LEFT = ['b8','c8','d8','e8','f8','g8','h8',].collect {
        colRow -> new Position(colRow)
    };
    private static final def VERTICAL_FROM_TOP_LEFT = ['a7','a6','a5','a4','a3','a2','a1',].collect {
        colRow -> new Position(colRow)
    };

    private static final def HORIZONTAL_FROM_BOTTOM_RIGHT = ['a1', 'b1','c1','d1','e1','f1','g1',].collect {
        colRow -> new Position(colRow)
    };

    private static final def VERTICAL_FROM_BOTTOM_RIGHT = ['h8','h7','h6','h5','h4','h3','h2',].collect {
        colRow -> new Position(colRow)
    };

    @Mock
    private BoardView board;

    private Rook rook;
    private Position start;

    @Before
    void setUp() {
        rook = new Rook(Player.White);
        start = new Position("d4");
    }

    @Test
    void testNextPositionsNotNull() {
        assert rook.getNextPositions(start, board) != null;
    }

    @Test
    void testMovesFromTopLeft() {
        Set<Position> positions = rook.getNextPositions(new Position('a' as char, 8), board);
        assert positions.containsAll(HORIZONTAL_FROM_TOP_LEFT + VERTICAL_FROM_TOP_LEFT);
        assert (HORIZONTAL_FROM_TOP_LEFT + VERTICAL_FROM_TOP_LEFT).containsAll(positions);
    }

    @Test
    void testMovesFromBottomRight() {
        Set<Position> positions = rook.getNextPositions(new Position('h' as char, 1), board);
        def expected = HORIZONTAL_FROM_BOTTOM_RIGHT + VERTICAL_FROM_BOTTOM_RIGHT
        assert positions.containsAll(expected);
        assert expected.containsAll(positions);
    }
}