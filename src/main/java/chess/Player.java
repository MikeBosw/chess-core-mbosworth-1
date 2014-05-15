package chess;

/**
 * Which side of the board is being played
 */
public enum Player {
    White, Black;

    /**
     * @return The opposite player.
     */
    public Player opposite() {
        return this == White ? Black : White;
    }

}
