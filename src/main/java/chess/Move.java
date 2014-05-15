/**
 *
 * @author Michael Anselm Bosworth
 * Due to a perverse and unpleasing character, Michael has not written a comment.
 */
package chess;

import static com.google.common.base.Preconditions.checkNotNull;

public class Move implements Comparable<Move> {
    private final Position start, end;

    public Move(Position start, Position end) {
        checkNotNull(start, "start");
        checkNotNull(end, "end");
        this.start = start;
        this.end = end;
    }

    public Move(String start, String end) {
        this.start = new Position(start);
        this.end = new Position(end);
    }

    @Override
    public String toString() {
        return "{" + start + " -> " + end + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        return end.equals(move.end) && start.equals(move.start);
    }

    @Override
    public int hashCode() {
        int result = start.hashCode();
        result = 31 * result + end.hashCode();
        return result;
    }

    @Override
    public int compareTo(Move o) {
        return (""+this).compareTo(""+o);
    }
}
