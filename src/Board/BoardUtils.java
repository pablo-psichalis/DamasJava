package Board;

import java.util.*;

public enum BoardUtils {

    INSTANCE;

    private static final int[] INITIAL_RED_POSITIONS_ID =
            {
                    11, 5, 31, 25,
                    10, 4, 30, 24,
                    3, 29, 23, 17
            };

    private static final int[] INITIAL_BLACK_POSITIONS_ID =
            {
                    26, 20, 14, 8,
                    19, 13, 7, 1,
                    18, 12, 6, 0
            };

    private static final int[] INITIAL_EMPTY_POSITIONS_ID =
            {
                    2, 28, 22, 16,
                    27, 21, 15, 9
            };

    private static final Map<String, Integer> squareIdsToBoardNotation;

    static {
        HashMap<String, Integer> tempMap = new HashMap<>();
        tempMap.put("B1", 11);
        tempMap.put("D1", 5);
        tempMap.put("F1", 31);
        tempMap.put("H1", 25);

        tempMap.put("A2", 10);
        tempMap.put("C2", 4);
        tempMap.put("E2", 50);
        tempMap.put("G2", 24);

        tempMap.put("B3", 3);
        tempMap.put("D3", 29);
        tempMap.put("F3", 23);
        tempMap.put("H3", 17);

        tempMap.put("A4", 2);
        tempMap.put("C4", 28);
        tempMap.put("E4", 22);
        tempMap.put("G4", 16);

        tempMap.put("B5", 27);
        tempMap.put("D5", 21);
        tempMap.put("F5", 15);
        tempMap.put("H5", 9);

        tempMap.put("A6", 26);
        tempMap.put("C6", 20);
        tempMap.put("E6", 14);
        tempMap.put("G6", 8);

        tempMap.put("B7", 19);
        tempMap.put("D7", 13);
        tempMap.put("F7", 7);
        tempMap.put("H7", 1);

        tempMap.put("A8", 18);
        tempMap.put("C8", 12);
        tempMap.put("E8", 6);
        tempMap.put("G8", 0);

        squareIdsToBoardNotation = Collections.unmodifiableMap(tempMap);
    }

    private static final Map<Integer, String> boardNotationToSquareIds;

    static {
        HashMap<Integer, String> tempMap = new HashMap<>();
        tempMap.put(11, "B1");
        tempMap.put(5, "D1");
        tempMap.put(31, "F1");
        tempMap.put(25, "H1");

        tempMap.put(10, "A2");
        tempMap.put(4, "C2");
        tempMap.put(50, "E2");
        tempMap.put(24, "G2");

        tempMap.put(3, "B3");
        tempMap.put(29, "D3");
        tempMap.put(23, "F3");
        tempMap.put(17, "H3");

        tempMap.put(2, "A4");
        tempMap.put(28, "C4");
        tempMap.put(22, "E4");
        tempMap.put(16, "G4");

        tempMap.put(27, "B5");
        tempMap.put(21, "D5");
        tempMap.put(15, "F5");
        tempMap.put(9, "H5");

        tempMap.put(26, "A6");
        tempMap.put(20, "C6");
        tempMap.put(14, "E6");
        tempMap.put(8, "G6");

        tempMap.put(19, "B7");
        tempMap.put(13, "D7");
        tempMap.put(7, "F7");
        tempMap.put(1, "H7");

        tempMap.put(18, "A8");
        tempMap.put(12, "C8");
        tempMap.put(6, "E8");
        tempMap.put(0, "G8");

        boardNotationToSquareIds = Collections.unmodifiableMap(tempMap);
    }


    public static int[] getInitialRedPositionsId() { return INITIAL_RED_POSITIONS_ID; }

    public static int[] getInitialBlackPositionsId() { return INITIAL_BLACK_POSITIONS_ID; }

    public static int[] getInitialEmptyPositionsId() { return INITIAL_EMPTY_POSITIONS_ID; }

    /**
     * Translates square coordinates from the format [Column][Row] (e.g. B1, D3)
     * to the corresponding square identifier (0,1, ... ,31).
     * Returns null if the input coordinates are invalid.
     */
    public static Integer translateToSquareId(String coordinates) {
        return squareIdsToBoardNotation.getOrDefault(coordinates, null);
    }

    public static String translateToBoardNotation(int squareId) {
        return boardNotationToSquareIds.getOrDefault(squareId, null);
    }
}
