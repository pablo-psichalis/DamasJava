package Board;

import Token.Token;

public final class Square {
    /**
     * Identifies the 32 tiles used in the game, out of 64 tiles of a 8x8 board.
     * {0,1, ... , 31}
     */

    private final int squareIdentifier;

    private Token token;

    public Square(int squareIdentifier, Token token) {
        this.squareIdentifier = squareIdentifier;
        this.token = token;
    }

    public boolean isTileOccupied() { return token != null; }

    public Token getToken() {
        return token;
    }

    public boolean equals(Square s) {
        return this.squareIdentifier == s.squareIdentifier &&
                this.token.equals(s.token);
    }
}
