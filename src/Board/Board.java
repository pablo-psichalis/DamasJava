package Board;

import Token.*;

import java.util.ArrayList;
import java.util.List;


public class Board {


    // Current token disposition and gamemode: "Spanish Draughts" way

    private List<Square> occupiedByRed;
    private static final int[] INITIAL_RED_POSITIONS_ID =
            {
                    0, 1, 2, 3,
                    4, 5, 6, 7,
                    8, 9, 10, 11
            };

    private List<Square> occupiedByBlack;
    private static final int[] INITIAL_BLACK_POSITIONS_ID =
            {
                    20, 21, 22, 23,
                    24, 25, 26, 27,
                    28, 29, 30, 31
            };

    private List<Square> emptyTiles;
    private static final int[] INITIAL_EMPTY_POSITIONS_ID = {12, 13, 14, 15, 16, 17, 18, 19};

    // TODO: Optimizar rendimiento implementando un Bitboard como estructura de datos para el tablero

    public Board() {
        occupiedByRed = new ArrayList<>();
        occupiedByBlack = new ArrayList<>();
        emptyTiles = new ArrayList<>();

        initializeBoard();
    }

    public void initializeBoard() {
        for (int id : INITIAL_RED_POSITIONS_ID) {
            occupiedByRed.add(new Square(id, new TokenRed(false)));
        }

        for (int id : INITIAL_BLACK_POSITIONS_ID) {
            occupiedByBlack.add(new Square(id, new TokenBlack(false)));
        }

        for (int id : INITIAL_EMPTY_POSITIONS_ID) {
            emptyTiles.add(new Square(id, null));
        }
    }

}
