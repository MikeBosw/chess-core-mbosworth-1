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
class PawnTest {

    @Mock
    private BoardView board;

    private Pawn pawn;

    @Before
    void setUp() {
        pawn = new Pawn(Player.White)
        Mockito.when(board.getPieceAt(new Position('b5'))).thenReturn(new Pawn(Player.Black))
    }

    @Test
    void testGameStartMoves() {
        def positions = pawn.getNextPositions(new Position('a2'), board)
        assert positions.collect{p->""+p}.containsAll(["a3","a4"])
        assert ["a3","a4"].containsAll(positions.collect{p->""+p})
    }

    @Test
    void testMidGameMoves() {
        def positions = pawn.getNextPositions(new Position('c3'), board)
        assert positions.collect{p->""+p}.containsAll(["c4"])
        assert ["c4"].containsAll(positions.collect{p->""+p})
    }

    @Test
    void testCaptureMoves() {
        def positions = pawn.getNextPositions(new Position('c4'), board)
        assert positions.collect{p->""+p}.containsAll(["c5","b5"])
        assert ["c5","b5"].containsAll(positions.collect{p->""+p})
    }

    @Test
    void testBlockedMoves() {
        def positions = pawn.getNextPositions(new Position('b4'), board)
        assert positions.empty
    }
}
