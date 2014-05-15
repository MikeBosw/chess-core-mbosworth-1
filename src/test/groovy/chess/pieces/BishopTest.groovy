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
class BishopTest {

    private static final Set<Position> DIAGONAL_FROM_TOP_LEFT = ['b7', 'c6', 'd5', 'e4', 'f3', 'g2', 'h1'].collect {
        colRow -> new Position(colRow)
    };
    private static final Set<Position> DIAGONAL_FROM_BOTTOM_RIGHT = ['a8', 'b7', 'c6', 'd5', 'e4', 'f3', 'g2'].collect {
        colRow -> new Position(colRow)
    };

    @Mock
    private BoardView board;

    private Bishop bishop;
    private Position start;

    @Before
    void setUp() {
        bishop = new Bishop(Player.White);
        start = new Position("d4");
    }

    @Test
    void testNextPositionsNotNull() {
        assert bishop.getNextPositions(start, board) != null;
    }

    @Test
    void testDiagonalFromTopLeft() {
        Set<Position> positions = bishop.getNextPositions(new Position('a' as char, 8), board);
        assert positions.containsAll(DIAGONAL_FROM_TOP_LEFT);
        assert DIAGONAL_FROM_TOP_LEFT.containsAll(positions);
    }

    @Test
    void testDiagonalFromBottomRight() {
        Set<Position> positions = bishop.getNextPositions(new Position('h' as char, 1), board);
        assert positions.containsAll(DIAGONAL_FROM_BOTTOM_RIGHT);
        assert DIAGONAL_FROM_BOTTOM_RIGHT.containsAll(positions);
    }

    @Test
    void testDiagonalFromTopRight() {
        //todo
    }

    @Test
    void testObstacles() {
        //todo
    }
}