package Board;

import Token.*;

import java.util.HashMap;
import java.util.Map;


public class Board {

    // Current token disposition and gamemode: "Spanish Draughts" way

    /*
       Board identifier distribution:
        ------------------
        |  11  05  31  25|
        |10  04  30  24  |
        |  03  29  23  17|
        |02  28  22  16  |
        |  27  21  15  09|
        |26  20  14  08  |
        |  19  13  07  01|
        |18  12  06  00  |
        ------------------
        First square is numbered after "11".
     */

    private Map<Integer, Square> occupiedByRed;
    private static final int[] INITIAL_RED_POSITIONS_ID =
            {
                    11, 5, 31, 25,
                    10, 4, 30, 24,
                    3, 29, 23, 17
            };

    private Map<Integer, Square> occupiedByBlack;
    private static final int[] INITIAL_BLACK_POSITIONS_ID =
            {
                    26, 20, 14, 8,
                    19, 13, 7, 1,
                    18, 12, 6, 0
            };

    private Map<Integer, Square> emptySquares;
    private static final int[] INITIAL_EMPTY_POSITIONS_ID = {12, 13, 14, 15, 16, 17, 18, 19};

    // TODO: Optimizar rendimiento implementando un Bitboard como estructura de datos para el tablero

    public Board() {
        occupiedByRed = new HashMap<Integer, Square>();
        occupiedByBlack = new HashMap<Integer, Square>();
        emptySquares = new HashMap<Integer, Square>();

        initializeBoard();
    }

    public void initializeBoard() {
        for (int id : INITIAL_RED_POSITIONS_ID) {
            occupiedByRed.put(INITIAL_RED_POSITIONS_ID[id], new Square(id, new TokenRed(false, id)));
        }

        for (int id : INITIAL_BLACK_POSITIONS_ID) {
            occupiedByBlack.put(INITIAL_BLACK_POSITIONS_ID[id], new Square(id, new TokenBlack(false, id)));
        }

        for (int id : INITIAL_EMPTY_POSITIONS_ID) {
            emptySquares.put(INITIAL_EMPTY_POSITIONS_ID[id], new Square(id, null));
        }
    }

    public boolean isOccupied(int squareId) {
        return (!emptySquares.containsKey(squareId));
    }

    public boolean isOccupiedByBlack(int squareId) {
        return (occupiedByBlack.containsKey(squareId));
    }

    public boolean isOccupiedByRed(int squareId) {
        return (occupiedByRed.containsKey(squareId));
    }

    public Map<Integer, Square> getOccupiedByRed() { return occupiedByRed; }

    public Map<Integer, Square> getOccupiedByBlack() { return occupiedByBlack; }

    public Map<Integer, Square> getEmptySquares() { return emptySquares; }
}
