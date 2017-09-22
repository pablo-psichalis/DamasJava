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

    public static final int MAX_SQUARE_ID = 32;
    public static final int NUM_ROWS = 8;
    public static final int NUM_COLUMNS = 8;

    private Map<Integer, Square> occupiedByRed;
    private Map<Integer, Square> occupiedByBlack;
    private Map<Integer, Square> emptySquares;


    // TODO: Optimizar rendimiento implementando un Bitboard como estructura de datos para el tablero

    public Board() {
        occupiedByRed = new HashMap<Integer, Square>();
        occupiedByBlack = new HashMap<Integer, Square>();
        emptySquares = new HashMap<Integer, Square>();

        initializeBoard(
                BoardUtils.getInitialRedPositionsId(),
                BoardUtils.getInitialBlackPositionsId(),
                BoardUtils.getInitialEmptyPositionsId()
        );
    }

    public Board(int[] initialRedPositionsID, int[] initialBlackPositionsID, int[] initialEmptyPositionsID) {
        occupiedByRed = new HashMap<Integer, Square>();
        occupiedByBlack = new HashMap<Integer, Square>();
        emptySquares = new HashMap<Integer, Square>();

        initializeBoard(initialRedPositionsID, initialBlackPositionsID, initialEmptyPositionsID);
    }

    private void initializeBoard(final int[] INITIAL_RED_POSITIONS_ID,
                                 final int[] INITIAL_BLACK_POSITIONS_ID,
                                 final int[] INITIAL_EMPTY_POSITIONS_ID)
    {
        for (int position_id : INITIAL_RED_POSITIONS_ID) {
            occupiedByRed.put(position_id, new Square(position_id, new TokenRed(false, position_id)));
        }

        for (int position_id : INITIAL_BLACK_POSITIONS_ID) {
            occupiedByBlack.put(position_id, new Square(position_id, new TokenBlack(false, position_id)));
        }

        for (int position_id : INITIAL_EMPTY_POSITIONS_ID) {
            emptySquares.put(position_id, new Square(position_id, null));
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



}
