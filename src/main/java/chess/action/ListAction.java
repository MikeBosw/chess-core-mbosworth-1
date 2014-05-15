/**
 *
 * @author Michael Anselm Bosworth
 * Due to a perverse and unpleasing character, Michael has not written a comment.
 */
package chess.action;

import chess.GameState;
import chess.Move;
import chess.Player;
import chess.UserFeedback;

import java.util.Set;

public class ListAction implements Action {
    private final GameState gameState;
    private final UserFeedback feedback;

    public ListAction(GameState gameState, UserFeedback feedback) {
        this.gameState = gameState;
        this.feedback = feedback;
    }

    @Override
    public void execute(String input) {
        Player player = gameState.getCurrentPlayer();
        Set<Move> moves = gameState.findPossibleMoves(player);
        for (Move move : moves) {
            feedback.writeOutput("" + move);
        }
    }
}
