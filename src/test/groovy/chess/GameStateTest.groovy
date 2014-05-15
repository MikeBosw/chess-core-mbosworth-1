package chess;

import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Basic unit tests for the GameState class
 */
public class GameStateTest {

    private GameState state;
    private GameState checkState;
    private GameState checkMateState;

    @Before
    public void setUp() {
        state = new GameState();

        checkMateState = new GameState();
        checkMateState.reset()
        checkMateState.placePiece(new Queen(Player.Black), new Position("d5"))
        checkMateState.placePiece(new Queen(Player.Black), new Position("f5"))
        def king = checkMateState.positionToPieceMap.remove(new Position("e1"))
        checkMateState.placePiece(king, new Position("e5"))

        checkState = new GameState();
        checkState.reset()
        checkState.placePiece(new Queen(Player.Black), new Position("d5"))
        king = checkState.positionToPieceMap.remove(new Position("e1"))
        checkState.placePiece(king, new Position("e5"))
    }

    @Test
    public void testStartsEmpty() {
        // Make sure all the positions are empty
        for (char col = Position.MIN_COLUMN; col <= Position.MAX_COLUMN; col++) {
            for (int row = Position.MIN_ROW; row <= Position.MAX_ROW; row++) {
                assertNull("All pieces should be empty", state.getPieceAt(String.valueOf(col) + row));
            }
        }
    }

    @Test
    public void testInitialGame() {
        // Start the game
        state.reset();

        // White should be the first player
        Player current = state.getCurrentPlayer();
        assertEquals("The initial player should be White", Player.White, current);

        // Spot check a few pieces
        Piece whiteRook = state.getPieceAt("a1");
        assertTrue("A rook should be at a1", whiteRook instanceof Rook);
        assertEquals("The rook at a1 should be owned by White", Player.White, whiteRook.getOwner());


        Piece blackQueen = state.getPieceAt("d8");
        assertTrue("A queen should be at d8", blackQueen instanceof Queen);
        assertEquals("The queen at d8 should be owned by Black", Player.Black, blackQueen.getOwner());
    }

    @Test
    public void testCheckmateCheck() {
        def moves = checkMateState.findPossibleMoves(Player.White)
        assert moves.empty
        //TODO
    }

    @Test
    public void testCheckCheck() {
        def moves = checkState.findPossibleMoves(Player.White)
        def expected = ["d5", "f4"]
        assert moves.collect{move->""+move.end}.containsAll(expected)
        assert expected.containsAll(moves.collect{move->""+move.end})
        //TODO
    }
}
