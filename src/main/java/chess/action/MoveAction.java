/**
 *
 * @author Michael Anselm Bosworth
 * Due to a perverse and unpleasing character, Michael has not written a comment.
 */
package chess.action;

import chess.GameState;
import chess.Move;
import chess.Position;
import chess.UserFeedback;
import chess.pieces.Piece;

import java.util.Set;

public class MoveAction implements Action {

    private final GameState gameState;
    private final UserFeedback feedback;

    public MoveAction(GameState gameState, UserFeedback feedback) {
        this.gameState = gameState;
        this.feedback = feedback;
    }

    private Move parseMove(String input) {
        int argsIndex = input.indexOf("move ");
        if (argsIndex < 0) {
            return null;
        }
        String argString = input.substring(argsIndex + "move ".length());
        String[] args = argString.split(" ");
        if (args.length != 2) {
            return null;
        }
        return new Move(args[0], args[1]);
    }

    private void move(Move move) {
        Position start = move.getStart();
        Piece piece = gameState.getPieceAt(start);
        if (piece == null || piece.getOwner() != gameState.getCurrentPlayer()) {
            feedback.writeOutput("You don't own a piece at " + start + ".");
            return;
        }
        Set<Move> possibleMoves = gameState.findPossibleMoves(piece, start);
        if (!possibleMoves.contains(move)) {
            feedback.writeOutput("Illegal move: " + move);
        } else {
            gameState.move(move);
            feedback.writeOutput("Moved: " + move);
        }
    }

    public void execute(String input) {
        Move move = parseMove(input);
        if (move == null) {
            feedback.writeOutput("Incorrect syntax. Type 'help' for a list of commands.");
        } else {
            move(move);
        }
    }
}
