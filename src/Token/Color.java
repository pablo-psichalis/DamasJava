package Token;

import Board.BoardUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum Color {
    BLACK {

        final Set<Integer> squaresAtBorderForwardLeft =
                new HashSet<>(Arrays.asList(8, 26, 2, 10, 11, 5, 31, 25));
        final Set<Integer> squaresAtBorderForwardRight =
                new HashSet<>(Arrays.asList(1, 9, 17, 25, 31, 5, 11));

        public boolean isSquareAtBorderForwardRight(int square_id) {
            return squaresAtBorderForwardRight.contains(square_id);
        }
        public boolean isSquareAtBorderForwardLeft(int square_id) {
            return squaresAtBorderForwardLeft.contains(square_id);
        }

        public int getOffsetForwardLeft() { return +7; }
        public int getOffsetForwardRight() { return +1; }
        public int getOffsetBackLeft() { return -7; }
        public int getOffsetBackRight() { return -1; }
    },

    RED {

        private final Set<Integer> squaresAtBorderForwardLeft =
                new HashSet<>(Arrays.asList(1, 9, 17, 25, 31, 5, 11));
        private final Set<Integer> squaresAtBorderForwardRight =
                new HashSet<>(Arrays.asList(8, 26, 2, 10, 11, 5, 31, 25));

        public boolean isSquareAtBorderForwardRight(int square_id) {
            return squaresAtBorderForwardRight.contains(square_id);
        }
        public boolean isSquareAtBorderForwardLeft(int square_id) {
            return squaresAtBorderForwardLeft.contains(square_id);
        }

        public int getOffsetForwardLeft() { return -7; }
        public int getOffsetForwardRight() { return -1; }
        public int getOffsetBackLeft() { return +7; }
        public int getOffsetBackRight() { return +1; }
    };

    public abstract boolean isSquareAtBorderForwardLeft(int square_id);
    public abstract boolean isSquareAtBorderForwardRight(int square_id);

    /**
     * Add the offset to the identifier of the current
     * square to obtain the identifier of the
     * destination square, in case the token moves
     * forward/back to its left or its right.
     */
    public abstract int getOffsetForwardLeft();
    public abstract int getOffsetForwardRight();
    public abstract int getOffsetBackLeft();
    public abstract int getOffsetBackRight();

}
