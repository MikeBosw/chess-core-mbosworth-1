package chess;

import chess.pieces.Piece;

public interface BoardView {
    public Piece getPieceAt(Position position);
}
