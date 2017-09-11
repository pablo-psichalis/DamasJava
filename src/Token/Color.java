package Token;

public enum Color {
    BLACK {
        @Override
        public int getDirection() {
            return UP_DIRECTION;
        }
        public int getOffsetForwardLeft() { return +7; }
        public int getOffsetForwardRight() { return +1; }
        public int getOffsetBackLeft() { return -7; }
        public int getOffsetBackRight() { return -1; }
    },

    RED {
        @Override
        public int getDirection() {
            return DOWN_DIRECTION;
        }
        public int getOffsetForwardLeft() { return -7; }
        public int getOffsetForwardRight() { return -1; }
        public int getOffsetBackLeft() { return +7; }
        public int getOffsetBackRight() { return +1; }
    };

    public abstract int getDirection();


    /**
     * Add the offset to the identifier of the current
     * square to obtain the identifier of the
     * destination square, in case the token moves
     * forward/back to its left or its right.
     */

    private static final int UP_DIRECTION = 1;
    private static final int DOWN_DIRECTION = -1;

    public abstract int getOffsetForwardLeft();

    public abstract int getOffsetForwardRight();

    public abstract int getOffsetBackLeft();

    public abstract int getOffsetBackRight();
}
