package chess;


import chess.pieces.*;
import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class that represents the current state of the game.  Basically, what pieces are in which positions on the
 * board.
 */
public class GameState implements BoardView {

    public static enum MoveViolation {
        NONE(""),
        DESTINATION_OCCUPIED("The destination is occupied."),
        WRONG_PLAYER("The piece at this location is not owned by the current player."),
        CHECK("This move would endanger your king."),
        NO_SUCH_PIECE("No piece exists at that location."),
        NO_PATH("The piece at this location cannot make such a move."),;

        private final String reason;

        MoveViolation(String reason) {
            this.reason = reason;
        }
    }

    /**
     * The current player
     */
    private Player currentPlayer = Player.White;

    /**
     * A map of board positions to pieces at that position
     */
    private Map<Position, Piece> positionToPieceMap;

    /**
     * Create the game state.
     */
    public GameState() {
        positionToPieceMap = new HashMap<>();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Call to initialize the game state into the starting positions
     */
    public void reset() {
        // White Pieces
        placePiece(new Rook(Player.White), new Position("a1"));
        placePiece(new Knight(Player.White), new Position("b1"));
        placePiece(new Bishop(Player.White), new Position("c1"));
        placePiece(new Queen(Player.White), new Position("d1"));
        placePiece(new King(Player.White), new Position("e1"));
        placePiece(new Bishop(Player.White), new Position("f1"));
        placePiece(new Knight(Player.White), new Position("g1"));
        placePiece(new Rook(Player.White), new Position("h1"));
        placePiece(new Pawn(Player.White), new Position("a2"));
        placePiece(new Pawn(Player.White), new Position("b2"));
        placePiece(new Pawn(Player.White), new Position("c2"));
        placePiece(new Pawn(Player.White), new Position("d2"));
        placePiece(new Pawn(Player.White), new Position("e2"));
        placePiece(new Pawn(Player.White), new Position("f2"));
        placePiece(new Pawn(Player.White), new Position("g2"));
        placePiece(new Pawn(Player.White), new Position("h2"));

        // Black Pieces
        placePiece(new Rook(Player.Black), new Position("a8"));
        placePiece(new Knight(Player.Black), new Position("b8"));
        placePiece(new Bishop(Player.Black), new Position("c8"));
        placePiece(new Queen(Player.Black), new Position("d8"));
        placePiece(new King(Player.Black), new Position("e8"));
        placePiece(new Bishop(Player.Black), new Position("f8"));
        placePiece(new Knight(Player.Black), new Position("g8"));
        placePiece(new Rook(Player.Black), new Position("h8"));
        placePiece(new Pawn(Player.Black), new Position("a7"));
        placePiece(new Pawn(Player.Black), new Position("b7"));
        placePiece(new Pawn(Player.Black), new Position("c7"));
        placePiece(new Pawn(Player.Black), new Position("d7"));
        placePiece(new Pawn(Player.Black), new Position("e7"));
        placePiece(new Pawn(Player.Black), new Position("f7"));
        placePiece(new Pawn(Player.Black), new Position("g7"));
        placePiece(new Pawn(Player.Black), new Position("h7"));
    }

    /**
     * Get the piece at the position specified by the String
     * @param colrow The string indication of position; i.e. "d5"
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(String colrow) {
        Position position = new Position(colrow);
        return getPieceAt(position);
    }

    /**
     * Get the piece at a given position on the board
     * @param position The position to inquire about.
     * @return The piece at that position, or null if it does not exist.
     */
    @Override
    public Piece getPieceAt(Position position) {
        return positionToPieceMap.get(position);
    }

    /**
     * Method to place a piece at a given position
     * @param piece The piece to place
     * @param position The position
     */
    private void placePiece(Piece piece, Position position) {
        positionToPieceMap.put(position, piece);
    }

    /**
     * Method to move a piece from one position to another.
     * @param piece The piece to place
     * @param begin The start position
     * @param end The end position
     * @return The piece that had been occupying the end position.
     */
    private Piece movePiece(Piece piece, Position begin, Position end) {
        Piece removed = positionToPieceMap.remove(begin);
        if (removed == null) {
            throw new IllegalStateException("Piece not found at " + begin + "!");
        }
        if (!piece.equals(removed)) {
            throw new IllegalArgumentException("Piece to be moved does not reside at " + begin + "!");
        }
        return positionToPieceMap.put(end, piece);
    }

    public Set<Move> findPossibleMoves() {
        Player player = getCurrentPlayer();
        Set<Move> allMoves = Sets.newTreeSet();
        for (char c = Position.MIN_COLUMN; c <= Position.MAX_COLUMN; c++) {
            for (int i = Position.MIN_ROW; i <= Position.MAX_ROW; i++) {
                Position position = new Position(c, i);
                Piece piece = getPieceAt(position);
                if (piece == null) {
                    continue;
                }
                if (piece.getOwner() != player) {
                    continue;
                }
                Set<Position> nextPositions = piece.getNextPositions(position, this);
                for (Position nextPosition : nextPositions) {
                    //TODO: validate the move. Would the player be putting his or her king in check?
                    Move move = new Move(position, nextPosition);
                    MoveViolation violation = verifyMove(move);
                    if (violation == MoveViolation.NONE) {
                        allMoves.add(move);
                    }
                }
            }
        }
        return allMoves;
    }

    /**
     * Makes the given move. If the move is unsafe, an exception is thrown. Callers should use {@link #verifyMove}
     * beforehand to ensure the move is safe, or {@link #findPossibleMoves()}.
     *
     * @param move The move to make.
     */
    public void move(Move move) {
        MoveViolation violation = verifyMove(move);
        if (violation != MoveViolation.NONE) {
            throw new IllegalMoveException(move + ": " + violation.reason); //programming error, not user error.
        }
        Position start = move.getStart();
        Piece piece = getPieceAt(start);
        Position end = move.getEnd();
        movePiece(piece, start, end);
        currentPlayer = currentPlayer.opposite();
    }

    /**
     * Check whether the given move is valid.
     *
     * @param move The move to make.
     * @return The violation that would be caused by this move, or {@link MoveViolation#NONE} if none.
     */
    public MoveViolation verifyMove(Move move) {
        Position start = move.getStart();
        Position end = move.getEnd();
        Piece piece = getPieceAt(start);
        if (piece == null) {
            return MoveViolation.NO_SUCH_PIECE;
        }
        /* The NO_PATH check here is redundant, as the program is currently implemented - squaring the time complexity
         * of the findPossibleMoves method. This *might* be a (pointless) performance bottleneck in some products. I
         * submit that we don't care about the milliseconds lost here because our product is human-to-computer, so,
         * let's do it, in keeping with the principle of least surprise. */
        Set<Position> nextPositions = piece.getNextPositions(start, this);
        if (!nextPositions.contains(end)) {
            return MoveViolation.NO_PATH;
        }
        if (piece.getOwner() != getCurrentPlayer()) {
            return MoveViolation.WRONG_PLAYER;
        }
        Piece captured = movePiece(piece, start, end);
        try {
            boolean isCastleMove = isCastleMove(move);
            if (captured != null && !isCastleMove && captured.getOwner() == piece.getOwner()) {
                return MoveViolation.DESTINATION_OCCUPIED;
            }
            if (isKingEndangered()) {
                return MoveViolation.CHECK;
            }
            return MoveViolation.NONE;
        } finally {
            //we moved the piece. revert!
            movePiece(piece, end, start);
            if (captured != null) {
                placePiece(captured, end);
            }
        }
    }

    private boolean isKingEndangered() {
        //TODO: implement this
        return false;
    }

    private boolean isCastleMove(Move move) {
        //TODO: implement this
        return false;
    }

    public static class IllegalMoveException extends RuntimeException {
        public IllegalMoveException(String move) {
            super("Illegal move: " + move);
        }
    }
}
