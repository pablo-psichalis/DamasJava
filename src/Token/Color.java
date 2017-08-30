package Token;

public enum Color {
    BLACK {
        @Override
        public int getDirection() {
            return UP_DIRECTION;
        }

        private int OffsetForwardLeft = +7;
        private int OffsetForwardRight = +1;
        private int OffsetBackLeft = -7;
        private int OffsetBackRight = -1;

    },

    RED {
        @Override
        public int getDirection() {
            return DOWN_DIRECTION;
        }

        private int OffsetForwardLeft = -7;
        private int OffsetForwardRight = -1;
        private int OffsetBackLeft = +7;
        private int OffsetBackRight = +1;
    };

    public abstract int getDirection();


    /**
     * Add the offset to the identifier of the current
     * square to obtain the identifier of the
     * destination square, in case the token moves
     * forward/back to its left or its right.
     */
    private int OffsetForwardLeft;
    private int OffsetForwardRight;
    private int OffsetBackLeft;
    private int OffsetBackRight;

    private static final int UP_DIRECTION = 1;
    private static final int DOWN_DIRECTION = -1;

    public int getOffsetForwardLeft() { return OffsetForwardLeft; }
    public int getOffsetForwardRight() { return OffsetForwardRight; }
    public int getOffsetBackLeft() { return OffsetBackLeft; }
    public int getOffsetBackRight() { return OffsetBackRight; }
}
