package chess.pieces

import chess.BoardView
import chess.Player
import chess.Position
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.class)
class KnightTest {

    @Mock
    private BoardView board;

    private Knight knight;

    @Before
    void setUp() {
        knight = new Knight(Player.White)
        Mockito.when(board.getPieceAt(new Position('b5'))).thenReturn(new Pawn(Player.Black))
        Mockito.when(board.getPieceAt(new Position('f5'))).thenReturn(new Pawn(Player.White))
    }

    @Test
    void testUnblockedMoves() {
        def positions = knight.getNextPositions(new Position('d3'), board)
        assert positions.collect{p->""+p}.containsAll(['b2', 'f2', 'b4', 'f4', 'c5', 'e5', 'c1', 'e1'])
        assert ['b2', 'f2', 'b4', 'f4', 'c5', 'e5', 'c1', 'e1'].containsAll(positions.collect{p->""+p})
    }

    @Test
    void testCaptureMoves() {
        def positions = knight.getNextPositions(new Position('c3'), board)
        def expected = ['a2', 'e2', 'a4', 'e4', 'b5', 'd5', 'b1', 'd1']
        assert positions.collect{p->""+p}.containsAll(expected)
        assert expected.containsAll(positions.collect{p->""+p})
    }

    @Test
    void testBlockedMove() {
        def positions = knight.getNextPositions(new Position('e3'), board)
        def expected = ['c2', 'g2', 'c4', 'g4', 'd5', /*'f5',*/ 'd1', 'f1']
        assert positions.collect{p->""+p}.containsAll(expected)
        assert expected.containsAll(positions.collect{p->""+p})
    }

}
